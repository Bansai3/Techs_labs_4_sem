package ru.fedotov.endpoint.endpoints;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.fedotov.dto.owners.mappers.OwnerMapper;
import ru.fedotov.dto.owners.request_dto.CreateOwnerDto;
import ru.fedotov.dto.owners.request_dto.GetOwnersByParametersDto;
import ru.fedotov.dto.owners.request_dto.UpdateOwnerDto;
import ru.fedotov.dto.owners.request_models.CreateOwnerRequest;
import ru.fedotov.dto.owners.request_models.GetOwnersByParametersRequest;
import ru.fedotov.dto.owners.request_models.UpdateOwnerRequest;
import ru.fedotov.dto.owners.response_dto.OwnerDto;
import ru.fedotov.dto.pair.Pair;
import ru.fedotov.endpoint.kafka_services.KafkaService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerMapper ownerMapper;

    @Autowired
    private KafkaService kafkaService;
    @GetMapping("/get_all_owners")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public OwnerDto[] getOwners() throws ExecutionException, InterruptedException {
        return kafkaService.sendSync("get_all_owners_request", "get_all_owners");
    }

    @GetMapping("/get_owner/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public OwnerDto getOwner(@PathVariable("id") Long ownerId) throws ExecutionException, InterruptedException {
        return kafkaService.sendSync("get_owner_request", ownerId);
    }

    @GetMapping("/get_specific_owners")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public OwnerDto[] getSpecificOwners(@RequestBody @Valid GetOwnersByParametersRequest getOwnersByParametersRequest)
            throws ExecutionException, InterruptedException {
        GetOwnersByParametersDto getOwnersByParametersDto = ownerMapper.GetOwnersByParametersRequestToGetOwnersByParametersDto(getOwnersByParametersRequest);
        return kafkaService.sendSync("get_specific_owners_request", getOwnersByParametersDto);
    }

    @PostMapping("/add_owner")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addOwner(@RequestBody @Valid CreateOwnerRequest createOwnerRequest) {
        CreateOwnerDto createOwnerDto = ownerMapper.CreateOwnerRequestToCreateOwnerDto(createOwnerRequest);
       kafkaService.sendAsync("add_owner_request", createOwnerDto);
    }

    @DeleteMapping("/delete_owner/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteOwner(@PathVariable("id") Long ownerId) {
       kafkaService.sendAsync("delete_owner_request", ownerId);
    }

    @PatchMapping("/update_owner")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateOwner(@RequestBody @Valid UpdateOwnerRequest updateOwnerRequest) {
        UpdateOwnerDto updateOwnerDto = ownerMapper.UpdateOwnerRequestToUpdateOwnerDto(updateOwnerRequest);
        kafkaService.sendAsync("update_owner_request", updateOwnerDto);
    }

    @PatchMapping("/adopt_cat/{ownerId}, {catId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void adoptCat(@PathVariable("ownerId") Long ownerId, @PathVariable("catId") Long catId) {
        Pair<Long, Long> ownerCatIdsPair = new Pair(ownerId, catId);
        kafkaService.sendAsync("adopt_cat_request", ownerCatIdsPair);
    }

    @PatchMapping("/cancel_cat_adoption/{ownerId}, {catId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void cancelCatAdoption(@PathVariable("ownerId") Long ownerId, @PathVariable("catId") Long catId) {
        Pair<Long, Long> ownerCatIdsPair = new Pair(ownerId, catId);
        kafkaService.sendAsync("cancel_cat_adoption_request", ownerCatIdsPair);
    }
}
