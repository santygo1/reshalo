package ru.danilkaspirin.reshalo.application.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.danilkaspirin.reshalo.application.converter.BreakageConverter;
import ru.danilkaspirin.reshalo.application.converter.DetailConverter;
import ru.danilkaspirin.reshalo.application.dto.BreakageDto;
import ru.danilkaspirin.reshalo.application.dto.DetailDto;
import ru.danilkaspirin.reshalo.application.dto.request.DetailRequest;
import ru.danilkaspirin.reshalo.application.dto.response.DetailResponse;
import ru.danilkaspirin.reshalo.application.service.DetailService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repair")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class RepairController {

    DetailService detailService;
    DetailConverter detailConverter;
    BreakageConverter breakageConverter;

    @PostMapping("/checkRepairs")
    ResponseEntity<DetailResponse> repairDetail(@RequestBody DetailRequest request) {
        log.debug("request: {}", request);
        DetailDto detailDto = detailConverter.toDto(request);
        log.debug("dto: {}", detailDto);

        List<BreakageDto> breakageDtos = detailService.repairDetail(detailDto);

        DetailResponse response = new DetailResponse(
                detailDto.getName(),
                detailDto.getDetailType(),
                breakageDtos.stream().map(breakageConverter::toDetailBreakageResponse).toList(),
                breakageDtos.isEmpty() ? DetailResponse.State.ALL_RIGHT : DetailResponse.State.BREAKING
        );
        log.debug("response: {}", response);
        return ResponseEntity.ok(response);
    }
}
