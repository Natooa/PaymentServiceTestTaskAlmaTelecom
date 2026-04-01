package kz.natooa.paymentservice.payment.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import kz.natooa.paymentservice.payment.entity.PaymentCurrency;

import java.math.BigDecimal;

public record CreatePaymentRequestDto(
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
        BigDecimal amount,

        @NotNull(message = "Currency is required")
        PaymentCurrency currency,

        String description,

        @NotNull(message = "Client ID is required")
        Long clientId
){
}
