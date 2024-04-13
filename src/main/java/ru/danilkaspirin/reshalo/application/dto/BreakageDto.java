package ru.danilkaspirin.reshalo.application.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.danilkaspirin.reshalo.application.models.PredicateGroupModel;
import ru.danilkaspirin.reshalo.application.models.RepairActionModel;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BreakageDto {
    Long id;

    String def;

    PredicateGroupModel predicate;

    List<RepairActionDto> repairActions;
}
