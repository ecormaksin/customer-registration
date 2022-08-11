package com.example.customerregistration.presentation.customer;

import com.example.customerregistration.domain.customer.*;
import com.example.customerregistration.domain.customer.Number;
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
@RequestMapping(RegistrationControllerPojo.MAPPED_URL_BASE)
public class RegistrationControllerPojo extends RegistrationControllerBase {

    static final String MAPPED_URL_BASE = "customer/pojo/register";

    public RegistrationControllerPojo(final CustomerRegistrationScenario registrationScenario) {
        super(registrationScenario);
    }

    String mappedUrlBase() {
        return MAPPED_URL_BASE;
    }

    Number customerNumber() {
        return Number.newInstanceByStaticMethod();
    }

    RegistrationRequestDate registrationRequestDate() {
        return RegistrationRequestDate.nowByStaticMethod();
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
