package de.overath.cocktailsundco.inventarverwaltung.view.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public interface IInventarverwaltungFrame
{
    final Color BUTTON_BACKGROUND_COLOR = Color.WHITE;
    final String INVENTARVERWALTUNG_LOGO = "img/C&C_icon.png";
    final String ABORT_TEXT = "Abbrechen";
    final String PRINT_TEXT = "Drucken";
    final Font TABLE_FONT = new Font("Serif", Font.BOLD, 14);
    final Font LABLE_FONT = new Font("Serif", Font.BOLD, 14);
    final Dimension TABLE_CELL_INTERSPACING = new Dimension(15,1);
    final String CREATE_TEXT = "Anlegen";
    final int GAP_BORDER = 10;
    final int GAP_COMPONENT = 10;
    final int SIZE_NAME_LABEL = 30;
    final int SIZE_CHECKBOX = 30;
    final int SIZE_TEXT_AREA = 120;
    
    final Dimension THUMBNAIL_SIZE = new Dimension(32,32);
}
