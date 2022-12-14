package me.eun.security;


import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import me.eun.model.MemberVO;

@Getter @Setter
public class CustomUser extends User{

   private MemberVO memberVO;

   private static final long serialVersionUID = -2142688104565900152L;

   public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
         boolean credentialsNonExpired, boolean accountNonLocked,
         Collection<? extends GrantedAuthority> authorities) {
      super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
   }

   public CustomUser(MemberVO memberVO) {
      super(memberVO.getUserId(), memberVO.getUserPw(), memberVO.getAuthList()
            .stream().map(vo-> new SimpleGrantedAuthority(vo.getAuth())).collect(Collectors.toList())
      );
      this.memberVO = memberVO;
   }
}