package com.example.demo.util;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfUtil {

	public static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	public static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	public static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	public static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	
	public static Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

	public static final String LOGO = "src/main/resources/static/img/logo-mapfre.png";

	public static void addHeader(PdfWriter writer, String title, String dates){
        PdfPTable header = new PdfPTable(2);
        try {
            // set defaults
            header.setWidths(new int[]{12, 24});
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add image
          
            Image logo = Image.getInstance(LOGO);
           header.addCell(logo);
           
            // add text
            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(15);
            text.setPaddingLeft(10);
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.LIGHT_GRAY);
            text.addElement(new Phrase(title, new Font(Font.FontFamily.HELVETICA, 12)));
            text.addElement(new Phrase(dates, new Font(Font.FontFamily.HELVETICA, 8)));
            header.addCell(text);
            //header.addCell(" 23");

            // write content
            header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
        } catch(Exception de) {
        	System.out.println("error "+de);
           
        } 
        
    }

	public Image getLogo() throws BadElementException, MalformedURLException, IOException {
		return Image.getInstance(LOGO);
	}

}
