package ru.fedotov.cats.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedotov.dto.cats.mappers.CatMapper;
import ru.fedotov.dto.cats.request_dto.CreateCatDto;
import ru.fedotov.dto.cats.request_dto.GetCatsByParametersDto;
import ru.fedotov.dto.cats.request_dto.UpdateCatDto;
import ru.fedotov.dto.cats.response_dto.CatDto;
import ru.fedotov.jpa.cats.entity.Cat;
import ru.fedotov.jpa.cats.repository.CatRepository;
import ru.fedotov.jpa.owners.entity.Owner;
import ru.fedotov.jpa.users.entity.User;
import ru.fedotov.jpa.users.repository.UserRepository;
import ru.fedotov.jpa.users.role.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CatServiceImpl implements CatService {
    @Autowired
    private CatRepository catRepository;
    @Autowired
    private CatMapper catMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addCat(CreateCatDto createCatDto) {
        Cat cat = catMapper.CreateCatDtoToCat(createCatDto);
        catRepository.save(cat);
    }

    @Override
    public void deleteCat(Long catId) {
        Cat cat = catRepository.getReferenceById(catId);
        Set<Cat> friendCats = cat.getFriendCats();
        for (Cat c : friendCats) {
            c.getFriendCats().remove(cat);
            catRepository.save(c);
        }
        Owner owner = cat.getOwner();
        if (owner != null)
            owner.getCats().remove(cat);
        catRepository.deleteById(catId);
    }

    @Override
    public List<CatDto> getAllCats() {
        List<Cat> cats = catRepository.findAll();
        List<CatDto> catDtos = new ArrayList<>();
        for(Cat cat : cats) {
            catDtos.add(catMapper.CatToCatDto(cat));
        }
        return catDtos;
    }

    @Override
    public List<CatDto> getCatsByParameters(GetCatsByParametersDto getCatsByParametersDto) {
        List<Cat> cats = catRepository.findAll().stream().filter(
                c -> c.getName() != null && c.getName().equals(getCatsByParametersDto.getName()) ||
                        c.getBirthdayDate() != null && c.getBirthdayDate().equals(getCatsByParametersDto.getBirthdayDate()) ||
                        c.getBreed() != null && c.getBreed().equals(getCatsByParametersDto.getBreed()) ||
                        c.getColour() != null && c.getColour().equals(getCatsByParametersDto.getColour())
        ).toList();

        User user = userRepository.findByUserName(getCatsByParametersDto.getUserName());
        if (user.getRole() == Role.ADMIN) {
            return cats.stream().map(c -> catMapper.CatToCatDto(c)).toList();
        } else {
            return cats.stream().filter(c -> c.getOwner() == user.getOwner()).map(c -> catMapper.CatToCatDto(c)).toList();
        }
    }

    @Override
    public void makeCatsFriends(Long catId1, Long catId2) {
        Cat cat1 = catRepository.getReferenceById(catId1);
        Cat cat2 = catRepository.getReferenceById(catId2);
        cat1.getFriendCats().add(cat2);
        cat2.getFriendCats().add(cat1);
        catRepository.save(cat1);
        catRepository.save(cat2);
    }

    @Override
    public void endCatsFriendship(Long catId1, Long catId2) {
        Cat cat1 = catRepository.getReferenceById(catId1);
        Cat cat2 = catRepository.getReferenceById(catId2);
        if (cat1.getFriendCats().contains(cat2) == false)
            throw new IllegalStateException("Cats with ids " + catId1 + ", " + catId2 + " are not friends!");
        cat1.getFriendCats().remove(cat2);
        cat2.getFriendCats().remove(cat1);
        catRepository.save(cat1);
        catRepository.save(cat2);
    }

    @Override
    public CatDto getCatById(Long catId) {
        return catMapper.CatToCatDto(catRepository.getReferenceById(catId));
    }

    @Override
    public void updateCat(UpdateCatDto updateCatDto) {
        Cat cat = catRepository.getReferenceById(updateCatDto.getId());
        cat.setName(updateCatDto.getName());
        cat.setBreed(updateCatDto.getBreed());
        cat.setColour(updateCatDto.getColour());
        cat.setBirthdayDate(updateCatDto.getBirthdayDate());
        catRepository.save(cat);
    }

    public CatRepository getCatRepository() {
        return this.catRepository;
    }

    public CatMapper getCatMapper() {
        return this.catMapper;
    }
}
