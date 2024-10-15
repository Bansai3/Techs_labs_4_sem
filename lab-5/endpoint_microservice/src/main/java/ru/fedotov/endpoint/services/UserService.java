package ru.fedotov.endpoint.services;

import ru.fedotov.dto.users.request_dto.CreateAdminDto;
import ru.fedotov.dto.users.request_dto.CreateUserDto;
import ru.fedotov.dto.users.request_dto.UpdateUserDto;
import ru.fedotov.dto.users.response_dto.UserDto;

import java.util.List;

public interface UserService {
    void addAdmin(CreateAdminDto createAdminDto);
    void addUser(CreateUserDto createUserDto);
    void deleteUser(Long userId);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long userId);
    void updateUser(UpdateUserDto updateUserDto);
}
