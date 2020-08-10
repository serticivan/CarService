package hr.inovatrend.carService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    @Column(name = "streetname")
    private String streetName;
    @Column(name = "housenumber")
    private String houseNumber;
    @Column(name = "postnumber")
    private int postNumber;
    @Column(name = "cityname")
    private String cityName;
    @Column(name = "countryname")
    private String countryName;


}
