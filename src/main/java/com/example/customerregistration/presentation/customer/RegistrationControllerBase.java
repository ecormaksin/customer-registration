package com.example.customerregistration.presentation.customer;

import com.example.customerregistration.domain.customer.*;
import com.example.customerregistration.domain.customer.Number;
import com.example.customerregistration.usecase.scenario.CustomerRegistrationScenario;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public abstract class RegistrationControllerBase {

    static final String VIEW_TEMPLATE_PATH_BASE = "customer/register/";
    static final String VIEW_TEMPLATE_PATH_FORM = VIEW_TEMPLATE_PATH_BASE + "form";
    static final String VIEW_TEMPLATE_PATH_COMPLETED = VIEW_TEMPLATE_PATH_BASE + "completed";
    final CustomerRegistrationScenario registrationScenario;

    public RegistrationControllerBase(final CustomerRegistrationScenario registrationScenario) {
        this.registrationScenario = registrationScenario;
    }

    abstract String mappedUrlBase();
    abstract Number customerNumber();
    abstract RegistrationRequestDate registrationRequestDate();

    String init(Model model) {
        model.addAttribute("registrationForm", RegistrationForm.newForm("/" + mappedUrlBase()));
        return VIEW_TEMPLATE_PATH_FORM;
    }

    String create(final RegistrationForm registrationForm,
                  BindingResult bindingResult,
                  RedirectAttributes attributes,
                  UriComponentsBuilder builder) {

        if (bindingResult.hasErrors()) {
            return VIEW_TEMPLATE_PATH_FORM;
        }

        final Number customerNumber = customerNumber();
        final RegistrationRequestDate registrationRequestDate = registrationRequestDate();

        RegistrationRequest meetingCreationRequest;

        try {
            meetingCreationRequest = registrationForm.toRegistrationRequest(
                customerNumber,
                registrationRequestDate
            );
        } catch (AgeUnderLimitException e) {
            FieldError error = new FieldError(bindingResult.getObjectName(),
                "birthDate.value",
                registrationForm.birthDate().value(),
                true, null, null, e.getMessage());
            bindingResult.addError(error);

            return VIEW_TEMPLATE_PATH_FORM;
        }

        final Customer customer = registrationScenario.execute(meetingCreationRequest);

        attributes.addFlashAttribute("customer", customer);

        URI uri = builder.path(mappedUrlBase() + "/completed").build().toUri();
        return "redirect:" + uri.toString();
    }

    String completed(Model model, Customer customer) {

        model.addAttribute("customer", customer);

        return VIEW_TEMPLATE_PATH_COMPLETED;
    }

    void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
            "name.value",
            "birthDate.value"
        );
    }
}
