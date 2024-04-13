package ru.danilkaspirin.reshalo.application.converter;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.danilkaspirin.reshalo.application.dto.BreakageDto;
import ru.danilkaspirin.reshalo.application.dto.response.DetailResponse;
import ru.danilkaspirin.reshalo.application.models.BreakageModel;
import ru.danilkaspirin.reshalo.domain.Breakage;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {RepairActionConverter.class}
)
public interface BreakageConverter {
    BreakageDto toDto(Breakage breakage);

    BreakageDto toDto(BreakageModel breakageModel);

    Breakage toEntity(BreakageModel breakageModel);

    DetailResponse.DetailBreakageResponse toDetailBreakageResponse(BreakageDto breakageDto);
}
