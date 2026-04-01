package kz.natooa.paymentservice.common.exception;

import kz.natooa.paymentservice.payment.entity.PaymentStatus;

public class PaymentAlreadyProcessedException extends BaseException{
    public PaymentAlreadyProcessedException(Long paymentId, PaymentStatus status) {
        super("Payment with id " + paymentId + " already processed with status: " + status);
    }
}
