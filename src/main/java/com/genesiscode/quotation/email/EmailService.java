package com.genesiscode.quotation.email;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        try {
            helper.setText(email, true);
            helper.setTo(to);
            System.out.println("email: " + email + "to: " + to);
            helper.setSubject("Confirm your email");
            helper.setFrom("genesiscode91@gmail.com");
            mailSender.send(mimeMessage);
        } catch(MessagingException | MailException mailException) {
            LOGGER.error("failed to send email", mailException);
            throw new IllegalStateException("Failed to send email");
        }
    }
}
