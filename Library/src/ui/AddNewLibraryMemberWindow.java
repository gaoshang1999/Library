package ui;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.LoginException;
import business.Person;
import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddNewLibraryMemberWindow extends Stage implements LibWindow {
	public static final AddNewLibraryMemberWindow INSTANCE = new AddNewLibraryMemberWindow();

	private Auth accessLevel = Auth.ADMIN;
	private boolean isInitialized = false;
	private MenuItem mItem=null;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private Text messageBar = new Text();
	public void clear() {
		messageBar.setText("");
	}

    private AddNewLibraryMemberWindow () {}
    public void init() {

        GridPane grid = new GridPane();
        grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add Member");
        setTitle("Add New Library Member");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        Label memberId = new Label("Member ID:");
        grid.add(memberId, 0, 1);

        TextField memberIdTextField = new TextField();
        //userTextField.setPrefColumnCount(10);
        //userTextField.setPrefWidth(30);
        grid.add(memberIdTextField, 1, 1);

        Label firstNameLabel = new Label("First Name:");
        grid.add(firstNameLabel, 0, 2);
        grid.setGridLinesVisible(false) ;

        TextField firstNameTextField = new TextField();
        grid.add(firstNameTextField, 1, 2);

        Label lastNameLabel = new Label("Last Name:");
        grid.add(lastNameLabel, 0, 3);
        grid.setGridLinesVisible(false) ;

        TextField lastNameTextField = new TextField();
        grid.add(lastNameTextField, 1, 3);

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
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(submitBtn);
        grid.add(hbBtn, 3, 9);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageBox.getChildren().add(messageBar);;
        grid.add(messageBox, 2, 10, 2,1);


        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		if(memberIdTextField.getText().equals("") || firstNameTextField.getText().equals("") ||
        				lastNameTextField.getText().equals("") || streetTextField.getText().equals("") || cityTextField.getText().equals("")||
        				stateTextField.getText().equals("") || zipTextField.getText().equals("") || telephoneTextField.getText().equals("")){
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + "All fields must be non-empty");
        			return;
        		}


        		try{
        			Integer.parseInt(memberIdTextField.getText());
        		}catch(Exception ex){
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + "Member ID must be Numeric");
        			return;
        		}

        		try{
        			Integer.parseInt(zipTextField.getText());
        		}catch(Exception ex){
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + "Zip code must be Numeric");
        			return;
        		}

        		try{
        			Integer.parseInt(telephoneTextField.getText());
        		}catch(Exception ex){
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + "Telephone must be Numeric");
        			return;
        		}

        		SystemController c = new SystemController();
    			if(c.allMemberIds().contains(memberIdTextField.getText())){
    				messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + "Member Id already exists");
        			return;
    			}


        		try {

        			Address add = new Address(streetTextField.getText(), cityTextField.getText(),
							stateTextField.getText(), zipTextField.getText());
        			LibraryMember per = new LibraryMember(memberIdTextField.getText(), firstNameTextField.getText(),
        					lastNameTextField.getText(), telephoneTextField.getText(), add);
        			c.addNewMember(per);
        			messageBar.setFill(Start.Colors.green);
             	    messageBar.setText("Member Created successfuly");
             	    //clearFields();
        		} catch(Exception ex) {
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + ex.getMessage());
        		}


        		Menu parent = mItem.getParentMenu();
        		parent.getItems().clear();
        		parent.getItems().addAll(Start.getAllowedWindows());

        		//mItem.getParentMenu().getItems().addA

        	}
        });

        Button backBtn = new Button("<= Back to Main");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Start.hideAllWindows();
        		Start.primStage().show();
        	}
        });
        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 0, 9);
        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);

    }
	@Override
	public boolean isAllowed(Auth x) {
		// TODO Auto-generated method stub
		if(accessLevel.equals(x)  || accessLevel.equals(Auth.BOTH)) return true;
		return true;
	}

	@Override
	public void setMenuItem(MenuItem x){
		mItem = x;
	}

	@Override
	public MenuItem getMenuItem(){
		return mItem;
	}


}
