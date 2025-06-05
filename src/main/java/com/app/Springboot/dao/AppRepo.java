package com.app.Springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Springboot.model.AppUser;
import com.app.Springboot.model.ConstantRole;

public interface AppRepo extends JpaRepository<AppUser,Long>{

	List<AppUser> findAllByRoleRoleName(ConstantRole fromString);

	List<AppUser> findAllByNumber(String phoneNumber);

	boolean existsByEmail(String email);

	boolean existsByNumber(String number);

}
