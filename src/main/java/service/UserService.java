package service;

import dto.SignInDTO;
import dto.SignUpDTO;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import utils.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(SignUpDTO signUpDTO) {
        if (userRepository.findByEmail(signUpDTO.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        userRepository.save(user);
    }

    public String authenticateUser(SignInDTO signInDTO) {
        User user = userRepository.findByEmail(signInDTO.getEmail());
        if (user == null || !passwordEncoder.matches(signInDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return "JWT Token"; // Simplified for the sake of example
    }
}