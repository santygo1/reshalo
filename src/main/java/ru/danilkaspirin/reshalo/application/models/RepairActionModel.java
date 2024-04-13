package ru.danilkaspirin.reshalo.application.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "repair_actions")
@ToString
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepairActionModel {

    @Column(name = "rpac_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String def;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "brkg_brkg_id", nullable = false)
    BreakageModel breakage;
}
