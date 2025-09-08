package com.luxury_hotel.auth_service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendOtpEmail(String to, String otp){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Verification code");
        message.setText("Your OTP for registration is: " + otp + "\nIt is valid for 5 minutes.");
        mailSender.send(message);
    }
    public  void sendPasswordResetOtpEmail(String to, String otp){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Password Reset Code");
        message.setText("Your OTP for resetting your password is: " + otp + "\nIt is valid for 5 minutes.");
        mailSender.send(message);
    }
}
