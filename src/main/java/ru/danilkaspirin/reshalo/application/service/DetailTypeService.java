package ru.danilkaspirin.reshalo.application.service;

import ru.danilkaspirin.reshalo.application.dto.DetailTypeDto;
import ru.danilkaspirin.reshalo.application.models.DetailTypeModel;

import java.util.List;

public interface DetailTypeService {
    DetailTypeModel getDetailTypeModel(String detailTypeDef);
    DetailTypeDto getDetailTypeDto(String detailTypeDef);

    List<String> getAllDefs();


    // Получение агрегата типа детали по детали
    // Агрегат: тип детали -> группа предикатов -> предикаты
}
