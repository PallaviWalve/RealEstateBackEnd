package com.example.realestate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.realestate.config.JwtProvider;
import com.example.realestate.entity.User;
import com.example.realestate.exception.EmailAlreadyExistException;
import com.example.realestate.exception.UserNotFoundException;
import com.example.realestate.repository.UserRepository;
import com.example.realestate.request.LoginRequest;
import com.example.realestate.response.AuthResponse;
import com.example.realestate.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserRepository userRepoRef;
	private JwtProvider jwtProvider;
	private PasswordEncoder encoder;
	private UserService userServiceRef;

	public AuthController(UserRepository userRepoRef, JwtProvider jwtProvider, PasswordEncoder encoder,
			UserService userServiceRef) {
		this.userRepoRef = userRepoRef;
		this.jwtProvider = jwtProvider;
		this.encoder = encoder;
		this.userServiceRef = userServiceRef;
	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws EmailAlreadyExistException {
		String email = user.getEmail();
		String pass = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		

		User isEmailExist = userRepoRef.findByEmail(email);
		if (isEmailExist != null) {
			throw new EmailAlreadyExistException("Email is already exist");
		}
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(encoder.encode(pass));
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);
		createdUser.setAddress(user.getAddress());
		createdUser.setPhoneNumber(user.getPhoneNumber());
		createdUser.setRole(user.getRole());
	

		User savedUser = userRepoRef.save(createdUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		return new ResponseEntity<AuthResponse>(new AuthResponse(token, "Signup success"), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) {
		String userName = loginRequest.getEmail();
		String pass = loginRequest.getPassword();

		UserDetails userDetails = userServiceRef.loadUserByUsername(userName);
		if (userDetails == null) {
			throw new BadCredentialsException("Invalid Username !!");
		}
		if (!encoder.matches(pass, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password !!");
		}

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		return new ResponseEntity<AuthResponse>(new AuthResponse(token, "Login success"), HttpStatus.OK);
	}
	
	@GetMapping("/getbyjwt")
	public ResponseEntity<?> getuserByJWTToken(@RequestHeader("Authorization") String token){
		try {
			return new ResponseEntity<User>(userServiceRef.findUserProfileByJwt(token),HttpStatus.OK);
		}catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
}










