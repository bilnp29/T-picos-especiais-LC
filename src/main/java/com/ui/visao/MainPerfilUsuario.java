package com.ui.visao;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPerfilUsuario extends Application {

	private Stage telaLogin;

	@Override
	public void start(Stage stage) {
		telaLogin = stage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("./FXMLperfilUsuario.fxml"));
			Scene scene = new Scene(root,875,512);
			scene.getStylesheets().add("com/ui/visao/application.css");
			telaLogin.setTitle("Perfil");
			telaLogin.setScene(scene);
			telaLogin.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public Stage getTelaLogin() {
		return telaLogin;
	}

}
