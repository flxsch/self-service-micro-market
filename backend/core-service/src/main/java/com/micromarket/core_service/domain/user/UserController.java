package com.micromarket.core_service.domain.user;

import com.micromarket.core_service.domain.user.dto.UserDTO;
import com.micromarket.core_service.domain.user.dto.UserMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UserController(final UserService userService, final UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<User> user = userService.findAll();
        return new ResponseEntity<>(userMapper.toDTOs(user), HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity <UserDTO> createUser(@RequestBody User user) {
        if (user.getCreatedByUserId() == null) {
            user.setCreatedByUserId(user.getId());
        }
        user.setCreatedAt(now());
        user.setLastModifiedBy(user.getCreatedByUserId());
        user.setUpdatedAt(now());
        User createdUser = userService.save(user);
        return new ResponseEntity<>(userMapper.toDTO(createdUser), HttpStatus.OK);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<UserDTO> getUserById(@PathVariable final String id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<UserDTO> updateUser(@PathVariable final String id, @RequestBody User user) {
        user.setUpdatedAt(now());
        User updatedUser = null;
        try {
            updatedUser = userService.updateById(id, user);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(userMapper.toDTO(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public ResponseEntity<UserDTO> deleteUser(@PathVariable final String id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
