package persistence;

import java.util.List;

import org.json.JSONObject;

import models.Administrator;

public class AdministratorRepository extends Repository<Administrator> {

	public AdministratorRepository(List<Administrator> items) {
		super(items, Administrator.class);
	}

	@Override
	protected Administrator getModel(JSONObject obj) {
		var admin = new Administrator();
		admin.setDeleted(obj.getBoolean("deleted"));
		admin.setFirstName(obj.getString("firstName"));
		admin.setLastName(obj.getString("lastName"));
		admin.setPassword(obj.getString("password"));
		admin.setUserName(obj.getString("userName"));
		return admin;
	}
}
