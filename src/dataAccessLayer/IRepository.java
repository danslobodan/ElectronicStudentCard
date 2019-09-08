package dataAccessLayer;

import java.util.ArrayList;

public interface IRepository<T> {

	int Add(T obj);
	void Update(T obj);
	T Get(int id);
	ArrayList<T> GetAll();
	void Delete(int id);
}