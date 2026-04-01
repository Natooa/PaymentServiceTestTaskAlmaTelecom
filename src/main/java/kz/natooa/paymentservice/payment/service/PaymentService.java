package kz.natooa.paymentservice.payment.service;

import kz.natooa.paymentservice.payment.dto.PaymentWithClientDto;
import kz.natooa.paymentservice.payment.entity.Payment;
import kz.natooa.paymentservice.payment.dto.ClientPaymentsResponseDto;
import kz.natooa.paymentservice.payment.dto.CreatePaymentRequestDto;
import kz.natooa.paymentservice.payment.dto.PaymentResponseDto;

import java.util.List;

public interface PaymentService {
    PaymentResponseDto createPayment(CreatePaymentRequestDto createPaymentDto);
    Payment getPaymentById(Long paymentId);
    PaymentResponseDto confirmPayment(Long paymentId);
    PaymentResponseDto cancelPayment(Long paymentId);
    PaymentWithClientDto getPaymentWithClientById(Long paymentId);
}
