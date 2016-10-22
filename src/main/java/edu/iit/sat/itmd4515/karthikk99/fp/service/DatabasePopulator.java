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
import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.Group;
import edu.iit.sat.itmd4515.karthikk99.fp.domain.security.User;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Service class to define  database operations in Banking system
 *
 * @author Karthik Keshav
 */
@Singleton
@Startup
public class DatabasePopulator {

    @EJB
    private UserService userService;

    @EJB
    private GroupService groupService;

    @EJB
    private BankService bankService;

    @EJB
    private CustomerService customerService;

    @EJB
    private EmployeeService employeeService;
    
    @EJB
    private AccountService accountService;

    public DatabasePopulator() {
    }

    @PostConstruct
    private void seedDatabase() {
        //Groups
        Group customerGroup = new Group("CUSTOMER", "Customer Group");
        Group employeeGroup = new Group("EMPLOYEE", "Employee Group");
        Group adminGroup = new Group("ADMIN", "Administrative Group");

        // Users - Admin
        User admin = new User("admin", "admin");

        // Users - Customer
        User customer = new User("james", "xyz123");
        User cust1 = new User("selena", "aba111");
        User cust2 = new User("katherine", "aba111");
        User cust3 = new User("michel", "abc111");
        User cust4 = new User("karthik", "aca111");

        // Users - Employee
        User employee = new User("charlie", "abc123");
        User emp1 = new User("carlson", "aaa111");
        User emp2 = new User("jeremy", "aca111");

        // Customer Group assign
        customer.addGroup(customerGroup);
        cust1.addGroup(customerGroup);
        cust2.addGroup(customerGroup);
        cust3.addGroup(customerGroup);
        cust4.addGroup(customerGroup);

        // Employee Group assign
        employee.addGroup(employeeGroup);
        emp1.addGroup(employeeGroup);
        emp2.addGroup(employeeGroup);

        // Admin Group assign
        admin.addGroup(adminGroup);

        // Bank creation
        Bank bank = new Bank("Bank1", "address1", 2323432);
        Bank bank2 = new Bank("Bank2", "address2", 2323432);
        Bank bank3 = new Bank("Bank3", "address3", 2323432);

        bankService.create(bank);
        bankService.create(bank2);
        bankService.create(bank3);

        // Employee Creation
        Employee e = new Employee("Karthik", "Keshav", "555 E Chicago", 2222222, bank);
        Employee e1 = new Employee("Kaushik", "Krishna", "23333 California", 22222, bank2);
        Employee e2 = new Employee("James", "Joy", "333 Washington", 22222, bank3);

        employeeService.create(e);
        employeeService.create(e1);
        employeeService.create(e2);

        bank.getEmployee().add(e);
        bank2.getEmployee().add(e1);
        bank3.getEmployee().add(e2);

        // Customer creation
        Customer c = new Customer("Shashank", "Bhat", "Chicago", 222222, bank, e);
        Customer c1 = new Customer("Rakshith", "Gowda", "Chicago", 222222, bank2, e1);
        Customer c2 = new Customer("Deepak", "Aithal", "California", 222222, bank2, e1);
        Customer c3 = new Customer("Kaushik", "Keshav", "California", 222222, bank3, e2);
        Customer c4 = new Customer("Sachin", "Krishna", "Ohio", 222222, bank3, e2);

        customerService.create(c);
        customerService.create(c1);
        customerService.create(c2);
        customerService.create(c3);
        customerService.create(c4);

        // Assign customers to bank
        bank.getCustomer().add(c);
        bank2.getCustomer().add(c1);
        bank2.getCustomer().add(c2);
        bank3.getCustomer().add(c3);
        bank3.getCustomer().add(c4);

        e.getCustomer().add(c);
        e1.getCustomer().add(c1);
        e1.getCustomer().add(c2);
        e2.getCustomer().add(c3);
        e2.getCustomer().add(c4);

        customer.setCustomer(c);
        cust1.setCustomer(c1);
        cust2.setCustomer(c2);
        cust3.setCustomer(c3);
        cust4.setCustomer(c4);

        employee.setEmployee(e);
        emp1.setEmployee(e1);
        emp2.setEmployee(e2);

        groupService.create(customerGroup);
        groupService.create(employeeGroup);
        groupService.create(adminGroup);

        userService.create(customer);
        userService.create(cust1);
        userService.create(cust2);
        userService.create(cust3);
        userService.create(cust4);

        userService.create(employee);
        userService.create(emp1);
        userService.create(emp2);

        userService.create(admin);

        Account a = new Account("savings-account", c, bank, e, 1000);
        Account b = new Account("Deposit-account", c, bank, e, 2000);
        Account ca = new Account("savings-account", c1, bank2, e1, 1000);
        Account d = new Account("Deposit-account", c1, bank2, e1, 2000);
        Account ea = new Account("savings-account", c2, bank2, e1, 1000);
        Account f = new Account("Deposit-account", c2, bank2, e1, 2000);
        Account g = new Account("savings-account", c3, bank3, e2, 1000);
        Account fa = new Account("Deposit-account", c3, bank3, e2, 2000);
        Account i = new Account("savings-account", c4, bank3, e2, 1000);
        Account j = new Account("Deposit-account", c4, bank3, e2, 2000);
        
        
        c.getAccount().add(a);
        c.getAccount().add(b);

        c1.getAccount().add(ca);
        c1.getAccount().add(d);

        c2.getAccount().add(ea);
        c2.getAccount().add(f);

        c3.getAccount().add(g);
        c3.getAccount().add(fa);

        c4.getAccount().add(i);
        c4.getAccount().add(j);

        bank.getAccount().add(a);
        bank.getAccount().add(b);
        bank2.getAccount().add(ca);
        bank2.getAccount().add(d);
        bank2.getAccount().add(ea);
        bank2.getAccount().add(f);
        bank3.getAccount().add(g);
        bank3.getAccount().add(fa);
        bank3.getAccount().add(i);
        bank3.getAccount().add(j);

        e.getAccount().add(a);
        e.getAccount().add(b);
        e1.getAccount().add(ca);
        e1.getAccount().add(d);
        e1.getAccount().add(ea);
        e1.getAccount().add(f);
        e2.getAccount().add(g);
        e2.getAccount().add(fa);
        e2.getAccount().add(i);
        e2.getAccount().add(j);
        
        accountService.create(a);
        accountService.create(b);
        accountService.create(ca);
        accountService.create(d);
        accountService.create(ea);
        accountService.create(f);
        accountService.create(g);
        accountService.create(fa);
        accountService.create(i);
        accountService.create(j);
        
        Transaction t1 = new Transaction("Debit", 22.22F, new Date(), c, "new comment");
        Transaction t2 = new Transaction("Credit", 212.22F, new Date(), c, "new comment2");
        Transaction t3 = new Transaction("Credit", 322.22F, new Date(), c1, "new comment");
        Transaction t4 = new Transaction("Credit", 142.22F, new Date(), c1, "new comment2");
        Transaction t5 = new Transaction("Debit", 226.22F, new Date(), c1, "new comment");
        Transaction t6 = new Transaction("Credit", 152.22F, new Date(), c2, "new comment2");
        Transaction t7 = new Transaction("Credit", 227.22F, new Date(), c2, "new comment");
        Transaction t8 = new Transaction("Credit", 212.22F, new Date(), c2, "new comment2");
        Transaction t9 = new Transaction("Debit", 242.22F, new Date(), c3, "new comment");
        Transaction t10 = new Transaction("Credit", 12.22F, new Date(), c3, "new comment2");
        Transaction t11 = new Transaction("Debit", 232.22F, new Date(), c3, "new comment");
        Transaction t12 = new Transaction("Credit", 1222.22F, new Date(), c3, "new comment2");
        Transaction t13 = new Transaction("Credit", 242.22F, new Date(), c, "new comment");
        Transaction t14 = new Transaction("Credit", 162.22F, new Date(), c, "new comment2");
        Transaction t15 = new Transaction("Debit", 227.22F, new Date(), c4, "new comment");
        Transaction t16 = new Transaction("Credit", 142.22F, new Date(), c4, "new comment2");
        Transaction t17 = new Transaction("Debit", 228.22F, new Date(), c4, "new comment");
        Transaction t18 = new Transaction("Debit", 132.22F, new Date(), c4, "new comment2");
        Transaction t19 = new Transaction("Debit", 222.22F, new Date(), c4, "new comment");
        Transaction t20 = new Transaction("Debit", 1552.22F, new Date(), c2, "new comment2");
        Transaction t21 = new Transaction("Debit", 2772.22F, new Date(), c2, "new comment");
        Transaction t22 = new Transaction("Credit", 132.22F, new Date(), c, "new comment2");
        Transaction t23 = new Transaction("Credit", 222.22F, new Date(), c2, "new comment");
        Transaction t24 = new Transaction("Credit", 172.22F, new Date(), c, "new comment2");
        Transaction t25 = new Transaction("Debit", 822.22F, new Date(), c2, "new comment");
        Transaction t26 = new Transaction("Credit", 512.22F, new Date(), c1, "new comment2");

        a.getTransaction().add(t1);
        b.getTransaction().add(t2);
        ca.getTransaction().add(t3);
        d.getTransaction().add(t4);
        ca.getTransaction().add(t5);
        ea.getTransaction().add(t6);
        f.getTransaction().add(t7);
        ea.getTransaction().add(t8);
        g.getTransaction().add(t9);
        g.getTransaction().add(t10);
        fa.getTransaction().add(t11);
        fa.getTransaction().add(t12);
        a.getTransaction().add(t13);
        a.getTransaction().add(t14);
        i.getTransaction().add(t15);
        i.getTransaction().add(t16);
        j.getTransaction().add(t17);
        i.getTransaction().add(t18);
        j.getTransaction().add(t19);
        ea.getTransaction().add(t20);
        f.getTransaction().add(t21);
        a.getTransaction().add(t22);
        ea.getTransaction().add(t23);
        a.getTransaction().add(t24);
        ea.getTransaction().add(t25);
        d.getTransaction().add(t26);
        
    }
}
