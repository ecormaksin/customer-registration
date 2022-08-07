package com.example.customerregistration.presentation.customer;

import com.example.customerregistration.domain.customer.*;
import com.example.customerregistration.domain.customer.Number;
import com.example.customerregistration.usecase.scenario.CustomerRegistrationScenario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping(RegistrationControllerPojo.MAPPED_URL_BASE)
@RequiredArgsConstructor
public class RegistrationControllerPojo {

    static final String MAPPED_URL_BASE = "customer/pojo/register";

    static final String VIEW_TEMPLATE_PATH_BASE = "customer/register/";
    static final String VIEW_TEMPLATE_PATH_FORM = VIEW_TEMPLATE_PATH_BASE + "form";
    static final String VIEW_TEMPLATE_PATH_COMPLETED = VIEW_TEMPLATE_PATH_BASE + "completed";

    private final CustomerRegistrationScenario registrationScenario;

    @GetMapping
    String init(Model model) {
        model.addAttribute("registrationForm", RegistrationForm.newForm());
        setActionPath(model);
        return VIEW_TEMPLATE_PATH_FORM;
    }

    private void setActionPath(Model model) {
        model.addAttribute("actionPath", "/" + MAPPED_URL_BASE);
    }

    @PostMapping
    String create(Model model,
                  @Validated @ModelAttribute("registrationForm") final RegistrationForm registrationForm,
                  BindingResult bindingResult,
                  @ModelAttribute("pathPart") final String pathPart,
                  RedirectAttributes attributes,
                  UriComponentsBuilder builder) {

        if (bindingResult.hasErrors()) {
            setActionPath(model);
            return VIEW_TEMPLATE_PATH_FORM;
        }

        final Number customerNumber = Number.newInstanceByStaticMethod();
        RegistrationRequest meetingCreationRequest;

        try {
            meetingCreationRequest = registrationForm.toRegistrationRequest(
                customerNumber,
                RegistrationRequestDate.now()
            );
        } catch (AgeUnderLimitException e) {
            FieldError error = new FieldError(bindingResult.getObjectName(),"birthDate.value", e.getMessage());
            bindingResult.addError(error);

            model.addAttribute("registrationForm", registrationForm);
            setActionPath(model);
            return VIEW_TEMPLATE_PATH_FORM;
        }

        final Customer customer = registrationScenario.execute(meetingCreationRequest);

        attributes.addFlashAttribute("customer", customer);

        URI uri = builder.path(MAPPED_URL_BASE + "/completed").build().toUri();
        return "redirect:" + uri.toString();
    }

    @GetMapping("completed")
    String completed(Model model,
                     @ModelAttribute("customer") Customer customer) {

        model.addAttribute("customer", customer);

        return "customer/register/completed";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(
            "name.value",
            "birthDate.value"
        );
    }
}
