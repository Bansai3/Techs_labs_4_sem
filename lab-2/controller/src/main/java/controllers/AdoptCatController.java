package controllers;

import request_dto.AdoptCatDto;
import service.OwnerService;

public class AdoptCatController {
    private OwnerService ownersService;

    public AdoptCatController(OwnerService ownersService) {
        this.ownersService = ownersService;
    }

    public void AdoptCat(AdoptCatDto adoptCatDto) {
        if (adoptCatDto == null)
            throw new IllegalArgumentException("Model of cat adoption id not defined!");
        ownersService.AdoptCat(adoptCatDto.getCatId(), adoptCatDto.getOwnerId());
    }
}
