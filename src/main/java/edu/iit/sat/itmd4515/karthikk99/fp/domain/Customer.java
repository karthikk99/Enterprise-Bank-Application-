/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.domain;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 * Model class to define Customer Entity in Banking system
 * @author Karthik Keshav
 */
@Entity
@Table(name = "Customer")
@NamedQueries({
@NamedQuery(name = "Customer.findById", query = "select r from Customer r where r.id = :id"),
@NamedQuery(name = "Customer.findForUserCreation", query = "select r from Customer r where r.user.userName IS NULL"),
@NamedQuery(name = "Customer.findAll", query = "select r from Customer r")})
public class Customer implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, name = "FName", unique = false, length = 30)
    private String name;
    @Column(nullable = false, name = "LName", unique = false, length = 30)
    private String name2;
    @Column(nullable = false, unique = false, length = 60)
    private String address;
    @Column(nullable = false, unique = false)
    private long contactNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "bank_id")
    private Bank bank;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "employee_id")
    private Employee employee;    
    
    @OneToMany(mappedBy = "customer")
    @CascadeOnDelete
    private List<Account> account = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    @CascadeOnDelete
    private List<Transaction> transaction = new ArrayList<>();
    
    @OneToOne(fetch=FetchType.LAZY, mappedBy="customer")
    private User user;
    
    public Customer() {
    }

    public Customer(String name, String name2, String address, long contactNo, Bank bank, Employee employee) {
        this.name = name;
        this.name2 = name2;
        this.address = address;
        this.contactNo = contactNo;
        this.bank = bank;
        this.employee = employee;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
