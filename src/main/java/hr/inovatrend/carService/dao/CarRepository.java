package hr.inovatrend.carService.dao;

import hr.inovatrend.carService.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
