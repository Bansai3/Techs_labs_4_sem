package service.in_memory_service;

import dao.InMemoryCatDAO;
import service.CatService;

public class InMemoryCatService extends CatService {
    public InMemoryCatService() {
      super(InMemoryCatDAO.GetInstance());
    }
}
