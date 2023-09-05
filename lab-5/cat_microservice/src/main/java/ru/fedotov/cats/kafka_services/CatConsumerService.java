package ru.fedotov.cats.kafka_services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import ru.fedotov.cats.services.CatServiceImpl;
import ru.fedotov.dto.cats.request_dto.CreateCatDto;
import ru.fedotov.dto.cats.request_dto.GetCatsByParametersDto;
import ru.fedotov.dto.cats.request_dto.UpdateCatDto;
import ru.fedotov.dto.cats.response_dto.CatDto;
import ru.fedotov.dto.pair.Pair;

import java.util.List;

@Service
public class CatConsumerService {
    @Autowired
    private CatServiceImpl catService;

    @KafkaListener(topics = "get_all_cats_request", groupId = "cats")
    @SendTo
    @Transactional
    public CatDto[] consumeAllCats(String msg) {
        List<CatDto> cats = catService.getAllCats();
        return cats.stream().toArray(CatDto[] :: new);
    }

    @KafkaListener(topics = "get_cat_request", groupId = "cats")
    @SendTo
    @Transactional
    public CatDto consumeCat(Long msg) {
        return catService.getCatById(msg);
    }

    @KafkaListener(topics = "get_specific_cats_request", groupId = "cats")
    @SendTo
    @Transactional
    public CatDto[] consumeSpecificCats(GetCatsByParametersDto msg) {
        List<CatDto> cats = catService.getCatsByParameters(msg);
        return cats.stream().toArray(CatDto[] :: new);
    }

    @KafkaListener(topics = "delete_cat_request", groupId = "cats")
    @Transactional
    public void consumeDeleteCat(Long msg) {
        catService.deleteCat(msg);
    }

    @KafkaListener(topics = "add_cat_request", groupId = "cats")
    @Transactional
    public void consumeAddCat(CreateCatDto msg) {
        catService.addCat(msg);
    }

    @KafkaListener(topics = "update_cat_request", groupId = "cats")
    @Transactional
    public void consumeUpdateCat(UpdateCatDto msg) {
        catService.updateCat(msg);
    }

    @KafkaListener(topics = "make_cats_friends_request", groupId = "cats")
    @Transactional
    public void consumeMakeCatsFriends(Pair<Integer, Integer> msg) {
        catService.makeCatsFriends(msg.getKey().longValue(), msg.getValue().longValue());
    }

    @KafkaListener(topics = "end_cats_friendship_request", groupId = "cats")
    @Transactional
    public void consumeEndCatsFriendship(Pair<Integer, Integer> msg) {
        catService.endCatsFriendship(msg.getKey().longValue(), msg.getValue().longValue());
    }
}
