package com.ile.Color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {
    @Test
    void Decodetest() {
        Color plava = new Color(31,240,255), test = Color.decode("0x1FF0FF");
        int PLAVA = plava.getBlue() + plava.getGreen() + plava.getRed(), TEST = test.getBlue() + test.getGreen() + test.getRed();
        assertEquals(PLAVA,TEST,0);
    }

    @Test
    void RGBtoCMYKtest() {
        Color boja = Color.decode("0x1FDE2D");
        float[] CYMKCode = new float[4];
        Color.RGBtoCMYK(boja.getRed(),boja.getBlue(),boja.getGreen(),CYMKCode);
        float CYMKCodeValue = CYMKCode[0] + CYMKCode[1] + CYMKCode[2] + CYMKCode[3], value_boja = (float) (0.12941176 + 0.8603604 + 0.0 + 0.7972973);
        assertEquals(CYMKCodeValue,value_boja,1);
    }

    @Test
    void RGBtoHSBtest() {
        Color boja = Color.decode("0x1FDE2D");
        float[] hsbCode = new float[3];
        Color.RGBtoHSB(boja.getRed(),boja.getBlue(),boja.getGreen(),hsbCode);
        float HsbCodeValue = hsbCode[0] + hsbCode[1] + hsbCode[2], value_boja = (float) (4.1120324 + 0.8603604 + 0.34140715);
        assertEquals(HsbCodeValue,value_boja,0.1);
    }

    @Test
    void RGBtoHSLtest() {
        Color boja = Color.decode("0x1FDE2D");
        float[] hslCode = new float[3];
        Color.RGBtoHSL(boja.getRed(),boja.getBlue(),boja.getGreen(),hslCode);
        float HslCodeValue = hslCode[0] + hslCode[1] + hslCode[2], value_boja = (float) (2.171153 + 0.75494075 + 0.49607843);
        assertEquals(HslCodeValue,value_boja,4);
    }
}