package com.spring.security.service.impl;

import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.dto.RoleDTO;
import com.spring.security.dto.UserDTO;
import com.spring.security.entity.User;
import com.spring.security.enumeration.RoleEnum;
import com.spring.security.repository.UserRepository;
import com.spring.security.service.RoleService;
import com.spring.security.service.UserService;
import com.spring.security.util.BCryptPassword;
import com.spring.security.util.converter.DAOConverter;
import com.spring.security.util.converter.DTOConverter;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Override
	public UserDTO create(UserDTO userDTO) {
		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setPassword(BCryptPassword.cryptPasswordEncoder(userDTO.getPassword()));
		user.setEmail(userDTO.getEmail());
		user.setActive(true);

		HashSet<RoleDTO> roles = new HashSet<>();
		roles.add(roleService.findByName(RoleEnum.MEMBER.getRole()));
		user.setRoles(roles.stream().map(DAOConverter::convertRole).collect(Collectors.toSet()));

		return DTOConverter.convertUser(userRepository.save(user));
	}

	@Override
	public UserDTO findByUserNameAndActiveTrue(String userName) {
		return DTOConverter.convertUser(userRepository.findByUserNameAndActiveTrue(userName));
	}

	@Override
	public UserDTO findByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		if (Objects.isNull(user)) {
			return null;
		}
		return DTOConverter.convertUser(user);
	}

}
