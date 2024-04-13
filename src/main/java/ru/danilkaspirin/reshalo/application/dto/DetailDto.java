package ru.danilkaspirin.reshalo.application.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.danilkaspirin.reshalo.application.models.DetailTypeModel;

import java.util.Map;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailDto {
    String name;
    String detailType;
    Map<String, Object> characteristics;
}
