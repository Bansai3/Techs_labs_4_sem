package service.db_service;

import dao.DbCatDAO;
import service.CatService;

public class DbCatService extends CatService {

    public DbCatService() {
        super(new DbCatDAO());
    }
}
