/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.web;

/**
 *
 * @author Karthik Keshav
 */
import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.User;
import edu.iit.sat.itmd4515.karthikk99.fp.service.controller.UserController;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import static edu.iit.sat.itmd4515.karthikk99.fp.web.LoginBean.user;
/**
 * Controller class to define Navigation operations in Banking system
 *
 * @author Karthik Keshav
 */
@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {

    /**
     * Redirect to login page.
     *
     * @return Login page name.
     */
    public String redirectToLogin() {
        return "/index.xhtml?faces-redirect=true";
    }

    /**
     * Go to login page.
     *
     * @return Login page name.
     */
    public String toLogin() {
        return "/index.xhtml";
    }

    /**
     * Redirect to info page.
     *
     * @return Info page name.
     */
    public String redirectToInfo() {
        return "/info.xhtml?faces-redirect=true";
    }

    /**
     * Go to info page.
     *
     * @return Info page name.
     */
    public String toInfo() {
        return "/info.xhtml";
    }

    /**
     * Redirect to welcome page.
     *
     * @return Welcome page name.
     */
    public String redirectToWelcome() {
        return "/admin/welcome.xhtml?faces-redirect=true";
    }

    /**
     * Go to welcome page.
     *
     * @return Welcome page name.
     */
    public String toWelcome() {
        return "/admin/welcome.xhtml";
    }

    public String toCreateBank() {
        return "/admin/Create/CreateBank.xhtml";
    }

    public String toCreateCustomer() {
        return "/employee/Create/CreateCustomer.xhtml";
    }

    public String toCreateEmployee() {
        return "/admin/Create/CreateEmployee.xhtml";
    }

    public String toCreateAccount() {
        return "/employee/Create/CreateAccount.xhtml";
    }

    public String toCreateTransaction() {
        return "/customer/Create/CreateTransaction.xhtml";
    }

    public String toCreateCustomerUser() {
        return "/admin/Create/CreateUser-Customer.xhtml";
    }

    public String toCreateEmployeeUser() {
        return "/admin/Create/CreateUser-Employee.xhtml";
    }
        
    public String toReadBank() {
        return "/employee/Read/ReadBank.xhtml";
    }

    public String toReadCustomer() {
        return "/employee/Read/ReadCustomer.xhtml";
    }

    public String toReadEmployee() {
        return "/admin/Read/ReadEmployee.xhtml";
    }

    public String toReadTransaction() {
        return "/customer/Read/ReadTransaction.xhtml";
    }

    public String toAccountDetails() {
        return "/customer/Read/ReadAccount.xhtml";
    }

    public String toReadUser() {
        return "/employee/Read/ReadUser.xhtml";
    }

    public String toEditUser() {
        return "/admin/Update/EditBank.xhtml";
    }

    public String toEditCustomer() {
        return "/employee/Update/EditCustomer.xhtml";
    }

    public String toEditEmployee() {
        return "/admin/Update/EditEmployee.xhtml";
    }

    public String toEditAccount() {
        return "/employee/Update/EditAccount.xhtml";
    }

    public String toDeleteBank() {
        return "/admin/Delete/DeleteBank.xhtml";
    }

    public String toDeleteCustomer() {
        return "/employee/Delete/DeleteCustomer.xhtml";
    }

    public String toDeleteEmployee() {
        return "/admin/Delete/DeleteEmployee.xhtml";
    }

    public String toDeleteUser() {
        return "/admin/Delete/DeleteUser.xhtml";
    }

    public String toDeleteAccount() {
        return "/employee/Delete/DeleteAccount.xhtml";
    }

    public String toBankMenu() {
        return "/admin/Bank.xhtml";
    }

    public String toEditBank()
    {
        return "/admin/Update/EditBank.xhtml";
    }
    
    public String toEmployeeMenu() {
        return "/admin/Employee.xhtml";
    }
    
    public String toReadAccount()
    {
        return "/employee/Read/ReadAccount.xhtml";
    }
    
    public String toUserMenu() {
        return "/admin/User.xhtml";
    }

    public String toCustomer() {
        return "/employee/Customer.xhtml";
    }

    public String toAccount() {
        return "/employee/Account.xhtml";
    }

    public String toUser() {
        return "/employee/User.xhtml";
    }
    
    public String toEditCustomerUser()
    {
        return "/customer/Update/EditUser.xhtml";
    }
    
    public String toEditEmployeeUser()
    {
        return "/employee/Update/EditUser.xhtml";
    }
    
    public String toUserTransaction()
    {
        return "/employee/Read/ReadTransaction.xhtml";
    }
    
    public String toReadBankAdmin()
    {
        return "/admin/Read/ReadBank.xhtml";
    }
    public String toReadUserAdmin()
    {
        return "/admin/Read/ReadUser.xhtml";
    }
    public String toCreateAdminUser()
    {
        return "/admin/Create/CreateAdminUser.xhtml";
    }
}
