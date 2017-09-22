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

public class OverduePane {
	public static final OverduePane INSTANCE = new OverduePane();

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

	private OverduePane() {}

	public Pane initPane() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Over Due");
        scenetitle.setFont(Font.font("Lucida Grande", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        Label searchLabel = new Label("Search by ISBN:");
        TextField searchTextField = new TextField();

		Button searchBtn = new Button("Search");
		searchBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		ControllerInterface sc = new SystemController();
        		try {
        			if(!searchTextField.getText().isEmpty())
        			{
        				List<CheckoutTableData> tableData = sc.readCheckoutsByIsbn(searchTextField.getText().trim());
    					//System.out.println(data);
    					table.setItems(FXCollections.observableArrayList(tableData));
    					messageBar.setFill(Start.Colors.green);
                 	    messageBar.setText("Checkouts has been retrieved");
        			}
        			else
        			{
        				List<CheckoutTableData> tableData = sc.readAllCheckoutsWithOverdue();
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

        TableColumn isbnCol = new TableColumn("ISBN");
        TableColumn copyNumberCol = new TableColumn("Copy Number");
        TableColumn checkoutDateCol = new TableColumn("Checkout Date");
        TableColumn dueDateCol = new TableColumn("Due Date");

        TableColumn memberIdCol = new TableColumn("Member ID");
        TableColumn isOverdue = new TableColumn("Is Overdue");


        isbnCol.setCellValueFactory(
                new PropertyValueFactory<>("isbn"));
        copyNumberCol.setCellValueFactory(
                new PropertyValueFactory<>("copyNumber"));
        checkoutDateCol.setCellValueFactory(
                new PropertyValueFactory<>("checkoutDate"));
        dueDateCol.setCellValueFactory(
                new PropertyValueFactory<>("dueDate"));
        memberIdCol.setCellValueFactory(
                new PropertyValueFactory<>("memberId"));
        isOverdue.setCellValueFactory(
                new PropertyValueFactory<>("isOverdue"));

        table.getColumns().clear();
        table.getColumns().addAll(isbnCol, copyNumberCol,checkoutDateCol,dueDateCol,memberIdCol, isOverdue);
        grid.add(table, 0,2);


		return grid;
	}
}
