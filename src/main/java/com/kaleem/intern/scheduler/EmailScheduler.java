package com.kaleem.intern.scheduler;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import com.kaleem.intern.service.EmailService;
import org.springframework.scheduling.TaskScheduler;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

@Component
@EnableScheduling
public class EmailScheduler {
    
    @Autowired
    private EmailService emailService;
    private String cronTiming;
    private String toEmail;
    private String subject;
    private String text;
    private String[] cc;
    private String[] bcc;
    private String image;
    private String pdf;
    private TaskScheduler taskScheduler;

    public EmailScheduler(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    public void scheduleEmail(String cronTiming, String toEmail, String subject, String text, String[] cc, String[] bcc, String image, String pdf) {
        this.cronTiming = cronTiming;
        this.toEmail = toEmail;
        this.subject = subject;
        this.text = text;
        this.cc = cc;
        this.bcc = bcc;
        this.image = image;
        this.pdf = pdf;

        scheduleTask();
    }

    private void scheduleTask() {
        Runnable emailTask = new Runnable() {
            @Override
            public void run() {
                try {
                    runScheduledEmail();
                } catch (MessagingException | IOException | TemplateException e) {
                    e.printStackTrace();
                }
            }
        };
        taskScheduler.schedule(emailTask, new org.springframework.scheduling.support.CronTrigger(cronTiming));
    }

    private void runScheduledEmail() throws MessagingException, IOException, TemplateException {
        System.out.println("\nScheduled Email Sent:");
        System.out.println("To: " + toEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Text: " + text);

        emailService.sendEmail(toEmail, subject, text, image, pdf);
    }

    public void runScheduledEmailMul() throws MessagingException {
        System.out.println("Scheduled Email sent to multiple recipients:");
        emailService.sendEmailMul(new String[]{toEmail}, subject, text, cc, bcc);
    }
}
