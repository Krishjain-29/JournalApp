package com.practice.journalApp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTest {
    @Autowired
    private EmailService emailService;
@Test
    public void testMail(){
        emailService.sendEmail("krishjain2902@gmail.com","Testing email","hi krish here ");
    }

}
