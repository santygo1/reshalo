package ru.danilkaspirin.reshalo.application.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.danilkaspirin.reshalo.application.dto.RepairActionDto;
import ru.danilkaspirin.reshalo.application.dto.response.DetailResponse;
import ru.danilkaspirin.reshalo.application.models.RepairActionModel;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface RepairActionConverter {

    @Mapping(ignore = true, target = "breakage")
    RepairActionDto toDto(RepairActionModel repairActionModel);

    DetailResponse.DetailRepairResponse toDetailRepairResponse(RepairActionDto repairActionDto);
}
