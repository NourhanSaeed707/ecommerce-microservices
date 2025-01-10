package com.example.payment.payment;
import com.example.payment.notification.NotificationProducer;
import com.example.payment.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.getOrderReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        request.getCustomer().getFirstname(),
                        request.getCustomer().getLastname(),
                        request.getCustomer().getEmail()
                )
        );
        return payment.getId();
    }
}
