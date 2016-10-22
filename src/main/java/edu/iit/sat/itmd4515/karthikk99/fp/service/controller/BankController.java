/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service.controller;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Account;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Bank;
import edu.iit.sat.itmd4515.karthikk99.fp.service.BankService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Controller class to define Bank Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Named
@SessionScoped
public class BankController implements Serializable {

    int id;

    private Bank cbank = new Bank();

    private Bank ebank = new Bank();

    private Bank dbank = new Bank();
    @EJB
    BankService svc;

    private List<Bank> banks = new ArrayList();

    /**
     * @PostConstruct public void init() { banks = this.showBank(); } *
     */
    public BankController() {
    }

    public void createBank() {
        svc.create(cbank);
        cbank.setId(0);
        cbank.setName(null);
        cbank.setAddress(null);
        cbank.setContactNo(0);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data saved"));
    }

    public void editBank() {
        if (ebank.getId() >= 1) {
            svc.update(ebank);
            ebank.setId(0);
            ebank.setName(null);
            ebank.setContactNo(0);
            ebank.setAddress(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data saved"));
        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bank not selected", "Bank is not selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<Bank> showBank() {
        return svc.findAll();
    }

    public void deleteBank() {
        try {
            if (dbank.getId() >= 1) {
                svc.deleteById(dbank.getId());
                dbank.setId(0);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You have successfully deleted a bank"));
            } else {
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bank not selected", "Bank is not selected");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to delete the bank as there are employees and customers associated with this bank", "Unable to delete the bank as there are employees and customers associated with this bank");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public Bank getCbank() {
        return cbank;
    }

    public void setCbank(Bank cbank) {
        this.cbank = cbank;
    }

    public Bank getEbank() {
        return ebank;
    }

    public void setEbank(Bank ebank) {
        this.ebank = ebank;
    }

    public Bank getDbank() {
        return dbank;
    }

    public void setDbank(Bank dbank) {
        this.dbank = dbank;
    }
}
