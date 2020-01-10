package itext7;

import com.itextpdf.io.font.otf.GlyphLine;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.junit.Test;

import java.io.IOException;

/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/1/7 22:46
 */
public class PDFWriterHeaderAndFooter {
    //https://itextpdf.com/en/resources/examples/itext-5-legacy/page-events-headers-and-footers
    //https://itextpdf.com/en/resources/examples/itext-7/header-and-footer-examples
    static String dest = "./itext_header_footer.pdf";
    static String img = "./rtop.png";

    @Test
    public void write()throws Exception {
        PdfWriter pdfWriter = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        Document doc = new Document(pdfDoc, PageSize.A4);
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, new RightHeader("jdprice-beta.jd.com", doc));
//        doc.setMargins(36, 36, 72, 36);
//        doc.add(new AreaBreak());
        SolidLine line = new SolidLine(1f);
        line.setColor(Color.RED);
        LineSeparator ls = new LineSeparator(line);
        ls.setMarginBottom(15);
        doc.add(ls);
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new RightFooter("jdprice-beta.jd.com", doc));
//        Table table = new Table(UnitValue.createPercentArray(new float[]{1,1,1})).useAllAvailableWidth();
        Table table = new Table(UnitValue.createPointArray(new float[]{4f,5f,3f}));
//        table.setWidth(100f);
        table.setFixedLayout();
        table.addHeaderCell("SKU_ID");
        table.addHeaderCell("SKU_Name");
        table.addHeaderCell("Price");
        /*for (int i = 1; i < 3; i++) {
            table.addCell(new Cell().add("53629479041" + i).setWidth(1f));
            table.addCell(new Cell().add("HUAWEI M Phone " + i).setWidth(1f));
            table.addCell(new Cell().add(2099 + i + ".00").setWidth(1f));
        }*/
//        doc.add(table);
//        doc.add(new AreaBreak());
        for (int i = 1; i < 3; i++) {
            table.addCell("53629479041" + i);
            table.addCell("HUAWEI M Phone " + i);
            table.addCell(2099 + i + ".00");
        }
        doc.add(table);
        doc.close();
    }

    class RightHeader implements IEventHandler {
        protected String header;
        protected Document doc;

        public RightHeader(String header, Document doc) {
            this.header = header;
            this.doc = doc;
        }

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
            try {
                new PdfCanvas(documentEvent.getPage())
                        .beginText()
                        .setFontAndSize(PdfFontFactory.createFont(), 12)
                        .moveText(450, 806)
                        .showText(header)
                        .endText()
                        .stroke();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class RightFooter implements IEventHandler {
        protected String footer;
        protected Document doc;

        public RightFooter(String footer, Document doc) {
            this.footer = footer;
            this.doc = doc;
        }

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
            try {
                new PdfCanvas(documentEvent.getPage())
                        .beginText()
                        .setFontAndSize(PdfFontFactory.createFont(), 12)
                        .moveText(450, 806)
                        .showText(footer)
                        .endText()
                        .stroke();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
