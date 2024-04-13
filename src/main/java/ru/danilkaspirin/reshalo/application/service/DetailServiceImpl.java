package ru.danilkaspirin.reshalo.application.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.danilkaspirin.reshalo.application.converter.BreakageConverter;
import ru.danilkaspirin.reshalo.application.converter.DetailConverter;
import ru.danilkaspirin.reshalo.application.dto.BreakageDto;
import ru.danilkaspirin.reshalo.application.dto.DetailDto;
import ru.danilkaspirin.reshalo.application.exception.detailtype.DetailCharacteristicsNotFound;
import ru.danilkaspirin.reshalo.application.exception.detailtype.DetailUselessCharacteristicsFound;
import ru.danilkaspirin.reshalo.application.models.BreakageModel;
import ru.danilkaspirin.reshalo.application.models.DetailTypeCharacteristicModel;
import ru.danilkaspirin.reshalo.application.models.DetailTypeModel;
import ru.danilkaspirin.reshalo.application.models.PredicateGroupModel;
import ru.danilkaspirin.reshalo.application.repository.BreakageRepository;
import ru.danilkaspirin.reshalo.application.repository.PredicateGroupRepository;
import ru.danilkaspirin.reshalo.application.util.PredicateConverter;
import ru.danilkaspirin.reshalo.domain.*;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DetailServiceImpl implements DetailService {

    DetailTypeService detailTypeService;
    VariableService variableService;

    PredicateGroupRepository predicateGroupRepository;
    BreakageRepository breakageRepository;

    BreakageConverter breakageConverter;
    PredicateConverter predicateConverter;
    DetailConverter detailConverter;

    @Override
    public List<BreakageDto> repairDetail(DetailDto detailDto) {
        log.debug("Проверка необходимости ремонта детали: {}", detailDto);
        log.debug("Этап 1: Определение соответствия указанного типа детали и передаваемой детали");

        // Получаем тип детали вместе с характеристиками
        DetailTypeModel detailType = getDetailType(detailDto);

        Entity entity = detailConverter.toEntity(detailDto);

        // Получаем переменные для подстановки в шаблоны предикатов
        Map<String, Object> variablePool = variableService.getVariablePool();

        // Получаем предикаты для определенной детали, по убыванию весов
        List<PredicateGroupModel> predicateGroups =
                predicateGroupRepository.findByDetailTypeOrderByWeightDesc(detailType.getTypeDef());

        log.debug("Группы предикатов:");
        // Преобразуем предикаты из базы данных в предикаты доменной логики
        List<EntityPredicateGroup> groups = predicateGroups.stream().map(
                (p) -> new EntityPredicateGroup(
                        p.getId(),
                        p.getPredicates().stream().map(p1 -> {
                            // Преобразуем в предикат для домена
                            log.debug("\t{}", p);
                            return new EntityPredicate(p1.getId(), predicateConverter.toPredicate(p1.getConditionTemplate(), variablePool));
                        }).toList(),
                        breakageConverter.toEntity(p.getBreakage())
                )
        ).toList();

        // Создаем решатель задач основанный на данных
        Solver solver = new SolverImpl(groups);

        // Решаем задачу является ли деталь испорченной, и опционально возвращаем действия для ремонта,
        List<Long> breakageIds = solver.determineBreakages(entity).stream().map(Breakage::id).toList();

        List<BreakageModel> breakages = breakageRepository.findAllByIdIn(breakageIds);

        // Подставляем переменные в текст решений
        breakages.forEach((b) -> b.getRepairActions().forEach((repair -> {
            if(repair.getDef().contains("$")){
                for (Map.Entry<String, Object> entry : variablePool.entrySet()) {
                    repair.setDef(repair.getDef().replaceAll("[$][{]" + entry.getKey() + "[}]", String.valueOf(entry.getValue())));
                }
            }
        })));

        breakages.forEach(b -> log.debug("Найденные решения {}", b.getRepairActions()));


        return breakages.stream().map(breakageConverter::toDto).toList();
    }

    // Проверяет соответствие переданной детали к типу детали, если деталь соответствует true
    // иначе ошибки
    private DetailTypeModel getDetailType(DetailDto detail) {
        if (detail == null)
            throw new RuntimeException("Не определена деталь");

        DetailTypeModel detailTypeModel = detailTypeService.getDetailType(detail.getDetailType());
        log.debug("Проверка соответствия детали {} типу {}", detail, detail.getDetailType());

        checkCharacteristics(detail, detailTypeModel);

        // Все проверки прошли значит передаваемая деталь подходит под тип детали
        log.debug("Деталь {} соответствует типу {}", detail, detail.getDetailType());
        return detailTypeModel;
    }

    private void checkCharacteristics(DetailDto detail, DetailTypeModel detailTypeModel) {
        Map<String, Object> detailCharacteristics = detail.getCharacteristics();
        // Если количество характеристик детали не соответствует количеству характеристик передаваемой детали
        if (detailCharacteristics.keySet().size() != detailTypeModel.getCharacteristics().size()) {
            log.debug("Количество характеристик детали({}) не соответствует количеству характеристик для типа детали({})", detail.getCharacteristics().size(), detail.getDetailType());
            throw new DetailUselessCharacteristicsFound(
                    detail.getDetailType(),
                    detailTypeModel.getCharacteristics().stream()
                            .map(DetailTypeCharacteristicModel::getName)
                            .collect(Collectors.toSet()));
        }

        // Проверяем что все характеристики, определенные в типе детали, определены для передаваемой детали
        // Если какая-либо характеристика не определена возвращаем какие вместе с ошибкой
        Stack<String> notFoundedCharacteristics = new Stack<>();
        for (var characteristic : detailTypeModel.getCharacteristics()) {
            Object founded = detailCharacteristics.get(characteristic.getName());
            if (founded == null) {
                notFoundedCharacteristics.push(characteristic.getName());
            }
        }
        if (!notFoundedCharacteristics.empty()) {
            log.debug("Для детали не были определены следующие характеристики: {}", notFoundedCharacteristics);
            throw new DetailCharacteristicsNotFound(detail.getDetailType(), notFoundedCharacteristics);
        }
    }
}
