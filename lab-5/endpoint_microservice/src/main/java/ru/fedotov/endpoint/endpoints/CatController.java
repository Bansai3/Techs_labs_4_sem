package ru.fedotov.endpoint.endpoints;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.fedotov.dto.cats.mappers.CatMapper;
import ru.fedotov.dto.cats.request_dto.CreateCatDto;
import ru.fedotov.dto.cats.request_dto.GetCatsByParametersDto;
import ru.fedotov.dto.cats.request_dto.UpdateCatDto;
import ru.fedotov.dto.cats.request_models.CreateCatRequest;
import ru.fedotov.dto.cats.request_models.GetCatsByParametersRequest;
import ru.fedotov.dto.cats.request_models.UpdateCatRequest;
import ru.fedotov.dto.cats.response_dto.CatDto;
import ru.fedotov.dto.pair.Pair;
import ru.fedotov.endpoint.kafka_services.KafkaService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/cats")
public class CatController {
    @Autowired
    private CatMapper catMapper;
    @Autowired
    private KafkaService kafkaService;

    @GetMapping("/get_all_cats")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public CatDto[] getCats() throws ExecutionException, InterruptedException, TimeoutException {
       return kafkaService.sendSync("get_all_cats_request", "get_all_cats");
    }

    @GetMapping("/get_cat/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public CatDto getCat(@PathVariable("id") Long catId) throws ExecutionException, InterruptedException {
       return kafkaService.sendSync("get_cat_request", catId);
    }

    @GetMapping("/get_specific_cats")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public CatDto[] getSpecificCats(@RequestBody @Valid GetCatsByParametersRequest getCatsByParametersRequest,
                                        @AuthenticationPrincipal(expression = "username") String userName)
            throws ExecutionException, InterruptedException, TimeoutException {
        GetCatsByParametersDto getCatsByParametersDto = catMapper.GetCatsByParametersRequestToGetCatsByParametersDto(getCatsByParametersRequest, userName);
        return kafkaService.sendSync("get_specific_cats_request", getCatsByParametersDto);
    }

    @PostMapping("/add_cat")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addCat(@RequestBody @Valid CreateCatRequest createCatRequest) {
        CreateCatDto createCatDto = catMapper.CreateCatRequestToCreateCatDto(createCatRequest);
        kafkaService.sendAsync("add_cat_request", createCatDto);
    }

    @DeleteMapping("/delete_cat/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCat(@PathVariable("id") Long catId) {
      kafkaService.sendAsync("delete_cat_request", catId);
    }

    @PatchMapping("/update_cat")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCat(@RequestBody @Valid UpdateCatRequest updateCatRequest) {
        UpdateCatDto updateCatDto = catMapper.UpdateCatRequestToUpdateCatDto(updateCatRequest);
        kafkaService.sendAsync("update_cat_request", updateCatDto);
    }

    @PatchMapping("/make_cats_friends/{id1}, {id2}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void makeCatsFriends(@PathVariable("id1") Long catId1, @PathVariable("id2") Long catId2) {
        Pair<Long, Long> catsIdsPair = new Pair<>(catId1, catId2);
        kafkaService.sendAsync("make_cats_friends_request", catsIdsPair);
    }

    @PatchMapping("/end_cats_friendship/{id1}, {id2}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void endCatsFriendship(@PathVariable("id1") Long catId1, @PathVariable("id2") Long catId2) {
        Pair<Long, Long> catsIdsPair = new Pair<>(catId1, catId2);
        kafkaService.sendAsync("end_cats_friendship_request", catsIdsPair);
    }
}