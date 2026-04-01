package kz.natooa.paymentservice.client;

import kz.natooa.paymentservice.common.exception.ClientNotFoundException;
import kz.natooa.paymentservice.payment.dto.ClientPaymentsResponseDto;
import kz.natooa.paymentservice.payment.dto.PaymentMapper;
import kz.natooa.paymentservice.payment.entity.Payment;
import kz.natooa.paymentservice.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{
    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;
    private final PaymentMapper paymentMapper;

    public ClientServiceImpl(ClientRepository clientRepository, PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.clientRepository = clientRepository;
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public List<ClientPaymentsResponseDto> getClientPayments(Long clientId) {
        if (clientRepository.findById(clientId).isEmpty()) {
            throw new ClientNotFoundException(clientId);
        }
        List<Payment> clientPayments = paymentRepository.findByClient_ClientId(clientId);
        return paymentMapper.toClientResponseDtoList(clientPayments);
    }

    @Override
    public Client createClient(Client client) {
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
    }
}
