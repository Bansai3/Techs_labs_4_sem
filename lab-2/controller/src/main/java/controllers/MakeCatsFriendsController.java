package controllers;

import request_dto.MakeCatsFriendsDto;
import service.CatService;

public class MakeCatsFriendsController {
    private CatService catService;

    public MakeCatsFriendsController(CatService catService) {
        this.catService = catService;
    }

    public void MakeCatsFriends(MakeCatsFriendsDto makeCatsFriendsDto) {
        if (makeCatsFriendsDto == null)
            throw new IllegalArgumentException("Model of making cats friends is not defined!");
        catService.MakeCatsFriends(makeCatsFriendsDto.getCatId1(), makeCatsFriendsDto.getCatId2());
    }
}
