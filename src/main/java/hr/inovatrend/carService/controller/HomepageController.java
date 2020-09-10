package hr.inovatrend.carService.controller;

import hr.inovatrend.carService.dao.ServiceRepository;
import hr.inovatrend.carService.domain.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {

    private final ServiceRepository serviceRepository;

    public HomepageController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @RequestMapping({"/homepage", "", "/"})
    private String getAllUsers(Model model) {
        Page<Service> findLastTenServices = serviceRepository.findAll(PageRequest.of(0,10, Sort.by(Sort.Order.desc("dateNow"))));
        model.addAttribute("services", findLastTenServices);
        return "last_ten_service_list";
    }

}
