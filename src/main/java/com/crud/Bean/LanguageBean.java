package com.crud.Bean;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.Locale;

@Named
@SessionScoped
public class LanguageBean {

    private Locale locale;

    @PostConstruct
    public void init() {
        FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
    private String selectedLocale;

    public String getSelectedLocale() {
        return selectedLocale;
    }

    public void setSelectedLocale(String selectedLocale) {
        this.selectedLocale = selectedLocale;
    }


    public void changeLocale() {

        if (selectedLocale.equals("en")) {
            setLocale(Locale.ENGLISH);
        } else if (selectedLocale.equals("fr")) {
            setLocale(Locale.FRENCH);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid language selection."));
        }

    }


}