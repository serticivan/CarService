package hr.inovatrend.carService.controller;

import hr.inovatrend.carService.dao.CarRepository;
import hr.inovatrend.carService.dao.UserRepository;
import hr.inovatrend.carService.domain.Car;
import hr.inovatrend.carService.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/car")
@SessionAttributes("car")
public class CarController {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public CarController(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/save")
    private String showCarForm(Model model) {
        Car car = new Car();
        model.addAttribute(car);

        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "car_form";
    }

    @GetMapping("/save/{userId}")
    private String showCarForm(Model model, @PathVariable Long userId) {
        Car car = new Car();
        model.addAttribute(car);

        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("selectedUser", userId);
        return "car_form";
    }

    @PostMapping("/save")
    private String saveCar(Model model, @ModelAttribute Car car) {
        Long userId = car.getUser().getId();
        carRepository.save(car);
        return "redirect:/user/info/" + userId;
    }

    @RequestMapping("/list")
    private String getAllCars(Model model) {
        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "car_list";
    }

    @RequestMapping("info/{carId}")
    private String showCarInfo(Model model, @PathVariable Long carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.isPresent()){
            model.addAttribute("car", car.get());
        }
        return "car_info";
    }

    @RequestMapping("delete/{carId}")
    private String deleteCar(@PathVariable Long carId){
        carRepository.deleteById(carId);
        return "redirect:/car/list";
    }

    @RequestMapping("edit/{carId}")
    private String editCar(Model model, @PathVariable Long carId){
        Optional<Car> car = carRepository.findById(carId);
        if (car.isPresent()){
            model.addAttribute("car", car);
        }
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "car_form";
    }
}
