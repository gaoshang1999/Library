package ui;

import business.Address;
import business.AlreadyExistsException;
import business.Author;
import business.ControllerInterface;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddAuthorPane extends Stage{
	public static final AddAuthorPane INSTANCE = new AddAuthorPane();


	private Text messageBar = new Text();
	public void setData(String data) {
		messageBar.setText(data);
	}
	public void clear() {
		messageBar.setText("");
	}
	private AddAuthorPane() {
		//accessLevel = Auth.ADMIN;
	}

	public Pane initPane() {
		GridPane grid = new GridPane();
        grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add New Author");
        //setTitle("Add New Library Member");
	   scenetitle.setFont(Font.font("Lucida Grande", FontWeight.NORMAL, 20)); //Tahoma
	   grid.add(scenetitle, 0, 0, 2, 1);

	   Label firstNameLabel = new Label("First Name:");
	   grid.add(firstNameLabel, 0, 1);
	   grid.setGridLinesVisible(false) ;

	   TextField firstNameTextField = new TextField();
	   grid.add(firstNameTextField, 1, 1);

	   Label lastNameLabel = new Label("Last Name:");
	   grid.add(lastNameLabel, 0, 2);


	   TextField lastNameTextField = new TextField();
	   grid.add(lastNameTextField, 1, 2);

	   Label bioNameLabel = new Label("Bio:");
	   grid.add(bioNameLabel, 0, 3);


	   TextField bioTextField = new TextField();
	   grid.add(bioTextField, 1, 3);

	   Label streetLabel = new Label("Street:");
	   grid.add(streetLabel, 0, 4);
	   grid.setGridLinesVisible(false) ;

	   TextField streetTextField = new TextField();
	   grid.add(streetTextField, 1, 4);

	   Label cityLabel = new Label("City:");
	   grid.add(cityLabel, 2, 1);
	   grid.setGridLinesVisible(false) ;

	   TextField cityTextField = new TextField();
	   grid.add(cityTextField, 3, 1);

	   Label stateLabel = new Label("State:");
	   grid.add(stateLabel, 2, 2);
	   grid.setGridLinesVisible(false) ;

	   TextField stateTextField = new TextField();
	   grid.add(stateTextField, 3, 2);

	   Label zipLabel = new Label("Zip:");
	   grid.add(zipLabel, 2, 3);
	   grid.setGridLinesVisible(false) ;

	   TextField zipTextField = new TextField();
	   grid.add(zipTextField, 3, 3);

	   Label telephoneLabel = new Label("Telephone #:");
	   grid.add(telephoneLabel, 2, 4);
	   grid.setGridLinesVisible(false) ;

	   TextField telephoneTextField = new TextField();
	   grid.add(telephoneTextField, 3, 4);

	   Button submitBtn = new Button("Submit");
	   submitBtn.getStyleClass().add("btn-primary");
       HBox hbBtn = new HBox(10);
       hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
       hbBtn.getChildren().add(submitBtn);
       grid.add(hbBtn, 3, 5);

       HBox messageBox = new HBox(10);
       messageBox.setAlignment(Pos.CENTER	);
       messageBox.getChildren().add(messageBar);;
       grid.add(messageBox, 0, 6, 4,1);




       submitBtn.setOnAction(new EventHandler<ActionEvent>() {
       	@Override
       	public void handle(ActionEvent e) {
       		if( firstNameTextField.getText().trim().equals("") ||
       				lastNameTextField.getText().trim().equals("") || streetTextField.getText().trim().equals("") ||
       				cityTextField.getText().trim().equals("")||	stateTextField.getText().trim().equals("") ||
       				zipTextField.getText().trim().equals("") || telephoneTextField.getText().trim().equals("")){
       			messageBar.setFill(Start.Colors.red);
       			messageBox.getStyleClass().add("alert-warning");
       			messageBar.setText("Error! " + "All fields must be non-empty");
       			return;
       		}

       		try{
       			Integer.parseInt(zipTextField.getText().trim());
       		}catch(Exception ex){
       			messageBar.setFill(Start.Colors.red);
       			messageBox.getStyleClass().add("alert-warning");
       			messageBar.setText("Error! " + "Zip code must be Numeric");
       			return;
       		}

       		try{
       			Integer.parseInt(telephoneTextField.getText().trim());
       		}catch(Exception ex){
       			messageBar.setFill(Start.Colors.red);
       			messageBox.getStyleClass().add("alert-warning");
       			messageBar.setText("Error! " + "Telephone must be Numeric");
       			return;
       		}

       		ControllerInterface c = new SystemController();


       		try {

       			Address add = new Address(streetTextField.getText().trim(), cityTextField.getText().trim(),
							stateTextField.getText().trim(), zipTextField.getText().trim());
       			Author author = new Author(firstNameTextField.getText().trim(), lastNameTextField.getText().trim(),
       					telephoneTextField.getText().trim(), add, bioTextField.getText().trim());
       			if(c.searchAuthor(author) != null){
       				throw new AlreadyExistsException("Author Already Exists");
       			}

       			c.addNewAuthor(author);

       			messageBar.setFill(Start.Colors.green);
       			messageBox.getStyleClass().add("alert-success");
            	messageBar.setText("Author Created successfuly");
            	    //clearFields();
       		} catch(Exception ex) {
       			messageBar.setFill(Start.Colors.red);
       			messageBox.getStyleClass().add("alert-warning");
       			messageBar.setText("Error! " + ex.getMessage());
       		}

       	}
       });



       messageBar.setText("");
		return grid;
	}
}
