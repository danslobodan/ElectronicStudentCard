package dataAccessLayer;

import java.io.File;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.IModel;
import utilities.Logger;

public class Repository<T extends IModel<T>> implements IRepository<T> {
	
	private Logger logger = Logger.GetLogger(this);
	
	private File file;
	private List<T> items;
	private ObjectMapper mapper;
	private static final String folder = "data";
	
	public Repository(Class<T> cls) {
		
		String className = cls.getSimpleName().toLowerCase();		
		mapper = new ObjectMapper();

		file = new File(String.format("%s/%ss.json", folder, className));		
		logger.debug(String.format("Loading %ss from file %s", className, file.getPath()));
		
		if (!file.exists())
			createFile(file);
		
		if (file.length() == 0) {
			logger.debug("File empty. Initializing empty array.");
			items = new ArrayList<T>();
		}
		else
			load(cls);
	}
	
	public boolean add(T item) {
		
		if (!isValid(item)) {
			logger.debug("Cannot add item - item is not valid");
			return false;
		}
		
		logger.debug("Added item to list.");
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
	
	public boolean update(Predicate<T> predicate, T item) {
		
		if (!isValid(item)) {
			logger.debug("Cannot update item - item is not valid.");
			return false;
		}
		
		if (!exists(predicate)) {
			logger.debug("Could not find item to update.");			
			return false;
		}
		
		items.set(indexOf(predicate), item);
		save();
		
		logger.debug("Updated item.");
		return true;
	}
	
	public boolean delete(Predicate<T> predicate) {
		
		if (!exists(predicate)) {
			logger.debug("Could not delete item - item not found.");			
			return false;
		}
		
		items.remove(indexOf(predicate));
		save();
		logger.debug("Deleted item.");
		return true;		
	}
	
	public boolean exists(Predicate<T> predicate) {
		return items.stream().anyMatch(predicate);
	}
	
	private boolean isValid(T item) {
		return item != null && item.modelIsValid();
	}
	
	private int indexOf(Predicate<T> predicate) {
		
		for (int i = 0; i < items.size(); i++) {
			T listItem = items.get(i);
			if (predicate.test(listItem))
				return i;
		}
		
		return -1;
	}
	
	private void save() {
		
		logger.debug("Saving changes.");
		try {
			mapper.writeValue(file, items);
		}
		catch (Exception ex) {
			logger.debug("Could not save changes.");
			ex.printStackTrace();
		}
	}
	
	private void load(Class<T> cls) {
		try {
			logger.debug("Reading file contents into list into List");

			JavaType type = mapper
				.getTypeFactory()
				.constructCollectionType(List.class, cls);
			
			items = mapper.readValue(file, type);
			logger.debug("Successfully read " + items.size() + " objects.");
		}
		catch (Exception ex) {
			logger.debug("Could not read into list");
			ex.printStackTrace();
			items = new ArrayList<T>();
		}
	}
	
	private void createFile(File file) {
		try	{
			var localFolder = new File(file.getParent());
			if (!localFolder.exists()) {
				logger.debug(String.format("Creating folder %s", folder));
				localFolder.mkdir();
			}
			
			logger.debug(String.format("Creating file %s", file.getPath()));
			file.createNewFile();
		}
		catch (Exception ex) {
			logger.debug("Could not create file.");
			ex.printStackTrace();
		}		
	}
}
