package ru.fedotov.dto.owners.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedotov.dto.owners.request_dto.CreateOwnerDto;
import ru.fedotov.dto.owners.request_dto.GetOwnersByParametersDto;
import ru.fedotov.dto.owners.request_dto.UpdateOwnerDto;
import ru.fedotov.dto.owners.request_models.CreateOwnerRequest;
import ru.fedotov.dto.owners.request_models.GetOwnersByParametersRequest;
import ru.fedotov.dto.owners.request_models.UpdateOwnerRequest;
import ru.fedotov.dto.owners.response_dto.OwnerDto;
import ru.fedotov.jpa.owners.entity.Owner;

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

    UpdateOwnerDto UpdateOwnerRequestToUpdateOwnerDto(UpdateOwnerRequest updateOwnerRequest);
    GetOwnersByParametersDto GetOwnersByParametersRequestToGetOwnersByParametersDto(GetOwnersByParametersRequest getOwnersByParametersRequest);
}
