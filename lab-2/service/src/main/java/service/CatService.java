package service;

import dao.DAO;
import entity.Cat;
import extensions.CatExtensions;
import request_dto.CreateCatDto;
import response_dto.CatDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class CatService {

    public final int MINIMAL_ID = 0;
    private DAO<Cat> catDAO;

    public CatService(DAO<Cat> catDAO) {
        this.catDAO = catDAO;
    }

    public void Add(CreateCatDto catDto) {
        if (catDto == null)
            throw new IllegalArgumentException("Cat is not defined!");
        Cat cat = CatExtensions.ToCat(catDto);
        catDAO.Add(cat);
    }


    public void Remove(long catId) {
        if (catId < MINIMAL_ID)
            throw new IllegalArgumentException("Cat id must be positive!");
        Cat cat = GetCatById(catId);
        Set<Cat> friends = cat.getFriendCats();
        for (Cat c : friends)
        {
            c.getFriendCats().remove(cat);
            Update(c);
        }
        cat.getFriendCats().clear();
        Update(cat);
        catDAO.Remove(cat);
    }

    public Cat GetCatById(long catId) {
        if (catId < MINIMAL_ID)
            throw new IllegalArgumentException("Cat id must be positive!");
        return catDAO.GetById(catId);
    }

    public CatDto GetCatDtoById(long catId) {
        Cat cat = GetCatById(catId);
        CatDto catDto = CatExtensions.ToCatDto(cat);
        return catDto;
    }


    public void Update(Cat cat) {
        if (cat == null)
            throw new IllegalArgumentException("Cat is not defined!");
        catDAO.Update(cat);
    }

    public List<CatDto> GetAll() {
        List<Cat> cats = catDAO.GetAll();
        ArrayList<CatDto> catsDto = new ArrayList<>();
        for (Cat cat : cats) {
            catsDto.add(CatExtensions.ToCatDto(cat));
        }
        return catsDto;
    }

    public void MakeCatsFriends(long catId1, long catId2) {
        if (catId1 < MINIMAL_ID || catId2 < MINIMAL_ID)
            throw new IllegalArgumentException("Cat id must be positive!");
        if (catId1 == catId2)
            throw new IllegalStateException("Cat can not be friend to itself!");
        Cat cat1 = catDAO.GetById(catId1);
        Cat cat2 = catDAO.GetById(catId2);
        Set<Cat> cat1Friends = cat1.getFriendCats();
        Set<Cat> cat2Friends = cat2.getFriendCats();
        if (cat1Friends.contains(cat2))
            throw new IllegalStateException("Cats with ids " + catId1 + " and " + catId2 + " are friends already");
        cat1.getFriendCats().add(cat2);
        cat2.getFriendCats().add(cat1);
        Update(cat1);
        Update(cat2);
    }

    public void setCatDAO(DAO<Cat> catDAO) {
        if (catDAO == null)
            throw new NullPointerException("Cat dao is not defined!");
        this.catDAO = catDAO;
    }
}
