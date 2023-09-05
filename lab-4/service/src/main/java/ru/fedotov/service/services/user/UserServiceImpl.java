package ru.fedotov.service.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedotov.dao.dao.OwnerRepository;
import ru.fedotov.dao.dao.UserRepository;
import ru.fedotov.dao.entity.Owner;
import ru.fedotov.dao.entity.User;
import ru.fedotov.service.mappers.UserMapper;
import ru.fedotov.service.request_dto.user.CreateAdminDto;
import ru.fedotov.service.request_dto.user.CreateUserDto;
import ru.fedotov.service.request_dto.user.UpdateUserDto;
import ru.fedotov.service.response_dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addAdmin(CreateAdminDto createAdminDto) {
        if (checkEqualUsers(createAdminDto.getUserName()) == true)
            throw new IllegalStateException("User with the name " + createAdminDto.getUserName() + " already exists!");
        User user = userMapper.CreateAdminDtoToAdmin(createAdminDto);
        userRepository.save(user);
    }

    @Override
    public void addUser(CreateUserDto createUserDto) {
        if (checkEqualUsers(createUserDto.getUserName()) == true)
            throw new IllegalStateException("User with the name " + createUserDto.getUserName() + " already exists!");
        User user = userMapper.CreateUserDtoToUser(createUserDto);
        Owner owner = ownerRepository.getReferenceById(createUserDto.getOwnerId());
        owner.setUser(user);
        user.setOwner(owner);
        userRepository.save(user);
        ownerRepository.save(owner);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> getAllUsers() {
       return userRepository.findAll().stream().map(u -> userMapper.UserToUserDto(u)).collect(Collectors.toList());
    }


    @Override
    public UserDto getUserById(Long userId) {
       return userMapper.UserToUserDto(userRepository.getReferenceById(userId));
    }

    @Override
    public void updateUser(UpdateUserDto updateUserDto) {
        if (userRepository.findByUserName(updateUserDto.getUserName()) != null)
            throw new IllegalStateException("Can not update user because user with userName "
                    + updateUserDto.getUserName() + " already exists!");
        User user = userRepository.getReferenceById(updateUserDto.getUserId());
        user.setUserName(updateUserDto.getUserName());
        user.setStatus(updateUserDto.getStatus());
        user.setPassword(updateUserDto.getPassword());
        userRepository.save(user);
    }

    private boolean checkEqualUsers(String userName) {
        User user = userRepository.findByUserName(userName);
        return user != null;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public OwnerRepository getOwnerRepository() {
        return this.ownerRepository;
    }

    public UserMapper getUserMapper() {
        return this.userMapper;
    }
}
