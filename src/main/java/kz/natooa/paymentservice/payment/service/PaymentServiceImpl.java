package kz.natooa.paymentservice.payment.service;

import kz.natooa.paymentservice.client.ClientRepository;
import kz.natooa.paymentservice.common.exception.ClientNotFoundException;
import kz.natooa.paymentservice.common.exception.PaymentAlreadyProcessedException;
import kz.natooa.paymentservice.common.exception.PaymentNotFoundException;
import kz.natooa.paymentservice.payment.dto.*;
import kz.natooa.paymentservice.payment.entity.Payment;
import kz.natooa.paymentservice.payment.entity.PaymentStatus;
import kz.natooa.paymentservice.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final ClientRepository clientRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(ClientRepository clientRepository, PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.clientRepository = clientRepository;
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentResponseDto createPayment(CreatePaymentRequestDto createPaymentDto) {
        var client = clientRepository.findById(createPaymentDto.clientId())
                .orElseThrow(() -> new ClientNotFoundException(createPaymentDto.clientId()));

        var payment = paymentMapper.toEntity(createPaymentDto);
        payment.setClient(client);
        payment.setStatus(PaymentStatus.PENDING);
        paymentRepository.save(payment);
        return paymentMapper.toResponseDto(payment);
    }

    @Override
    public PaymentWithClientDto getPaymentWithClientById(Long paymentId) {
        Payment payment = getPaymentById(paymentId);
        return paymentMapper.toPaymentWithClientDto(payment);
    }

    @Transactional
    @Override
    public PaymentResponseDto confirmPayment(Long paymentId) {
        var payment = getPaymentById(paymentId);
        validatePaymentStatus(payment);
        payment.setStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
        return paymentMapper.toResponseDto(payment);
    }

    @Transactional
    @Override
    public PaymentResponseDto cancelPayment(Long paymentId) {
        var payment = getPaymentById(paymentId);
        validatePaymentStatus(payment);
        payment.setStatus(PaymentStatus.CANCELED);
        paymentRepository.save(payment);
        return paymentMapper.toResponseDto(payment);
    }




    @Override
    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
    }

    private void validatePaymentStatus(Payment payment) {
        PaymentStatus status = payment.getStatus();
        if (status == PaymentStatus.CONFIRMED ||
                status == PaymentStatus.CANCELED) {
            throw new PaymentAlreadyProcessedException(payment.getPaymentId(), status);
        }
    }
}
