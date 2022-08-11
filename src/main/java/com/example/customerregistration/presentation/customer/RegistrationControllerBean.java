package com.example.customerregistration.presentation.customer;

import com.example.customerregistration.domain.customer.Customer;
import com.example.customerregistration.domain.customer.Number;
import com.example.customerregistration.domain.customer.RegistrationRequestDate;
import com.example.customerregistration.usecase.scenario.CustomerRegistrationScenario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping(RegistrationControllerBean.MAPPED_URL_BASE)
public class RegistrationControllerBean extends RegistrationControllerBase {

    static final String MAPPED_URL_BASE = "customer/bean/register";

    final Number number;
    final RegistrationRequestDate registrationRequestDate;

    public RegistrationControllerBean(final CustomerRegistrationScenario registrationScenario,
                                      final Number number,
                                      final RegistrationRequestDate registrationRequestDate) {
        super(registrationScenario);

        this.number = number;
        this.registrationRequestDate = registrationRequestDate;
    }

    String mappedUrlBase() {
        return MAPPED_URL_BASE;
    }

    Number customerNumber() {
        return number.newInstanceByInstanceMethod();
    }

    RegistrationRequestDate registrationRequestDate() {
        return registrationRequestDate.nowByInstanceMethod();
    }

    @GetMapping
    String init(Model model) {
        return super.init(model);
    }

    @PostMapping
    String create(@Validated @ModelAttribute("registrationForm") final RegistrationForm registrationForm,
                  BindingResult bindingResult,
                  RedirectAttributes attributes,
                  UriComponentsBuilder builder) {

        return super.create(registrationForm, bindingResult, attributes, builder);
    }

    @GetMapping("completed")
    String completed(Model model,
                     @ModelAttribute("customer") Customer customer) {

        return super.completed(model, customer);
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        super.initBinder(binder);
    }
}
