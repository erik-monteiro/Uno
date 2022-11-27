package poo.uno.game;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageFactory {
	private static ImageFactory imgf = new ImageFactory();
	private Map<String, Image> images;

	public static ImageFactory getInstance() {
		return imgf;
	}

	public ImageFactory() {
		images = new HashMap<>();
	}

	private String id2File(String imgId) {
		switch (imgId) {
		case "img1":
			return ("/imagens/blue_0_large.png");
		case "img2":
			return ("/imagens/blue_1_large.png");
		case "img3":
			return ("/imagens/blue_2_large.png");
		case "img4":
			return ("/imagens/blue_3_large.png");
		case "img5":
			return ("/imagens/blue_4_large.png");
		case "img6":
			return ("/imagens/blue_5_large.png");
		case "img7":
			return ("/imagens/blue_6_large.png");
		case "img8":
			return ("/imagens/blue_7_large.png");
		case "img9":
			return ("/imagens/blue_8_large.png");
		case "img10":
			return ("/imagens/blue_9_large.png");
		case "img11":
			return ("/imagens/green_0_large.png");
		case "img12":
			return ("/imagens/green_1_large.png");
		case "img13":
			return ("/imagens/green_2_large.png");
		case "img14":
			return ("/imagens/green_3_large.png");
		case "img15":
			return ("/imagens/green_4_large.png");
		case "img16":
			return ("/imagens/green_5_large.png");
		case "img17":
			return ("/imagens/green_6_large.png");
		case "img18":
			return ("/imagens/green_7_large.png");
		case "img19":
			return ("/imagens/green_8_large.png");
		case "img20":
			return ("/imagens/green_9_large.png");
		case "img21":
			return ("/imagens/red_0_large.png");
		case "img22":
			return ("/imagens/red_1_large.png");
		case "img23":
			return ("/imagens/red_2_large.png");
		case "img24":
			return ("/imagens/red_3_large.png");
		case "img25":
			return ("/imagens/red_4_large.png");
		case "img26":
			return ("/imagens/red_5_large.png");
		case "img27":
			return ("/imagens/red_6_large.png");	
		case "img28":
			return ("/imagens/red_7_large.png");	
		case "img29":
			return ("/imagens/red_8_large.png");	
		case "img30":
			return ("/imagens/red_9_large.png");	
		case "img31":
			return ("/imagens/yellow_0_large.png");	
		case "img32":
			return ("/imagens/yellow_1_large.png");	
		case "img33":
			return ("/imagens/yellow_2_large.png");	
		case "img34":
			return ("/imagens/yellow_3_large.png");	
		case "img35":
			return ("/imagens/yellow_4_large.png");	
		case "img36":
			return ("/imagens/yellow_5_large.png");	
		case "img37":
			return ("/imagens/yellow_6_large.png");	
		case "img38":
			return ("/imagens/yellow_7_large.png");	
		case "img39":
			return ("/imagens/yellow_8_large.png");	
		case "img40":
			return ("/imagens/yellow_9_large.png");	
		case "img41":
			return ("/imagens/wild_colora_changer_large.png");
		case "img42":
			return ("/imagens/wild_pick_four_large.png");
		case "imgBck":
			return ("/imagens/Back.png");
		default:
			throw new IllegalArgumentException("Invalid image Id");
		}
	}


	public ImageView createImage(String imgId) {
		Image img = images.get(imgId);
		
		if (img == null) {
//			img = new Image(id2File(imgId));
			img = new Image(getClass().getResourceAsStream(id2File(imgId)),220, 150,true,true);
			images.put(imgId, img);
		}

		ImageView imgv = new ImageView(img);
		return imgv;
	}
}
