package org.example.lab3Gtics.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job {

    @Id
    private String jobId;

    private String jobTitle;

    private BigDecimal minSalary;

    private BigDecimal maxSalary;

    private Integer departmentId;

}

