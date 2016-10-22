/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.web;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Customer;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Employee;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.User;
import edu.iit.sat.itmd4515.karthikk99.fp.service.CustomerService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.EmployeeService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.UserService;
import edu.iit.sat.itmd4515.karthikk99.fp.web.AbstractJSFBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Web class to define Login OPERATIONS in Banking system
 *
 * @author Karthik Keshav
 */
@Named
@RequestScoped
public class LoginBean extends AbstractJSFBean {

    private static final Logger LOG = Logger.getLogger(LoginBean.class.getName());
    @NotNull(message = "You shall not pass without a password!")
    @Size(min = 5, message = "Password must be at least 5 characters in length.")
    private String password;
    @NotNull(message = "You shall not pass without a username!")
    private String username;
    Customer cus = new Customer();
    public static int uid;
    public static String user;

    Boolean isLoggedIn;

    private CustomerService csvc;

    @EJB
    private EmployeeService esvc;

    @EJB
    private UserService usvc;

    public LoginBean() {
        super();
    }

    @PostConstruct
    private void postConstruct() {
        super.postContruct();
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemoteUser() {
        return facesContext.getExternalContext().getRemoteUser();
    }

    public boolean isAdmin() {
        return facesContext.getExternalContext().isUserInRole("admin");
    }

    public boolean isCustomer() {
        return facesContext.getExternalContext().isUserInRole("customer");
    }

    public boolean isEmployee() {
        return facesContext.getExternalContext().isUserInRole("employee");
    }

    public String getPortalPathByRole(String path) {
        if (isAdmin()) {
            user = null;
            return "/admin" + path;// + FACES_REDIRECT;
        } else if (isCustomer()) {
            user = this.username;
            User u = new User();
            u = usvc.findByUserName(username);
            if (u.getCustomer().getId() >= 1) {
                uid = u.getCustomer().getId();
            }
            return "/customer" + path;// + FACES_REDIRECT;
        } 
        else if (isEmployee()) {
            User u = new User();
            user = this.username;
            u = usvc.findByUserName(username);
            if (u.getEmployee().getId() >= 1) {
                uid = u.getEmployee().getId();
            }
            return "/employee" + path;// + FACES_REDIRECT;
        } else {
            return path;// + FACES_REDIRECT;
        }
    }

    public String doLogin() {
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            req.login(username, password);
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, "There has been a problem invoking HttpServletRequest.login", ex);
            facesContext.addMessage(null, new FacesMessage("Bad Login", "Detail: You made a bad login!"));
            // return the user to the login page with an error message
            this.isLoggedIn = false;
            return "/login.xhtml";
        }
        this.isLoggedIn = true;
        // send user to welcome page
        return getPortalPathByRole("/welcome.xhtml");
    }

    public String doLogout() {
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        try {
            req.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, "There has been a problem invoking HttpServletRequest.logout", ex);
            facesContext.addMessage(null, new FacesMessage("Bad Logout", "Bad Logout"));
            // return the user to the login page with an error message
            return "/error.xhtml";
        }
        // return the user to the login page
        facesContext.addMessage(null, new FacesMessage("Logged out successfully", "Logged out successfully"));
        user = null;
        return "/login.xhtml";
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
