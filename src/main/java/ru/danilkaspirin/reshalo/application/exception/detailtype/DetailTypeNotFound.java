package ru.danilkaspirin.reshalo.application.exception.detailtype;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailTypeNotFound extends RuntimeException{

    String typeDef;

    // TODO: message должен быть определен не здесь
    public DetailTypeNotFound(String typeDef){
        super(String.format("Не удалось обнаружить подходящую деталь с типом %s", typeDef));
        this.typeDef = typeDef;
    }
}
