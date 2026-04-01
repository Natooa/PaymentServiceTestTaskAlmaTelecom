package kz.natooa.paymentservice.common.exception;

public class PaymentNotFoundException extends BaseException{
    public PaymentNotFoundException(Long paymentId) {
        super("Payment with id " + paymentId + " not found");
    }
}
