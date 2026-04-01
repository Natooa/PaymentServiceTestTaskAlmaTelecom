package kz.natooa.paymentservice.payment.controller;

import jakarta.validation.Valid;
import kz.natooa.paymentservice.payment.dto.ClientPaymentsResponseDto;
import kz.natooa.paymentservice.payment.dto.CreatePaymentRequestDto;
import kz.natooa.paymentservice.payment.dto.PaymentResponseDto;
import kz.natooa.paymentservice.payment.dto.PaymentWithClientDto;
import kz.natooa.paymentservice.payment.entity.Payment;
import kz.natooa.paymentservice.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(@Valid @RequestBody CreatePaymentRequestDto createPaymentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.createPayment(createPaymentRequestDto));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentWithClientDto> getStatusByPaymentId(@PathVariable Long paymentId){
        return ResponseEntity.ok(paymentService.getPaymentWithClientById(paymentId));
    }

    @PostMapping("/{paymentId}/confirm")
    public ResponseEntity<PaymentResponseDto> confirmPayment(@PathVariable Long paymentId){
        return ResponseEntity.ok(paymentService.confirmPayment(paymentId));
    }

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<PaymentResponseDto> cancelPayment(@PathVariable Long paymentId){
        return ResponseEntity.ok(paymentService.cancelPayment(paymentId));
    }
}
