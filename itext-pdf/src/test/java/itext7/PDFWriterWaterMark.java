package itext7;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.junit.Test;
/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/1/7 22:34
 */
public class PDFWriterWaterMark {

    static String dest = "./itext7.pdf";
    //water mark
    static String desc_mark = "./itext7_mark.pdf";
//    static String imgUrl = "/rtop.png";
    static String imgUrl = "src/test/resources/rtop.png";
    //E:\projects\stu-test\itext-pdf\src\test\resources\rtop.png

    @Test
    public void write()throws Exception {
        //
        //https://itextpdf.com/en/resources/examples?title=multiple+row&itext-version=All&example_language_support=All
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(dest), new PdfWriter(desc_mark));
        Document doc = new Document(pdfDoc);
        int n = pdfDoc.getNumberOfPages();
        PdfFont font = FontUtil.SIMHEI;
//        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        Paragraph p = new Paragraph("huangyijun6").setFont(font).setFontSize(30);
        // image watermark
        ImageData img = ImageDataFactory.create(imgUrl);
        //  Implement transformation matrix usage in order to scale image
        float w = img.getWidth();
        float h = img.getHeight();
        // transparency
        PdfExtGState gs1 = new PdfExtGState();
        gs1.setFillOpacity(0.15f);
        // properties
        PdfCanvas over;
        Rectangle pagesize;
        float x, y;
        // loop over every page
        for (int i = 1; i <= n; i++) {
            PdfPage pdfPage = pdfDoc.getPage(i);
            pagesize = pdfPage.getPageSizeWithRotation();
            pdfPage.setIgnorePageRotationForContent(true);

            x = (pagesize.getLeft() + pagesize.getRight()) / 2;
            y = (pagesize.getTop() + pagesize.getBottom()) / 2;
            over = new PdfCanvas(pdfDoc.getPage(i));
            over.setFillColor(Color.BLACK);
            over.saveState();
            over.setExtGState(gs1);
            /*if (i % 2 == 1) {
                doc.showTextAligned(p, x, y, i, TextAlignment.CENTER, VerticalAlignment.TOP, 0);
            } else {
                over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2), false);
            }*/

            for (float j = 0; j < pagesize.getWidth();) {
                for (float k = 0; k < pagesize.getHeight();) {
                    new Canvas(over, pdfDoc, pdfDoc.getDefaultPageSize())
                            .showTextAligned(p, j, k, i, TextAlignment.CENTER, VerticalAlignment.TOP, 45);
                    k +=150f;
                }
                j +=150f;
            }
            over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2), true);
            over.restoreState();
        }
        pdfDoc.close();
        doc.close();
    }
}
