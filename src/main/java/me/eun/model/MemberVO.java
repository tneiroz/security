package me.eun.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberVO {
   private String userId; 
   private String userPw;  
   private String confirmUserPw;
   private String userName;
   private String email;
   private String dateOfBirth;
   private String phoneNumber;
   private GENDER gender;
   private boolean enabled;
   private LocalDateTime regDate;
   private LocalDateTime updateDate;
   private List<AuthVO> authList;
   
   enum GENDER {MALE,FEMALE}
}