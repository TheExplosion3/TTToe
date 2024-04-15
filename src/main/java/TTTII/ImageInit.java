package TTTII;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.LinkedHashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;

public class ImageInit {
	LinkedHashMap<Integer, ImageView> imgArr = new LinkedHashMap<Integer, ImageView>(3);
	private static ImageInit init = null;
	
	private ImageInit() throws FileNotFoundException {
		imgArr.put(0, null);
		imgArr.put(1, new ImageView(new Image("/circle.png")));
		imgArr.put(2, new ImageView(new Image("/x.png")));
		
		imgArr.get(1).setFitWidth(2);
		imgArr.get(1).setFitHeight(3);
		imgArr.get(1).setPreserveRatio(true);
		
		imgArr.get(2).setFitWidth(2);
		imgArr.get(2).setFitHeight(3);
		imgArr.get(2).setPreserveRatio(true);
	}
	
	public static synchronized ImageInit getInstance() throws FileNotFoundException {
		if(init == null) {
			init = new ImageInit();
		}

		return init;
	}
}
