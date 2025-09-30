package com.example.demo.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.stereotype.Service;

@Service
public class TwoFactorAuthService {

    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    // Generate secret key for a user
    public String generateSecretKey() {
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        return key.getKey();
    }

    // Verify code entered by user
    public boolean verifyCode(String secretKey, int code) {
        return gAuth.authorize(secretKey, code);
    }

    // Generate QR code URL for Google Authenticator
    public String getQRBarcodeURL(String user, String host, String secretKey) {
        String format = "otpauth://totp/%s@%s?secret=%s&issuer=%s";
        return String.format(format, user, host, secretKey, host);
    }
}
