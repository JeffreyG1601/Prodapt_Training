package com.example.demo.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.trialNumber}")
    private String trialNumber;

    public void sendSms(String to, String body) {
        Message message = Message.creator(
                new PhoneNumber(to),     // To
                new PhoneNumber(trialNumber), // From (Twilio number)
                body
        ).create();

        System.out.println("✅ SMS sent with SID: " + message.getSid());
    }
}
