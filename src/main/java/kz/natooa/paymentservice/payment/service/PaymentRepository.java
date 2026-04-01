package kz.natooa.paymentservice.payment.service;

import kz.natooa.paymentservice.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByClient_ClientId(Long clientId);
}
