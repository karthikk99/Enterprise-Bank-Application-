/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.Account;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.Group;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service class to define Group Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Stateless
public class GroupService extends AbstractService<Group>{

@PersistenceContext(unitName = "karthikk99PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupService() {
        super(Group.class);
    }

    @Override
    public List<Group> findAll() {
        return getEntityManager().createNamedQuery("Group.findAll").getResultList();
    }
    
}
