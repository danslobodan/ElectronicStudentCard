package dataAccessLayer;

import java.io.File;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Repository<T> implements IRepository<T> {
	
	private File file;
	private List<T> items;
	private ObjectMapper mapper;
	
	protected abstract boolean idenityComparer(T item1, T item2);
	protected abstract boolean isValid(T item);
	
	public Repository(Class<T> cls) {
		
		String className = cls.getSimpleName().toLowerCase();
		System.out.println(String.format("class name %s", className));
		
		mapper = new ObjectMapper();
		file = new File(String.format("data/%s", className));

		try	{
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("Creating new file.");
			}
		}
		catch (Exception ex) {
			System.out.println("Could not create file.");
			ex.printStackTrace();
		}
		

		try {
			if (file.length() == 0) {
				items = new ArrayList<T>();
				mapper.writeValue(file, items);				
			}			
		}
		catch (Exception ex) {
			System.out.println("Could not write initial empty array.");
			ex.printStackTrace();
		}
		
		try {
			System.out.println("Reading file contents into list into List");
			

			JavaType type = mapper
				.getTypeFactory()
				.constructCollectionType(List.class, cls);
			
			items = mapper.readValue(file, type);
			System.out.println("Successfully read " + items.size() + " objects.");
		}
		catch (Exception ex) {
			System.out.println("Could not read into list");
			ex.printStackTrace();
			items = new ArrayList<T>();
		}
	}
	
	public boolean add(T item) {
		
		if (exists(item)) {
			System.out.println("Cannot add to list - item already exists.");
			return false;
		}
		
		if (!isValid(item)) {
			System.out.println("Cannot add item - item is not valid");
			return false;
		}
		
		System.out.println("Added item to list.");
		items.add(item);
		Save();
		return true;
	}
	
	public List<T> get(Predicate<T> predicate) {
		
		List<T> result = items
				.stream()
				.filter(predicate)
				.collect(Collectors.toList());

		return result;
	}
	
	public List<T> getAll() {
		return new ArrayList<T>(items);
	}
	
	public boolean update(T item) {
		
		if (!exists(item)) {
			System.out.println("Could not find item to update.");			
			return false;
		}
		
		if (!isValid(item)) {
			System.out.println("Cannot update item - item is not valid.");
			return false;
		}
		
		
		
		Save();
		System.out.println("Updated item.");
		return true;
	}
	
	public boolean delete(T item) {
		
		if (!exists(item)) {
			System.out.println("Could not delete item - item not found.");			
			return false;
		}
		
		Save();
		System.out.println("Successfully updated item.");
		return true;		
	}
	
	public boolean exists(T item) {
		return items.stream().anyMatch(listItem -> idenityComparer(listItem, item)); 
	}
	
	private void Save() {
		
		System.out.println("Saving changes.");
		try {
			mapper.writeValue(file, items);
			System.out.println("Changes saved successfully.");
		}
		catch (Exception ex) {
			System.out.println("Could not save changes.");
			ex.printStackTrace();
		}
	}
}
