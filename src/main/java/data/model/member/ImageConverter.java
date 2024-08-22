package data.model.member;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.persistence.AttributeConverter;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageConverter implements AttributeConverter<Image, byte[]> {
	@Override
	public byte[] convertToDatabaseColumn(Image image) {
		// TODO Auto-generated method stub
		 if (image == null) {
	            return null;
	        }
	        try {
	            // Convert JavaFX Image to BufferedImage
	            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

	            // Convert BufferedImage to byte array
	            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
	                ImageIO.write(bufferedImage, "png", baos); // Use PNG or another supported format
	                return baos.toByteArray();
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("Error converting image to byte array", e);
	        }
	}

	@Override
	public Image convertToEntityAttribute(byte[] dbData) {
		// TODO Auto-generated method stub
		 if (dbData == null) {
	            return null;
	        }
	        try {
	            // Convert byte array to BufferedImage
	            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(dbData));

	            // Convert BufferedImage to JavaFX Image
	            return SwingFXUtils.toFXImage(bufferedImage, null);
	        } catch (IOException e) {
	            throw new RuntimeException("Error converting byte array to image", e);
	        }
	    }
				}


