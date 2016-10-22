/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Bank;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service class to define Bank Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Stateless
public class BankService extends AbstractService<Bank> {

    @PersistenceContext(unitName = "karthikk99PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BankService() {
        super(Bank.class);
    }

    @Override
    public List<Bank> findAll() {
        return getEntityManager().createNamedQuery("Bank.findAll").getResultList();
    }
}
