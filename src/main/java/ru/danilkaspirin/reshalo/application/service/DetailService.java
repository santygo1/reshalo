package ru.danilkaspirin.reshalo.application.service;

import ru.danilkaspirin.reshalo.application.dto.DetailDto;
import ru.danilkaspirin.reshalo.application.dto.BreakageDto;

import java.util.List;

public interface DetailService {
    List<BreakageDto> repairDetail(DetailDto detail);

}
