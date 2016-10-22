/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service.controller;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Bank;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Customer;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Employee;
import edu.iit.sat.itmd4515.karthikk99.fp.service.BankService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.CustomerService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.EmployeeService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import static edu.iit.sat.itmd4515.karthikk99.fp.web.LoginBean.uid;

/**
 * Controller class to define Customer Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Named
@SessionScoped
public class CustomerController implements Serializable {

    private int bid;
    private Customer customer = new Customer();
    private Customer ecus = new Customer();
    @EJB
    CustomerService svc;

    @EJB
    BankService bsvc;
    
    @EJB
    EmployeeService esvc;

    private int cid;

    private int eid;

    public CustomerController() {

    }

    public void createCustomer() {
        this.eid = uid;
        if (bid >= 1) {
            Bank b = bsvc.find(bid);
            Employee e = esvc.find(eid);
            svc.create(customer, b, e);
            customer.setName(null);
            customer.setName2(null);
            customer.setAddress(null);
            customer.setEmployee(null);
            customer.setContactNo(0);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer record created"));
        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bank not selected", "Bank is not selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void editCustomer() {
        this.eid = uid;
        if (ecus.getId() >= 1) {
            if (bid >= 1) {
                Bank b = bsvc.find(bid);
                Employee e = esvc.find(eid);
                svc.update(ecus, b, e);
                ecus.setName(null);
                ecus.setName2(null);
                ecus.setAddress(null);
                ecus.setContactNo(0);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer details updated"));
            } else {
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bank not selected", "Bank is not selected");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer not selected", "Customer is not selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void deleteCustomer() {
        this.cid = uid;
        try {
            if (this.getCid() >= 1) {
                svc.deleteById(this.getCid());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer deleted"));
            } else {
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer not selected", "Customer is not selected");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to delete the customer. Check if the customer has an active user login account", "Unable to delete the customer. Check if the customer has an active user login account");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<Customer> showCustomer() {
        return svc.findAll();
    }

    public List<Bank> showBank() {
        return bsvc.findAll();

    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Customer getEcus() {
        return ecus;
    }

    public void setEcus(Customer ecus) {
        this.ecus = ecus;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
}
