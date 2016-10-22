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
import edu.iit.sat.itmd4515.karthikk99.fp.service.EmployeeService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import static edu.iit.sat.itmd4515.karthikk99.fp.web.LoginBean.uid;
import java.awt.event.ActionEvent;

/**
 * Controller class to define Employee Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Named
@SessionScoped
public class EmployeeController implements Serializable {

    private Employee cemp = new Employee();
    private Employee eemp = new Employee();
    private Employee demp = new Employee();
    @EJB
    EmployeeService esvc;

    @EJB
    BankService bsvc;

    private int bid, bbid;

    private Bank bank;

    private int eid;

    public EmployeeController() {
            this.eid = uid;
    }

    public void createEmployee() {
        if (bid >= 1) {
            Bank b = bsvc.find(bid);
            esvc.create(cemp, b);
            cemp.setId(0);
            cemp.setName(null);
            cemp.setName2(null);
            cemp.setContactNo(0);
            cemp.setAddress(null);
            cemp.setBank(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You have successfully added an employee"));
        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bank not selected", "Bank is not selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<Employee> showEmployee() {
        return esvc.findAll();
    }

    public List<Bank> showBank() {
        return bsvc.findAll();
    }

    public Employee getCemp() {
        return cemp;
    }

    public void setCemp(Employee cemp) {
        this.cemp = cemp;
    }

    public Employee getEemp() {
        return eemp;
    }

    public void setEemp(Employee eemp) {
        this.eemp = eemp;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void editEmployee() {
        if (eemp.getId() >= 1) {
            if (bbid >= 1) {
                Bank b = bsvc.find(bbid);
                esvc.update(eemp, b);
                eemp.setId(0);
                eemp.setName(null);
                eemp.setName2(null);
                eemp.setContactNo(0);
                eemp.setAddress(null);
                eemp.setBank(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employee data updated successfully"));
            } else {
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bank not selected", "Bank is not selected");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee not selected", "Employee is not selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void deleteEmployee() {
        try {
            if (demp.getId() >= 1) {
                esvc.deleteById(demp.getId());
                demp.setId(0);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You have successfully deleted an employees"));
            } else {
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee not selected", "Employee is not selected");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to delete the employee as employee's login account is active or have data dependency in other tables", "Unable to delete the employee as employee's login account is active or data dependency in other tables");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Employee getDemp() {
        return demp;
    }

    public void setDemp(Employee demp) {
        this.demp = demp;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getBbid() {
        return bbid;
    }

    public void setBbid(int bbid) {
        this.bbid = bbid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
}
