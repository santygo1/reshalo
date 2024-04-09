package ru.danilkaspirin.reshalo.domain;

import java.util.Map;

public record Detail(String id, String name, Map<String, Object> characteristics) {
}
