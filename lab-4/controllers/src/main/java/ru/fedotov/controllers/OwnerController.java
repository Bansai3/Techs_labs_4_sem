package ru.fedotov.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.fedotov.service.mappers.OwnerMapper;
import ru.fedotov.service.request_models.owner.CreateOwnerRequest;
import ru.fedotov.service.request_models.owner.GetOwnersByParametersRequest;
import ru.fedotov.service.response_dto.OwnerDto;
import ru.fedotov.service.services.owner.OwnerServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerServiceImpl ownerService;
    @Autowired
    private OwnerMapper ownerMapper;
    @GetMapping("/get_all_owners")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<OwnerDto> getOwners() {
       return ownerService.getAllOwners();
    }

    @GetMapping("/get_owner/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public OwnerDto getOwner(@PathVariable("id") Long ownerId) {
        return ownerService.getOwnerById(ownerId);
    }

    @GetMapping("/get_specific_owners")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<OwnerDto> getSpecificOwners(@RequestBody @Valid GetOwnersByParametersRequest getOwnersByParametersRequest) {
        return ownerService.getOwnersByParameters(ownerMapper.GetOwnersByParametersRequestToGetOwnersByParametersDto(getOwnersByParametersRequest));
    }

    @PostMapping("/add_owner")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addOwner(@RequestBody @Valid CreateOwnerRequest createOwnerRequest) {
        ownerService.addOwner(ownerMapper.CreateOwnerRequestToCreateOwnerDto(createOwnerRequest));
    }

    @DeleteMapping("/delete_owner/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteOwner(@PathVariable("id") Long ownerId) {
        ownerService.deleteOwner(ownerId);
    }

    @PatchMapping("/update_owner/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateOwner(@RequestBody @Valid CreateOwnerRequest createOwnerRequest, @PathVariable("id") Long ownerId) {
        ownerService.updateOwner(ownerMapper.CreateOwnerRequestToCreateOwnerDto(createOwnerRequest), ownerId);
    }

    @PatchMapping("/adopt_cat/{ownerId}, {catId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void adoptCat(@PathVariable("ownerId") Long ownerId, @PathVariable("catId") Long catId) {
        ownerService.adoptCat(ownerId, catId);
    }

    @PatchMapping("/cancel_cat_adoption/{ownerId}, {catId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void cancelCatAdoption(@PathVariable("ownerId") Long ownerId, @PathVariable("catId") Long catId) {
        ownerService.cancelAdoption(ownerId, catId);
    }
}
