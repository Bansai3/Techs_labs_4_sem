package controllers;

import dao.DAO;
import request_dto.CreateOwnerDto;
import entity.Owner;
import service.OwnerService;


public class AddOwnerController {
    private OwnerService ownerService;

    public AddOwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public void AddOwner(CreateOwnerDto ownerDto) {
        if (ownerDto == null)
            throw new IllegalArgumentException("Model of owner is not defined!");
        ownerService.Add(ownerDto);
    }
}
