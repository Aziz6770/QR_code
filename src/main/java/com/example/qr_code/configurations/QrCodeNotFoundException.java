package com.example.qr_code.configurations;


public class QrCodeNotFoundException extends RuntimeException{
    public QrCodeNotFoundException(String message) {
        super(message);
    }
}
