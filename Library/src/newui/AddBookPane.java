package newui;

import business.AuthException;
import business.ControllerInterface;
import business.NotExistsException;
import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AddBookPane extends OurStage{
	public static final AddBookPane INSTANCE = new AddBookPane();


	private TextField isbnTextField;
	private TextField titleTextField;
	private TextField authorsTextArea;
	private TextField checoutTextField;
	private TextField numberOfCopiesTextField;
	public String getIsbn() {
		return this.isbnTextField.getText().trim();
	}

	private Text messageBar = new Text();
	public void setData(String data) {
		messageBar.setText(data);
	}
	public void clear() {
		messageBar.setText("");
	}
	private AddBookPane() {
		accessLevel = Auth.ADMIN;
	}

	public Pane initPane() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add Book");
        scenetitle.setFont(Font.font("Lucida Grande", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        Label isbnLabel = new Label();
        isbnLabel.setText("ISBN:");
        grid.setStyle("-fx-border: 1px");
        grid.add(isbnLabel, 0,1);

        isbnTextField = new TextField();
		grid.add(isbnTextField, 1,1);

		Label titleLabel = new Label("Title:");
		grid.add(titleLabel, 0,2);
		titleTextField= new TextField();
		grid.add(titleTextField, 1,2);
		Label authorsLabel = new Label("Authors:");
		grid.add(authorsLabel, 0,3, 1, 2);
		authorsTextArea= new TextField("Separate Authors by ,");

		grid.add(authorsTextArea, 1,3, 1, 2);
		/*authorsTextArea.maxWidth(titleTextField.getWidth());*/
		Label checkoutLabel = new Label("Checkout Length:");
		grid.add(checkoutLabel, 0,5);
		checoutTextField= new TextField();
		grid.add(checoutTextField, 1,5);
		Label numberOfCopiesbnLabel = new Label("Number of Copies:");
		grid.add(numberOfCopiesbnLabel, 0,6);
		numberOfCopiesTextField= new TextField();
		grid.add(numberOfCopiesTextField, 1,6);


		authorsTextArea.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(authorsTextArea.getText().contains("Separate")){
					authorsTextArea.setText("");
				}
			}

		});
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
        HBox hOk = new HBox();
        hOk.setAlignment(Pos.BOTTOM_RIGHT);
        hOk.getChildren().add(okBtn);
        grid.add(hOk, 1, 7);


        messageBar = new Text();
        grid.add(messageBar,  1,8);

		return grid;
	}
}
