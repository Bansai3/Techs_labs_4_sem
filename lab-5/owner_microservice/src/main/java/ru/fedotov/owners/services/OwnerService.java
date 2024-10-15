package ru.fedotov.owners.services;



import ru.fedotov.dto.owners.request_dto.CreateOwnerDto;
import ru.fedotov.dto.owners.request_dto.GetOwnersByParametersDto;
import ru.fedotov.dto.owners.request_dto.UpdateOwnerDto;
import ru.fedotov.dto.owners.response_dto.OwnerDto;

import java.util.List;

public interface OwnerService {
    void addOwner(CreateOwnerDto createOwnerDto);
    void deleteOwner(Long ownerId);
    void adoptCat(Long ownerId, Long catId);
    void cancelAdoption(Long ownerId, Long catId);
    List<OwnerDto> getAllOwners();
    OwnerDto getOwnerById(Long ownerId);
    List<OwnerDto> getOwnersByParameters(GetOwnersByParametersDto getOwnersByParametersDto);
    void updateOwner(UpdateOwnerDto updateOwnerDto);
}
