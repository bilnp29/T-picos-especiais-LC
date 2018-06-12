package com.ui.visao;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) {
			
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/fxml/FXMLtelaLogin.fxml"));
			} catch (IOException e) {
				e.getLocalizedMessage();
				e.printStackTrace();
			}
			Scene scene = new Scene(root,875,512);
			stage.setTitle("Caroneiro Login");
			stage.setScene(scene);
			stage.show();
			
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
