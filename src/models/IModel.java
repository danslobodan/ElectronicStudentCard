package models;

public interface IModel<T> {
	boolean modelIsValid();
	boolean isIdenticalTo(T model);
}
