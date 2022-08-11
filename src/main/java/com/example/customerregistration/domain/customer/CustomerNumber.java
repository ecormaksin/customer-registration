package com.example.customerregistration.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Component
public class CustomerNumber {

    public static final int SIZE = 32;
    static final String REGEXP = "[\\da-z]{" + SIZE + "}";
    static final String VALIDATION_MESSAGE = "CustomerNumber must be " + SIZE + " length with alpha-numeric value.";

    @NotNull(message = VALIDATION_MESSAGE)
    @Pattern(regexp=REGEXP, message = VALIDATION_MESSAGE)
    String value;

    @Deprecated
    CustomerNumber() {}

    private static String newValue() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static CustomerNumber newInstanceByStaticMethod() {
        return new CustomerNumber(newValue());
    }

    /**
     * this method is meaningless, of course
     * @return
     */
    public CustomerNumber newInstanceByInstanceMethod() {
        return new CustomerNumber(newValue());
    }
}
