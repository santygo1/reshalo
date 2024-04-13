package ru.danilkaspirin.reshalo.application.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.danilkaspirin.reshalo.application.repository.VariableRepository;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NonCacheableVariableService implements VariableService {

    VariableRepository repository;

    @Override
    public Map<String, Object> getVariablePool() {
        log.debug("Получение общих переменных");
        Map<String, Object> variablePool = new HashMap<>();
        for (var var : repository.findAll()) {
            variablePool.put(var.getKey(), var.getValue());
        }
        log.debug("Переменные: {}", variablePool);
        return variablePool;
    }
}
