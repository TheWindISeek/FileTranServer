package com.web.FileTran.web.function;

import com.web.FileTran.dto.UserDTO;
import com.web.FileTran.vo.UserVO;
import com.web.FileTran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
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
            // 注册并自动登录
            userService.registerUser(username, password,session);
            UserDTO newUser = userService.loginUser(username, password,session);
            UserVO userVO = new UserVO(newUser.getId(), newUser.getUsername(),newUser.getUserDirectoryId(),newUser.getFavoritesFolderId());
            return ResponseEntity.ok(userVO);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Stream.of(e.getStackTrace()).forEach(System.out::println);
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
            UserDTO user = userService.loginUser(username, password,session);
            UserVO userVO = new UserVO(user.getId(), user.getUsername(),user.getUserDirectoryId(),user.getFavoritesFolderId());
            return ResponseEntity.ok(userVO);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Stream.of(e.getStackTrace()).forEach(System.out::println);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        /* 暂时不管了TODO 细化异常类型
        catch (Exception e) {

        }
        */
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logoutUser(HttpSession session) {
        // 账号相关的逻辑完全交给service层完成controller只负责类型转换
        try {
            // 具体逻辑由service层完成
            // 用户退出登录的逻辑
            boolean result = userService.logoutUser(session);
            return ResponseEntity.ok(result);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Stream.of(e.getStackTrace()).forEach(System.out::println);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        /* 暂时不管了TODO 细化异常类型
        catch (Exception e) {

        }
        */
    }

    // Other controller methods for the remaining requirements
    // ...
}