package newui;

import java.util.ArrayList;
import java.util.List;

import business.AlreadyExistsException;
import business.AuthException;
import business.Author;
import business.Book;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddBookPane extends Stage{
	public static final AddBookPane INSTANCE = new AddBookPane();


	private TextField isbnTextField;
	private TextField titleTextField;
	private TextField authorsTextArea;
	private TextField checkoutTextField;
	private TextField numberOfCopiesTextField;

	public String getIsbn(){
		return isbnTextField.getText().trim();
	}
	public TextField getAuthorsTextArea() {
		return authorsTextArea;
	}

	private Text messageBar = new Text();
	public void setData(String data) {
		messageBar.setText(data);
	}
	public void clear() {
		messageBar.setText("");
	}
	private AddBookPane() {
		//accessLevel = Auth.ADMIN;
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
		grid.add(authorsLabel, 0,3);
		authorsTextArea= new TextField();
		grid.add(authorsTextArea, 1,3);

		Button findAuthorBtn = new Button("Find Author");
		grid.add(findAuthorBtn, 2,3);

		findAuthorBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FindAuthorWindow.INSTANCE.init();
				FindAuthorWindow.INSTANCE.show();
			}

		});
		/*authorsTextArea.maxWidth(titleTextField.getWidth());*/
		Label checkoutLabel = new Label("Checkout Length:");
		grid.add(checkoutLabel, 0,5);
		checkoutTextField= new TextField();
		grid.add(checkoutTextField, 1,5);
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

        		if(isbnTextField.getText().equals("") || titleTextField.getText().equals("") ||
        				authorsTextArea.getText().equals("") || checkoutTextField.getText().equals("") ||
        				numberOfCopiesTextField.getText().equals("")){
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + "All fields must be non-empty");
        			return;
        		}

        		try{
        			Integer.parseInt(isbnTextField.getText().trim());
        		}catch(Exception ex){
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + "ISBN must be Numeric");
        			return;
        		}

        		try{
        			Integer.parseInt(checkoutTextField.getText().trim());
        		}catch(Exception ex){
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + "Checkout lenght must be Numeric");
        			return;
        		}

        		try{
        			Integer.parseInt(numberOfCopiesTextField.getText().trim());
        		}catch(Exception ex){
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! " + "Number of copies must be Numeric");
        			return;
        		}

        		if(getIsbn().length() < 10 || getIsbn().length() > 13) {

        		}else {
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("ISBN length should be either 10 or 13");
        			return;
        		}

        		if((getIsbn().length() == 13 && (getIsbn().startsWith("978")|| getIsbn().startsWith("979"))) || getIsbn().length() == 10 ) {
        			//return;
        		}else {
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("ISBN must starts with 978 or 979.");
        			return;
        		}

        		if((getIsbn().length() == 10 && (getIsbn().startsWith("0")|| getIsbn().startsWith("1"))) || getIsbn().length() == 13 ) {
        			//return;
        		}else {
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("ISBN must starts with 0 or 1.");
        			return;
        		}

        		try {
        			ControllerInterface sc = new SystemController();


    				if (sc.allBookIds().contains(getIsbn()) == true){
    					throw new AlreadyExistsException("Error! Book already exists!");
    				}
        			//int numOfCopy = c.addBookCopy(getIsbn());
        			String[] split = authorsTextArea.getText().trim().split(",");
        			List<Author> authors = new ArrayList<Author>();
        			for(String name: split){
        				String[] nameSplit = name.split(" ");
        				Author xx = sc.searchAuthor(new Author(nameSplit[0].trim(), nameSplit[1].trim(), null, null, null));
   					 	authors.add(xx);
        			}
        			Book newBook = new Book(getIsbn(), titleTextField.getText().trim(), Integer.parseInt(checkoutTextField.getText().trim()),
        					authors);

        			sc.addBook(newBook);
        			for(int i = 0; i < Integer.parseInt(numberOfCopiesTextField.getText().trim()); i++){
        				sc.addBookCopy(getIsbn());
        			}
        			messageBar.setFill(Start.Colors.green);
             	    messageBar.setText("New Book added successfully.");
        		} catch(AuthException ex) {
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! Only Admin can do this operation!");
        		} catch(NotExistsException ex) {
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText("Error! Book is not exists!");
        		}catch(AlreadyExistsException ex){
        			messageBar.setFill(Start.Colors.red);
        			messageBar.setText(ex.getMessage());
        		}

        	}
        });
        HBox hOk = new HBox();
        hOk.setAlignment(Pos.BOTTOM_RIGHT);
        hOk.getChildren().add(okBtn);
        grid.add(hOk, 1, 7);



        grid.add(messageBar,  1,8);

		return grid;
	}
}
