package com.example.qr_code.service.qr_code;

import com.example.qr_code.dto.QrCodeResponseDTO;
import com.example.qr_code.dto.QrTextDTO;
import com.google.zxing.WriterException;

import java.io.IOException;

public interface QrCodeService {
   QrCodeResponseDTO generateQrCode(QrTextDTO qrText) throws WriterException, IOException;
   QrCodeResponseDTO getQrCodeById(Long id);
}
