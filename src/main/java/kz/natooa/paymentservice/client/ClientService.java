package kz.natooa.paymentservice.client;

import kz.natooa.paymentservice.payment.dto.ClientPaymentsResponseDto;

import java.util.List;

public interface ClientService {
    List<ClientPaymentsResponseDto> getClientPayments(Long clientId);

    Client createClient(Client client);
    Client getClientById(Long clientId);
}
