package ru.fedotov.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedotov.dao.entity.Cat;
import ru.fedotov.service.request_dto.cat.CreateCatDto;
import ru.fedotov.service.request_dto.cat.GetCatsByParametersDto;
import ru.fedotov.service.request_models.cat.CreateCatRequest;
import ru.fedotov.service.request_models.cat.GetCatsByParametersRequest;
import ru.fedotov.service.response_dto.CatDto;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Mapping(target = "userName", expression = "java(userName)")
    GetCatsByParametersDto GetCatsByParametersRequestToGetCatsByParametersDto(GetCatsByParametersRequest getCatsByParametersRequest, String userName);
}
