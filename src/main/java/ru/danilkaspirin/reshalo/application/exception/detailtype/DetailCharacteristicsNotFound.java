package ru.danilkaspirin.reshalo.application.exception.detailtype;

public class DetailCharacteristicsNotFound extends RuntimeException{

    public DetailCharacteristicsNotFound(String detailType, Iterable<String> notFoundedCharacteristics){
        super(prepareMessage(detailType, notFoundedCharacteristics));
    }

    // TODO: message должен быть определен не здесь
    private static String prepareMessage(String detailType, Iterable<String> notFoundedCharacteristics){
        return String.format("Для детали %s должны быть определены характеристики:", detailType)
                + String.join(", ", notFoundedCharacteristics);
    }
}
