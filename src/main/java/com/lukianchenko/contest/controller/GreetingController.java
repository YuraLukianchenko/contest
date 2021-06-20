package com.lukianchenko.contest.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
  @GetMapping("/greeting")
  public String greetings(@RequestParam(required = false, defaultValue = "world") String name, Map<String, String> model) {
    model.put("name", name);
    return "greetingPage";
  }

  @GetMapping("/main")
  public String main() {

    return "mainPage";
  }
}
