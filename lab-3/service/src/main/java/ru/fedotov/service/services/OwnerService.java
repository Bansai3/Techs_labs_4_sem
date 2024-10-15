package ru.fedotov.service.services;

import ru.fedotov.service.request_dto.CreateOwnerDto;
import ru.fedotov.service.request_dto.GetOwnersByParametersDto;
import ru.fedotov.service.request_models.CreateCatRequest;
import ru.fedotov.service.request_models.CreateOwnerRequest;
import ru.fedotov.service.response_dto.OwnerDto;

import java.util.List;

public interface OwnerService {
    void addOwner(CreateOwnerDto createOwnerDto);
    void deleteOwner(Long ownerId);
    void adoptCat(Long ownerId, Long catId);
    void cancelAdoption(Long ownerId, Long catId);
    List<OwnerDto> getAllOwners();
    OwnerDto getOwnerById(Long ownerId);
    List<OwnerDto> getOwnersByParameters(GetOwnersByParametersDto getOwnersByParametersDto);
    void updateOwner(CreateOwnerDto createOwnerDto, Long ownerId);
}
