package com.dat.coreservice.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/private")
public class NoteEndpoint {

    @GetMapping(path = "notes")
    public ResponseEntity<?> getNotes() {
        return ResponseEntity.ok("Authentication Successfully!");
    }

}
