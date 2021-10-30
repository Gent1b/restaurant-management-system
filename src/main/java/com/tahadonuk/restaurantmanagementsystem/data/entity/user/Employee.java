package com.tahadonuk.restaurantmanagementsystem.data.entity.user;

import com.sun.istack.NotNull;
import com.tahadonuk.restaurantmanagementsystem.data.EmployeeRole;
import com.tahadonuk.restaurantmanagementsystem.dto.Address;
import com.tahadonuk.restaurantmanagementsystem.dto.Email;
import com.tahadonuk.restaurantmanagementsystem.dto.Name;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employees")
@Data
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private long employeeId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "firstName", column = @Column(name = "EMPLOYEE_FIRST_NAME")),
            @AttributeOverride( name = "lastName", column = @Column(name = "EMPLOYEE_LAST_NAME"))
    })
    private Name name;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "email", column = @Column(name = "EMPLOYEE_EMAIL"))
    })
    private Email email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "NATIONALITY")
    private String nationality;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "street", column = @Column(name = "STREET")),
            @AttributeOverride( name = "address", column = @Column(name = "ADDRESS"))
    })
    private Address address;

    @Column(name = "ROLE")
    private EmployeeRole role;

    @Column(name = "SALARY")
    private double salary;

    @Column(name = "JOIN_DATE")
    private Date joinDate;

    @Column("PASSWORD")
    private char[] password;
}
