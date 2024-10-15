package service.in_memory_service;

import dao.InMemoryCatDAO;
import dao.InMemoryOwnerDAO;
import service.OwnerService;

public class InMemoryOwnerService extends OwnerService {
    public InMemoryOwnerService() {
        super(InMemoryOwnerDAO.GetInstance(), InMemoryCatDAO.GetInstance());
    }
}
