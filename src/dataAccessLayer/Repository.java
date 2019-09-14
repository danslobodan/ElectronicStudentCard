package dataAccessLayer;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.IModel;

public class Repository<T extends IModel<T>> implements IRepository<T> {
	
	private File file;
	private List<T> items;
	private ObjectMapper mapper;
	private static final String folder = "data";
	
	public Repository(Class<T> cls) {
		
		String className = cls.getSimpleName().toLowerCase();
		System.out.println(String.format("class name %s", className));
		
		mapper = new ObjectMapper();

		file = new File(String.format("%s/%ss.json", folder, className));		
		
		if (!file.exists())
			createFile(file);
		
		if (file.length() == 0)
			items = new ArrayList<T>();
		else
			load(cls);
	}
	
	public boolean add(T item) {
		
		if (!isValid(item)) {
			System.out.println("Cannot add item - item is not valid");
			return false;
		}
		
		if (exists(item)) {
			System.out.println("Cannot add to list - item already exists.");
			return false;
		}
		
		System.out.println("Added item to list.");
		items.add(item);
		save();
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
		
		if (!isValid(item)) {
			System.out.println("Cannot update item - item is not valid.");
			return false;
		}
		
		if (!exists(item)) {
			System.out.println("Could not find item to update.");			
			return false;
		}
		
		items.set(indexOf(item), item);				
		save();
		
		System.out.println("Updated item.");
		return true;
	}
	
	public boolean delete(T item) {
		
		if (!exists(item)) {
			System.out.println("Could not delete item - item not found.");			
			return false;
		}
		
		save();
		System.out.println("Successfully updated item.");
		return true;		
	}
	
	public boolean exists(T item) {
		return items.stream().anyMatch(listItem -> item.isIdenticalTo(listItem)); 
	}
	
	private boolean isValid(T item) {
		return item != null && item.modelIsValid();
	}
	
	private int indexOf(T item) {
		for (int i = 0; i < items.size(); i++) {
			T listItem = items.get(i);
			if (item.isIdenticalTo(listItem))
				return i;
		}
		
		return -1;
	}
	
	private void save() {
		
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
	
	private void load(Class<T> cls) {
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
	
	private void createFile(File file) {
		try	{
			var localFolder = new File(file.getParent());
			if (!localFolder.exists()) {
				System.out.println(String.format("Creating folder %s", folder));
				localFolder.mkdir();
			}
			
			System.out.println(String.format("Creating file %s", file.getPath()));
			file.createNewFile();
		}
		catch (Exception ex) {
			System.out.println("Could not create file.");
			ex.printStackTrace();
		}		
	}
}
