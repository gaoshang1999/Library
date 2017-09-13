package newui;

import dataaccess.Auth;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AllMembersPane  extends OurStage{
	public static final AllMembersPane INSTANCE = new AllMembersPane();

	private AllMembersPane() {
		accessLevel = Auth.ADMIN;
	}

	private TextArea ta;
	public void setData(String data) {
		ta.setText(data);
	}

	public Pane initPane() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("All Member IDs");
        scenetitle.setFont(Font.font("Lucida Grande", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        ta = new TextArea();
		grid.add(ta, 0,1);

		return grid;
	}
}
