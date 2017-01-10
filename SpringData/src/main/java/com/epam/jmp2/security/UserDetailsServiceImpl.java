package com.epam.jmp2.security;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("user name=============>>>>>>>>>>" + userName);
		ResourceBundle resource = ResourceBundle.getBundle("user");
		String value = resource.getString(userName); 
		User user = new User();
		if(value != null && !"".equals(value)){
		   String[] userInfo = value.split(",");		  
		   user.setUsername(userInfo[0]);
		   user.setPassword(userInfo[1]);
		   Set<Role> roles = new HashSet<Role>();
		   for(int i=2; i<userInfo.length; i++){
			   roles.add(new Role(userInfo[i]));	   
		   }			   		   
		   user.setRoles(roles);
		}else{
			throw new UsernameNotFoundException("Can't find user with username:" + userName);
		}
		
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        System.out.println(user.getUsername() + "=======>>>>>>>>>>" + user.getPassword() + "=============>>>>>>" +user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
