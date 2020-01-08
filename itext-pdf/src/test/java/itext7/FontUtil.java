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
    static {
        try {
            STSong_Light = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H",true);
            MHei_Medium = PdfFontFactory.createFont("MHei-Medium", "UniCNS-UCS2-H", true);
            SIMHEI = PdfFontFactory.createFont("src/test/resources/simhei.ttf", PdfEncodings.IDENTITY_H,true);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
