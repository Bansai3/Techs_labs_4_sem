package ru.fedotov.service.mappers;

import ru.fedotov.dao.entity.Owner;
import ru.fedotov.service.response_dto.OwnerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedotov.service.request_dto.CreateOwnerDto;
import ru.fedotov.service.request_dto.GetOwnersByParametersDto;
import ru.fedotov.service.request_models.CreateOwnerRequest;
import ru.fedotov.service.request_models.GetOwnersByParametersRequest;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring", imports = {HashSet.class, Collectors.class, Stream.class})
public interface OwnerMapper {
    @Mapping(target = "catsIds", expression = "java(owner.getCats().stream().flatMap(c -> Stream.of(c.getId())).collect(Collectors.toSet()))")
    OwnerDto OwnerToOwnerDto(Owner owner);

    @Mapping(target = "cats", expression = "java(new HashSet<>())")
    @Mapping(target = "id", expression = "java(0)")
    Owner CreateOwnerDtoToOwner(CreateOwnerDto createOwnerDto);

    CreateOwnerDto CreateOwnerRequestToCreateOwnerDto(CreateOwnerRequest createOwnerRequest);
    GetOwnersByParametersDto GetOwnersByParametersRequestToGetOwnersByParametersDto(GetOwnersByParametersRequest getOwnersByParametersRequest);
}
