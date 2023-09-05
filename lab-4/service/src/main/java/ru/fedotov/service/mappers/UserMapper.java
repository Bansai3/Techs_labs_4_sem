package ru.fedotov.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedotov.dao.entity.User;
import ru.fedotov.dao.role.Role;
import ru.fedotov.dao.status.Status;
import ru.fedotov.service.request_dto.user.CreateAdminDto;
import ru.fedotov.service.request_dto.user.CreateUserDto;
import ru.fedotov.service.request_dto.user.UpdateUserDto;
import ru.fedotov.service.request_models.user.CreateAdminRequest;
import ru.fedotov.service.request_models.user.CreateUserRequest;
import ru.fedotov.service.request_models.user.UpdateUserRequest;
import ru.fedotov.service.response_dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
