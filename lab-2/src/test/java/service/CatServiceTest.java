package service;

import dao.DAO;
import dao.DbCatDAO;
import dao.InMemoryCatDAO;
import entity.Cat;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import request_dto.CreateCatDto;
import response_dto.CatDto;
import service.db_service.DbCatService;
import service.in_memory_service.InMemoryCatService;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class CatServiceTest {
    private CatService catService;
    @Mock
    private DbCatDAO catDAO;


    public CatServiceTest() {
        MockitoAnnotations.initMocks(this);
        catService = new InMemoryCatService();
    }

    @Test
    void add() {
        LocalDate ld = LocalDate.of(2015, 10, 5);
        CreateCatDto createCatDto = new CreateCatDto("Bob", ld, "C", "Black");
        catService.Add(createCatDto);
        assertThat(catService.GetCatDtoById(0) != null).isTrue();
    }

    @Test
    void remove() {
        LocalDate ld = LocalDate.of(2015, 10, 5);
        CreateCatDto createCatDto = new CreateCatDto("Bob", ld, "C", "Black");
        catService.Add(createCatDto);
        catService.Remove(0);
        assertEquals(0, catService.GetAll().size());
    }

    @Test
    void getById() {
        catService = new DbCatService();
        catService.setCatDAO(catDAO);
        given(catDAO.GetById(0)).willReturn(new Cat());
        Cat cat = catService.GetCatById(0);
        assertThat(cat != null).isTrue();
    }

    @Test
    void getAll() {
        catService = new DbCatService();
        catService.setCatDAO(catDAO);
        given(catDAO.GetAll()).willReturn(new ArrayList<>());
        List<CatDto> cats = catService.GetAll();
        assertThat(cats != null).isTrue();
    }

    @Test
    void makeCatsFriends() {
        LocalDate ld = LocalDate.of(2015, 10, 5);
        CreateCatDto createCatDto1 = new CreateCatDto("Bob", ld, "C", "Black");
        LocalDate ld2 = LocalDate.of(2014, 10, 5);
        CreateCatDto createCatDto2 = new CreateCatDto("Bob", ld, "C", "Black");
        catService.Add(createCatDto1);
        catService.Add(createCatDto2);
        catService.MakeCatsFriends(0, 1);
        CatDto catDto = catService.GetCatDtoById(1);
        assertThat(catService.GetCatDtoById(0).getFriendCatsIds().contains(catDto.getId())).isTrue();
    }
}