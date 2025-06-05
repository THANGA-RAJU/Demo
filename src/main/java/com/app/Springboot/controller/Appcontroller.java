package com.app.Springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Springboot.dto.UserRequest;
import com.app.Springboot.model.AppUser;
import com.app.Springboot.service.AppService;

@RestController
@RequestMapping("/api")
public class Appcontroller {
	
	@Autowired
	AppService appService;
	
	@GetMapping("/get")
	public ResponseEntity<List<AppUser>> getAllUsers() {
		List<AppUser> users=appService.getAllUsers();
		return ResponseEntity.ok(users);
		
	}
	
	@PostMapping("/save")
	public ResponseEntity<List<AppUser>> saveUser(@RequestBody UserRequest user){
        List<AppUser> save=appService.saveUsers(user);
		return ResponseEntity.ok(save);
	}
	
	@PutMapping
	public ResponseEntity<AppUser> saveUser(@PathVariable Long id,@RequestBody UserRequest user) {
		AppUser save=appService.updateUser(id,user);
		return ResponseEntity.ok(save);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AppUser> getUserById(@PathVariable Long id){
		Optional<AppUser> user=appService.getUseerById(id);
		return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
	    try {
	        appService.deleteUserById(id);
	        return ResponseEntity.ok("User deleted successfully.");
	    } catch (Exception e) {
	        return ResponseEntity.status(404).body("User not found with ID: " + id);
	    }
	}
	@GetMapping("/role/{roleName}")
	public ResponseEntity<List<AppUser>> getByRoleName(@PathVariable String roleName ){
		List<AppUser> users=appService.getByRolename(roleName);
		    return new ResponseEntity<List<AppUser>>(users,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/{phoneNumber}")
	public ResponseEntity<List<AppUser>> getByMobilenumber(@PathVariable String phoneNumber ){
		
		List<AppUser> users=appService.getByPhonenumber(phoneNumber);
		return new ResponseEntity<List<AppUser>>(users,HttpStatus.ACCEPTED);

}
}

