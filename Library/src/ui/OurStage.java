package ui;

import dataaccess.Auth;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public abstract class OurStage extends Stage{
	protected boolean isInitialized = false;
	protected MenuItem mItem=null;
	protected Auth accessLevel=null;


	public abstract void setMenuItem(MenuItem x);
	public abstract MenuItem getMenuItem();
	public abstract boolean isAllowed(Auth x);
}
