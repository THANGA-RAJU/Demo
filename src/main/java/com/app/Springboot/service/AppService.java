package com.app.Springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.Springboot.Exception.EmailAlreadyExistsException;
import com.app.Springboot.dao.AppRepo;
import com.app.Springboot.dto.UserRequest;
import com.app.Springboot.model.AppUser;
import com.app.Springboot.model.ConstantRole;
import com.app.Springboot.model.Roles;

@Service
public class AppService {
	
    @Autowired
	AppRepo appRepo;
	
	public List<AppUser> getAllUsers() {
		return appRepo.findAll();
	}

	public List<AppUser> saveUsers(UserRequest user) {
		
		if(appRepo.existsByEmail(user.getEmail())){
			throw new EmailAlreadyExistsException("this Email already existed "+user.getEmail(),HttpStatus.BAD_REQUEST.value());
		}
		if(appRepo.existsByNumber(user.getNumber())){
			throw new EmailAlreadyExistsException("This Mobile already existed "+user.getNumber(),HttpStatus.CONFLICT.value());
		}
		
		AppUser savedUser= new AppUser();
		savedUser.setEmail(user.getEmail());
		savedUser.setName(user.getName());
		savedUser.setNumber(user.getNumber());
		Roles savedRole= new Roles();
		savedRole.setRoleName(ConstantRole.fromString(user.getRole()));
		savedUser.setRole(savedRole);
		
		
	        return (List<AppUser>) appRepo.save(savedUser);
	}


	public AppUser updateUser(Long id,UserRequest user) {
       Optional<AppUser> optionalUser=appRepo.findById(id);
       
       if(optionalUser.isPresent()) {
    	   optionalUser.get().setName(user.getName()!=null?user.getName():optionalUser.get().getName());
    	   optionalUser.get().setEmail(user.getEmail()!=null?user.getEmail():optionalUser.get().getEmail());
    	   optionalUser.get().setNumber(user.getNumber()!=null?user.getNumber():optionalUser.get().getNumber());
    	   Roles saveRole=new Roles();
    	   saveRole.setRoleName(ConstantRole.fromString(user.getRole()!=null?user.getRole():optionalUser.get().getRole().getRoleName().name()));
           optionalUser.get().setRole(saveRole);
       }
       
       
		return appRepo.save(optionalUser.get());
	}

	public Optional<AppUser> getUseerById(Long id) {
        return appRepo.findById(id);
	}

	public void deleteUserById(Long id) {
		appRepo.deleteById(id);;
	}
	public List<AppUser> getByRolename(String roleName) {
		// TODO Auto-generated method stub
		return appRepo.findAllByRoleRoleName(ConstantRole.fromString(roleName));
	}

	public List<AppUser> getByPhonenumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return appRepo.findAllByNumber(phoneNumber);
	}

}
