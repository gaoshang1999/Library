package newui;



import business.Author;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FindAuthorWindow extends Stage{
	public static final FindAuthorWindow INSTANCE = new FindAuthorWindow();

	private boolean isInitialized = false;

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
    private FindAuthorWindow () {}
    public void init() {
    	getIcons().add(new Image("/newui/Library-icon.png"));
		if( !isInitialized){
			isInitialized = true;
	        GridPane grid = new GridPane();

	        grid.setMinWidth(400);
	        grid.setId("top-container");
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));
	        Text scenetitle = new Text("Find Author");
	        setTitle("Find Author");
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

		   Button findButton = new Button("Find");
		   Button addButton = new Button("Add to List");
		   findButton.getStyleClass().add("btn-primary");
		   addButton.getStyleClass().add("btn-success");
		   addButton.setDisable(true);


		   HBox hBox = new HBox();
		   hBox.setSpacing(5);
		   hBox.getChildren().add(addButton);
		   hBox.getChildren().add(findButton);

		   hBox.setAlignment(Pos.CENTER_RIGHT);
		   grid.add(hBox, 1, 3);

		   HBox messageBox = new HBox();
		   messageBox.getChildren().add(messageBar);
		   grid.add(messageBox,  0,4,2,1);
	        Scene scene = new Scene(grid);
	        scene.getStylesheets().add( getClass().getResource("Left.css").toExternalForm());
	        setScene(scene);


	        findButton.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					SystemController sc = new SystemController();
					addButton.setDisable(true);

				 try{
					 Author xx = sc.searchAuthor(new Author(firstNameTextField.getText().trim(), lastNameTextField.getText().trim(), null, null, null));
					 if(xx == null){
						 AddAuthorWindow.INSTANCE.init();
					 }else{
						 addButton.setDisable(false);
						 messageBar.setFill(Start.Colors.green);
						 messageBox.getStyleClass().add("alert-success");
		             	 messageBar.setText("Author Found. Click 'Add to List' \nto add to Author's List");
					 }
				 }catch(Exception ex){

				 }
				}

	        });

	        addButton.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					TextField tf = AddBookPane.INSTANCE.getAuthorsTextArea();

					String xx  = tf.getText().trim();
					xx+= firstNameTextField.getText().trim() + " "+ lastNameTextField.getText().trim() + ",";
					tf.setText(xx);
					messageBar.setFill(Start.Colors.green);
             	    messageBar.setText("Author Added. You may close this window \nor search for another Author.");
             	   addButton.setDisable(true);
				}

	        });


	        this.show();
		} else {
			this.show();
		}
    }


}
