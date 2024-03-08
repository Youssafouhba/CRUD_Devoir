package com.crud.Validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

public class EmailValidator implements Validator {
    private static final int MIN_EMAIL_LENGTH = 5;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
        String email = (String) object;
        if(!isValidEmail(email)){
            throw new ValidatorException(new FacesMessage("Address Email non Valide !!!!"));
        }
        if(email.length() < MIN_EMAIL_LENGTH){
            throw new ValidatorException(new FacesMessage("Addresse Email invalide doit contenaire au moins "+MIN_EMAIL_LENGTH +"!!!!"));
        }
    }
    private Boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}
