package service.db_service;

import dao.*;
import service.OwnerService;

public class DbOwnerService extends OwnerService {
    public DbOwnerService() {
        super(new DbOwnerDAO(), new DbCatDAO());
    }
}
