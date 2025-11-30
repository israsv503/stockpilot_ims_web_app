package com.stockpilot.ims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

  // Handles GET requests to the /login endpoint
  @GetMapping("/login")
  public String login() {
    // Return the name of the Thymeleaf template (login.html)
    return "login";
  }
}
