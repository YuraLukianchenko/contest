package com.lukianchenko.contest.controller;

import com.lukianchenko.contest.domain.User;
import com.lukianchenko.contest.repository.UserRepo;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecretController {

  private static final Integer LEVEL2 = 2;
  private UserRepo userRepo;

  public SecretController(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @GetMapping("/AAD")
  public String secretMethod(Principal principal) {
    User currentUser = userRepo.findByUsername(principal.getName());
    Integer userLevel = currentUser.getLevel();
    if(userLevel < LEVEL2) {
      userLevel = LEVEL2;
      currentUser.setLevel(userLevel);
      userRepo.save(currentUser);
    }
    return "redirect:/main";
  }
}
