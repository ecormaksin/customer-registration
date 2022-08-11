package com.example.customerregistration.presentation.customer.registration;

import com.example.customerregistration.domain.customer.*;
import com.example.customerregistration.domain.customer.CustomerNumber;
import com.example.customerregistration.usecase.scenario.CustomerRegistrationScenario;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public abstract class ControllerBase {

    static final String VIEW_TEMPLATE_PATH_BASE = "customer/register/";
    static final String VIEW_TEMPLATE_PATH_FORM = VIEW_TEMPLATE_PATH_BASE + "form";
    static final String VIEW_TEMPLATE_PATH_COMPLETED = VIEW_TEMPLATE_PATH_BASE + "completed";
    final CustomerRegistrationScenario registrationScenario;

    public ControllerBase(final CustomerRegistrationScenario registrationScenario) {
        this.registrationScenario = registrationScenario;
    }

    abstract String mappedUrlBase();
    abstract CustomerNumber customerNumber();
    abstract RegistrationRequestDate registrationRequestDate();

    String init(Model model) {
        model.addAttribute("form", Form.newForm("/" + mappedUrlBase()));
        return VIEW_TEMPLATE_PATH_FORM;
    }

    String create(final Form form,
                  BindingResult bindingResult,
                  RedirectAttributes attributes,
                  UriComponentsBuilder builder) {

        if (bindingResult.hasErrors()) {
            return VIEW_TEMPLATE_PATH_FORM;
        }

        final CustomerNumber customerNumber = customerNumber();
        final RegistrationRequestDate registrationRequestDate = registrationRequestDate();

        RegistrationRequest meetingCreationRequest;

        try {
            meetingCreationRequest = form.toRegistrationRequest(
                customerNumber,
                registrationRequestDate
            );
        } catch (AgeUnderLimitException e) {
            FieldError error = new FieldError(bindingResult.getObjectName(),
                "birthdate.value",
                form.birthdate().value(),
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
            "customerName.value",
            "birthdate.value"
        );
    }
}
