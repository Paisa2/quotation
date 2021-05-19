package com.genesiscode.quotation.service;
import com.genesiscode.quotation.domain.EmailSender;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@AllArgsConstructor @Slf4j
@Service
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;

    private static final String CONFIRMATION_MSG = "Confirm your email";
    private static final String FROM = "genesiscode91@gmail.com";
    private static final String FAILED_MSG = "Failed to send email";

    @Async
    @Override
    public void send(String to, String email) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        try {
            createMessage(helper, to, email);
            mailSender.send(mimeMessage);
        } catch(MessagingException | MailException mailException) {
            log.error(FAILED_MSG, mailException);
            throw new IllegalStateException(FAILED_MSG);
        }
    }

    private void createMessage(MimeMessageHelper helper, String to, String email) throws MessagingException{
        helper.setText(email, true);
        helper.setTo(to);
        helper.setSubject(CONFIRMATION_MSG);
        helper.setFrom(FROM);
    }
}
