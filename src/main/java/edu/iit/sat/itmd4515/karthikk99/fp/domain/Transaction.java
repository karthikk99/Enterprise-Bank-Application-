/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Model class to define Transaction Entity in Banking system
 * @author Karthik Keshav
 */
@Entity
@Table(name = "Transaction")
@NamedQueries({
@NamedQuery(name = "Transaction.findForUser", query = "select t from Transaction t where t.customer.id = :id")})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    @Column(name = "TransactionType")
    private String type;
    @Column(name = "amount", nullable = false)
    private float amount;
    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;

    @ManyToMany(mappedBy = "transaction")
    private List<Account> account = new ArrayList<>();

    @Column(name = "memo", nullable = false)
    private String comment;

    public Transaction() {
    }

    public Transaction(String type, float amount, Date timestamp, Customer customer, String comment) {
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.customer = customer;
        this.comment = comment;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transactionId=" + transactionId + ", type=" + type + ", amount=" + amount + ", customer=" + customer + ", account=" + account + '}';
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
