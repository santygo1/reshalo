package ru.danilkaspirin.reshalo.application.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "detail_type_characteristics")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailTypeCharacteristicModel {

    @Column(name = "dlch_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(nullable = false)
    String name;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "dtlt_dtlt_id", nullable = false)
    DetailTypeModel detailType;
}
