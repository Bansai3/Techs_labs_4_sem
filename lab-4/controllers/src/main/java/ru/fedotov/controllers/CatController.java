package ru.fedotov.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.fedotov.service.mappers.CatMapper;
import ru.fedotov.service.request_models.cat.CreateCatRequest;
import ru.fedotov.service.request_models.cat.GetCatsByParametersRequest;
import ru.fedotov.service.response_dto.CatDto;
import ru.fedotov.service.services.cat.CatServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {
    @Autowired
    private CatServiceImpl catService;
    @Autowired
    private CatMapper catMapper;

    @GetMapping("/get_all_cats")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<CatDto> getCats() {
        return catService.getAllCats();
    }

    @GetMapping("/get_cat/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public CatDto getCat(@PathVariable("id") Long catId) {
        return catService.getCatById(catId);
    }

    @GetMapping("/get_specific_cats")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<CatDto> getSpecificCats(@RequestBody @Valid GetCatsByParametersRequest getCatsByParametersRequest,
                                        @AuthenticationPrincipal(expression = "username") String userName){
        return catService.getCatsByParameters(catMapper.GetCatsByParametersRequestToGetCatsByParametersDto(getCatsByParametersRequest, userName));
    }

    @PostMapping("/add_cat")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addCat(@RequestBody @Valid CreateCatRequest createCatRequest) {
        catService.addCat(catMapper.CreateCatRequestToCreateCatDto(createCatRequest));
    }

    @DeleteMapping("/delete_cat/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCat(@PathVariable("id") Long catId) {
       catService.deleteCat(catId);
    }

    @PatchMapping("/update_cat/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCat(@RequestBody @Valid CreateCatRequest createCatRequest, @PathVariable("id") Long catId) {
       catService.updateCat(catMapper.CreateCatRequestToCreateCatDto(createCatRequest), catId);
    }

    @PatchMapping("/make_cats_friends/{id1}, {id2}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void makeCatsFriends(@PathVariable("id1") Long catId1, @PathVariable("id2") Long catId2) {
       catService.makeCatsFriends(catId1, catId2);
    }

    @PatchMapping("/end_cats_friendship/{id1}, {id2}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void endCatsFriendship(@PathVariable("id1") Long catId1, @PathVariable("id2") Long catId2) {
        catService.endCatsFriendship(catId1, catId2);
    }
}