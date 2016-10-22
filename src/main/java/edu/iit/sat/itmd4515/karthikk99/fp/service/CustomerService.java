/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Bank;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Customer;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service class to define Customer Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Stateless
public class CustomerService extends AbstractService<Customer> {

    @PersistenceContext(unitName = "karthikk99PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void create(Customer c, Bank b, Employee e) {
        b = getEntityManager().getReference(
                Bank.class,
                b.getId()
        );
        e = getEntityManager().getReference(
                Employee.class,
                e.getId()
        );
        c.setBank(b);
        c.setEmployee(e);
        b.getCustomer().add(c);
        e.getCustomer().add(c);
        getEntityManager().persist(c);
    }

    public CustomerService() {
        super(Customer.class);
    }

        public void update(Customer c, Bank b, Employee e) {
        b = getEntityManager().getReference(
                Bank.class,
                b.getId()
        );
        e = getEntityManager().getReference(
                Employee.class,
                e.getId()
        );
        c.setBank(b);
        c.setEmployee(e);
        b.getCustomer().add(c);
        e.getCustomer().add(c);
        getEntityManager().merge(c);
    }
        
    @Override
    public List<Customer> findAll() {
        return getEntityManager().createNamedQuery("Customer.findAll").getResultList();
    }

    public List<Customer> findUserCustomer() {
        return getEntityManager().createNamedQuery("Customer.findForUserCreation").getResultList();
    }
    
    public Customer findUser(String uName)
    {
        return (Customer)getEntityManager().createNamedQuery("Customer.findCustomerId").getSingleResult();
    }
}
