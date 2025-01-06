package com.example.customer.customer;
import ch.qos.logback.core.util.StringUtil;
import com.example.customer.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    public final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(mapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.getId()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Cannot update customer:: No customer found with the provided ID:: %s", customerRequest.getId())
        ));
        mergeCustomer(customer, customerRequest);
        customerRepository.save(customer);

    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.getFirstName()) ) {
            customer.setFirstName(customerRequest.getFirstName());
        }
        if(StringUtils.isNotBlank(customerRequest.getLastName()) ) {
            customer.setLastName(customerRequest.getLastName());
        }
        if(StringUtils.isNotBlank(customerRequest.getEmail()) ) {
            customer.setEmail(customerRequest.getEmail());
        }
        if(customerRequest.getAddress() != null ) {
            customer.setAddress(customerRequest.getAddress());
        }
    }
}
