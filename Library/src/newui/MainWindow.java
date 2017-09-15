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
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        Scene scene = new Scene(root);
        VBox VBox = (VBox )root.lookup("#VBox");

        Button bookBtn = (Button )root.lookup("#bookBtn");
        Button memBtn = (Button )root.lookup("#memBtn");
        Button addAuthorBtn = (Button )root.lookup("#addAuthorBtn");
        Button addBookBtn = (Button )root.lookup("#addBookBtn");
        Button addBookCopyBtn = (Button )root.lookup("#addBookCopyBtn");
        Button addLibraryMemberBtn = (Button )root.lookup("#addLibraryMemberBtn");
        Button checkOutBtn = (Button )root.lookup("#checkOutBtn");
        Button logoutBtn = (Button )root.lookup("#logoutBtn");

        Button isOverduetBtn = (Button )root.lookup("#isOverduetBtn");

        if(SystemController.currentAuth == Auth.ADMIN){
        	VBox.getChildren().remove(checkOutBtn);
        }else if(SystemController.currentAuth == Auth.LIBRARIAN){
        	VBox.getChildren().remove(addBookCopyBtn);
        	VBox.getChildren().remove(addLibraryMemberBtn);
        }


        GridPane  p = (GridPane )root.lookup("#main");



        bookBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	p.getChildren().clear();
                p.add(AllBooksPane.INSTANCE.initPane(), 0, 0);
            }
		});

        memBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	p.getChildren().clear();
                p.add(AllMembersPane.INSTANCE.initPane(), 0, 0);
            }
		});

        addBookBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	p.getChildren().clear();
                p.add(AddBookPane.INSTANCE.initPane(), 0, 0);

            }
		});

        addAuthorBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	p.getChildren().clear();
                p.add(AddAuthorPane.INSTANCE.initPane(), 0, 0);

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

        isOverduetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	p.getChildren().clear();
                p.add(OverduePane.INSTANCE.initPane(), 0, 0, 2, 1);
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

//	        bookBtn.fire();
        scene.getStylesheets().add( getClass().getResource("Left.css").toExternalForm());
        this.setScene(scene);

        //https://stackoverflow.com/questions/9861178/javafx-primarystage-remove-windows-borders
        if( !isInitialized){
			isInitialized = true;
	        this.initStyle(StageStyle.UNDECORATED);
        }
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
    }

    private double xOffset = 0;
    private double yOffset = 0;
    /**
     * @param args the command line arguments
     */


}
