package ru.danilkaspirin.reshalo.application.converter;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.danilkaspirin.reshalo.application.dto.request.DetailRequest;
import ru.danilkaspirin.reshalo.domain.Entity;
import ru.danilkaspirin.reshalo.application.dto.DetailDto;

import java.util.HashMap;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {RepairActionConverter.class}
)
public interface DetailConverter {
    DetailDto toDto(DetailRequest request);
    Entity toEntity(DetailDto detailDto);
}
