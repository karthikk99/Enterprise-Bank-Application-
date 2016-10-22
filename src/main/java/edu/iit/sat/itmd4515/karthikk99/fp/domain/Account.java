/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Model class to define Account Entity in Banking system
 *
 * @author Karthik Keshav
 */
@Entity
@Table(name = "Account")
@NamedQueries({
@NamedQuery(name = "Account.findByCustomer", query = "select a from Account a where a.customer.id = :id"),
@NamedQuery(name = "Account.findAll", query = "select a from Account a")})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private int accountId; //Account ID
    @Column(name = "type", nullable = false)
    private String type; //Account Type
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "bank_id")
    private Bank bank;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "employee_id")
    private Employee employee;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ACCNT_TRAN", joinColumns = @JoinColumn(name = "accntId"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private List<Transaction> transaction = new ArrayList<>();
    
    @Column(name = "balance", nullable = true)
    private int balance;
    /**
     * Default constructor
     */
    public Account() {
        balance = 0;
    }

    public Account(String type, Customer customer, Bank bank, Employee employee, int balance) {
        this.type = type;
        this.customer = customer;
        this.bank = bank;
        this.employee = employee;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", customer=" + customer + '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
