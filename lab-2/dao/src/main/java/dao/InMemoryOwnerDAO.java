package dao;

import entity.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InMemoryOwnerDAO implements DAO<Owner> {
    private static InMemoryOwnerDAO inMemoryOwnerDAO;
    private ArrayList<Owner> owners;

    private InMemoryOwnerDAO() {
        owners = new ArrayList<>();
    }

    public static InMemoryOwnerDAO GetInstance() {
        if (inMemoryOwnerDAO == null)
            inMemoryOwnerDAO = new InMemoryOwnerDAO();
        return inMemoryOwnerDAO;
    }

    @Override
    public void Add(Owner owner) {
        owners.add(owner);
    }

    @Override
    public void Remove(Owner owner) {
        owners.remove(owner);
    }

    @Override
    public Owner GetById(long id) {
        return owners.get((int) id);
    }

    @Override
    public void Update(Owner owner) {
        if (owners.contains(owner) == false)
            throw new NoSuchElementException("Can not update cat because it does not exist!");
    }

    @Override
    public List<Owner> GetAll() {
        return owners;
    }
}
