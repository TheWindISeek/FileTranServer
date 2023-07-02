package com.web.FileTran.web.function;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {
    @GetMapping
    public ResponseEntity<String> getSessionId(HttpSession session)
    {
        String sessionId = session.getId();
        System.out.println(sessionId);
        return ResponseEntity.ok(sessionId);
    }
}
