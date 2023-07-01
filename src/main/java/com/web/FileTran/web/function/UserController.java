package com.web.FileTran.web.function;

import com.web.FileTran.vo.UserVO;
import com.web.FileTran.dto.UserDTO;
import com.web.FileTran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    // Constructor with dependency injection

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint for querying user information
    @GetMapping("/userinfo/{userId}")
    public ResponseEntity<UserVO> getUserInfo(
            @PathVariable int userId,
            HttpSession session) {
        // TODO 根据session进行检测,对未登录用户重定向
        try {
            UserDTO userDTO = userService.getUserInfoById(userId,session);
            UserVO userVO = null;
            // TODO DTO转VO
            return ResponseEntity.ok(userVO);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}