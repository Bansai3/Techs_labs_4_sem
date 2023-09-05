package extensions;

import entity.Cat;
import request_dto.CreateOwnerDto;
import entity.Owner;
import lombok.experimental.UtilityClass;
import response_dto.OwnerDto;

import java.util.HashSet;

@UtilityClass
public class OwnerExtensions {
    public static Owner ToOwner(CreateOwnerDto createOwnerDto) {
        Owner owner = new Owner();
        owner.setName(createOwnerDto.getName());
        owner.setBirthDate(createOwnerDto.getBirthDate());
        owner.setCats(new HashSet<>());
        return owner;
    }

    public static OwnerDto ToOwnerDto(Owner owner) {
        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setId(owner.getId());
        ownerDto.setName(owner.getName());
        ownerDto.setBirthDate(owner.getBirthDate());
        ownerDto.setCatsIds(new HashSet<>());
        for (Cat c : owner.getCats()) {
            ownerDto.getCatsIds().add(c.getId());
        }
        return ownerDto;
    }
}
