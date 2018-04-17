package com.s3corp.util.converter;

import com.s3corp.dto.RoleDTO;
import com.s3corp.dto.UserDTO;
import com.s3corp.entity.Role;
import com.s3corp.entity.User;
import com.s3corp.util.NumberUtils;

public class DAOConverter {

	public static User convertUser(UserDTO userDTO) {
		User user = new User();
		user.setId(NumberUtils.isEmpty(userDTO.getId()) ? null : userDTO.getId());
		user.setUserName(userDTO.getUserName());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setActive(userDTO.isActive());
		return user;
	}

	public static Role convertRole(RoleDTO roleDTO) {
		Role role = new Role();
		role.setId(NumberUtils.isEmpty(roleDTO.getId()) ? null : roleDTO.getId());
		role.setName(roleDTO.getName());
		return role;
	}

}
