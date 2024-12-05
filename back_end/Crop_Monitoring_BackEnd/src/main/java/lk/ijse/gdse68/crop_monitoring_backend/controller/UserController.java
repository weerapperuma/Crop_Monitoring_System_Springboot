package lk.ijse.gdse68.crop_monitoring_backend.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse68.crop_monitoring_backend.dto.impl.UserDTO;
import lk.ijse.gdse68.crop_monitoring_backend.exception.AlreadyExistsException;
import lk.ijse.gdse68.crop_monitoring_backend.exception.DataPersistFailedException;
import lk.ijse.gdse68.crop_monitoring_backend.exception.NotFoundException;
import lk.ijse.gdse68.crop_monitoring_backend.service.UserService;
import lk.ijse.gdse68.crop_monitoring_backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final Mapping mapping;

    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO user) {
        log.info("Attempting to save user with email: {}", user.getEmail());
        try {
            userService.saveUser(user);
            log.info("User saved successfully with email: {}", user.getEmail());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            log.warn("User already exists with email: {}", user.getEmail());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailedException e) {
            log.error("Failed to persist user with email: {}", user.getEmail(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        log.info("Fetching user with email: {}", email);
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PatchMapping(value = "/{email}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO user, @PathVariable("email") String email) {
        log.info("Attempting to update user with email: {}", email);
        try {
            userService.updateUser(user, email);
            log.info("User updated successfully with email: {}", email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            log.warn("User not found with email: {}", email);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}