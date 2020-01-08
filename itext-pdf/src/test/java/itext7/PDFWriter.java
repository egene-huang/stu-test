package itext7;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import org.junit.Test;
/**
 * <br/>
 * User: eugene <br/>
 * Date&Time: 2020/1/7 22:34
 */
public class PDFWriter {

    static String dest = "./itext7.pdf";

    @Test
    public void write()throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, PageSize.A3);
        Table table = new Table(UnitValue.createPercentArray(new float[]{3,3,3})).useAllAvailableWidth();
        PdfFont font = FontUtil.SIMHEI;
//        PdfFont font = PdfFontFactory.createFont(FontConstants.COURIER_OBLIQUE);
        table.setFont(font);
        table.setFontSize(12f);
        table.addHeaderCell("SKU_ID");
        table.addHeaderCell("商品名称");
        table.addHeaderCell("价格");
//        table.setSkipFirstHeader(true);
        for (int i = 1; i < 1000; i++) {
//            new Cell()
            table.addCell("53629479041" + i);
            table.addCell("华为手机HUAWEI M Phone " + i);
            table.addCell(2099 + i + ".00");
        }
        doc.add(table);
        doc.close();
    }
}
