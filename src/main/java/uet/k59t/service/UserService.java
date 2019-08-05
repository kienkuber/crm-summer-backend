package uet.k59t.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import uet.k59t.dto.UserDto;
import uet.k59t.model.User;
import uet.k59t.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        User user = userRepository.findByUserName(userDto.getUserName());

        if(user == null){
            user = new User();
            user.setUserName(userDto.getUserName());
            user.setPassword(userDto.getPassword());
            user.setToken(UUID.randomUUID().toString());
            userRepository.save(user);
            return userDto;
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Not Found",
                    new Error());
        }
    }

    public UserDto getUserById(Long id) {
        UserDto userDto = new UserDto();
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            userDto.setUserName(userOptional.get().getUserName());
            return userDto;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Id Invalid",
                    new Error()
            );
        }
    }
}
