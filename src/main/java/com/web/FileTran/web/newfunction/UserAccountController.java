package com.web.FileTran.web.newfunction;

import com.web.FileTran.newvo.UserVO;
import com.web.FileTran.newservice.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    private final UserService userService;

    // Constructor with dependency injection

    public UserAccountController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserVO> registerUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        try {
            // User registration logic
            UserVO newUser = userService.registerUser(username, password,session);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserVO> loginUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        try {
            // User login logic
            UserVO user = userService.loginUser(username, password,session);

            // Set user session attribute
            session.setAttribute("user", user);

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logoutUser(HttpSession session) {
        try {
            // User logout logic

            // Invalidate session
            session.invalidate();

            boolean result = userService.logoutUser(session);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Other controller methods for the remaining requirements
    // ...
}