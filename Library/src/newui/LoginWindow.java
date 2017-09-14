package newui;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWindow extends Stage{
	public static final LoginWindow INSTANCE = new LoginWindow();

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
    private LoginWindow () {}
    public void init() {
		if( !isInitialized){
			isInitialized = true;
	        GridPane grid = new GridPane();
	        grid.setId("top-container");
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));
	        grid.setMinWidth(400);
	        setTitle("Library Management System");

	        Text scenetitle = new Text("Login");
	        scenetitle.setFont(Font.font("Lucida Grande", FontWeight.NORMAL, 20)); //Tahoma
	        grid.add(scenetitle, 0, 0, 2, 1);

	        Label userName = new Label("User Name:");
	        grid.add(userName, 0, 1);

	        TextField userTextField = new TextField();
	        //userTextField.setPrefColumnCount(10);
	        //userTextField.setPrefWidth(30);
	        grid.add(userTextField, 1, 1);

	        Label pw = new Label("Password:");
	        grid.add(pw, 0, 2);
	        grid.setGridLinesVisible(false) ;

	        PasswordField pwBox = new PasswordField();
	        grid.add(pwBox, 1, 2);

	        Button loginBtn = new Button("Log in");
	        HBox hbBtn = new HBox(10);
	        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	        hbBtn.getChildren().add(loginBtn);
	        grid.add(hbBtn, 1, 4);

	        HBox messageBox = new HBox(10);
	        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
	        messageBox.getChildren().add(messageBar);;
	        grid.add(messageBox, 1, 6);

	        pwBox.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					//System.out.println("enter pressed");
					loginBtn.fire();
				}

	        });

	        userTextField.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					loginBtn.fire();
				}

	        });

	        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent e) {
	        		try {
	        			ControllerInterface c = new SystemController();
	        			c.login(userTextField.getText().trim(), pwBox.getText().trim());
	        			messageBar.setFill(Start.Colors.green);
	             	    messageBar.setText("Login successful");

	             	    //Thread.sleep(1000);

	             	    MainWindow.INSTANCE.init();
	                    hide();
	        		} catch(LoginException ex) {
	        			messageBar.setFill(Start.Colors.red);
	        			messageBar.setText("Error! " + ex.getMessage());
	        			//ex.printStackTrace();
	        		}catch(Exception ex) {
	        			messageBar.setFill(Start.Colors.red);
	        			messageBar.setText("Error! " + ex.getMessage());
	        			//ex.printStackTrace();
	        		}


	        	}
	        });

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
