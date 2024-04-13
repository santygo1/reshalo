package ru.danilkaspirin.reshalo.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class DetailResponse {
    public enum State {
        ALL_RIGHT,
        BREAKING
    }

    @Data
    public static class DetailBreakageResponse {
        String def;
        List<DetailRepairResponse> repairActions;
    }

    @Data
    public static class DetailRepairResponse {
        String def;
    }

    String detailType;
    String detailName;

    State state;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<DetailBreakageResponse> breakages;

    public DetailResponse(String detailName, String detailType, List<DetailBreakageResponse> breakages, State state){
        this.detailType = detailType;
        this.breakages = breakages;
        this.state = state;
        this.detailName = detailName;
    }
}
