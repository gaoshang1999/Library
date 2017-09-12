package ui;

import business.AuthException;
import business.ControllerInterface;
import business.NotExsitsException;
import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddBookCopyWindow extends Stage implements LibWindow {
	public static final AddBookCopyWindow INSTANCE = new AddBookCopyWindow();

	Auth accessLevel = Auth.ADMIN;
	private MenuItem mItem=null;
	private boolean isInitialized = false;
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private TextField isbn;
	public String getIsbn() {
		return this.isbn.getText().trim();
	}

	private Text messageBar = new Text();
	public void setData(String data) {
		messageBar.setText(data);
	}
	public void clear() {
		messageBar.setText("");
	}
	private AddBookCopyWindow() {
	}

	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add Book Copy");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        Label l = new Label();
        l.setText("ISBN:");
        grid.add(l, 0,1);

        isbn = new TextField();
		grid.add(isbn, 1,1);

		Button okBtn = new Button("Ok");
		okBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		try {
        			ControllerInterface c = new SystemController();
        			int numOfCopy = c.addBookCopy(getIsbn());
        			messageBar.setFill(Start.Colors.green);
             	    messageBar.setText("New copy added successfully.\nThe book has " + numOfCopy + " copies now!");
        		} catch(AuthException ex) {
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! Only Admin can do this operation!");
        		} catch(NotExsitsException ex) {
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! Book is not exists!");
        		}
        	}
        });
        HBox hOk = new HBox(10);
        hOk.setAlignment(Pos.BOTTOM_LEFT);
        hOk.getChildren().add(okBtn);
        grid.add(hOk, 3, 1);

        messageBar = new Text();
        grid.add(messageBar, 1, 2, 2, 1);

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
        grid.add(hBack, 0, 3);


		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
	}
	@Override
	public boolean isAllowed(Auth x) {
		// TODO Auto-generated method stub
		if(accessLevel.equals(x)  || accessLevel.equals(Auth.BOTH)) return true;
		return false;
	}
	@Override
	public MenuItem getMenuItem() {
		// TODO Auto-generated method stub
		return mItem;
	}
	@Override
	public void setMenuItem(MenuItem x) {
		// TODO Auto-generated method stub
		mItem = x;
	}
}
