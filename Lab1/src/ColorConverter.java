import com.ile.Color.*;

//import java.awt.Color;
public class ColorConverter {
    public static void main(String[] args) {

        String hexColor = "0x1FF0FF";
        //Color c = Color.decode(hexColor);
        Color c_ile = Color.decode(hexColor);
        float[] hsbCode = new float[3];
        float[] hslCode = new float[3];
        float[] cymkCode = new float[4];

        Color.RGBtoHSB(c_ile.getRed(), c_ile.getGreen(), c_ile.getBlue(), hsbCode);
        Color.RGBtoHSL(c_ile.getRed(), c_ile.getGreen(), c_ile.getBlue(), hslCode);
        Color.RGBtoCMYK(c_ile.getRed(), c_ile.getBlue(), c_ile.getGreen(), cymkCode);

        System.out.println("Boja u HEX formatu: 0x" + Integer.toHexString(c_ile.getRGB() & 0x00FFFFFF));
        System.out.println("Boja u RGB formatu: " + c_ile.getRed() + ", " +  c_ile.getGreen() + ", " + c_ile.getBlue());
        System.out.println("Boja u HSB formatu: " + hsbCode[0] * 360 + "°, " +  hsbCode[1] * 100 + "%, " + hsbCode[2] * 100 + "%");
        System.out.println("Boja u HSL formatu: " + hslCode[0]*360 + "°   "  +  hslCode[1]*100 + "%   " +"   "+ hslCode[2]*100 + "% " );
        System.out.println("Boja u CMYK formatu: " + cymkCode[0] * 100 + "%, " +  cymkCode[1] * 100 + "%, " + cymkCode[2] * 100 + "%,   " + cymkCode[3] * 100 + "%");
    }
}
