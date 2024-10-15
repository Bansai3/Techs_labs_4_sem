package ru.fedotov.service.services.user;

import ru.fedotov.service.request_dto.user.CreateAdminDto;
import ru.fedotov.service.request_dto.user.CreateUserDto;
import ru.fedotov.service.request_dto.user.UpdateUserDto;
import ru.fedotov.service.response_dto.UserDto;

import java.util.List;

public interface UserService {
    void addAdmin(CreateAdminDto createAdminDto);
    void addUser(CreateUserDto createUserDto);
    void deleteUser(Long userId);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long userId);
    void updateUser(UpdateUserDto updateUserDto);
}
