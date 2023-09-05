package controllers;

import dao.DAO;
import request_dto.CreateCatDto;
import entity.Cat;
import service.CatService;

public class AddCatController {
    private CatService catService;

    public AddCatController(CatService catService) {
        this.catService = catService;
    }

    public void AddCat(CreateCatDto catDto) {
        if (catDto == null)
            throw new IllegalArgumentException("Model of cat is not defined!");
        catService.Add(catDto);
    }
}
