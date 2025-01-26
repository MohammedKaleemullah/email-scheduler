package com.kaleem.intern;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.kaleem.intern.scheduler.EmailScheduler;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

@SpringBootApplication
public class EmailSchedulerApplication {

    private static boolean isValidCron(String cron) {
        return cron.matches("\\d{1,2} \\d{1,2}/\\d{1,2} \\* \\* \\* \\?");
    }
    public static void main(String[] args) throws MessagingException, IOException, TemplateException {
        Scanner sc = new Scanner(System.in);

        

        boolean waiter = true;
        
        while (waiter) {
            try {
				ConfigurableApplicationContext context = SpringApplication.run(EmailSchedulerApplication.class, args);
                System.out.println("Welcome to the Email Scheduler Application!");
                System.out.print("Some rules apply:\r\n" + //
                                        "A field may be an asterisk (*), which always stands for “first-last”. For the day-of-the-month or day-of-the-week fields, a question mark (?) may be used instead of an asterisk.\r\n" + //
                                        "Commas (,) are used to separate items of a list.\r\n" + //
                                        "Two numbers separated with a hyphen (-) express a range of numbers. The specified range is inclusive.\r\n" + //
                                        "Following a range (or *) with / specifies the interval of the number’s value through the range.\r\n" + //
                                        "English names can also be used for the day-of-month and day-of-week fields. Use the first three letters of the particular day or month (case does not matter).");

                System.out.print("Enter the schedule timing (cron format, e.g., 0 0/1 * * * ?): ");
                String cronTiming = sc.nextLine();

                isValidCron(cronTiming);
                System.out.print("Enter the recipient email (To): ");
                String toEmail = sc.nextLine();

                System.out.print("Enter the subject of the email: ");
                String subject = sc.nextLine();

                System.out.print("Enter the body text of the email: ");
                String text = sc.nextLine();

                System.out.print("Enter the CC email (comma separated, or leave blank): ");
                String ccInput = sc.nextLine();
                String[] cc = ccInput.isEmpty() ? new String[]{} : ccInput.split(",");

                System.out.print("Enter the BCC email (comma separated, or leave blank): ");
                String bccInput = sc.nextLine();
                String[] bcc = bccInput.isEmpty() ? new String[]{} : bccInput.split(",");

                System.out.print("Enter the path to image attachment (or leave blank): ");
                String image = sc.nextLine();

                System.out.print("Enter the path to PDF attachment (or leave blank): ");
                String pdf = sc.nextLine();

                EmailScheduler job = context.getBean(EmailScheduler.class);

                if (image.isEmpty())
                    image="C:\\Users\\DELL\\Desktop\\Email Schedule\\EmailScheduler\\src\\main\\java\\com\\kaleem\\intern\\scheduler\\download.jpeg";
                if (pdf.isEmpty())
					pdf="C:\\Users\\DELL\\Desktop\\Email Schedule\\EmailScheduler\\src\\main\\java\\com\\kaleem\\intern\\scheduler\\sample.pdf";

                job.scheduleEmail(cronTiming, toEmail, subject, text, cc, bcc, image, pdf);

                System.out.println("Email scheduled successfully!");
                System.out.print("Please Wait until the mail is sent");
                sc.nextLine();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sc.close();
    }
}
