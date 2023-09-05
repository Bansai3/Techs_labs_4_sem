package ru.fedotov.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedotov.dao.entity.Owner;
import ru.fedotov.service.request_dto.owner.CreateOwnerDto;
import ru.fedotov.service.request_dto.owner.GetOwnersByParametersDto;
import ru.fedotov.service.request_models.owner.CreateOwnerRequest;
import ru.fedotov.service.request_models.owner.GetOwnersByParametersRequest;
import ru.fedotov.service.response_dto.OwnerDto;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring", imports = {HashSet.class, Collectors.class, Stream.class})
public interface OwnerMapper {
    @Mapping(target = "catsIds", expression = "java(owner.getCats().stream().flatMap(c -> Stream.of(c.getId())).collect(Collectors.toSet()))")
    OwnerDto OwnerToOwnerDto(Owner owner);

    @Mapping(target = "cats", expression = "java(new HashSet<>())")
    @Mapping(target = "id", expression = "java(0)")
    @Mapping(target = "user", expression = "java(null)")
    Owner CreateOwnerDtoToOwner(CreateOwnerDto createOwnerDto);

    CreateOwnerDto CreateOwnerRequestToCreateOwnerDto(CreateOwnerRequest createOwnerRequest);
    GetOwnersByParametersDto GetOwnersByParametersRequestToGetOwnersByParametersDto(GetOwnersByParametersRequest getOwnersByParametersRequest);
}
