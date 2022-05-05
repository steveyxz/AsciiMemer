package me.partlysunny;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;

//See https://www.dreamincode.net/forums/topic/363938-Converting-an-Image-to-ASCII-Art-%28with-color%21%29/
public class ImageToAscii {

    private final int pixelsPerChar;

    public ImageToAscii(int pixelsPerChar) {
        this.pixelsPerChar = pixelsPerChar;
    }
    public String[] convert(BufferedImage bufferedImage) {
        StringBuilder[] imageInASCII = new StringBuilder[bufferedImage.getHeight() / pixelsPerChar];
        for (int i = 0; i < imageInASCII.length; ++i) {
            imageInASCII[i] = new StringBuilder();
        }
        //Converts to greyscale.
        ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp colorConvertOp = new ColorConvertOp(colorSpace, null);
        bufferedImage = colorConvertOp.filter(bufferedImage, null);
        //Gets mean color of each of squares.
        int meanColor;
        Color color;
        for (int y = 0; y < bufferedImage.getHeight(); y += pixelsPerChar) {
            for (int x = 0; x < bufferedImage.getWidth(); x += pixelsPerChar) {
                if (y + pixelsPerChar <= bufferedImage.getHeight() && x + pixelsPerChar <= bufferedImage.getWidth()) {
                    meanColor = 0;
                    if (pixelsPerChar == 1) {
                        color = new Color(bufferedImage.getRGB(x, y));
                        meanColor += color.getBlue(); //Could be also green or red, they all are the same, because the image is in greyscale.
                    } else {
                        for (int square = 0; square < pixelsPerChar; ++square) {
                            color = new Color(bufferedImage.getRGB(x, y + square));
                            meanColor += color.getBlue();
                            color = new Color(bufferedImage.getRGB(x + square, y));
                            meanColor += color.getBlue();
                        }
                        meanColor /= pixelsPerChar;
                    }
                    imageInASCII[y / pixelsPerChar].append(colorToChar(meanColor)).append(' ');
                }
            }
        }
        String[] asciiImage = new String[bufferedImage.getHeight() / pixelsPerChar];
        for (int i = 0; i < asciiImage.length; ++i) {
            asciiImage[i] = imageInASCII[i].toString();
        }
        return asciiImage;
    }
    private char colorToChar(int color) {
        if (color < 25) {
            return '@';
        } else if (color < 51) {
            return '#';
        } else if (color < 76) {
            return '8';
        } else if (color < 102) {
            return '&';
        } else if (color < 127) {
            return 'o';
        } else if (color < 153) {
            return '*';
        } else if (color < 178) {
            return '/';
        } else if (color < 204) {
            return '\'';
        } else if (color < 229) {
            return '.';
        }
        return ' ';
    }

}
