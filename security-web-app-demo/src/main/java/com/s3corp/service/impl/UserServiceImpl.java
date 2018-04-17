package com.s3corp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s3corp.dto.RoleDTO;
import com.s3corp.dto.UserDTO;
import com.s3corp.entity.User;
import com.s3corp.enumeration.RoleEnum;
import com.s3corp.repository.UserRepository;
import com.s3corp.service.RoleService;
import com.s3corp.service.UserService;
import com.s3corp.util.BCryptPassword;
import com.s3corp.util.converter.DAOConverter;
import com.s3corp.util.converter.DTOConverter;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Override
	public List<UserDTO> findAll() {
		Iterable<User> users = userRepository.findAll();
		if (users == null) {
			return new ArrayList<>();
		}
		Stream<User> companyStream = StreamSupport.stream(users.spliterator(), false);
		return companyStream.map(DTOConverter::convertUser).collect(Collectors.toList());
	}

	@Override
	public UserDTO findOne(Integer id) {
		User user = userRepository.findOne(id);
		if (Objects.isNull(user)) {
			return null;
		}
		return DTOConverter.convertUser(userRepository.findOne(id));
	}

	@Override
	public UserDTO create(UserDTO userDTO) {
		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setPassword(BCryptPassword.cryptPasswordEncoder(userDTO.getPassword()));
		user.setEmail(userDTO.getEmail());
		user.setActive(false);

		HashSet<RoleDTO> roles = new HashSet<>();
		roles.add(roleService.findByName(RoleEnum.MEMBER.getRole()));
		user.setRoles(roles.stream().map(DAOConverter::convertRole).collect(Collectors.toSet()));

		return DTOConverter.convertUser(userRepository.save(user));
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		Integer id = userDTO.getId();

		User user = userRepository.findOne(id);
		if (Objects.isNull(user)) {
			return null;
		}

		user.setId(id);
		user.setUserName(userDTO.getUserName());
		user.setPassword(BCryptPassword.cryptPasswordEncoder(userDTO.getPassword()));
		user.setEmail(userDTO.getEmail());
		user.setActive(userDTO.isActive());

		HashSet<RoleDTO> roles = new HashSet<>();
		roles.add(roleService.findByName(RoleEnum.MEMBER.getRole()));
		user.setRoles(roles.stream().map(DAOConverter::convertRole).collect(Collectors.toSet()));

		return DTOConverter.convertUser(userRepository.save(user));
	}

	@Override
	public void delete(Integer id) {
		userRepository.delete(userRepository.findOne(id));
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
