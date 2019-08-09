package uet.k59t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uet.k59t.dto.UserDto;
import uet.k59t.service.UserService;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //create User
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    //find User by id
    @RequestMapping(value = "getuser", method = RequestMethod.GET)
    public ResponseEntity<?> findUser(@PathVariable("user_id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.login(userDto));

    }
}
