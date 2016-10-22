/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service.controller;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Account;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Bank;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Customer;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Employee;
import edu.iit.sat.itmd4515.karthikk99.fp.service.AccountService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.BankService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.CustomerService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.EmployeeService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import static edu.iit.sat.itmd4515.karthikk99.fp.web.LoginBean.uid;

/**
 * Controller class to define Account Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Named
@SessionScoped
public class AccountController implements Serializable {

    private Account account = new Account();
    private Account eaccount = new Account();

    private Employee emp = new Employee();

    private Bank bank = new Bank();

    private Customer customer = new Customer();

    private List<Integer> emps;

    private List<Integer> banks;

    private List<Integer> custs;

    @EJB
    AccountService svc;
    @EJB
    EmployeeService esvc;
    @EJB
    BankService bsvc;
    @EJB
    CustomerService csvc;

    private int cid;

    private int bid;

    private int eid;

    private String type;
    
    private int uuid;

    public AccountController() {
        uuid = uid;
    }

    public void createAccount() {
        if (cid >= 1 && bid >= 1 && eid >= 1) {
            Customer c = csvc.find(cid);
            Bank b = bsvc.find(bid);
            Employee e = esvc.find(eid);
            svc.create(c, b, e, account);
            account.setBank(null);
            account.setCustomer(null);
            account.setEmployee(null);
            account.setType(null);
            c = null;
            b = null;
            e = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Account created successfully"));
        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data is not selected", "Data is not selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Account> showAllAccount() {
        return svc.findAll();
    }

    public List<Account> showAccount() {
        return svc.findAccountUser(this.uuid);
    }

    public void deleteAccount() {
        try{
        if (this.account.getAccountId() >= 1) {
            svc.deleteById(this.account.getAccountId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Account of a customer is deleted successfully"));
        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Account ID not selected", "Account ID is not selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        }catch(Exception e)
        {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error deleting account. Check if the data dependent on the account details are deleted first.", "Error deleting account. Check if the data dependent on the account details are deleted first.");
            FacesContext.getCurrentInstance().addMessage(null, msg);            
        }
    }

    public List<Integer> showbanks() {
        List<Bank> bList = bsvc.findAll();
        for (int i = 0; i <= bList.size(); i++) {
            banks.add(i, bList.get(i).getId());
        }
        return banks;
    }

    public List<Integer> showcusts() {
        List<Customer> cList = csvc.findAll();
        for (int i = 0; i <= cList.size(); i++) {
            custs.add(i, cList.get(i).getId());
        }
        return custs;
    }

    public void editAccount() {
        if (eaccount.getAccountId() >= 1 && cid >= 1 && bid >= 1 && eid >= 1) {
            Customer c = csvc.find(cid);
            Bank b = bsvc.find(bid);
            Employee e = esvc.find(eid);
            svc.update(c, b, e, eaccount);
            eaccount.setBank(null);
            eaccount.setCustomer(null);
            eaccount.setEmployee(null);
            eaccount.setType(null);
            c = null;
            b = null;
            e = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Account details updated successfully"));
        } else {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data not selected", "Data is not selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Account getEaccount() {
        return eaccount;
    }

    public void setEaccount(Account eaccount) {
        this.eaccount = eaccount;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }
}
