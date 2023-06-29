package com.web.FileTran.web.newfunction;

import com.web.FileTran.newvo.UserVO;
import com.web.FileTran.newservice.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // Constructor with dependency injection

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint for querying user information
    @GetMapping("/userinfo/{userId}")
    public ResponseEntity<UserVO> getUserInfo(
            @PathVariable long userId,
            HttpSession session) {
        // TODO 根据session进行检测,对未登录用户重定向
        try {
            UserVO user = userService.getUserInfoById(userId,session);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}