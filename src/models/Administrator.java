package models;

public class Administrator extends User implements IModel<Administrator> {
	
	public Administrator() {
	}

	@Override
	public boolean isIdenticalTo(Administrator model) {
		return super.isIdenticalTo(model);
	}
}
