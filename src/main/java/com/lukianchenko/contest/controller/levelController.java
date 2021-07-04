package com.lukianchenko.contest.controller;

import com.lukianchenko.contest.domain.Role;
import com.lukianchenko.contest.domain.User;
import com.lukianchenko.contest.repository.UserRepo;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class levelController {

  private static final Integer LEVEL2 = 2;
  private static final Integer LEVEL3 = 3;

  private UserRepo userRepo;

  public levelController(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @GetMapping("/${secretURL}")
  public String startLevel1(Principal principal) {
    User currentUser = userRepo.findByUsername(principal.getName());
    Set<Role> userRoles = currentUser.getRoles();
    Integer userLevel = currentUser.getLevel();
    if(userLevel < LEVEL2) {
      userLevel = LEVEL2;
      currentUser.setLevel(userLevel);
      userRoles.add(Role.LEVEL2);
      currentUser.setRoles(userRoles);
      userRepo.save(currentUser);

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      List<GrantedAuthority> updatedAuthority = new ArrayList<>(auth.getAuthorities());
      updatedAuthority.add(new SimpleGrantedAuthority(Role.LEVEL2.name()));
      Authentication newAuth = new UsernamePasswordAuthenticationToken(principal, auth.getCredentials(), updatedAuthority);
      SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
    return "redirect:/main";
  }

  @PreAuthorize("hasAuthority('LEVEL2')")
  @GetMapping("/level2")
  public String startLevel2(Principal principal) {
    User currentUser = userRepo.findByUsername(principal.getName());
    Set<Role> userRoles = currentUser.getRoles();
    Integer userLevel = currentUser.getLevel();
    if(userLevel < LEVEL3) {
      userLevel = LEVEL3;
      currentUser.setLevel(userLevel);
      userRoles.add(Role.LEVEL3);
      currentUser.setRoles(userRoles);
      userRepo.save(currentUser);

      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      List<GrantedAuthority> updatedAuthority = new ArrayList<>(auth.getAuthorities());
      updatedAuthority.add(new SimpleGrantedAuthority(Role.LEVEL3.name()));
      Authentication newAuth = new UsernamePasswordAuthenticationToken(principal, auth.getCredentials(), updatedAuthority);
      SecurityContextHolder.getContext().setAuthentication(newAuth);

    }
    return "redirect:/main";
  }
}
