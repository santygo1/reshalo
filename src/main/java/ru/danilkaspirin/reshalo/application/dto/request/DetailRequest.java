package ru.danilkaspirin.reshalo.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class DetailRequest {
    String name;

    @JsonProperty(value = "type")
    String detailType;

    Map<String, Object> characteristics;
}
