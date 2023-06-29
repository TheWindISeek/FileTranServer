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
@RequestMapping("/user")
public class UserAccountController {

    private final UserService userService;

    // Constructor with dependency injection

    public UserAccountController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserVO> registerUser(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {
        // 账号相关的逻辑完全交给service层完成controller只负责类型转换
        try {
            // User registration logic
            // 具体逻辑由service层完成
            UserVO newUser = userService.registerUser(username, password,session);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserVO> loginUser(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {
        // 账号相关的逻辑完全交给service层完成controller只负责类型转换
        try {
            // 具体逻辑由service层完成
            UserVO user = userService.loginUser(username, password,session);
            return ResponseEntity.ok(user);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        /* TODO 细化异常类型
        catch (Exception e) {

        }
        */
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logoutUser(HttpSession session) {
        // 账号相关的逻辑完全交给service层完成controller只负责类型转换
        try {
            // 具体逻辑由service层完成
            // TODO 用户退出登录的逻辑
            boolean result = userService.logoutUser(session);
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /* TODO 细化异常类型
        catch (Exception e) {

        }
        */
    }

    // Other controller methods for the remaining requirements
    // ...
}