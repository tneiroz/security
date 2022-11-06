package me.eun.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.eun.mapper.MemberMapper;
import me.eun.model.AuthVO;
import me.eun.model.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

   @Autowired
   MemberMapper mapper;
   
   @Autowired
   PasswordEncoder passwordEncoder; 
   
   @Override
   @Transactional
   public void register(MemberVO memberVO) {
      // 패스워드 인코딩
      String pwEncoding = passwordEncoder.encode(memberVO.getUserPw());
      memberVO.setUserPw(pwEncoding);
      
      // 회원등록
      mapper.insert(memberVO);
      
      // 권한 등록
      memberVO.setAuthList(new ArrayList<AuthVO>());
      memberVO.getAuthList().add(new AuthVO("ROLE_MEMBER"));
      String userId = memberVO.getUserId();
      memberVO.getAuthList().forEach(v-> mapper.authenticate(userId,v.getAuth()));
   }

   @Override
   public void modify(MemberVO memberVO) {
      mapper.update(memberVO);
   }

   @Override
   public void remove(MemberVO memberVO) {
      mapper.delete(memberVO);
   }

   @Override
   public MemberVO findById(String userId) {
      return mapper.findByUserId(userId);
   }

}
