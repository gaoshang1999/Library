package newui;

import dataaccess.Auth;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class OurStage extends Stage{
	protected boolean isInitialized = false;
	protected Button btn=null;
	protected Auth accessLevel=null;



	public void setButton(Button x) {
		// TODO Auto-generated method stub
		btn = x;
	}

	public Button getButton() {
		// TODO Auto-generated method stub
		return btn;
	}

	public boolean isAllowed(Auth x) {
		// TODO Auto-generated method stub
		if(x == Auth.BOTH || accessLevel == Auth.BOTH || x == accessLevel) return true;

		return false;
	}
}
