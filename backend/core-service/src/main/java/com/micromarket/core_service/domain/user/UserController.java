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
    public ResponseEntity <UserDTO> createUser(@RequestBody UserDTO inputedUser) {
        if (inputedUser.getCreatedByUserId() == null) {
            inputedUser.setCreatedByUserId("This User");
        }
        inputedUser.setCreatedAt(now().toString());
        inputedUser.setLastModifiedBy(inputedUser.getCreatedByUserId());
        inputedUser.setUpdatedAt(now().toString());
        User user = userMapper.fromDTO(inputedUser);
        User createdUser = userService.save(user);
        return new ResponseEntity<>(userMapper.toDTO(createdUser), HttpStatus.OK);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<UserDTO> getUserById(@PathVariable final String id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<UserDTO> updateUser(@PathVariable final String id, @RequestBody UserDTO inputedUser) {
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
    public ResponseEntity<UserDTO> deleteUser(@PathVariable final String id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
