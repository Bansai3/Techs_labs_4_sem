package dao;

import java.util.List;

public interface DAO<T> {

    void Add(T object);

    void Remove(T object);

    T GetById(long id);

    void Update(T object);

    List<T> GetAll();
}
