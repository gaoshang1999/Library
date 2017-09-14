package newui;

import javafx.scene.Scene;
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
		if( !isInitialized){
			isInitialized = true;
	        GridPane grid = (GridPane) AddAuthorPane.INSTANCE.initPane();
	        setTitle("Add New Author");
	        Scene scene = new Scene(grid);
	        setScene(scene);

	        this.show();
		} else {
			this.show();
		}
    }

    private double xOffset = 0;
    private double yOffset = 0;
}
