package com.practice.journalApp.Cron;

import com.practice.journalApp.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailCronService {
    @Autowired
    private EmailService emailService;
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendScheduleEmail(){
        System.out.println("Executing scheduled job ");
        emailService.sendEmail("krishjain2902@gmal.com","test","Hi krish here");
    }

}
