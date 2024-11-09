package controller;

import dto.SignInDTO;
import dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpDTO signUpDTO) {
        userService.registerUser(signUpDTO);
        return "User registered successfully!";
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody SignInDTO signInDTO) {
        return userService.authenticateUser(signInDTO);
    }
}