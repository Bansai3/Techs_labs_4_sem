package ru.fedotov.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fedotov.dao.dao.CatRepository;
import ru.fedotov.dao.dao.OwnerRepository;
import ru.fedotov.dao.entity.Cat;
import ru.fedotov.dao.entity.Owner;
import ru.fedotov.service.mappers.OwnerMapper;
import ru.fedotov.service.request_dto.CreateOwnerDto;
import ru.fedotov.service.request_dto.GetOwnersByParametersDto;
import ru.fedotov.service.response_dto.OwnerDto;

import java.util.ArrayList;
import java.util.List;

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
                o.getBirthDate() != null && o.getBirthDate().equals(getOwnersByParametersDto.getBirthDate())
        ).toList();
        List<OwnerDto> ownerDtos = new ArrayList<>();
        for(Owner owner : owners) {
            ownerDtos.add(ownerMapper.OwnerToOwnerDto(owner));
        }
        return ownerDtos;
    }

    @Override
    public void updateOwner(CreateOwnerDto createOwnerDto, Long ownerId) {
        Owner owner = ownerRepository.getReferenceById(ownerId);
        owner.setBirthDate(createOwnerDto.getBirthDate());
        owner.setName(createOwnerDto.getName());
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
