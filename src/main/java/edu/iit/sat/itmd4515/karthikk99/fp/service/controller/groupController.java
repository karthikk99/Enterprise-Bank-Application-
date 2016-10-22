/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.karthikk99.fp.service.controller;

import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.Group;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.User;
import edu.iit.sat.itmd4515.karthikk99.fp.service.GroupService;
import edu.iit.sat.itmd4515.karthikk99.fp.service.UserService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 * Controller class to define Group Entity database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Named("groupController")
@SessionScoped
public class groupController implements Serializable {

@EJB 
GroupService svc = new GroupService();

public List<Group> showGroup()
{
    return svc.findAll();
}

}
