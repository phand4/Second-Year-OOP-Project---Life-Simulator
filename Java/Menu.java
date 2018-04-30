import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.audio.*;


public class Menu extends Application {


	private static final Font FONT = Font.loadFont("file:res/arcadeclassic/ARCADECLASSIC.TTF", 36);
	private static final Font TITLE = Font.loadFont("file:res/arcadeclassic/ARCADECLASSIC.TTF", 76);

	public static GamePanel game = new GamePanel(); 

	public static VBox menuBox;

	public static Stage stage = new Stage();

	public static Pane root;

	public static boolean newGame = false;
	

	private int currentItem = 0;

	private int messages = 0;

	private ScheduledExecutorService bgThread = Executors.newSingleThreadScheduledExecutor();

	public Parent createContent() throws InterruptedException {

		root = new Pane();
		root.setPrefSize(900, 600);
		Rectangle bg = new Rectangle(900, 600);

		ContentFrame frame2 = new ContentFrame(createMiddleContent());

		HBox hbox = new HBox(15, frame2);
		hbox.setTranslateX(-60);
		hbox.setTranslateY(55);

		MenuItem itemExit = new MenuItem("Exit");
		itemExit.setOnActivate(() -> System.exit(0));

		menuBox = new VBox(10,
				new MenuItem("New Game"),
				new MenuItem("Rules"),
				itemExit);
		menuBox.setAlignment(Pos.TOP_CENTER);
		menuBox.setTranslateX(360);
		menuBox.setTranslateY(300);

		getMenuItem(0).setActive(true);

		root.getChildren().addAll(bg, hbox, menuBox);

		return root;
	}



	private Node createMiddleContent() throws InterruptedException {

		String title = "Game  of   Life";
		HBox letters = new HBox(0);
		letters.setAlignment(Pos.CENTER);
		for (int i = 0; i < title.length(); i++) {

			Text letter = printLetter(title.charAt(i));
			letters.getChildren().add(letter);

		}
		return letters;

	}

	private Text printLetter(char x) throws InterruptedException {

		Text letter = new Text(x + "");
		letter.setFont(TITLE);
		letter.setFill(Color.WHITE);

		return letter;
	}



	private MenuItem getMenuItem(int index) {
		return (MenuItem)menuBox.getChildren().get(index);
	}

	private static class ContentFrame extends StackPane {
		public ContentFrame(Node content) {
			setAlignment(Pos.CENTER);

			Rectangle frame = new Rectangle(1000, 200);
			frame.setStroke(Color.WHITESMOKE);

			getChildren().addAll(frame, content);
		}
	}

	public static void music(){
		
		String bip = "res/game_music.mp3";
		Media hit = new Media(new File(bip).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
		
	}

	private static class MenuItem extends HBox {

		private Pointers c1 = new Pointers(), c2 = new Pointers();
		private Text text;
		private Runnable script;

		public MenuItem(String name) {
			super(15);
			setAlignment(Pos.CENTER);

			text = new Text(name);
			text.setFont(FONT);
			text.setEffect(new GaussianBlur(2));

			getChildren().addAll(c1, text, c2);
			setActive(false);
			setOnActivate(() -> {	
				try {
					startGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		public void startGame() throws Exception {
			menuBox.getChildren().clear();
			menuBox.getChildren().removeAll();
			newGame = true;
			root.setVisible(false);
			game.start();

		}

		public void setActive(boolean b) {
			c1.setVisible(b);
			c2.setVisible(b);
			text.setFill(b ? Color.WHITE : Color.GREY);
		}

		public void setOnActivate(Runnable r) {
			script = r;
		}

		public void activate() {
			if (script != null)
				script.run();
		}
	}

	private static class Pointers extends Parent {

		public Pointers() {

			Shape shape3 = Shape.subtract(new Circle(5), new Circle(2));
			shape3.setFill(Color.WHITE);
			shape3.setTranslateX(2.5);
			shape3.setTranslateY(-5);

			getChildren().addAll(shape3);

			setEffect(new GaussianBlur(2));
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Scene scene = new Scene(createContent());
		scene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.UP) {
				if (currentItem > 0) {
					getMenuItem(currentItem).setActive(false);
					getMenuItem(--currentItem).setActive(true);
				}
			}

			if (event.getCode() == KeyCode.DOWN) {
				if (currentItem < menuBox.getChildren().size() - 1) {
					getMenuItem(currentItem).setActive(false);
					getMenuItem(++currentItem).setActive(true);
				}
			}

			if (event.getCode() == KeyCode.ENTER) {

				
				if(newGame == false) {
					getMenuItem(currentItem).activate();
				}
				primaryStage.close();
			}
		});

		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(event -> {
			bgThread.shutdownNow();
		});
		primaryStage.show();
	}

	public static void main(String[] args) {
		music();
		launch(args);
		
	}
}