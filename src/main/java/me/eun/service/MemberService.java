package me.eun.service;

import me.eun.model.MemberVO;

public interface MemberService {

   void remove(MemberVO memberVO);
   void modify(MemberVO memberVO);
   void register(MemberVO memberVO);
   MemberVO findById(String userId);
}
