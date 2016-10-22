/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service.controller;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Customer;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Employee;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.Group;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.User;
import edu.iit.sat.itmd4515.karthikk99.fp.service.CustomerService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.EmployeeService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.GroupService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.UserService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import static edu.iit.sat.itmd4515.karthikk99.fp.web.LoginBean.user;
import static edu.iit.sat.itmd4515.karthikk99.fp.web.LoginBean.uid;
import javax.annotation.PostConstruct;

/**
 * Controller class to define User Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    private User euser = new User();
    private User cuser = new User();
    private User auser = new User();
    private String userName;
    private int cid, eid;
    private String groupName;

    @EJB
    UserService svc;

    @EJB
    GroupService gsvc;

    @EJB
    CustomerService csvc;

    @EJB
    EmployeeService esvc;

    public UserController() {
    }

    public List<User> showUser() {
        return svc.findAll();
    }

    public List<Group> showGroup() {
        return gsvc.findAll();
    }

    public User getEuser() {
        return euser;
    }

    public void setEuser(User euser) {
        this.euser = euser;
    }

    public User getCuser() {
        return cuser;
    }

    public void setCuser(User cuser) {
        this.cuser = cuser;
    }

    public void createCustomerUser() {
        if (cid >= 1) {
            Customer cus = new Customer();
            cus = csvc.find(cid);
            svc.createCustomerUser(cuser, cus);
            cuser.setUserName(null);
            cuser.setUserName(null);
            cuser.setCustomer(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("New user created!"));
        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer not selected", "Customer is not selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void createEmployeeUser() {
        if (eid >= 1) {
            Employee employee = new Employee();
            employee = esvc.find(eid);
            svc.createEmployeeUser(euser, employee);
            euser.setUserName(null);
            euser.setPassword(null);
            euser.setEmployee(null);
            euser.setCustomer(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("New user created!"));
        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee not selected", "Employee is not selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void createAdminUser() {
        auser.setCustomer(null);
        auser.setEmployee(null);
        svc.createAdminUser(auser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("New admin login created!"));
        auser.setUserName(null);
        auser.setPassword(null);
    }

    public void deleteUser() {
        try {
            if (userName != null) {
                svc.deleteById(userName);
                this.userName = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User account deleted!"));
            } else {
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not selected", "User is not selected");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to delete the user account", "Unable to delete the user accoun");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void editCustomerUser() {
        this.euser.setUserName(user);
        Customer c = new Customer();
        c = csvc.find(uid);
        User unew = svc.find(user);
        unew.setPassword(euser.getPassword());
        svc.updateCustomer(euser, c);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password updated successfully!"));
        this.euser.setUserName(null);
        this.euser.setPassword(null);
        this.euser.setCustomer(null);
        this.euser.setEmployee(null);
    }

    public void editEmployeeUser() {
        this.euser.setUserName(user);
        Employee emp = new Employee();
        emp = esvc.find(uid);
        User unew = svc.find(user);
        unew.setPassword(euser.getPassword());
        svc.updateEmployee(unew, emp);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password updated successfully!"));
        this.euser.setUserName(null);
        this.euser.setPassword(null);
        this.euser.setCustomer(null);
        this.euser.setEmployee(null);
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getAuser() {
        return auser;
    }

    public void setAuser(User auser) {
        this.auser = auser;
    }

}
