package ru.danilkaspirin.reshalo.application.dto;

import lombok.Data;
import ru.danilkaspirin.reshalo.application.models.DetailTypeCharacteristicModel;
import ru.danilkaspirin.reshalo.application.models.PredicateGroupModel;

import java.util.List;

@Data
public class DetailTypeDto {

    String typeDef;

    List<DetailTypeCharacteristicModel> characteristics;

    List<PredicateGroupModel> predicateGroups;
}
