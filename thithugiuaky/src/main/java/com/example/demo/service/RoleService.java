package com.example.demo.service;


import com.example.demo.entity.Role;

public interface RoleService {
	void addRole(Role role);
	Role findRoleByRoleName(String roleName);
}	
