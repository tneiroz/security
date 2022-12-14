package me.eun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

   @GetMapping("/accessError")
   public String accessDenied() {
      return "error/accessError";   
   }
   
   @GetMapping("/customLogin")
   public String loginForm(String error, Model model) {
      if(error != null) {
         model.addAttribute("error", error);
         System.out.println(error);
      }
      return "member/login";
   }
}