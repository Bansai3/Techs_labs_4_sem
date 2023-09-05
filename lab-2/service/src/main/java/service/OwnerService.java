package service;

import dao.DAO;
import entity.Cat;
import entity.Owner;
import extensions.OwnerExtensions;
import request_dto.CreateOwnerDto;
import response_dto.OwnerDto;

import java.util.ArrayList;
import java.util.List;

public abstract class OwnerService {
    public final int MINIMAL_ID = 0;
    private DAO<Owner> ownerDAO;
    private DAO<Cat> catDAO;

    public OwnerService(DAO<Owner> ownerDAO, DAO<Cat> catDAO) {
        this.ownerDAO = ownerDAO;
        this.catDAO = catDAO;
    }


    public void Add(CreateOwnerDto ownerDto) {
        if (ownerDto == null)
            throw new IllegalArgumentException("Owner is not defined!");
        Owner owner = OwnerExtensions.ToOwner(ownerDto);
        ownerDAO.Add(owner);
    }

    public void Remove(long ownerId) {
        if (ownerId < MINIMAL_ID)
            throw new IllegalArgumentException("Owner id must be positive!");
        Owner owner = GetOwnerById(ownerId);
        ownerDAO.Remove(owner);
    }

    public Owner GetOwnerById(long ownerId) {
        if (ownerId < MINIMAL_ID)
            throw new IllegalArgumentException("Owner id must be positive!");
        return ownerDAO.GetById(ownerId);
    }

    public OwnerDto GetOwnerDtoById(long ownerId) {
        if (ownerId < MINIMAL_ID)
            throw new IllegalArgumentException("Owner id must be positive!");
        Owner owner = ownerDAO.GetById(ownerId);
        OwnerDto ownerDto = OwnerExtensions.ToOwnerDto(owner);
        return ownerDto;
    }

    public void Update(Owner owner) {
        if (owner == null)
            throw new IllegalArgumentException("Owner is not defined!");
        ownerDAO.Update(owner);
    }

    public List<OwnerDto> GetAll() {
        List<Owner> owners = ownerDAO.GetAll();
        ArrayList<OwnerDto> ownersDto = new ArrayList<>();
        for (Owner owner : owners) {
            ownersDto.add(OwnerExtensions.ToOwnerDto(owner));
        }
        return ownersDto;
    }

    public void AdoptCat(long catId, long ownerId) {
        if (ownerId < MINIMAL_ID || catId < MINIMAL_ID)
            throw new IllegalArgumentException("Owner and cat id must be positive!");
        Cat cat = catDAO.GetById(catId);
        Owner owner = ownerDAO.GetById(ownerId);
        cat.setOwner(owner);
        owner.getCats().add(cat);
        Update(owner);
        catDAO.Update(cat);
    }

    public void setOwnerDAO(DAO<Owner> ownerDAO) {
        if (ownerDAO == null)
            throw new NullPointerException("Owner dao is not defined!");
        this.ownerDAO = ownerDAO;
    }

    public void setCatDAO(DAO<Cat> catDAO) {
        if (catDAO == null)
            throw new NullPointerException("Cat dao is not defined!");
        this.catDAO = catDAO;
    }
}
