package com.web.FileTran.web.function;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {
    @PostMapping
    public ResponseEntity<String> getSessionId(HttpSession session)
    {
        String sessionId = session.getId();
        return ResponseEntity.ok(sessionId);
    }
}
