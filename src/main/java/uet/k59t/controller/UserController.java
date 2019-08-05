package uet.k59t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.k59t.dto.UserDto;
import uet.k59t.service.UserService;

/**
 * Created by Long on 11/21/2016.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //create User
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public UserDto createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    //find User by id
    @RequestMapping(value = "getuser", method = RequestMethod.GET)
    public UserDto findUser(@PathVariable("user_id") Long id){
        return userService.getUserById(id);
    }
}
