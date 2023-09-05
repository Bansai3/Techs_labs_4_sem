package ru.fedotov.dto.users.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.fedotov.dto.users.request_dto.CreateAdminDto;
import ru.fedotov.dto.users.request_dto.CreateUserDto;
import ru.fedotov.dto.users.request_dto.UpdateUserDto;
import ru.fedotov.dto.users.request_models.CreateAdminRequest;
import ru.fedotov.dto.users.request_models.CreateUserRequest;
import ru.fedotov.dto.users.request_models.UpdateUserRequest;
import ru.fedotov.dto.users.response_dto.UserDto;
import ru.fedotov.jpa.users.entity.User;
import ru.fedotov.jpa.users.role.Role;
import ru.fedotov.jpa.users.status.Status;


@Mapper(componentModel = "spring", imports = {Role.class, Status.class, BCryptPasswordEncoder.class})
public interface UserMapper {
    @Mapping(target = "status", expression = "java(Status.ACTIVE)")
    @Mapping(target = "role", expression = "java(Role.ADMIN)")
    CreateAdminDto CreateAdminRequestToCreateAdminDto(CreateAdminRequest createAdminRequest);

    @Mapping(target = "status", expression = "java(Status.ACTIVE)")
    @Mapping(target = "role", expression = "java(Role.USER)")
    CreateUserDto CreateUserRequestToCreateUserDto(CreateUserRequest createUserRequest);
    @Mapping(target = "id", expression = "java(0)")
    @Mapping(target = "owner", expression = "java(null)")
    User CreateAdminDtoToAdmin(CreateAdminDto createAdminDto);

    @Mapping(target = "id", expression = "java(0)")
    @Mapping(target = "owner", expression = "java(null)")
    @Mapping(target = "password", expression = "java((new BCryptPasswordEncoder(12)).encode(createUserDto.getPassword()))")
    User CreateUserDtoToUser(CreateUserDto createUserDto);
    @Mapping(target = "ownerId", expression = "java(user.getOwner() == null ? -1 : user.getOwner().getId())")
    UserDto UserToUserDto(User user);

    UpdateUserDto UpdateUserRequestToUpdateUserDto(UpdateUserRequest updateUserRequest);
}
