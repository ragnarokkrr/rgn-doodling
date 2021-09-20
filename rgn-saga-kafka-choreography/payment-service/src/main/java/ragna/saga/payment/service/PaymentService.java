package ragna.saga.payment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ragna.saga.choreography.dto.PaymentDto;
import ragna.saga.choreography.events.order.OrderEvent;
import ragna.saga.choreography.events.payment.PaymentEvent;
import ragna.saga.choreography.events.payment.PaymentStatus;
import ragna.saga.payment.entity.UserTransaction;
import ragna.saga.payment.repository.UserBalanceRepository;
import ragna.saga.payment.repository.UserTransactionRepository;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        final var purchaseOrder = orderEvent.getPurchaseOrder();
        final var paymentDto = PaymentDto.of(purchaseOrder.getOrderId(), purchaseOrder.getUserId(), purchaseOrder.getPrice());

        return this.userBalanceRepository.findById(paymentDto.getUserId())
                .filter(userBalance -> userBalance.getBalance() >= purchaseOrder.getPrice())
                .map(userBalance -> {
                    userBalance.setBalance(userBalance.getBalance() - purchaseOrder.getPrice());
                    this.userTransactionRepository.save(UserTransaction.of(purchaseOrder.getOrderId(), purchaseOrder.getUserId(), purchaseOrder.getPrice()));
                    return new PaymentEvent(paymentDto, PaymentStatus.RESERVED);
                })
                .orElse(new PaymentEvent(paymentDto, PaymentStatus.REJECTED));
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {
        this.userTransactionRepository.findById(orderEvent.getPurchaseOrder().getOrderId())
                .ifPresent(userTransaction -> {
                    this.userTransactionRepository.delete(userTransaction);
                    this.userBalanceRepository.findById(userTransaction.getUserId())
                            .ifPresent(userBalance -> userBalance.setBalance(userBalance.getBalance() + userTransaction.getAmount()));
                });
    }
}
