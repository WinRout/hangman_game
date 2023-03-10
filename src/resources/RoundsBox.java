package resources;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import application.Rounds;

public class RoundsBox {
	
	public static void display(Rounds rounds) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Dictionary");
		window.setMinWidth(300);
		window.setMinHeight(100);
		
		Label label1 = new Label();
		Label label2 = new Label();
		label1.setText("Last 5 Rounds History:");
		label2.setText(rounds.toString());
		
		Button closeButton = new Button("OK");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20));
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(label1, label2, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}