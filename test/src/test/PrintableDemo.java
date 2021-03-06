/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Dell
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintableDemo implements Printable {
  public int print(Graphics g, PageFormat pf, int pageIndex) {
    if (pageIndex != 0)
      return NO_SUCH_PAGE;
    Graphics2D g2 = (Graphics2D) g;
    g2.setFont(new Font("Serif", Font.PLAIN, 36));
    g2.setPaint(Color.black);
    g2.drawString("Java Source and Support", 100, 100);
    Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf
        .getImageableY(), pf.getImageableWidth(), pf
        .getImageableHeight());
    g2.draw(outline);
    return PAGE_EXISTS;
  }
  public static void main(String[] args) {
    PrinterJob pj = PrinterJob.getPrinterJob();

    PageFormat pf = pj.defaultPage();
    Paper paper = new Paper();
    double margin = 36; // half inch
    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2,
        paper.getHeight() - margin * 2);
    pf.setPaper(paper);

    pj.setPrintable(new PrintableDemo(), pf);
    if (pj.printDialog()) {
      try {
        pj.print();
      } catch (PrinterException e) {
        System.out.println(e);
      }
    }
  }
}
