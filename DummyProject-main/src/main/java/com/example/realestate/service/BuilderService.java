package com.example.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.realestate.config.JwtProvider;
import com.example.realestate.entity.Agent;
import com.example.realestate.entity.Builder;
import com.example.realestate.entity.User;
import com.example.realestate.exception.UserNotFoundException;
import com.example.realestate.repository.BuilderRepository;
import com.example.realestate.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuilderService {

	 @Autowired
	    private UserRepository userRepository;
	    
	    @Autowired
	    private JwtProvider jwtProviderRef;
	    
	    @Autowired
	    private PasswordEncoder encoder;
	
    @Autowired
    private BuilderRepository builderRepository;

    public Builder addBuilder(Builder builder) {
        return builderRepository.save(builder);
    }

    public Builder findById(Long id) {
        return builderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Builder not found with id: " + id));
    }

    public List<Builder> findAll() {
        return builderRepository.findAll();
    }

    public void deleteBuilder(Long id) {
        builderRepository.deleteById(id);
    }

    public Builder updateBuilder(Long id, Builder updatedBuilder) {
        Builder builder = findById(id);
        builder.setName(updatedBuilder.getName());
        builder.setContactInfo(updatedBuilder.getContactInfo());
        return builderRepository.save(builder);
    }
    
    public UserDetails loadBuilderByname(String name) throws UserNotFoundException {
		Builder builder = builderRepository.findByEmail(name);
		if (builder == null) {
			throw new UserNotFoundException("Builder not found with email: " + name);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(builder.getEmail(), builder.getPassword(), authorities);
	}
    
    public Builder findBuilderProfileByJwt(String jwt) throws UserNotFoundException {
		String email = jwtProviderRef.getEmailFromToken(jwt);
		Builder builder = builderRepository.findByEmail(email);
		if (builder == null) {
			throw new UserNotFoundException("Builder not found with this email: " + email);
		}
		return builder;
}

	public User GetByEmail(String mail) {
		User userRef = userRepository.findByEmail(mail);
		if (userRef != null) {
			return userRef;
		} else {

			throw new UserNotFoundException("User Not Found");

		}
	}
	
}
