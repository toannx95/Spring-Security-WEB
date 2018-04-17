package com.s3corp.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s3corp.dto.RoleDTO;
import com.s3corp.entity.Role;
import com.s3corp.repository.RoleRepository;
import com.s3corp.service.RoleService;
import com.s3corp.util.converter.DAOConverter;
import com.s3corp.util.converter.DTOConverter;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleDTO create(RoleDTO roleDTO) {
		return DTOConverter.convertRole(roleRepository.save(DAOConverter.convertRole(roleDTO)));
	}

	@Override
	public RoleDTO findByName(String name) {
		Role role = roleRepository.findByName(name);
		if (Objects.isNull(role)) {
			return null;
		}
		return DTOConverter.convertRole(role);
	}

}
