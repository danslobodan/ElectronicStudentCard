package dataAccessLayer;

import java.util.List;
import java.util.function.Predicate;

public interface IRepository<T> {

	boolean add(T item);
	boolean update(T item);
	List<T> get(Predicate<T> filter);
	List<T> getAll();
	boolean delete(T item);
	boolean exists(T item);
	boolean exists(Predicate<T> predicate);
}