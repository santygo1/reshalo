package ru.danilkaspirin.reshalo.application.service;

import ru.danilkaspirin.reshalo.application.dto.DetailDto;
import ru.danilkaspirin.reshalo.application.models.DetailTypeModel;

public interface DetailTypeService {
    DetailTypeModel getDetailType(String detailType);


    // Получение агрегата типа детали по детали
    // Агрегат: тип детали -> группа предикатов -> предикаты
}
