package ru.fedotov.cats.services;

import ru.fedotov.dto.cats.request_dto.CreateCatDto;
import ru.fedotov.dto.cats.request_dto.GetCatsByParametersDto;
import ru.fedotov.dto.cats.request_dto.UpdateCatDto;
import ru.fedotov.dto.cats.response_dto.CatDto;

import java.util.List;

public interface CatService {
    void addCat(CreateCatDto createCatDto);
    void deleteCat(Long catId);
    List<CatDto> getAllCats();
    List<CatDto> getCatsByParameters(GetCatsByParametersDto getCatsByParametersDto);
    void makeCatsFriends(Long catId1, Long catId2);
    void endCatsFriendship(Long catId1, Long catId2);
    CatDto getCatById(Long catId);
    void updateCat(UpdateCatDto updateCatDto);
}
