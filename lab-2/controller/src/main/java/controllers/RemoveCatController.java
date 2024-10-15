package controllers;

import dao.DAO;
import request_dto.RemoveCatDto;
import entity.Cat;
import service.CatService;

public class RemoveCatController {
    private CatService catService;

    public RemoveCatController(CatService catService) {
        this.catService = catService;
    }

    public void RemoveCat(RemoveCatDto removeCatDto) {
        if (removeCatDto == null)
            throw new IllegalArgumentException("Model of cat removal is not defined!");
        catService.Remove(removeCatDto.getCatId());
    }
}
