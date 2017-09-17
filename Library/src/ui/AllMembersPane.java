package ui;

import java.util.List;

import business.ControllerInterface;
import business.LibraryMember;
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

public class AllMembersPane  {
	public static final AllMembersPane INSTANCE = new AllMembersPane();

	private AllMembersPane() {}

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

        Text scenetitle = new Text("All Library Members");
        scenetitle.setFont(Font.font("Lucida Grande", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);


        table.setEditable(false);

        TableColumn memberIdCol = new TableColumn("memberId");
        TableColumn firstNameCol = new TableColumn("firstName");
        TableColumn lastNameCol = new TableColumn("lastName");
        TableColumn telephoneCol = new TableColumn("telephone");
        TableColumn addressCol = new TableColumn("address");

        memberIdCol.setCellValueFactory(
                new PropertyValueFactory<>("memberId"));

        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
        telephoneCol.setCellValueFactory(
                new PropertyValueFactory<>("telephone"));
        addressCol.setCellValueFactory(
                new PropertyValueFactory<>("address"));

        table.getColumns().clear();
        table.getColumns().addAll(memberIdCol, firstNameCol, lastNameCol, telephoneCol, addressCol);
        grid.add(table, 0, 1, 2, 1);

        ControllerInterface sc = new SystemController();
        List<LibraryMember> tableData = sc.allLibraryMembers();
		//System.out.println(data);
		table.setItems(FXCollections.observableArrayList(tableData));

		TextArea ta = new TextArea();
        ta.setVisible(false);
		grid.add(ta, 0,2);

		return grid;
	}
}
