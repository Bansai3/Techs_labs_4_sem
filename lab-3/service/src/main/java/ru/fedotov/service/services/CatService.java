package ru.fedotov.service.services;

import ru.fedotov.service.request_dto.CreateCatDto;
import ru.fedotov.service.request_dto.GetCatsByParametersDto;
import ru.fedotov.service.request_models.CreateCatRequest;
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
