package com.example.customerregistration.presentation.customer.registration;

import com.example.customerregistration.domain.customer.*;
import com.example.customerregistration.domain.customer.CustomerNumber;
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
@RequestMapping(ControllerPojo.MAPPED_URL_BASE)
public class ControllerPojo extends ControllerBase {

    static final String MAPPED_URL_BASE = "customer/pojo/register";

    public ControllerPojo(final CustomerRegistrationScenario registrationScenario) {
        super(registrationScenario);
    }

    String mappedUrlBase() {
        return MAPPED_URL_BASE;
    }

    CustomerNumber customerNumber() {
        return CustomerNumber.newInstanceByStaticMethod();
    }

    RegistrationRequestDate registrationRequestDate() {
        return RegistrationRequestDate.nowByStaticMethod();
    }

    @GetMapping
    String init(Model model) {
        return super.init(model);
    }

    @PostMapping
    String create(@Validated @ModelAttribute("form") final Form form,
                  BindingResult bindingResult,
                  RedirectAttributes attributes,
                  UriComponentsBuilder builder) {

        return super.create(form, bindingResult, attributes, builder);
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
