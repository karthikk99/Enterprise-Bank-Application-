/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Account;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Customer;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Transaction;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service class to define Transaction Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Stateless
public class TransactionService extends AbstractService<Transaction> {

    @PersistenceContext(unitName = "karthikk99PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransactionService() {
        super(Transaction.class);
    }

    @Override
    public List<Transaction> findAll() {
        return getEntityManager().createNamedQuery("Bank.findAll").getResultList();
    }

    public List<Transaction> findByCustomer(int id) {
        return getEntityManager().createNamedQuery("Transaction.findForUser").setParameter("id", id).getResultList();
    }

    public void create(Account a, Customer c, Transaction t) {
        a = getEntityManager().getReference(
                Account.class,
                a.getAccountId()
        );
        c = getEntityManager().getReference(
                Customer.class,
                c.getId()
        );
        t.setCustomer(c);
        c.getTransaction().add(t);
        a.getTransaction().add(t);
        this.create(t);
    }
}
