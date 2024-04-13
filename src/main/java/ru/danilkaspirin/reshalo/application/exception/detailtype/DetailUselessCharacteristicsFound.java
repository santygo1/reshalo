package ru.danilkaspirin.reshalo.application.exception.detailtype;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetailUselessCharacteristicsFound extends RuntimeException {

    Iterable<String> nonUselessCharacteristics;

    public DetailUselessCharacteristicsFound(String detailType, Iterable<String> nonUselessCharacteristics) {
        super(prepareMessage(detailType, nonUselessCharacteristics));
        this.nonUselessCharacteristics = nonUselessCharacteristics;
    }

    // TODO: message должен быть определен не здесь
    private static String prepareMessage(String detailType, Iterable<String> nonUselessCharacteristics) {
        return String.format("Указаны лишние поля для детали %s.", detailType)
                + " Деталь поддерживает только следующие характеристики:"
                + String.join(", ", nonUselessCharacteristics);
    }

}
