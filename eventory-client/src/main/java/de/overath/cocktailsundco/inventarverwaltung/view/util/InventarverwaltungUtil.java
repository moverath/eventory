package de.overath.cocktailsundco.inventarverwaltung.view.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import de.overath.cocktailsundco.inventarverwaltung.view.frame.IInventarverwaltungFrame;

public class InventarverwaltungUtil implements IInventarverwaltungFrame
{
    
    public static JButton createButton(String string, ActionListener listener) {
	JButton button = new JButton();
	button.setLayout(new BorderLayout());
	button.setForeground(BUTTON_BACKGROUND_COLOR);
	button.setBackground(BUTTON_BACKGROUND_COLOR);
	JLabel label = new JLabel(string);
	label.setForeground(new Color(231, 30, 50));
	label.setFont(new Font("button", Font.BOLD, 16));
	button.add(label, BorderLayout.PAGE_START);
	
	button.addActionListener(listener);
	return button;
    }
    
    public static byte[] createByteFromImage(File selectedFile) {
	try
	{
	    byte[] fileContent = Files.readAllBytes(selectedFile.toPath());
	    return fileContent;
	} catch (IOException e1)
	{
	    e1.printStackTrace();
	}
	return null;
    }
    
    public static void createImageForLabel(String absolutePath, JLabel imageLabel) {
	ImageIcon icon = new ImageIcon(absolutePath);
	ImageIcon sizedIcon = changeSizeForIcon(icon, imageLabel);
	imageLabel.setIcon(sizedIcon);
    }
    
    public static ImageIcon changeSizeForIcon(ImageIcon icon, JLabel label) {
	return getScaledImage(icon.getImage(), label.getWidth(), label.getHeight());
    }
    
    /**
     * Resizes an image using a Graphics2D object backed by a BufferedImage.
     * @param srcImg - source image to scale
     * @param w - desired width
     * @param h - desired height
     * @return - the new resized image
     */
    public static ImageIcon getScaledImage(Image srcImg, int d, int e){
//	1. First we need to load image from a file using ImageIO.read() method.

	double width = srcImg.getWidth(null);
	double height = srcImg.getHeight(null);
	double factor;
	
	if(width >= height)
	{
	factor = width / d;
	}
	else
	{
	factor = height / e;
	}
	
	int scaledWidth = (int)(width / factor);
	int scaledHeight = (int)(height / factor);
	
	Image scaledImg = srcImg.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
	
	return new ImageIcon(scaledImg);
    }
    
    public static byte[] createThumbnail(String absolutePath, double d, double e) {
//	1. First we need to load image from a file using ImageIO.read() method.

	try {
	    File sourceImageFile = new File(absolutePath);
	    BufferedImage img = ImageIO.read(sourceImageFile);
	    
//	2. Then we resize the image by calling getScaledInstance()
	    
	    double width = img.getWidth();
	    double height = img.getHeight();
	    double factor;
	    
	    if(width >= height)
	    {
		factor = width / d;
	    }
	    else
	    {
		factor = height / e;
	    }
	    
	    int scaledWidth = (int)(width / factor);
	    int scaledHeight = (int)(height / factor);
	    
	    Image scaledImg = img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
	    
//	3. Create a new image with desired dimension and draw it
	    
	    BufferedImage thumbnail = new BufferedImage((int)d,(int) e, BufferedImage.TYPE_INT_RGB);
	    thumbnail.createGraphics().drawImage(scaledImg,0,0,null);
	    
//	4. get the image as an array of bytes
	    
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(thumbnail, "jpg", baos);
	    baos.flush();
	    byte[] imageBytes = baos.toByteArray();
	    
	    return imageBytes;
	    
	}
	catch(IOException ex)
	{
	    ex.printStackTrace();
	}
	return null;
    }
    
    private static String extractType(String absolutePath) {
	int lastIndexOf = absolutePath.lastIndexOf(".");
	String substring = absolutePath.substring(lastIndexOf+1);
	return substring;
    }
    
    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    
    /**
     * Creates an ImageIcon if the path is valid.
     * @param String - resource path
     * @param String - description of the file
     */
    protected ImageIcon createImageIcon(String path) {
	java.net.URL imgURL = getClass().getResource(path);
	if (imgURL != null) {
	    return new ImageIcon(imgURL);
	} else {
	    System.err.println("Couldn't find file: " + path);
	    return null;
	}
    }
    
    public static String formatDate(Date datumBeginn) {
	DateFormat formatter = DateFormat.getDateInstance();
	return formatter.format( datumBeginn ); // 26.04.10 18:11
    }

    public static String formatPreisForTable(float preis) {
	DecimalFormat df = new DecimalFormat("#,##0.00");

	return df.format(preis) + " €";
    }
    
}
