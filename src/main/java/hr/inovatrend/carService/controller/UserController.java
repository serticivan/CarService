package hr.inovatrend.carService.controller;

import hr.inovatrend.carService.dao.CarRepository;
import hr.inovatrend.carService.dao.UserRepository;
import hr.inovatrend.carService.domain.Car;
import hr.inovatrend.carService.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public UserController(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    @GetMapping("/save")
    private String showUserForm(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "user_form";
    }

    @PostMapping("/save")
    private String save(Model model, @ModelAttribute User user) {
        userRepository.save(user);
        return "user_info";
    }

    @RequestMapping("/list")
    private String getAllUsers(Model model) {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
        model.addAttribute("users", users);
        return "user_list";
    }

    @RequestMapping("/info/{userId}")
    private String showUserInfo(Model model, @PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        }
        return "user_info";
    }

    @RequestMapping("delete/{userId}")
    private String deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return "redirect:/user/list";
    }

    @RequestMapping("edit/{userId}")
    private String editUser(Model model, @PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            model.addAttribute("user", user);
        }
        return "user_form";

    }


}
