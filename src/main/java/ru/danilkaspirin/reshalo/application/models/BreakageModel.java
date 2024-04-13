package ru.danilkaspirin.reshalo.application.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "breakages")
@Setter @Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BreakageModel {

    @Column(name = "brkg_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(nullable = false)
    String def;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "dpgp_dpgp_id", referencedColumnName = "dpgp_id", nullable = false)
    PredicateGroupModel predicate;

    @ToString.Exclude
    @OneToMany(mappedBy = "breakage")
    List<RepairActionModel> repairActions;
}
