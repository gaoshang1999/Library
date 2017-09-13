package newui;

import business.AuthException;
import business.ControllerInterface;
import business.NotExistsException;
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

public class AddBookCopyPane {
	public static final AddBookCopyPane INSTANCE = new AddBookCopyPane();


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
	private AddBookCopyPane() {}

	public Pane initPane() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add Book Copy");
        scenetitle.setFont(Font.font("Lucida Grande", FontWeight.NORMAL, 20)); //Tahoma
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
        		} catch(NotExistsException ex) {
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

		return grid;
	}
}
