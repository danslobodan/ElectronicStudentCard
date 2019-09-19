package views;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Exam;
import models.ExamTerm;

public class ExamsTable extends TableView<Exam> {

	public ExamsTable() {
		
		var classId = new TableColumn<Exam, String>("Class");
		classId.setCellValueFactory(new PropertyValueFactory<>("classId"));
		
		var date = new TableColumn<Exam, String>("Date");
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		var examTerm = new TableColumn<Exam, ExamTerm>("Term");
		examTerm.setCellValueFactory(new PropertyValueFactory<>("examTerm"));
		
		var grade = new TableColumn<Exam, Integer>("Grade");
		grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
		
		getColumns().add(classId);
		getColumns().add(date);
		getColumns().add(examTerm);
		getColumns().add(grade);
	}

}
