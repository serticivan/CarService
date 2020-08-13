package hr.inovatrend.carService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_sequence")
    @SequenceGenerator(name = "service_sequence", allocationSize = 10)
    private Long serviceId;

    @Column(name = "create_date")
    private Instant dateNow = Instant.now();

    @Column(name = "worker_first_name")
    private String workerFirstName;

    @Column(name = "worker_last_name")
    private String workerLastName;

    @Column(name = "work_description")
    private String workDescription;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

}
