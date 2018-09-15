import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Principal {

	public static void main(String[] args) {

		int[] vet = new int[9];
		int aux;
		BufferedImage buf = null;
		Raster raster = null;
		int[] pixel = new int[1];
		int mediana = 0;

		try {
			buf = ImageIO.read(new File("lena.png"));
			raster = buf.getRaster();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		WritableRaster wRaster = buf.getRaster();

		for (int i = 0; i < buf.getWidth(); i++) {
			for (int j = 0; j < buf.getHeight(); j++) {

				vet[0] = ((i == 0 || j == 0) ? 0 : wRaster.getPixel(i - 1, j - 1, pixel)[0]);
				vet[1] = ((j > 0) ? wRaster.getPixel(i, j - 1, pixel)[0] : 0);
				vet[2] = ((j == 0 || i == wRaster.getWidth() - 1) ? 0 : wRaster.getPixel(i + 1, j - 1, pixel)[0]);
				vet[3] = ((i > 0) ? wRaster.getPixel(i - 1, j, pixel)[0] : 0);
				vet[4] = wRaster.getPixel(i, j, pixel)[0];
				vet[5] = ((i < wRaster.getWidth() - 1) ? wRaster.getPixel(i + 1, j, pixel)[0] : 0);
				vet[6] = ((i == 0 || j == wRaster.getHeight() - 1) ? 0 : wRaster.getPixel(i - 1, j + 1, pixel)[0]);
				vet[7] = ((j < wRaster.getHeight() - 1) ? wRaster.getPixel(i, j + 1, pixel)[0] : 0);
				vet[8] = ((i == wRaster.getWidth() - 1 || j == wRaster.getHeight() - 1) ? 0
						: wRaster.getPixel(i + 1, j + 1, pixel)[0]);

				for (int a = 0; a < 9; a++) {
					for (int b = 0; b < 8; b++) {
						if (vet[b] > vet[b + 1]) {
							aux = vet[b];
							vet[b] = vet[b + 1];
							vet[b + 1] = aux;

						}
					}
				}
				for (int a = 0; a < 9; a++) {
					System.out.println(vet[a]);
				}
				mediana = vet[4];

				System.out.println("i = " + i + "    j = " + j + "   mediana = " + mediana);
				int[] insert = { mediana };
				wRaster.setPixel(i, j, insert);
			}
		}

		try {
			ImageIO.write(buf, "PNG", new File("newLena.png"));
			System.out.println("Rotina executada.");
		} catch (IOException e) {
			System.out.println(e);
		}

	}

}