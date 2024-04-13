package ru.danilkaspirin.reshalo.application.converter;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.danilkaspirin.reshalo.application.dto.DetailTypeDto;
import ru.danilkaspirin.reshalo.application.models.DetailTypeModel;
import ru.danilkaspirin.reshalo.application.models.DetailTypeResponse;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface DetailTypeConverter {
    DetailTypeResponse toResponse(DetailTypeDto dto);
    DetailTypeDto toDto(DetailTypeModel model);
}
