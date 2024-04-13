package ru.danilkaspirin.reshalo.application.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Entity
@Table(name = "predicate_groups")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PredicateGroupModel {

    @Column(name = "dpgp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(nullable = false)
    Integer weight = 0;

    @OneToMany(mappedBy = "group")
    List<PredicateModel> predicates;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "dtlt_dtlt_id", nullable = false)
    DetailTypeModel detailType;

    @ToString.Exclude
    @OneToOne(mappedBy = "predicate")
    BreakageModel breakage;
}
