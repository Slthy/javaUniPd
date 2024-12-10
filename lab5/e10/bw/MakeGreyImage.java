import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MakeGreyImage {
   public static void main(String args[])throws IOException {
      if (args.length != 2){
         System.out.println("Usage: java DecodeImage inputFileName outputFileName");
         System.exit(0);
      }
      
      SimpleImage img = new SimpleImage(args[0]);
      for(int y = 0; y<img.height(); y++){
      	for(int x = 0; x<img.width(); x++){
      		Color c = img.get(x, y);
      		final int avgColor = (c.getRed() + c.getGreen() + c.getBlue())/3;
      		img.set(x, y, new Color(avgColor, avgColor, avgColor));
      	}
      }

      img.save(args[1]);
      System.out.println("Done...");
   }
}
