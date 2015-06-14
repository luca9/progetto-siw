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

    private String username;
    private String password;


    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String username) {
        this.username = username;
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

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public String login() {
        User u = this.userFacade.getUser(username);
        if (u == null) {
            this.errorMessage = "Invalid email";
            return "login";
        } else if (!this.password.equals(u.getPassword())) {
            this.errorMessage = "Invalid password";
            return "login";
        }
        this.errorMessage = null;
        this.user = u;

        return "welcomeUser";
    }

    public String logout() {
        user = null;
        return "pretty:home";
    }

    public User getCustomer() {
        return null;
    }
}