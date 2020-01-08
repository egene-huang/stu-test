package itext7;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

/**
 * <br/>
 * User: huangyijun@jd.com <br/>
 * Date&Time: 2020/1/8 15:26
 */
public class FontUtil {
    public static PdfFont STSong_Light;
    public static PdfFont MHei_Medium;
    public static PdfFont SIMHEI;
    public static PdfFont DroidSans;
    public static PdfFont HYS1GFM;
    public static PdfFont simsunb;
    static {
        try {
            STSong_Light = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H",true);
            SIMHEI = PdfFontFactory.createFont("src/test/resources/fonts/simhei.ttf", PdfEncodings.IDENTITY_H,true);
            simsunb = PdfFontFactory.createFont("src/test/resources/fonts/simsunb.ttf", PdfEncodings.IDENTITY_H,true);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
