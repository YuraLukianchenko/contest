package com.lukianchenko.contest.controller;

import com.lukianchenko.contest.domain.Role;
import com.lukianchenko.contest.domain.User;
import com.lukianchenko.contest.repository.UserRepo;
import java.util.Collections;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

  private UserRepo userRepo;

  public RegistrationController(UserRepo userRepo) {
    this.userRepo = userRepo;
  }
  @GetMapping("/registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("/registration")
  public String addUser(User user, Map<String, Object> model) {
    User userFromDB = userRepo.findByUsername(user.getUsername());

    if (userFromDB != null) {
      model.put("message", "User exists");
      return "registration";
    }

    user.setActive(true);
    user.setRoles(Collections.singleton(Role.USER));
    userRepo.save(user);
    return "redirect:/login";
  }
}
