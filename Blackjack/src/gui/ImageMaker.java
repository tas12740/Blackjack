package gui;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageMaker {

	public static void makeImages() {
		try {
			File file = new File("cards.png");
			BufferedImage image = ImageIO.read(file);

			int x = 0;
			int y = 0;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("AceClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("TwoClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("ThreeClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("FourClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("FiveClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("SixClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("SevenClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("EightClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("NineClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("TenClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("JackClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("QueenClubs.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("KingClubs.png"));
			y += 98;

			x = 0;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("AceSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("TwoSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("ThreeSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("FourSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("FiveSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("SixSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("SevenSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("EightSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("NineSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("TenSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("JackSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("QueenSpades.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("KingSpades.png"));
			y += 98;

			x = 0;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("AceHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("TwoHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("ThreeHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("FourHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("FiveHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("SixHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("SevenHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("EightHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("NineHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("TenHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("JackHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("QueenHearts.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("KingHearts.png"));
			y += 98;

			x = 0;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("AceDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("TwoDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("ThreeDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("FourDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("FiveDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("SixDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("SevenDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("EightDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("NineDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("TenDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("JackDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("QueenDiamonds.png"));
			x += 950 / 13;
			ImageIO.write(crop(image, x, y, 950 / 13, 98), "png", new File("KingDiamonds.png"));
		} catch (Exception e) {

		}
	}

	private static BufferedImage crop(BufferedImage src, int x, int y, int width, int height) {
		BufferedImage dest = src.getSubimage(x, y, width, height);
		return dest;
	}
}
