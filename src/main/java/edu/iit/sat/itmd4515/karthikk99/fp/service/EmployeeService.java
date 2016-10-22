/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Bank;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service class to define Employee Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Stateless
public class EmployeeService extends AbstractService<Employee> {

    @PersistenceContext(unitName = "karthikk99PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void create(Employee e, Bank b) {
        b = getEntityManager().getReference(
                Bank.class,
                b.getId()
        );
        e.setBank(b);
        b.getEmployee().add(e);
        getEntityManager().persist(e);
    }

    public void update(Employee e, Bank b) {
        b = getEntityManager().getReference(
                Bank.class,
                b.getId()
        );
        e.setBank(b);
        b.getEmployee().add(e);
        getEntityManager().merge(e);
    }

    public EmployeeService() {
        super(Employee.class);
    }

    @Override
    public List<Employee> findAll() {
        return getEntityManager().createNamedQuery("Employee.findAll").getResultList();
    }

    public List<Employee> findUserEmployee() {
        return getEntityManager().createNamedQuery("Employee.findForUserCreation").getResultList();
    }

    public Employee findUser(String uName) {
        return (Employee) getEntityManager().createNamedQuery("Employee.findEmployeeId").getSingleResult();
    }
}
