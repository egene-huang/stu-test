package itext7;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceCmyk;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.GrooveBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
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
//        Table table = new Table(UnitValue.createPointArray(new float[]{3,3,3})).useAllAvailableWidth();
        Table table = new Table(UnitValue.createPercentArray(new float[]{4,4,4,4,4,4,4,4,4,4,4,4,4,4,6,6,4})).useAllAvailableWidth();
//        table = new Table(UnitValue.createPercentValue(100)).useAllAvailableWidth();
//        table = new Table(new float[17]);
        table.setFixedLayout();
        PdfFont font = FontUtil.SIMHEI;
//        PdfFont font = PdfFontFactory.createFont(FontConstants.COURIER_OBLIQUE);
//        table.setFont(font);
//        table.setFontSize(12f);
        /*table.addHeaderCell("SKU_ID");
        table.addHeaderCell(new Paragraph("商品名称").setFont(font));
        table.addHeaderCell(new Paragraph("价格").setFont(font));*/

//        table.setAutoLayout();
//        table.setBorder(new GrooveBorder(DeviceCmyk.BLACK, 0.5f));
        table.addHeaderCell("skuID");
        table.addHeaderCell(new Paragraph("事业群").setFont(font));
        table.addHeaderCell(new Paragraph("事业部").setFont(font));
        table.addHeaderCell(new Paragraph("一级部门").setFont(font));
        table.addHeaderCell(new Paragraph("一级类目ID").setFont(font));
        table.addHeaderCell(new Paragraph("一级类目名称").setFont(font));
        table.addHeaderCell(new Paragraph("二级类目ID").setFont(font));
        table.addHeaderCell(new Paragraph("二级类目名称").setFont(font));
        table.addHeaderCell(new Paragraph("三级类目ID").setFont(font));
        table.addHeaderCell(new Paragraph("三级类目名称").setFont(font));
        table.addHeaderCell(new Paragraph("ERP账号").setFont(font));
        table.addHeaderCell(new Paragraph("品牌").setFont(font));
        table.addHeaderCell(new Paragraph("自营/POP").setFont(font));
        table.addHeaderCell(new Paragraph("优惠路径").setFont(font));
        table.addHeaderCell(new Paragraph("实时普惠到手价").setFont(font));
        table.addHeaderCell(new Paragraph("历史校验期最低成交价").setFont(font));
        table.addHeaderCell(new Paragraph("价格力系数").setFont(font));
//        table.setSkipFirstHeader(true);
        for (int i = 1; i < 1000; i++) {
//            new Cell()
            table.addCell("53629479041" + i);
//            table.addCell(new Cell(1, 1));
//            table.addCell(new Paragraph("华为手机HUAWEI M Phone " + i).setFont(font));
            table.addCell(new Paragraph("事业群" + i).setFont(font));
            table.addCell(new Paragraph("事业部" + i).setFont(font));
            table.addCell(new Paragraph("一级部门" + i).setFont(font));
            table.addCell(new Paragraph("一级类目ID" + i).setFont(font));
            table.addCell(new Paragraph("一级类目名称-" + i).setFont(font));
            table.addCell(new Paragraph("二级类目ID" + i).setFont(font));
            table.addCell(new Paragraph("二级类目名称-" + i).setFont(font));
            table.addCell(new Paragraph("三级类目ID" + i).setFont(font));
            table.addCell(new Paragraph("三级类目名称-" + i).setFont(font));
            table.addCell(new Paragraph("ERP账号" + i).setFont(font));
            table.addCell(new Paragraph("品牌").setFont(font));
            if (i%2 == 0) {
                table.addCell(new Paragraph("自营").setFont(font));
            }else {
                table.addCell(new Paragraph("POP").setFont(font));
            }
            table.addCell(new Paragraph("234343" + i + "/324343" + i).setFont(font));
            table.addCell(2099 + i + ".00");
            table.addCell(20119 + i + ".00");
            table.addCell(i + "");
        }
        doc.add(table);
        doc.close();
    }
}
