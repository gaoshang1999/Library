package ui;

import java.util.List;

import business.ControllerInterface;
import business.NotExistsException;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.domain.CheckoutTableData;

public class CheckoutWindow extends Stage implements CoWindow {
	public static final CheckoutWindow INSTANCE = new CheckoutWindow();
	private TableView table = new TableView();
	private Text messageBar = new Text();
	public TableView getTable() {
		return table;
	}


	public void setData(String data) {
		messageBar.setText(data);
	}
	public void clear() {
		messageBar.setText("");
	}

	private boolean isInitialized = false;
	@Override
	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Checkouts");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        Label searchLabel = new Label("Search by Member ID:");
        TextField searchTextField = new TextField();

		Button searchBtn = new Button("Search");
		searchBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		ControllerInterface sc = new SystemController();
        		try {
        			if(!searchTextField.getText().isEmpty())
        			{
        				List<CheckoutTableData> tableData = sc.readCheckoutsByMemberId(searchTextField.getText().trim());
    					//System.out.println(data);
    					table.setItems(FXCollections.observableArrayList(tableData));
    					messageBar.setFill(Start.Colors.green);
                 	    messageBar.setText("Checkouts has been retrieved");
        			}
        			else
        			{
        				List<CheckoutTableData> tableData = sc.readAllCheckouts();
        				table.setItems(FXCollections.observableArrayList(tableData));
    					messageBar.setFill(Start.Colors.green);
                 	    messageBar.setText("All Checkouts has been retrieved");
        			}

        		} catch (NotExistsException e1) {
					// TODO Auto-generated catch block
					messageBar.setFill(Start.Colors.red);
             	    messageBar.setText("ID doesn't exist.");
				}
        	}
        });

        HBox hSearch = new HBox(10);
        hSearch.setAlignment(Pos.BOTTOM_LEFT);
        hSearch.getChildren().addAll(searchLabel,searchTextField,searchBtn);
        grid.add(hSearch, 0, 1);

        table.setEditable(false);
        TableColumn memberIdCol = new TableColumn("Member ID");
        TableColumn isbnCol = new TableColumn("ISBN");
        TableColumn copyNumberCol = new TableColumn("Copy Number");
        TableColumn checkoutDateCol = new TableColumn("Checkout Date");
        TableColumn dueDateCol = new TableColumn("Due Date");

        memberIdCol.setCellValueFactory(
                new PropertyValueFactory<>("memberId"));
        isbnCol.setCellValueFactory(
                new PropertyValueFactory<>("isbn"));
        copyNumberCol.setCellValueFactory(
                new PropertyValueFactory<>("copyNumber"));
        checkoutDateCol.setCellValueFactory(
                new PropertyValueFactory<>("checkoutDate"));
        dueDateCol.setCellValueFactory(
                new PropertyValueFactory<>("dueDate"));

        table.getColumns().addAll(memberIdCol, isbnCol, copyNumberCol,checkoutDateCol,dueDateCol);
        grid.add(table, 0,2);

		Button backBtn = new Button("<= Back to Main");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Start.hideAllWindows();
        		Start.primStage().show();
        	}
        });

        Label memberIdLabel = new Label("Member ID:");
        TextField memberIdTextField = new TextField();
        //userTextField.setPrefColumnCount(10);
        //userTextField.setPrefWidth(30);

        Label isbnLabel = new Label("ISBN:");
        grid.setGridLinesVisible(false) ;

        TextField isbnTextField = new TextField();

        Button addBtn = new Button("Add Checkout");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		ControllerInterface sc = new SystemController();
        		try {
					CheckoutTableData data = sc.checkoutBook(memberIdTextField.getText().trim(), isbnTextField.getText().trim());
					//System.out.println(data);
					table.getItems().add(data);
					messageBar.setFill(Start.Colors.green);
             	    messageBar.setText("Book has been checked out");
        		} catch (NotExistsException e1) {
					// TODO Auto-generated catch block
					messageBar.setFill(Start.Colors.red);
             	    messageBar.setText("Book or member ID doesn't exist.");
				}
        	}
        });


        HBox hAddPart = new HBox(10);
        hAddPart.setAlignment(Pos.BOTTOM_LEFT);
        hAddPart.getChildren().addAll(memberIdLabel,memberIdTextField,isbnLabel,isbnTextField,addBtn);
        grid.add(hAddPart, 0, 3);

        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().addAll(backBtn,messageBar);
        grid.add(hBack, 0, 4);
		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);

        isInitialized = true;
	}


	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;

	}

}
