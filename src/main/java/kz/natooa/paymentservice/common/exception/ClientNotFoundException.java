package kz.natooa.paymentservice.common.exception;

public class ClientNotFoundException extends BaseException{
    public ClientNotFoundException(Long clientId) {
        super("Client with id " + clientId + " not found");
    }
}
