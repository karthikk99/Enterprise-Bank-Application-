/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Customer;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Employee;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.Group;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service class to define User Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Stateless
public class UserService extends AbstractService<User> {

    @PersistenceContext(unitName = "karthikk99PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserService() {
        super(User.class);
    }

    public void createCustomerUser(User u, Customer c) {
        c = getEntityManager().getReference(
                Customer.class,
                c.getId()
        );
        u.setCustomer(c);
        u.setEmployee(null);
        c.setUser(u);
        Group cusgrp = new Group("CUSTOMER", "Customer Group");
        u.addGroup(cusgrp);
        this.create(u);
    }

    public void createEmployeeUser(User u, Employee e) {
        e = getEntityManager().getReference(
                Employee.class,
                e.getId()
        );
        u.setEmployee(e);
        u.setCustomer(null);
        e.setUser(u);
        Group dj = new Group("EMPLOYEE", "Employee Group");
        u.addGroup(dj);
        this.create(u);
    }
    
    public void updateCustomer(User u, Customer c)
    {
        c = getEntityManager().getReference(
                Customer.class,
                c.getId()
        );
        u.setCustomer(c);
        u.setEmployee(null);
        this.update(u);        
    }
    
    public void updateEmployee(User u, Employee e)
    {
        e = getEntityManager().getReference(
                Employee.class,
                e.getId()
        );
        u.setEmployee(e);
        u.setCustomer(null);
        this.update(u);
    }
    
    public void createAdminUser(User u)
    {
        Group dj = new Group("ADMIN", "Administrative Group");
        u.addGroup(dj);
        this.create(u);
    }
    
    @Override
    public List<User> findAll() {
        return getEntityManager().createNamedQuery("User.findAll").getResultList();
    }
    
    public User findByUserName(String UserName)
    {
        return (User)em.createNamedQuery("User.findByUserName",User.class).setParameter("userName", UserName).getSingleResult();
    }
}
