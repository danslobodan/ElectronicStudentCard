package controllers;

import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.IRepository;
import models.UniClass;
import utilities.Logger;
import utilities.RandomStringGenerator;

public class UniClassesController {

	private Logger logger = Logger.GetLogger(this);
	private IRepository<UniClass> classes;
	private ILoggedInUser login;
	
	public UniClassesController(IRepository<UniClass> classes, ILoggedInUser login) {
		this.classes = classes;
		this.login = login;
	}

	public void addUniClass(UniClass uniClass) {
		
		logger.debug("Adding uni class.");
		
		if (canAdd(uniClass)) {			
			uniClass.setId(newId(uniClass));
			logger.debug("Adding class %s with id %s", uniClass.getName(), uniClass.getId());
			classes.add(uniClass);
		}
		
		if (!hasAccess())
			logger.debug("Access denied.");
		
		if (!uniClass.modelIsValid())
			logger.debug("Model is not valid.");
	}
	
	public List<UniClass> getClasses() {
		return new ArrayList<UniClass>(classes.getAll());
	}
	
	private boolean canAdd(UniClass uniClass) {
		return uniClass != null && hasAccess() && uniClass.modelIsValid();
	}
	
	private boolean hasAccess() {
		return login.getAccessLevel() == AccessLevel.administrator;
	}
	
	private String newId(UniClass uniClass) {
		while(true) {
			var newId = randomId(uniClass);
			if (!classes.exists(uc -> uc.getId().equals(newId)))
				return newId;
		}
	}
	
	private String randomId(UniClass uniClass) {
		return String.format("%s%s%s", 
				RandomStringGenerator.Generate(2), 
				Integer.toString(uniClass.getCalendarYear()).substring(2), 
				uniClass.getSyllabus());
	}
}
