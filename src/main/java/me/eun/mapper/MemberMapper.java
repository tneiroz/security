package me.eun.mapper;
import org.apache.ibatis.annotations.Param;

import me.eun.model.MemberVO;

public interface MemberMapper {

	   MemberVO read(String userId);
	   void authenticate(
	         @Param("userId") String userId,
	         @Param("auth") String auth);
	   void insert(MemberVO memberVO);
	   void update(MemberVO memberVO);
	   void delete(MemberVO membeVO);
	   
	   MemberVO findByUserId(String userId);
}