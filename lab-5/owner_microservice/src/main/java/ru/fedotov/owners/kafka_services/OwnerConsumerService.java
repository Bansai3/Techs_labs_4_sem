package ru.fedotov.owners.kafka_services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import ru.fedotov.dto.owners.request_dto.CreateOwnerDto;
import ru.fedotov.dto.owners.request_dto.GetOwnersByParametersDto;
import ru.fedotov.dto.owners.request_dto.UpdateOwnerDto;
import ru.fedotov.dto.owners.response_dto.OwnerDto;
import ru.fedotov.dto.pair.Pair;
import ru.fedotov.owners.services.OwnerServiceImpl;

import java.util.List;

@Service
public class OwnerConsumerService {
    @Autowired
    private OwnerServiceImpl ownerService;

    @KafkaListener(topics = "get_owner_request", groupId = "owners")
    @SendTo
    @Transactional
    public OwnerDto consumeOwnerRequest(Long msg) {
        return ownerService.getOwnerById(msg);
    }
    @KafkaListener(topics = "get_all_owners_request", groupId = "owners")
    @SendTo
    @Transactional
    public OwnerDto[] consumeAllOwnersRequest(String msg) {
        List<OwnerDto> owners = ownerService.getAllOwners();
        return owners.stream().toArray(OwnerDto[] :: new);
    }
    @KafkaListener(topics = "get_specific_owners_request", groupId = "owners")
    @SendTo
    @Transactional
    public OwnerDto[] consumeSpecificOwnersRequest(GetOwnersByParametersDto msg) {
        List<OwnerDto> owners = ownerService.getAllOwners();
        return owners.stream().toArray(OwnerDto[] :: new);
    }

    @KafkaListener(topics = "delete_owner_request", groupId = "owners")
    @Transactional
    public void consumeDeleteOwnerRequest(Long msg) {
        ownerService.deleteOwner(msg);
    }

    @KafkaListener(topics = "add_owner_request", groupId = "owners")
    @Transactional
    public void consumeAddOwnerRequest(CreateOwnerDto msg) {
        ownerService.addOwner(msg);
    }

    @KafkaListener(topics = "update_owner_request", groupId = "owners")
    @Transactional
    public void consumeUpdateOwnerRequest(UpdateOwnerDto msg) {
        ownerService.updateOwner(msg);
    }

    @KafkaListener(topics = "adopt_cat_request", groupId = "owners")
    @Transactional
    public void consumeAdoptCatRequest(Pair<Integer, Integer> msg) {
        ownerService.adoptCat(msg.getKey().longValue(), msg.getValue().longValue());
    }

    @KafkaListener(topics = "cancel_cat_adoption_request", groupId = "owners")
    @Transactional
    public void consumeCancelCatAdoptionRequest(Pair<Integer, Integer> msg) {
        ownerService.cancelAdoption(msg.getValue().longValue(), msg.getKey().longValue());
    }

}
