package com.spring.security.service;

import com.spring.security.dto.RoleDTO;

public interface RoleService {

	RoleDTO create(RoleDTO roleDTO);

	RoleDTO findByName(String name);

}
