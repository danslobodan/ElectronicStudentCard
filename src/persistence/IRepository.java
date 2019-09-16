package persistence;

import java.util.List;
import java.util.function.Predicate;

import models.IModel;

public interface IRepository<T extends IModel<T>> {

	boolean add(T item);
	boolean update(Predicate<T> predicate, T item);
	List<T> get(Predicate<T> filter);
	List<T> getAll();
	boolean delete(Predicate<T> predicate);
	boolean exists(Predicate<T> predicate);
}