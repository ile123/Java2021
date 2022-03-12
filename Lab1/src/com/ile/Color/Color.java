package com.ile.Color;
import java.math.*;

public class Color {
    private static int RED;
    private static int BLUE;
    private static int GREEN;

    public Color(int r,int g,int b) {
        RED = r;
        GREEN = g;
        BLUE = b;
    }

    public int getBlue() {
        return BLUE;
    }

    public int getGreen() {
        return GREEN;
    }

    public int getRed() {
        return RED;
    }

    public void setRed(int red){
        RED = red;
    }

    public void setBlue(int blue){
        BLUE = blue;
    }

    public void setGreen(int green){
        GREEN = green;
    }

    public static Color decode(String HEX) {
        int hex = Integer.decode(HEX);
        return new Color((hex >> 16) & 0xFF, (hex >> 8) & 0xFF, hex & 0xFF);
    }

    public static void RGBtoHSB(int RED,int GREEN,int BLUE,float[] hsbCode) {
        float RED2 = RED/255.0f, BLUE2 = BLUE/255.0f, GREEN2 = GREEN/255.0f;
        float hue = 0, saturation = 0, brightness = 0;
        float cmax = Math.max(RED2, Math.max(GREEN2, BLUE2));
        float cmin = Math.min(RED2, Math.min(GREEN2, BLUE2));
        float razlika = cmax-cmin;

        if(razlika == 0) {
            hue = 0;
        }
        else if(cmax == RED2) {
            hue = ((float)(Math.PI / 3)) * (((GREEN2-BLUE2)/razlika)%6);
        }
        else if (cmax == GREEN2) {
            hue = ((float)(Math.PI / 3)) * (((BLUE2-RED2)/razlika)+2);
        }
        else if(cmax == BLUE2) {
            hue = ((float)(Math.PI / 3)) * (((RED2-GREEN2)/razlika)+4);
        }

        if (cmax == 0) {
            saturation = 0;
        }

        else {
            saturation = razlika / cmax;
        }

        brightness = (((float) cmax) / 255.0f)*100;

        hsbCode[0] = hue;
        hsbCode[1] = saturation;
        hsbCode[2] = brightness;
    }

    public int getRGB() {
        int rgb = RED;
        rgb = (rgb << 8) + GREEN;
        rgb = (rgb << 8) + BLUE;
        return rgb;
    }

    public static void RGBtoHSL(int RED,int GREEN,int BLUE,float[] hslCode) {
        float RED2 = RED/255.0f, BLUE2 = BLUE/255.0f, GREEN2 = GREEN/255.0f;
        float hue = 0, saturation = 0, light = 0;
        float cmax = Math.max(RED2, Math.max(GREEN2, BLUE2));
        float cmin = Math.min(RED2, Math.min(GREEN2, BLUE2));
        float razlika = cmax-cmin;
        light = (cmax+cmin)/2;
        if(razlika == 0) {
            hue = 0;
        }
        else if(cmax == RED2) {
            hue = ((float)(Math.PI / 3)) * (((GREEN2-BLUE2)/razlika)%6);
        }
        else if (cmax == GREEN2) {
            hue = ((float)(Math.PI / 3)) * (((BLUE2-RED2)/razlika)+2);
        }
        else if(cmax == BLUE2) {
            hue = ((float)(Math.PI / 3)) * (((RED2-GREEN2)/razlika)+4);
        }

        if(razlika==0) {
            saturation = 0;
        }
        else {
            saturation = (razlika/(1-Math.abs(2*light-1)));
        }
        hslCode[0] = hue;
        hslCode[1] = saturation;
        hslCode[2] = light;
    }

    public static void RGBtoCMYK(int RED,int BLUE,int GREEN,float[] CMYK) {
        float RED2 = RED/255.0f, BLUE2 = BLUE/255.0f, GREEN2 = GREEN/255.0f;
        float K = 1 - Math.max(RED2, Math.max(GREEN2, BLUE2));
        float C = (1 - RED2 - K) / (1 - K);
        float M = (1 - GREEN2 - K) / (1 - K);
        float Y = (1 - BLUE2 - K) / (1 - K);
        CMYK[0] = C;
        CMYK[1] = M;
        CMYK[2] = Y;
        CMYK[3] = K;
    }
}

