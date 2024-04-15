package TTTII;

import java.util.Arrays;
import java.util.LinkedHashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;

public class Main extends Application {
	public static ImageInit IMAGES; 
	public static final double BOXWIDTH = 900;
	public static final double BOXHEIGHT = 900;
	public static final String[] XWIN = {"X", "X", "X"};
	public static final String[] OWIN = {"O", "O", "O"};
	public static String WINCONDITION = null;
	/**
	 * INPUT ARR
	 * 
	 */
	public static String[][] BOARD = {
			{"","",""},
			{"","",""},
			{"","",""}
			};
	{
		try {
			IMAGES = ImageInit.getInstance();
		}
		catch(Exception e) {
			System.out.printf("Exception caught, printing trace & exiting.%n");
			e.printStackTrace();
			System.exit(1); 
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage s) throws Exception {
		// TODO Auto-generated method stub
		s.setTitle("GAMING!!!!!!!");
		s.setHeight(900);
		s.setWidth(900);
		int ctr = 0;
		GridPane gp = new GridPane();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ctr++;
				gp.add(new Pane(createButton(BOARD[i][j], i, j)), j, i);
			}
		}
		gp.setVgap(BOXWIDTH/3);
		gp.setHgap(BOXHEIGHT/3);
		gp.setAlignment(Pos.CENTER);
		Scene sc = new Scene(gp);
		sc.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	try {
					if(WINCONDITION.equals("X")) {
						gp.getChildren().forEach(k -> {
							k.setDisable(true);
						});
						s.setTitle("Player 1 has won!");
					}
					else if(WINCONDITION.equals("O")) {
						gp.getChildren().forEach(k -> {
							k.setDisable(true);
						});
						s.setTitle("Player 2 has won!");
					}
					
				}
				catch(NullPointerException f) {
					System.out.printf("Win Condition is null, continuing.%n");
					boolean boardFull = true;
					for(int i = 0; i < BOARD.length; i++) {
						for(int j = 0; j <  BOARD[i].length; j++) {
							if(BOARD[j][i].equals("")) {
								boardFull = false;
							}
						}
					}
					if(boardFull) {
						gp.getChildren().forEach(k -> {
							k.setDisable(true);
						});
						s.setTitle("Game inconclusive, players tied.");
					}
				}

		    }
		});
		s.setScene(sc);
		s.show();
	}
	
	public static Button createButton(String cnd, int x, int y) {
		Button btn = new Button();
		btn.setScaleX(15);
		btn.setScaleY(10);
		if(cnd.equals("X")) {
			btn.setGraphic(imageCloner("x"));
			btn.setDisable(true);
		}
		else if(cnd.equals("O")) {
			btn.setGraphic(imageCloner("circle"));
			btn.setDisable(true);
		}
		btn.setOnMouseClicked(e -> {
			if(e.getButton() == MouseButton.PRIMARY) {
				btn.setGraphic(imageCloner("x"));
				btn.setDisable(true);
				BOARD[x][y] = "X";
				WINCONDITION = checkWin();

				
				WINCONDITION = checkWin();
			}
			else if(e.getButton() == MouseButton.SECONDARY) {
				btn.setGraphic(imageCloner("circle"));
				btn.setDisable(true);
				BOARD[x][y] = "O";
				WINCONDITION = checkWin();
			}
		});
		
		return btn;
	}
	
	private static ImageView imageCloner(String image) {
		if(image.equals("circle")) {
			ImageView temp = new ImageView("/circle.png");
			temp.setFitWidth(2);
			temp.setFitHeight(3);
			temp.setPreserveRatio(true);
			return temp;
		}
		else {
			ImageView temp = new ImageView("/x.png");
			temp.setFitWidth(2);
			temp.setFitHeight(3);
			temp.setPreserveRatio(true);
			return temp;
		}
	}
	
	private static String checkWin() {
		String[] vertArray = {"","",""};
		String[] diagL = {BOARD[0][0], BOARD[1][1],BOARD[2][2]};
		String[] diagR = {BOARD[0][2], BOARD[1][1], BOARD[2][0]};
		for(int i = 0; i < BOARD.length; i++) {
			if(Arrays.deepEquals(BOARD[i], XWIN)) {
				return "X";
			}
			else if(Arrays.deepEquals(BOARD[i], OWIN)) {
				return "O";
			}
			for(int j = 0; j < BOARD[i].length; j++) {
				vertArray[j] = BOARD[j][i];
				if(Arrays.deepEquals(vertArray, XWIN) || Arrays.deepEquals(vertArray, XWIN)) {
					return "X";
				}
				else if(Arrays.deepEquals(vertArray, OWIN) || Arrays.deepEquals(vertArray, OWIN)) {
					return "O";
				}
			}
			
			if(Arrays.deepEquals(diagL, XWIN) || Arrays.deepEquals(diagR, XWIN)) {
				return "X";
			}
			else if(Arrays.deepEquals(diagL, OWIN) || Arrays.deepEquals(diagR, OWIN)) {
				return "O";
			}
			vertArray[0] = "";
			vertArray[1] = "";
			vertArray[2] = "";
		}
		return null;
	}
}