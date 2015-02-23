package de.overath.cocktailsundco.inventarverwaltung.view.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ImageFilter extends FileFilter
{
    
    @Override
    public boolean accept(File f) {
	if(f.isDirectory())
	{
	    return true;
	}
	
	String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.TIFF) ||
                extension.equals(Utils.TIF) ||
                extension.equals(Utils.GIF) ||
                extension.equals(Utils.JPEG) ||
                extension.equals(Utils.JPG) ||
                extension.equals(Utils.PNG)) {
                    return true;
            } else {
                return false;
            }
        }
 
        return false;
    }
    
  //The description of this filter
    public String getDescription() {
        return "Just Images";
    }
    
}
