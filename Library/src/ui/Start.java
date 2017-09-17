/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import business.SystemController;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Shang Gao 986027
 * https://www.youtube.com/watch?v=lHDghZE7c-8
 *
 */
public class Start extends Application {

	public static class Colors {
		static Color green = Color.web("#034220");
		static Color red = Color.FIREBRICK;
	}

    @Override
    public void start(Stage stage) throws Exception {
    	if(SystemController.currentAuth == null){
    		LoginWindow.INSTANCE.init();
    	}else{
    		MainWindow.INSTANCE.init();
    	}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
