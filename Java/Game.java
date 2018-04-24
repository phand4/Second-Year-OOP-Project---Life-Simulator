import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application{

	private ScheduledExecutorService gameThread = Executors.newSingleThreadScheduledExecutor();
	
	private static final Font FONT = Font.loadFont("file:res/arcadeclassic/ARCADECLASSIC.TTF", 24);


	private Parent createContent() throws InterruptedException {


		Pane root = new Pane();
		root.setPrefSize(900, 600);
		Rectangle bg = new Rectangle(900, 600);
		String string = "This is the game of life";
		slowPrint(string, 100);
		root.getChildren().addAll(bg);
		return root;

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage secondaryStage) throws Exception {

		Scene gameScene = new Scene(createContent());

		secondaryStage.setScene(gameScene);
		secondaryStage.setOnCloseRequest(event -> {
			gameThread.shutdownNow();
		});
		secondaryStage.show();


	}

	public static void slowPrint(String message, long millisPerChar) throws InterruptedException {

		
		for(int i = 0; i< message.length(); i++) {
			Text letter = printLetter(message.charAt(i));
			Thread.sleep(millisPerChar);
		}
		
		

	}

	private static Text printLetter(char x) {
		Text letter = new Text(x + "");
		letter.setFont(FONT);
		letter.setFill(Color.WHITE);

		return letter;
	}


}


