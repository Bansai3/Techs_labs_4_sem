package ru.fedotov.service.mappers;

import ru.fedotov.dao.entity.Cat;
import ru.fedotov.service.response_dto.CatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedotov.service.request_dto.CreateCatDto;
import ru.fedotov.service.request_dto.GetCatsByParametersDto;
import ru.fedotov.service.request_models.CreateCatRequest;
import ru.fedotov.service.request_models.GetCatsByParametersRequest;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashSet;

@Mapper(componentModel = "spring", imports = {HashSet.class, Collectors.class, Stream.class})
public interface CatMapper {
    @Mapping(target = "ownerId", expression = "java(cat.getOwner() == null ? null : cat.getOwner().getId())")
    @Mapping(target = "friendCatsIds", expression = "java(cat.getFriendCats().stream().flatMap(c -> Stream.of(c.getId())).collect(Collectors.toSet()))")
    CatDto CatToCatDto(Cat cat);
    @Mapping(target = "owner", expression = "java(null)")
    @Mapping(target = "friendCats", expression = "java(new HashSet<>())")
    @Mapping(target = "id", expression = "java(0)")
    Cat CreateCatDtoToCat(CreateCatDto createCatDto);
    CreateCatDto CreateCatRequestToCreateCatDto(CreateCatRequest createCatRequest);
    GetCatsByParametersDto GetCatsByParametersRequestToGetCatsByParametersDto(GetCatsByParametersRequest getCatsByParametersRequest);
}
