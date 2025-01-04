package com.example.qr_code.controller;

import com.example.qr_code.dto.QrCodeResponseDTO;
import com.example.qr_code.dto.QrTextDTO;
import com.example.qr_code.service.qr_code.QrCodeService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/qr_code")
@RequiredArgsConstructor
public class QrCodeController {
    private final QrCodeService qrCodeService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateQrCode(@RequestBody QrTextDTO qrText) throws IOException, WriterException {
        QrCodeResponseDTO qrCode = qrCodeService.generateQrCode(qrText);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "image/png");
        headers.add("QR_ID", qrCode.getId().toString());

        return new ResponseEntity<>(qrCode.getQrCodeImage(), headers, HttpStatus.CREATED);
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<byte[]> getQrCodeById(@PathVariable Long id) throws IOException {
        QrCodeResponseDTO qrCodeImage = qrCodeService.getQrCodeById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "image/png");
        headers.add("QR_ID", qrCodeImage.getId().toString());

        return new ResponseEntity<>(qrCodeImage.getQrCodeImage(), headers, HttpStatus.OK);
    }


}
