package kz.natooa.paymentservice.client;

import kz.natooa.paymentservice.payment.dto.ClientPaymentsResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}/payments")
    public ResponseEntity<List<ClientPaymentsResponseDto>> getClientPayments(@PathVariable Long clientId){
        return ResponseEntity.ok(clientService.getClientPayments(clientId));
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        return ResponseEntity.status(201).body(clientService.createClient(client));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId){
        return ResponseEntity.ok(clientService.getClientById(clientId));
    }
}
