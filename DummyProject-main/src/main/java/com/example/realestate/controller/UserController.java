package com.example.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.realestate.entity.User;
import com.example.realestate.exception.CustomException;
import com.example.realestate.exception.UserNotFoundException;
import com.example.realestate.request.UpdatePass;
import com.example.realestate.service.UserService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getbyemail")
	public ResponseEntity<?> getByEmail(@RequestHeader("Authorization") String token) {
		try {
			User user = userService.findUserProfileByJwt(token);
			String email = user.getEmail();
			return new ResponseEntity<>(userService.GetByEmail(email), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
    @PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User userRef) {
		try {
			return userService.updateUser(userRef);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			return userService.deleteUser(id);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
    
    @PutMapping("/updatepassword")
	public ResponseEntity<?> updatePasword1(@RequestBody UpdatePass updatePassRef) {
		// System.out.println(userId + "," + password);
		try {
			return new ResponseEntity<>(userService.upadatePassword(updatePassRef.getEmail(),
					updatePassRef.getOldPassword(), updatePassRef.getNewPassword()), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
