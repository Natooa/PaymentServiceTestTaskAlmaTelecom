package kz.natooa.paymentservice.payment.dto;

import kz.natooa.paymentservice.payment.entity.PaymentStatus;

public record PaymentResponseDto(
    Long paymentId,
    PaymentStatus status
){
}
