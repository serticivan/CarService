package hr.inovatrend.carService.domain;

import lombok.Getter;

@Getter
public enum CarType {

    TOYOTA_AURIS("Toyota", "Auris"),
    SKODA_SUPERB("Skoda", "Superb"),
    TOYOTA_YARIS("Toyota", "Yaris"),
    SKODA_FABIA("Skoda", "Fabia"),
    FORD_FOCUS("Ford", "Focus"),
    FIAT_BRAVO("Fiat", "Bravo"),
    VOLKSWAGEN_GOLF("Volkswagen", "Golf"),
    VOLKSWAGEN_POLO("Volkswagen", "Polo"),
    CHEVROLET_CAMARO("Chevrolet", "Camaro"),
    OPEL_CORSA("Opel", "Corsa");

    private final String manufacturer;
    private final String model;

    CarType(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }
}
