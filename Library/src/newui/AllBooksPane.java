package newui;

import java.util.List;

import business.Book;
import business.ControllerInterface;
import business.SystemController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AllBooksPane  {
	public static final AllBooksPane INSTANCE = new AllBooksPane();


	private TableView table = new TableView();

	public TableView getTable() {
		return table;
	}


	public Pane initPane() {
        GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("All Books");
        scenetitle.setFont(Font.font("Lucida Grande", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);


        table.setEditable(false);

        TableColumn isbnCol = new TableColumn("ISBN");
        TableColumn titleCol = new TableColumn("Title");
        TableColumn maxCheckoutLengthCol = new TableColumn("Max Checkout Length");
        TableColumn copyNumberCol = new TableColumn("Copy Number");
        TableColumn authorsCol = new TableColumn("Authors");


        isbnCol.setCellValueFactory(
                new PropertyValueFactory<>("isbn"));

        titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        maxCheckoutLengthCol.setCellValueFactory(
                new PropertyValueFactory<>("maxCheckoutLength"));
        copyNumberCol.setCellValueFactory(
                new PropertyValueFactory<>("numCopies"));
        authorsCol.setCellValueFactory(
                new PropertyValueFactory<>("authors"));

        table.getColumns().clear();
        table.getColumns().addAll(isbnCol, titleCol, maxCheckoutLengthCol, copyNumberCol, authorsCol);
        grid.add(table, 0, 1, 2, 1);

        ControllerInterface sc = new SystemController();
        List<Book> tableData = sc.allBooks();
		//System.out.println(data);
		table.setItems(FXCollections.observableArrayList(tableData));

		TextArea ta = new TextArea();
        ta.setVisible(false);
		grid.add(ta, 0,2);

		return grid;
	}
}
