package kz.natooa.paymentservice.payment.dto;

import kz.natooa.paymentservice.payment.entity.PaymentCurrency;
import kz.natooa.paymentservice.payment.entity.PaymentStatus;

import java.math.BigDecimal;

public record ClientPaymentsResponseDto(
        Long paymentId,
        BigDecimal amount,
        PaymentCurrency currency,
        PaymentStatus status
) {
}
