package extensions;

import request_dto.CreateCatDto;
import entity.Cat;
import lombok.experimental.UtilityClass;
import response_dto.CatDto;

import java.util.HashSet;

@UtilityClass
public class CatExtensions {
    public static Cat ToCat(CreateCatDto createCatDto) {
        Cat cat = new Cat();
        cat.setName(createCatDto.getName());
        cat.setBreed(createCatDto.getBreed());
        cat.setColour(createCatDto.getColour());
        cat.setBirthdayDate(createCatDto.getBirthdayDate());
        cat.setFriendCats(new HashSet<>());
        return cat;
    }

    public static CatDto ToCatDto(Cat cat) {
        CatDto catDto = new CatDto();
        catDto.setName(cat.getName());
        catDto.setId(cat.getId());
        catDto.setBreed(cat.getBreed());
        catDto.setColour(cat.getColour());
        catDto.setOwnerId(cat.getOwner() == null ? null : cat.getOwner().getId());
        catDto.setBirthdayDate(cat.getBirthdayDate());
        catDto.setFriendCatsIds(new HashSet<>());
        for (Cat c : cat.getFriendCats()) {
            catDto.getFriendCatsIds().add(c.getId());
        }
        return catDto;
    }
}
