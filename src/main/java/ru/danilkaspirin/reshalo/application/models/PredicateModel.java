package ru.danilkaspirin.reshalo.application.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "predicates")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PredicateModel {

    @Column(name = "pdcs_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(columnDefinition = "text")
    String conditionTemplate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "dpgp_dpgp_id", nullable = false)
    PredicateGroupModel group;

}
