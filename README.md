# Email Scheduler

The **Email Scheduler** is a simple yet powerful tool that allows users to schedule emails with custom timing, recipient details, and attachments. It uses cron-style scheduling to specify when an email should be sent and integrates with JavaMail for reliable email delivery.

## Features

- **Cron-style Scheduling**: Schedule emails with flexible timing using cron expressions (e.g., every hour, specific days of the week, etc.).
- **Recipient Customization**: Allow users to define email recipients, CCs, and BCCs.
- **Text & HTML Body Support**: Users can specify both plain text and HTML content for the email body.
- **Attachment Support**: Add image and PDF attachments to emails.
- **Error Handling**: Provides feedback in case of missing fields or invalid inputs.
- **Multiple Scheduling**: Users can schedule multiple emails one after the other without restarting the application.
- **Customizable Email Template**: Use FreeMarker for email template rendering (optional).
- **User-Friendly Interface**: The terminal-based interface guides users through each step of scheduling their email.

## Requirements

- Java 17 or higher
- Spring Boot 3.3.x
- JavaMail API for email handling
- FreeMarker for email template rendering (optional)
- Maven for dependency management

## Setup Instructions

### 1. Clone the Repository

Clone the repository to your local machine.

```bash
git clone <repository-url>
cd EmailScheduler
```

### 2. Install Dependencies

Ensure Maven is installed and use it to fetch dependencies.

```bash
mvn clean install
```

### 3. Run the Application

Execute the following command to start the email scheduler application.

```bash
mvn spring-boot:run
```

### 4. Input Details

Once the application is running, follow the prompts in the terminal to schedule your emails:

- **Cron Expression**: Input the cron schedule (e.g., `0 0/1 * * * ?` for every minute).
- **Recipient Email**: Provide the recipient's email address.
- **Subject and Body**: Enter the subject and body text of the email.
- **Attachments**: You can specify paths for image and PDF attachments if required.
  
The application will then schedule the email to be sent at the specified time.

## Example Cron Expressions

- `0 0/1 * * * ?` - Every minute.
- `0 0 20 1 * MON` - 8 PM on the first Monday of every month.
- `0 0 0 1 * ?` - At midnight on the first day of every month.

## Screenshots

