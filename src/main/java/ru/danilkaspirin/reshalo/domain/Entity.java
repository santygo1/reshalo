package ru.danilkaspirin.reshalo.domain;

import java.util.Map;

public record Entity(Long id, String name, Map<String, Object> characteristics) {
}
