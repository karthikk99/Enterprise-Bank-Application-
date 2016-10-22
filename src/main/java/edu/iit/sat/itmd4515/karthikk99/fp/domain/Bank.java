/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Model class to define Bank Entity in Banking system
 * @author Karthik Keshav
 */
@Entity
@Table(name = "Bank")
@NamedQueries({
@NamedQuery(name = "Bank.findAll", query = "select b from Bank b")})
public class Bank implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = false, length = 50)
    private String name;
    @Column(nullable = false, unique = false, length = 50)
    private String address;
    @Column(nullable = false, unique = false, length = 10)
    private long contactNo;

    @OneToMany(mappedBy = "bank")
    private List<Employee> employee = new ArrayList<>();
    @OneToMany(mappedBy = "bank")
    private List<Customer> customer = new ArrayList<>();
    @OneToMany(mappedBy = "bank")
    private List<Account> account = new ArrayList<>();

    public Bank() {
    }

    public Bank(String name, String address, long contactNo) {
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
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

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Bank{" + "id=" + id + ", name=" + name + ", address=" + address + ", contactNo=" + contactNo + ", employee=" + employee + ", customer=" + customer + ", account=" + account + '}';
    }

}
