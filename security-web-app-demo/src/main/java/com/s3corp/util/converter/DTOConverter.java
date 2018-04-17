package com.s3corp.util.converter;

import com.s3corp.dto.RoleDTO;
import com.s3corp.dto.UserDTO;
import com.s3corp.entity.Role;
import com.s3corp.entity.User;

public class DTOConverter {

	public static UserDTO convertUser(User user) {
		return new UserDTO(user.getId(), user.getUserName(), user.getPassword(), user.getEmail(), user.isActive());
	}

	public static RoleDTO convertRole(Role role) {
		return new RoleDTO(role.getId(), role.getName());
	}

}
