package com.example.qr_code.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "qr_code")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QrCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String text;

    @Column(nullable = false, columnDefinition = "BYTEA")
    byte[] qrCodeImage;

}
