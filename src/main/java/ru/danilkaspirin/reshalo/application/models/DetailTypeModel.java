package ru.danilkaspirin.reshalo.application.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "detail_types")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailTypeModel {

    @Column(name = "dtlt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(unique = true, nullable = false)
    String typeDef;

    @OneToMany(mappedBy = "detailType")
    List<DetailTypeCharacteristicModel> characteristics;

    @ToString.Exclude
    @OneToMany(mappedBy = "detailType")
    List<PredicateGroupModel> predicateGroups;
}
