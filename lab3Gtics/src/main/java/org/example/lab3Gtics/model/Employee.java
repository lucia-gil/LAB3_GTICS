package org.example.lab3Gtics.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {

    @Id
    private Integer employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate hireDate;

    private String jobId;

    private BigDecimal salary;

    private BigDecimal commissionPct;

    private Integer managerId;

    private Integer departmentId;

}