package ru.fedotov.endpoint.endpoints;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.fedotov.dto.users.mappers.UserMapper;
import ru.fedotov.dto.users.request_dto.CreateAdminDto;
import ru.fedotov.dto.users.request_dto.CreateUserDto;
import ru.fedotov.dto.users.request_dto.UpdateUserDto;
import ru.fedotov.dto.users.request_models.CreateAdminRequest;
import ru.fedotov.dto.users.request_models.CreateUserRequest;
import ru.fedotov.dto.users.request_models.UpdateUserRequest;
import ru.fedotov.dto.users.response_dto.UserDto;
import ru.fedotov.endpoint.services.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/add_admin")
    public void addAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest) {
        CreateAdminDto createAdminDto = userMapper.CreateAdminRequestToCreateAdminDto(createAdminRequest);
        userService.addAdmin(createAdminDto);
    }

    @PostMapping("/add_user")
    public void addAdmin(@RequestBody @Valid CreateUserRequest createUserRequest) {
        CreateUserDto createUserDto = userMapper.CreateUserRequestToCreateUserDto(createUserRequest);
        userService.addUser(createUserDto);
    }

    @PatchMapping("/update_user")
    public void updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        UpdateUserDto updateUserDto = userMapper.UpdateUserRequestToUpdateUserDto(updateUserRequest);
        userService.updateUser(updateUserDto);
    }

    @DeleteMapping("/delete_user/{id}")
    public void deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/get_all_users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

}
