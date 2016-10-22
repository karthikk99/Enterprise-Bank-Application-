/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service.controller;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Account;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Bank;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Customer;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Transaction;
import edu.iit.sat.itmd4515.karthikk99.fp.service.AccountService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.CustomerService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.TransactionService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import static edu.iit.sat.itmd4515.karthikk99.fp.web.LoginBean.uid;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Controller class to define Transaction Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Named
@SessionScoped
public class TransactionController implements Serializable {

    private Transaction tran = new Transaction();

    private int aid;

    private int cid;
   
    private int ccid;
    @EJB
    TransactionService svc;

    @EJB
    AccountService asvc;

    @EJB
    CustomerService csvc;

    public TransactionController() {
        this.tran.setTimestamp(new Date());
    }
    
    private boolean visible = false; // getter/setter

    public void getUserList(ActionEvent event) {
        setVisible(true);

        // Your code
    }
    public void createTransaction() {
        this.cid = uid;
        if (this.aid >= 1 && this.cid >= 1) {
            Account a = new Account();
            Customer c = new Customer();
            a = asvc.find(this.aid);
            c = csvc.find(this.cid);
            svc.create(a, c, tran);
            tran.setType(null);
            tran.setComment(null);
            tran.setAmount(0.00F);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Transaction Successful"));
        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account is not selected", "Account is not selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Transaction getTran() {
        return tran;
    }

    public void setTran(Transaction tran) {
        this.tran = tran;
    }

    public List<Transaction> showTransaction() {
        this.cid = uid;
        return svc.findByCustomer(this.cid);
    }
    
    public List<Transaction> showCustomerById()
    {
        return svc.findByCustomer(this.ccid);
    }
    
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

        public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getCcid() {
        return ccid;
    }

    public void setCcid(int ccid) {
        this.ccid = ccid;
    }
    
}
