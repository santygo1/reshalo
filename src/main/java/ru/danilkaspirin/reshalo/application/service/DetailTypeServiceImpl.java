package ru.danilkaspirin.reshalo.application.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.danilkaspirin.reshalo.application.converter.DetailConverter;
import ru.danilkaspirin.reshalo.application.converter.DetailTypeConverter;
import ru.danilkaspirin.reshalo.application.dto.DetailTypeDto;
import ru.danilkaspirin.reshalo.application.exception.detailtype.DetailTypeNotFound;
import ru.danilkaspirin.reshalo.application.models.DetailTypeModel;
import ru.danilkaspirin.reshalo.application.repository.DetailTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class DetailTypeServiceImpl implements DetailTypeService {

    DetailTypeRepository detailTypeRepository;
    DetailTypeConverter converter;

    @Override
    public DetailTypeModel getDetailTypeModel(String detailType) {
        Optional<DetailTypeModel> findBy = detailTypeRepository.findByTypeDef(detailType);
        return findBy.orElseThrow(() -> {
            log.debug("Не удалось найти деталь с типом {}", detailType);
            return new DetailTypeNotFound(detailType);
        });
    }

    @Override
    public DetailTypeDto getDetailTypeDto(String detailTypeDef) {
        return converter.toDto(getDetailTypeModel(detailTypeDef));
    }

    @Override
    public List<String> getAllDefs() {
        return detailTypeRepository.findAllDefs();
    }

}
