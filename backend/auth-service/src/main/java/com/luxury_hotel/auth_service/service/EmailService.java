package com.luxury_hotel.auth_service.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    // 🔹 Send OTP for Registration
    public void sendOtpEmail(String to, String otp) {
        try {
            Context context = new Context();
            context.setVariable("otp", otp);

            String htmlContent = templateEngine.process("otp-email", context);

            sendHtmlEmail(to, "Luxury Hotel - Email Verification", htmlContent);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }

    // 🔹 Send OTP for Password Reset
    public void sendPasswordResetOtpEmail(String to, String otp) {
        try {
            Context context = new Context();
            context.setVariable("otp", otp);

            String htmlContent = templateEngine.process("password-reset-email", context);

            sendHtmlEmail(to, "Luxury Hotel - Password Reset", htmlContent);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send password reset email", e);
        }
    }

    // 🔹 Helper method to send HTML emails
    private void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // true = HTML content

        mailSender.send(message);
    }
}
