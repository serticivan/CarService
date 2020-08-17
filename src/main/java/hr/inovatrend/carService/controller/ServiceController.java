package hr.inovatrend.carService.controller;

import hr.inovatrend.carService.dao.CarRepository;
import hr.inovatrend.carService.dao.ServiceRepository;
import hr.inovatrend.carService.domain.Car;
import hr.inovatrend.carService.domain.Service;
import hr.inovatrend.carService.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/service")
@SessionAttributes("service")
public class ServiceController {

    private final ServiceRepository serviceRepository;
    private final CarRepository carRepository;

    public ServiceController(ServiceRepository serviceRepository, CarRepository carRepository) {
        this.serviceRepository = serviceRepository;
        this.carRepository = carRepository;
    }

    @GetMapping("/save")
    private String showServiceForm(Model model) {
        Service service = new Service();
        model.addAttribute(service);

        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "service_form";
    }

    @GetMapping("/save/{carId}")
    private String showCarForm(Model model, @PathVariable Long carId) {
        Service service = new Service();
        model.addAttribute(service);

        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        model.addAttribute("selectedCar", carId);
        return "service_form";
    }

    @PostMapping("/save")
    private String saveService(Model model, @ModelAttribute Service service) {
        serviceRepository.save(service);
        return "redirect:/service/list";
    }

    @RequestMapping("/list")
    private String getAllServices(Model model) {
        List<Service> services = serviceRepository.findAll();
        model.addAttribute("services", services);
        return "service_list";
    }

    @RequestMapping("/info/{serviceId}")
    private String showServiceInfo(Model model, @PathVariable Long serviceId) {
        Optional<Service> service = serviceRepository.findById(serviceId);
        if (service.isPresent()) {
            model.addAttribute("service", service.get());
        }
        return "service_info";
    }

    @RequestMapping("/delete/{serviceId}")
    private String deleteService(@PathVariable Long serviceId) {
        serviceRepository.deleteById(serviceId);
        return "redirect:/service/list";
    }

    @RequestMapping("/edit/{serviceId}")
    private String editService(Model model, @PathVariable Long serviceId) {
        Optional<Service> service = serviceRepository.findById(serviceId);
        if (service.isPresent()) {
            model.addAttribute("service", service.get());
        }

        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);

        return "service_form";
    }
}
