package ru.danilkaspirin.reshalo.application.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.danilkaspirin.reshalo.application.models.BreakageModel;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepairActionDto {

    Long id;

    String def;

    @ToString.Exclude
    BreakageDto breakage;
}
