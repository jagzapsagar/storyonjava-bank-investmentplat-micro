package com.storyinvest.authservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.storyinvest.authservice.entity.User;
import com.storyinvest.authservice.repository.UserRepository;
import com.storyinvest.authservice.util.JwtUtil;

@Service
public class AuthService {
	 private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final JwtUtil jwtUtil;

	    public AuthService(UserRepository userRepository,
	                       PasswordEncoder passwordEncoder,
	                       JwtUtil jwtUtil) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.jwtUtil = jwtUtil;
	    }

	    public void register(String username, String password, String role) {
	        User user = new User();
	        user.setUsername(username);
	        user.setPassword(passwordEncoder.encode(password));
	        user.setRole(role);

	        userRepository.save(user);
	    }

	    public String login(String username, String password) {
	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        if (!passwordEncoder.matches(password, user.getPassword())) {
	            throw new RuntimeException("Invalid credentials");
	        }
	        System.out.println("Validate credentials..");
	         String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
	         System.out.println(token);
	        return token;
	    }
}
