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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 * Model class to define Employee Entity in Banking system
 * @author Karthik Keshav
 */
@Entity
@Table(name = "Employee")
@NamedQueries({
@NamedQuery(name = "Employee.findAll", query = "select e from Employee e"),
@NamedQuery(name = "Employee.findForUserCreation", query = "select e from Employee e where e.user.userName IS NULL")})
public class Employee implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, name = "FName", unique = false, length = 50)
    private String name;
    @Column(nullable = false, name = "LName", unique = false, length = 50)
    private String name2;
    @Column(nullable = false, unique = false, length = 60)
    private String address;
    @Column(nullable = false, unique = false, length = 10)
    private long contactNo;

    @OneToMany(mappedBy = "employee")
    private List<Account> account = new ArrayList<>();
    @OneToMany(mappedBy = "employee")
    private List<Customer> customer = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "bank_id")
    private Bank bank;
        
    @OneToOne(fetch=FetchType.LAZY, mappedBy="employee")
    private User user;

    public Employee(String name, String name2, String address, long contactNo, Bank bank) {
        this.name = name;
        this.name2 = name2;
        this.address = address;
        this.contactNo = contactNo;
        this.bank = bank;
    }

    public Employee() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }
}
