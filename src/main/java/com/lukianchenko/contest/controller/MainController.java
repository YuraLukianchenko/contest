package com.lukianchenko.contest.controller;

import com.lukianchenko.contest.domain.User;
import com.lukianchenko.contest.repository.UserRepo;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  private UserRepo userRepo;

  public MainController(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @GetMapping("")
  public String greetings(@RequestParam(required = false, defaultValue = "world") String name, Map<String, String> model) {
    model.put("name", name);
    return "greetingPage";
  }

  @GetMapping("/main")
  public String main(Map<String, Object> model) {
    Iterable<User> users = userRepo.findAll();
    model.put("users", users);
    return "mainPage";
  }

  @PostMapping("/main")
  public String addUsername(Map<String, Object> model) {
    Iterable<User> users = userRepo.findAll();
    model.put("users", users);

    return "mainPage";
  }
}
