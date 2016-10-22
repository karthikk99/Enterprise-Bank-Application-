/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Account;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Bank;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Customer;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Employee;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Transaction;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service class to define Account Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Stateless
public class AccountService extends AbstractService<Account> {

    @PersistenceContext(unitName = "karthikk99PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountService() {
        super(Account.class);
    }

    @Override
    public List<Account> findAll() {
        return getEntityManager().createNamedQuery("Account.findAll").getResultList();
    }

    public void create(Customer c, Bank b, Employee e, Account a) {
        b = getEntityManager().getReference(
                Bank.class,
                b.getId()
        );
        c = getEntityManager().getReference(
                Customer.class,
                c.getId()
        );
        e = getEntityManager().getReference(
                Employee.class,
                e.getId()
        );
        a.setBank(b);
        a.setCustomer(c);
        a.setEmployee(e);
        b.getAccount().add(a);
        c.getAccount().add(a);
        e.getAccount().add(a);
        getEntityManager().persist(a);
    }
    
    public void update(Customer c, Bank b, Employee e, Account a)
    {
        b = getEntityManager().getReference(
                Bank.class,
                b.getId()
        );
        c = getEntityManager().getReference(
                Customer.class,
                c.getId()
        );
        e = getEntityManager().getReference(
                Employee.class,
                e.getId()
        );
        a.setBank(b);
        a.setCustomer(c);
        a.setEmployee(e);
        b.getAccount().add(a);
        c.getAccount().add(a);
        e.getAccount().add(a);
        this.update(a);
    }
    
    public List<Account> findAccountUser(int id)
    {
       return getEntityManager().createNamedQuery("Account.findByCustomer").setParameter("id", id).getResultList();
    }
}
