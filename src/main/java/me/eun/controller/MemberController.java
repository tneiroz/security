package me.eun.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.eun.model.MemberVO;
import me.eun.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
   
   @Autowired
   private MemberService service;
   
   // 회원가입폼
   @GetMapping("/signUp")
   public String join() {
      return "member/signUp";
   }

   // 회원가입 처리
   @PostMapping("/signUp")
   public String register(MemberVO member) {
      service.register(member);
      return "redirect:/";
   }
  
  
   // 아이디 중복 조회
   @GetMapping("/idCheck/{userId}")
   @ResponseBody
   public boolean findByUserId(@PathVariable String userId) {
      MemberVO findById = service.findById(userId);
      if(findById!=null) {
         System.out.println("아이디 중복이다");
         return false;
      }
      return true; 
   }
   
   
}
