package com.example.demo.controller;

import com.example.demo.service.TwoFactorAuthService;
import com.example.demo.util.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/2fa")
public class TwoFactorAuthController {

    @Autowired
    private TwoFactorAuthService twoFactorAuthService;

    // Generate secret key
    @GetMapping("/generate")
    public String generateSecret() {
        return twoFactorAuthService.generateSecretKey();
    }

    // Generate QR code image for scanning
    @GetMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getQRCode(@RequestParam String user, @RequestParam String host, @RequestParam String secret) throws Exception {
        String barcodeUrl = twoFactorAuthService.getQRBarcodeURL(user, host, secret);
        return QRCodeGenerator.generateQRCodeImage(barcodeUrl, 200, 200);
    }

    // Verify code
    @PostMapping("/verify")
    public String verify(@RequestParam String secret, @RequestParam int code) {
        boolean isValid = twoFactorAuthService.verifyCode(secret, code);
        return isValid ? "Code is valid!" : "Invalid code!";
    }
}
