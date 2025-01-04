package com.example.qr_code.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QrCodeResponseDTO {
    Long id;
    byte[] qrCodeImage;
}
