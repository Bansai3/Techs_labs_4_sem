package controllers;

import dao.DAO;
import request_dto.RemoveOwnerDto;
import entity.Owner;
import service.OwnerService;

public class RemoveOwnerController {
    private OwnerService ownerService;

    public RemoveOwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public void RemoveOwner(RemoveOwnerDto removeOwnerDto) {
        if (removeOwnerDto == null)
            throw new IllegalArgumentException("Model of owner removal is not defined!");
        ownerService.Remove(removeOwnerDto.getOwnerId());
    }
}
