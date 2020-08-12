package hr.inovatrend.carService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_sequence")
    @SequenceGenerator(name = "car_sequence", allocationSize = 10)
    private Long carId;

    @Column(name = "car")
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name = "manufacturedYear")
    private int manufacturedYear;

    @Column(name = "registrationNumber")
    private String registrationNumber;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    private Set<Service> serviceList = new HashSet<>();


}
