package com.s3corp.service;

import com.s3corp.dto.RoleDTO;

public interface RoleService {

	RoleDTO create(RoleDTO roleDTO);

	RoleDTO findByName(String name);

}
