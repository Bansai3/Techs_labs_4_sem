package dao;

import entity.Cat;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InMemoryCatDAO implements DAO<Cat> {

    private static InMemoryCatDAO inMemoryCatDAO;
    private ArrayList<Cat> cats;

    private InMemoryCatDAO() {
        cats = new ArrayList<>();
    }

    public static InMemoryCatDAO GetInstance() {
        if (inMemoryCatDAO == null)
            inMemoryCatDAO = new InMemoryCatDAO();
        return inMemoryCatDAO;
    }

    @Override
    public void Add(Cat cat) {
        cats.add(cat);
    }

    @Override
    public void Remove(Cat cat) {
        cats.remove(cat);
    }

    @Override
    public Cat GetById(long id) {
        return cats.get((int) id);
    }

    @Override
    public void Update(Cat cat) {
        for (Cat c : cats) {
            if (c == cat)
                return;
        }
        throw new NoSuchElementException("Can not update cat because it does not exist!");
    }

    @Override
    public List<Cat> GetAll() {
        return cats;
    }
}
