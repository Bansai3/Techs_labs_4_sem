package ru.fedotov.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.fedotov.service.mappers.UserMapper;
import ru.fedotov.service.request_dto.user.CreateAdminDto;
import ru.fedotov.service.request_dto.user.CreateUserDto;
import ru.fedotov.service.request_dto.user.UpdateUserDto;
import ru.fedotov.service.request_models.user.CreateAdminRequest;
import ru.fedotov.service.request_models.user.CreateUserRequest;
import ru.fedotov.service.request_models.user.UpdateUserRequest;
import ru.fedotov.service.response_dto.UserDto;
import ru.fedotov.service.services.user.UserServiceImpl;

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
