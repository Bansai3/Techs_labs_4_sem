package service;

import dao.DbCatDAO;
import dao.DbOwnerDAO;
import dao.InMemoryCatDAO;
import dao.InMemoryOwnerDAO;
import entity.Owner;
import extensions.CatExtensions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import request_dto.CreateCatDto;
import request_dto.CreateOwnerDto;
import response_dto.OwnerDto;
import service.db_service.DbOwnerService;
import service.in_memory_service.InMemoryOwnerService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class OwnerServiceTest {

    private OwnerService ownerService;
    @Mock
    private DbOwnerDAO dbOwnerDAO;

    public OwnerServiceTest() {
        MockitoAnnotations.initMocks(this);
        ownerService = new InMemoryOwnerService();
    }

    @Test
    void add() {
        LocalDate ld = LocalDate.of(2015, 10, 5);
        CreateOwnerDto createOwnerDto = new CreateOwnerDto("Bob", ld);
        ownerService.Add(createOwnerDto);
        assertThat(ownerService.GetOwnerDtoById(0) != null).isTrue();
    }

    @Test
    void remove() {
        LocalDate ld = LocalDate.of(2015, 10, 5);
        CreateOwnerDto createOwnerDto = new CreateOwnerDto("Bob", ld);
        ownerService.Add(createOwnerDto);
        ownerService.Remove(0);
        assertEquals(0, ownerService.GetAll().size());
    }

    @Test
    void getById() {
        ownerService = new DbOwnerService();
        ownerService.setOwnerDAO(dbOwnerDAO);
        given(dbOwnerDAO.GetById(0)).willReturn(new Owner());
        Owner owner = ownerService.GetOwnerById(0);
        assertThat(owner != null).isTrue();
    }

    @Test
    void getAll() {
        ownerService = new DbOwnerService();
        ownerService.setOwnerDAO(dbOwnerDAO);
        given(dbOwnerDAO.GetAll()).willReturn(new ArrayList<>());
        List<OwnerDto> owners = ownerService.GetAll();
        assertThat(owners != null).isTrue();
    }

    @Test
    void adopt() {
        LocalDate ld = LocalDate.of(2015, 10, 5);
        LocalDate ld2 = LocalDate.of(2000, 10, 5);
        CreateCatDto createCatDto = new CreateCatDto("Bob", ld, "C", "Black");
        CreateOwnerDto createOwnerDto = new CreateOwnerDto("Bob", ld2);

        InMemoryCatDAO inMemoryCatDAO = InMemoryCatDAO.GetInstance();
        inMemoryCatDAO.Add(CatExtensions.ToCat(createCatDto));
        ownerService.Add(createOwnerDto);
        ownerService.AdoptCat(0, 0);

        Owner owner = ownerService.GetOwnerById(0);
        assertEquals(0, owner.getCats().stream().findFirst().get().getId());
    }
}