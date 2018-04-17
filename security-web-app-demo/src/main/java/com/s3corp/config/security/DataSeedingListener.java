package com.s3corp.config.security;

import java.util.HashSet;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.s3corp.entity.Role;
import com.s3corp.entity.User;
import com.s3corp.enumeration.RoleEnum;
import com.s3corp.repository.RoleRepository;
import com.s3corp.repository.UserRepository;
import com.s3corp.util.BCryptPassword;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// init ROLE_ADMIN and ROLE_MEMBER into DB
		if (Objects.isNull(roleRepository.findByName(RoleEnum.ADMIN.getRole()))) {
			roleRepository.save(new Role(RoleEnum.ADMIN.getRole()));
		}

		if (Objects.isNull(roleRepository.findByName(RoleEnum.MEMBER.getRole()))) {
			roleRepository.save(new Role(RoleEnum.MEMBER.getRole()));
		}

		// init two user: admin and member into DB
		if (Objects.isNull(userRepository.findByUserName("admin"))) {
			User user = new User();
			user.setUserName("admin");
			user.setPassword(BCryptPassword.cryptPasswordEncoder("admin"));
			user.setEmail("");
			user.setActive(true);

			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName(RoleEnum.ADMIN.getRole()));
			roles.add(roleRepository.findByName(RoleEnum.MEMBER.getRole()));
			user.setRoles(roles);
			
			userRepository.save(user);
		}

		if (Objects.isNull(userRepository.findByUserName("member"))) {
			User user = new User();
			user.setUserName("member");
			user.setPassword(BCryptPassword.cryptPasswordEncoder("member"));
			user.setEmail("");
			user.setActive(true);

			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName(RoleEnum.MEMBER.getRole()));
			user.setRoles(roles);
			
			userRepository.save(user);
		}
	}

}
