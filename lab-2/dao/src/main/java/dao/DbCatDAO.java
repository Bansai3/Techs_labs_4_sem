package dao;

import entity.Cat;
import jakarta.persistence.Query;
import org.hibernate.Session;
import utils.SessionUtil;

import java.util.List;

public class DbCatDAO extends SessionUtil implements DAO<Cat> {

    @Override
    public void Add(Cat cat) {
        OpenTransactionSection();
        Session session = getSession();
        session.save(cat);
        CloseTransactionSection();
    }

    @Override
    public void Remove(Cat cat) {
        OpenTransactionSection();
        Session session = getSession();
        session.remove(cat);
        CloseTransactionSection();
    }

    @Override
    public Cat GetById(long catId) {
        OpenTransactionSection();
        Session session = getSession();
        Cat cat = session.get(Cat.class, catId);
        CloseTransactionSection();
        return cat;
    }

    @Override
    public void Update(Cat cat) {
        OpenTransactionSection();
        Session session = getSession();
        session.update(cat);
        CloseTransactionSection();
    }

    @Override
    public List<Cat> GetAll() {
        OpenTransactionSection();
        String sql = "SELECT * FROM CATS";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Cat.class);
        List<Cat> cats = query.getResultList();
        CloseTransactionSection();
        return cats;
    }
}
