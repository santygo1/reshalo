package ru.danilkaspirin.reshalo.application.controller.manage;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.danilkaspirin.reshalo.application.converter.DetailTypeConverter;
import ru.danilkaspirin.reshalo.application.models.DetailTypeResponse;
import ru.danilkaspirin.reshalo.application.service.DetailTypeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/manage/detailTypes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DetailTypeController {

    DetailTypeService service;
    DetailTypeConverter converter;

    @GetMapping("/allDefs")
    ResponseEntity<List<String>> getAllTypesDefs(){
        List<String> typeDefs = service.getAllDefs();
        return ResponseEntity.ok(typeDefs);
    }

    @GetMapping("/allDefs/{detailTypeDef}")
    ResponseEntity<DetailTypeResponse> getDetailTypeInfo(@PathVariable String detailTypeDef){
        DetailTypeResponse detailTypeResponse = converter.toResponse(service.getDetailTypeDto(detailTypeDef));
        return ResponseEntity.ok(detailTypeResponse);
    }

}
