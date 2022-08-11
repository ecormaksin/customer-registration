package com.example.customerregistration.usecase.scenario;

import com.example.customerregistration.domain.customer.Customer;
import com.example.customerregistration.domain.customer.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegistrationScenario {

    public Customer execute(RegistrationRequest registrationRequest) {
        // do nothing for the simplicity of this example project.
        return new Customer(
            registrationRequest.customerNumber(),
            registrationRequest.customerName(),
            registrationRequest.birthdate()
        );
    }
}
