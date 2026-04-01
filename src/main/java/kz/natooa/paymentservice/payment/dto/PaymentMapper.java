package kz.natooa.paymentservice.payment.dto;

import kz.natooa.paymentservice.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
    Payment toEntity(CreatePaymentRequestDto dto);
    PaymentResponseDto toResponseDto(Payment entity);
    ClientPaymentsResponseDto toClientResponseDto(Payment entity);
    List<ClientPaymentsResponseDto> toClientResponseDtoList(List<Payment> entities);

    @Mapping(source = "client.clientId", target = "clientId")
    PaymentWithClientDto toPaymentWithClientDto(Payment payment);
}
