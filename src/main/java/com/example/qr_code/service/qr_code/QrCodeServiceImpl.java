package com.example.qr_code.service.qr_code;

import com.example.qr_code.configurations.QrCodeNotFoundException;
import com.example.qr_code.dto.QrCodeResponseDTO;
import com.example.qr_code.dto.QrTextDTO;
import com.example.qr_code.entity.QrCode;
import com.example.qr_code.repository.QrCodeRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class QrCodeServiceImpl implements QrCodeService {
    private final QrCodeRepository repository;


    @Override
    public QrCodeResponseDTO generateQrCode(QrTextDTO qrText) throws WriterException, IOException {

        try {
            QrCode qrCode = generateAndSaveQRCode(qrText);

            return QrCodeResponseDTO.builder()
                    .id(qrCode.getId())
                    .qrCodeImage(qrCode.getQrCodeImage())
                    .build();


        } catch (IOException e) {
            throw new RuntimeException("Error generating QR code", e);
        }
    }

    @Override
    public QrCodeResponseDTO getQrCodeById(Long id) {
        QrCode qrCode = repository.findById(id).orElseThrow(() -> new QrCodeNotFoundException("ID: "+id+ " bo'yicha QR Kod topilmadi."));
        return QrCodeResponseDTO.builder()
                .id(qrCode.getId())
                .qrCodeImage(qrCode.getQrCodeImage())
                .build();
    }

    private QrCode generateAndSaveQRCode(QrTextDTO qrText) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrText.getText(), BarcodeFormat.QR_CODE, 300, 300);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        byte[] qrCodeImage = outputStream.toByteArray();

        QrCode.QrCodeBuilder qrCodeBuilder = QrCode.builder().text(qrText.getText()).qrCodeImage(qrCodeImage);
        return repository.save(qrCodeBuilder.build());
    }
}
