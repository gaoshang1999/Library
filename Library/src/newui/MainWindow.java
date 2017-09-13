/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newui;

import java.util.Collections;
import java.util.List;

import business.ControllerInterface;
import business.SystemController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Shang Gao 986027
 * https://www.youtube.com/watch?v=lHDghZE7c-8
 *
 */
public class MainWindow extends Stage {
	 public static final MainWindow INSTANCE = new MainWindow();

	 private boolean isInitialized = false;
	 public boolean isInitialized() {
		return isInitialized;
	 }

	 public void init() throws Exception {
		if( !isInitialized){
			isInitialized = true;
	        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

	        Scene scene = new Scene(root);

	        Button bookBtn = (Button )root.lookup("#bookBtn");
	        Button memBtn = (Button )root.lookup("#memBtn");
	        Button addBookCopyBtn = (Button )root.lookup("#addBookCopyBtn");
	        Button addLibraryMemberBtn = (Button )root.lookup("#addLibraryMemberBtn");
	        Button checkOutBtn = (Button )root.lookup("#checkOutBtn");
	        
	        Button logoutBtn = (Button )root.lookup("#logoutBtn");

	        GridPane  p = (GridPane )root.lookup("#main");



	        bookBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	p.getChildren().clear();
	                p.add(AllBooksPane.INSTANCE.initPane(), 0, 0);

					ControllerInterface ci = new SystemController();
					List<String> ids = ci.allBookIds();
					Collections.sort(ids);
					StringBuilder sb = new StringBuilder();
					for(String s: ids) {
						sb.append(s + "\n");
					}
					AllBooksPane.INSTANCE.setData(sb.toString());

	            }
			});

	        memBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	p.getChildren().clear();
	                p.add(AllMembersPane.INSTANCE.initPane(), 0, 0);

	                ControllerInterface ci = new SystemController();
					List<String> ids = ci.allMemberIds();
					Collections.sort(ids);
					System.out.println(ids);
					StringBuilder sb = new StringBuilder();
					for(String s: ids) {
						sb.append(s + "\n");
					}
					AllMembersPane.INSTANCE.setData(sb.toString());

	            }
			});

	        addBookCopyBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	p.getChildren().clear();
	                p.add(AddBookCopyPane.INSTANCE.initPane(), 0, 0);
	            }
			});

	        addLibraryMemberBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	p.getChildren().clear();
	                p.add(AddLibraryMemberPane.INSTANCE.initPane(), 0, 0);
	            }
			});
	        
	        checkOutBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	p.getChildren().clear();
	                p.add(CheckoutPane.INSTANCE.initPane(), 0, 0, 2, 1);
	            }
			});

	        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	SystemController.currentAuth = null;
	            	hide();
	         	    LoginWindow.INSTANCE.init();
	         	    LoginWindow.INSTANCE.clear();
	         	    LoginWindow.INSTANCE.show();
	            }
			});

	        bookBtn.fire();
	        this.setScene(scene);

	        //https://stackoverflow.com/questions/9861178/javafx-primarystage-remove-windows-borders
	        this.initStyle(StageStyle.UNDECORATED);

	        //How to make an undecorated window movable / draggable in JavaFX?
	        //https://stackoverflow.com/questions/13206193/how-to-make-an-undecorated-window-movable-draggable-in-javafx
	        root.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffset = event.getSceneX();
	                yOffset = event.getSceneY();
	            }
	        });
	        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            	setX(event.getScreenX() - xOffset);
	            	setY(event.getScreenY() - yOffset);
	            }
	        });


	        this.show();
		} else {
			this.show();
		}

    }

    private double xOffset = 0;
    private double yOffset = 0;
    /**
     * @param args the command line arguments
     */


}
