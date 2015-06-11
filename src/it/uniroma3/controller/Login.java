package it.uniroma3.controller;

import it.uniroma3.facade.UserFacade;
import it.uniroma3.model.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;


@ManagedBean(name = "login")
@SessionScoped
public class Login {

    @EJB(name = "user")
    private UserFacade userFacade;

    private User user;

    private String errorMessage;

    private String email;
    private String password;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    private void redirectHome() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect("/");
    }


    public void checkIsLoggedIn() throws IOException {
        if (isLoggedIn()) {
            redirectHome();
        }
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public String login() {
        User u = this.userFacade.findUser(email);
        if (u == null) {
            this.errorMessage = "Invalid email";
            return "pretty:login";
        } else if (!this.password.equals(u.getPassword())) {
            this.errorMessage = "Invalid password";
            return "pretty:login";
        }
        this.errorMessage = null;
        this.user = u;

        return "pretty:home";
    }

    public String logout() {
        user = null;
        return "pretty:home";
    }

    public User getCustomer() {
        return null;
    }
}