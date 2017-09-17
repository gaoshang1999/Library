package ui;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddAuthorWindow extends Stage{
	public static final AddAuthorWindow INSTANCE = new AddAuthorWindow();

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
    private AddAuthorWindow () {}
    public void init() {
    	getIcons().add(new Image("/newui/Library-icon.png"));
		if( !isInitialized){
			isInitialized = true;
	        GridPane grid = (GridPane) AddAuthorPane.INSTANCE.initPane();
	        setTitle("Add New Author");
	        Scene scene = new Scene(grid);

	        scene.getStylesheets().add( getClass().getResource("Left.css").toExternalForm());
	        setScene(scene);

	        this.show();
		} else {
			this.show();
		}
    }

    private double xOffset = 0;
    private double yOffset = 0;
}
