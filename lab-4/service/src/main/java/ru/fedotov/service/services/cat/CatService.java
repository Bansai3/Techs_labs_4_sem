package ru.fedotov.service.services.cat;

import ru.fedotov.service.request_dto.cat.CreateCatDto;
import ru.fedotov.service.request_dto.cat.GetCatsByParametersDto;
import ru.fedotov.service.response_dto.CatDto;

import java.util.List;

public interface CatService {
    void addCat(CreateCatDto createCatDto);
    void deleteCat(Long catId);
    List<CatDto> getAllCats();
    List<CatDto> getCatsByParameters(GetCatsByParametersDto getCatsByParametersDto);
    void makeCatsFriends(Long catId1, Long catId2);
    void endCatsFriendship(Long catId1, Long catId2);
    CatDto getCatById(Long catId);
    void updateCat(CreateCatDto createCatDto, Long catId);
}
