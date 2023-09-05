package ru.fedotov.dto.cats.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fedotov.dto.cats.request_dto.CreateCatDto;
import ru.fedotov.dto.cats.request_dto.GetCatsByParametersDto;
import ru.fedotov.dto.cats.request_dto.UpdateCatDto;
import ru.fedotov.dto.cats.request_models.CreateCatRequest;
import ru.fedotov.dto.cats.request_models.GetCatsByParametersRequest;
import ru.fedotov.dto.cats.request_models.UpdateCatRequest;
import ru.fedotov.dto.cats.response_dto.CatDto;
import ru.fedotov.jpa.cats.entity.Cat;

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

    UpdateCatDto UpdateCatRequestToUpdateCatDto(UpdateCatRequest updateCatRequest);
    @Mapping(target = "userName", expression = "java(userName)")
    GetCatsByParametersDto GetCatsByParametersRequestToGetCatsByParametersDto(GetCatsByParametersRequest getCatsByParametersRequest, String userName);
}
