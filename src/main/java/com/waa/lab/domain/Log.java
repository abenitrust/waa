package com.waa.lab.domain;

import com.waa.lab.util.Factory;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private long time; // execution time in ms;
    // TODO: Convert this static name into proper domain
    private String principle = Factory.getPrinciple().getFirstname();
    private String operation;
    
}
