<<<<<<< HEAD
package newui;

import java.util.List;

import business.ControllerInterface;
import business.NotExistsException;
import business.SystemController;
import dataaccess.Auth;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import library.domain.CheckoutTableData;
import newui.Start;

public class CheckoutPane extends OurStage{
	public static final CheckoutPane INSTANCE = new CheckoutPane();

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

	private CheckoutPane() {
		accessLevel = Auth.LIBRARIAN;
	}

	public Pane initPane() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Check Out");
        scenetitle.setFont(Font.font("Lucida Grande", FontWeight.NORMAL, 20)); //Tahoma
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

		searchBtn.fire();

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

        table.getColumns().clear();
        table.getColumns().addAll(memberIdCol, isbnCol, copyNumberCol,checkoutDateCol,dueDateCol);
        grid.add(table, 0,2);


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

		return grid;
	}



}
=======
package newui;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import library.domain.CheckoutTableData;
import newui.Start;

public class CheckoutPane {
	public static final CheckoutPane INSTANCE = new CheckoutPane();

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

	private CheckoutPane() {}

	public Pane initPane() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Check Out");
        scenetitle.setFont(Font.font("Lucida Grande", FontWeight.NORMAL, 20)); //Tahoma
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
                 	   setMessageBar("Checkouts has been retrieved.",Start.Colors.green);
        			}
        			else
        			{
        				List<CheckoutTableData> tableData = sc.readAllCheckouts();
        				table.setItems(FXCollections.observableArrayList(tableData));
                 	   setMessageBar("All Checkouts has been retrieved.",Start.Colors.green);
        			}

        		} catch (NotExistsException e1) {
					// TODO Auto-generated catch block
        			setMessageBar("ID doesn't exist.",Start.Colors.red);
				}
        	}
        });

		searchBtn.fire();
		messageBar.setVisible(false);

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

        table.getColumns().clear();
        table.getColumns().addAll(memberIdCol, isbnCol, copyNumberCol,checkoutDateCol,dueDateCol);
        grid.add(table, 0,2);


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
             	   setMessageBar("Book has been checked out.",Start.Colors.green);
        		} catch (NotExistsException e1) {
					// TODO Auto-generated catch block
             	   setMessageBar("Book or member ID doesn't exist.",Start.Colors.red);
				}
        	}
        });


        HBox hAddPart = new HBox(10);
        hAddPart.setAlignment(Pos.BOTTOM_LEFT);
        hAddPart.getChildren().addAll(memberIdLabel,memberIdTextField,isbnLabel,isbnTextField,addBtn);
        grid.add(hAddPart, 0, 3);

        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().addAll(messageBar);
        grid.add(hBack, 0, 4);

		return grid;
	}

	private void setMessageBar(String text,Paint color ){
		messageBar.setVisible(true);
		messageBar.setFill(color);
 	    messageBar.setText(text);
	}
}
>>>>>>> mleung/master
