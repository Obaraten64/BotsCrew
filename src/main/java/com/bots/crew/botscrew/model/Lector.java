package com.bots.crew.botscrew.model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private Position position;
    @ManyToOne
    @JoinColumn(name = "department", referencedColumnName = "id")
    private Department department;
    private Double salary;
}
