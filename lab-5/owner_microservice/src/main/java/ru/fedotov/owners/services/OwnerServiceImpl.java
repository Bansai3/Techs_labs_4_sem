package ru.fedotov.owners.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedotov.dto.owners.mappers.OwnerMapper;
import ru.fedotov.dto.owners.request_dto.CreateOwnerDto;
import ru.fedotov.dto.owners.request_dto.GetOwnersByParametersDto;
import ru.fedotov.dto.owners.request_dto.UpdateOwnerDto;
import ru.fedotov.dto.owners.response_dto.OwnerDto;
import ru.fedotov.jpa.cats.entity.Cat;
import ru.fedotov.jpa.cats.repository.CatRepository;
import ru.fedotov.jpa.owners.entity.Owner;
import ru.fedotov.jpa.owners.repository.OwnerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private CatRepository catRepository;
    @Autowired
    private OwnerMapper ownerMapper;
    @Override
    public void addOwner(CreateOwnerDto createOwnerDto) {
        Owner owner = ownerMapper.CreateOwnerDtoToOwner(createOwnerDto);
        ownerRepository.save(owner);
    }

    @Override
    public void deleteOwner(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }

    @Override
    public void adoptCat(Long ownerId, Long catId) {
        Owner owner = ownerRepository.getReferenceById(ownerId);
        Cat cat = catRepository.getReferenceById(catId);
        cat.setOwner(owner);
        catRepository.save(cat);
        ownerRepository.save(owner);
    }

    @Override
    public void cancelAdoption(Long ownerId, Long catId) {
        Owner owner = ownerRepository.getReferenceById(ownerId);
        Cat cat = catRepository.getReferenceById(catId);
        if (owner.getCats().contains(cat) == false)
            throw new IllegalStateException("Cat with id " + catId + " is not adopted by owner with id " + ownerId);
        owner.getCats().remove(cat);
        cat.setOwner(null);
        ownerRepository.save(owner);
        catRepository.save(cat);
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        List<OwnerDto> ownerDtos = new ArrayList<>();
        List<Owner> owners = ownerRepository.findAll();
        for(Owner owner : owners) {
            ownerDtos.add(ownerMapper.OwnerToOwnerDto(owner));
        }
        return ownerDtos;
    }

    @Override
    public OwnerDto getOwnerById(Long ownerId) {
        return ownerMapper.OwnerToOwnerDto(ownerRepository.getReferenceById(ownerId));
    }

    @Override
    public List<OwnerDto> getOwnersByParameters(GetOwnersByParametersDto getOwnersByParametersDto) {
        List<Owner> owners = ownerRepository.findAll().stream().filter(
                o -> o.getName() != null && o.getName().equals(getOwnersByParametersDto.getName()) ||
                o.getBirthdayDate() != null && o.getBirthdayDate().equals(getOwnersByParametersDto.getBirthDate())
        ).toList();
        return owners.stream().map(o -> ownerMapper.OwnerToOwnerDto(o)).collect(Collectors.toList());
    }

    @Override
    public void updateOwner(UpdateOwnerDto updateOwnerDto) {
        Owner owner = ownerRepository.getReferenceById(updateOwnerDto.getId());
        owner.setBirthdayDate(updateOwnerDto.getBirthdayDate());
        owner.setName(updateOwnerDto.getName());
        ownerRepository.save(owner);
    }

    public OwnerRepository getOwnerRepository() {
        return this.ownerRepository;
    }

    public CatRepository getCatRepository() {
        return this.catRepository;
    }

    public OwnerMapper getOwnerMapper() {
        return this.ownerMapper;
    }
}
