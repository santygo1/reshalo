package ru.danilkaspirin.reshalo.application.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "variables")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VariableModel {

    @Column(name = "var_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name = "\"key\"", unique = true)
    String key;


    @Column(name = "\"value\"")
    Double value;
}
