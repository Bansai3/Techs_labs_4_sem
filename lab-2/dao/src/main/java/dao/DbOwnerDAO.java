package dao;

import entity.Owner;
import jakarta.persistence.Query;
import org.hibernate.Session;
import utils.SessionUtil;

import java.util.HashSet;
import java.util.List;

public class DbOwnerDAO extends SessionUtil implements DAO<Owner> {
    @Override
    public void Add(Owner owner) {
        OpenTransactionSection();
        Session session = getSession();
        session.save(owner);
        CloseTransactionSection();
    }

    @Override
    public void Remove(Owner owner) {
        OpenTransactionSection();
        Session session = getSession();
        session.remove(owner);
        CloseTransactionSection();
    }

    @Override
    public Owner GetById(long ownerId) {
        OpenTransactionSection();
        Session session = getSession();
        Owner owner = session.get(Owner.class, ownerId);
        if (owner.getCats() == null)
            owner.setCats(new HashSet<>());
        CloseTransactionSection();
        return owner;
    }

    @Override
    public void Update(Owner owner) {
        OpenTransactionSection();
        Session session = getSession();
        session.update(owner);
        CloseTransactionSection();
    }

    @Override
    public List<Owner> GetAll() {
        OpenTransactionSection();
        String sql = "SELECT * FROM OWNERS";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Owner.class);
        List<Owner> owners = query.getResultList();
        CloseTransactionSection();
        return owners;
    }
}
