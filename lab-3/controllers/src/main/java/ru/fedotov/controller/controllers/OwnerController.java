package ru.fedotov.controller.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.fedotov.service.mappers.OwnerMapper;
import ru.fedotov.service.request_models.CreateOwnerRequest;
import ru.fedotov.service.request_models.GetOwnersByParametersRequest;
import ru.fedotov.service.response_dto.OwnerDto;
import ru.fedotov.service.services.OwnerServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerServiceImpl ownerService;
    @Autowired
    private OwnerMapper ownerMapper;
    @GetMapping("/get_all_owners")
    public List<OwnerDto> getOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/get_owner/{id}")
    public OwnerDto getOwner(@PathVariable("id") Long ownerId) {
        return ownerService.getOwnerById(ownerId);
    }

    @GetMapping("/get_specific_owners")
    public List<OwnerDto> getSpecificOwners(@RequestBody @Valid GetOwnersByParametersRequest getOwnersByParametersRequest) {
        return ownerService.getOwnersByParameters(ownerMapper.GetOwnersByParametersRequestToGetOwnersByParametersDto(getOwnersByParametersRequest));
    }

    @PostMapping("/add_owner")
    public void addOwner(@RequestBody @Valid CreateOwnerRequest createOwnerRequest) {
        ownerService.addOwner(ownerMapper.CreateOwnerRequestToCreateOwnerDto(createOwnerRequest));
    }

    @DeleteMapping("/delete_owner/{id}")
    public void deleteOwner(@PathVariable("id") Long ownerId) {
        ownerService.deleteOwner(ownerId);
    }

    @PatchMapping("/update_owner/{id}")
    public void updateOwner(@RequestBody @Valid CreateOwnerRequest createOwnerRequest, @PathVariable("id") Long ownerId) {
        ownerService.updateOwner(ownerMapper.CreateOwnerRequestToCreateOwnerDto(createOwnerRequest), ownerId);
    }

    @PatchMapping("/adopt_cat/{ownerId}, {catId}")
    public void adoptCat(@PathVariable("ownerId") Long ownerId, @PathVariable("catId") Long catId) {
        ownerService.adoptCat(ownerId, catId);
    }

    @PatchMapping("/cancel_cat/{ownerId}, {catId}")
    public void cancelCatAdoption(@PathVariable("ownerId") Long ownerId, @PathVariable("catId") Long catId) {
        ownerService.cancelAdoption(ownerId, catId);
    }
}
