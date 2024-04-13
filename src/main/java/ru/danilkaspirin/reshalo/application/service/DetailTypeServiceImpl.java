package ru.danilkaspirin.reshalo.application.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.danilkaspirin.reshalo.application.exception.detailtype.DetailTypeNotFound;
import ru.danilkaspirin.reshalo.application.models.DetailTypeModel;
import ru.danilkaspirin.reshalo.application.repository.DetailTypeRepository;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class DetailTypeServiceImpl implements DetailTypeService {

    DetailTypeRepository detailTypeRepository;

    @Override
    public DetailTypeModel getDetailType(String detailType) {
        Optional<DetailTypeModel> findBy = detailTypeRepository.findByTypeDef(detailType);
        return findBy.orElseThrow(() -> {
            log.debug("Не удалось найти деталь с типом {}", detailType);
            return new DetailTypeNotFound(detailType);
        });
    }

}
