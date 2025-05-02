package com.micromarket.core_service.domain.user;

import com.micromarket.core_service.domain.user.dto.UserDTO;
import com.micromarket.core_service.domain.user.dto.UserMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(final UserService userService, final UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;

    }

    @PostMapping({"", "/"})
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        if (user.getKeycloakId() == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User createdUser = userService.save(userMapper.fromDTO(user));
        return new ResponseEntity<>(userMapper.toDTO(createdUser), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> retrieveById(@PathVariable String email) {
        try {
            User user = userService.findByEmail(email);
            return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<User> user = userService.findAll();
        return new ResponseEntity<>(userMapper.toDTOs(user), HttpStatus.OK);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO inputedUser) {
        if (inputedUser.getLastModifiedBy() == null) {
            inputedUser.setLastModifiedBy(inputedUser.getId());
        }
        inputedUser.setUpdatedAt(now().toString());
        User newUser = userMapper.fromDTO(inputedUser);
        User updatedUser = null;
        try {
            updatedUser = userService.updateById(id, newUser);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(userMapper.toDTO(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public ResponseEntity<UserDTO> deleteUser(@PathVariable String id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
