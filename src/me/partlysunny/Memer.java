package me.partlysunny;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Memer {

    private static final String memeUrl = null;
    
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("C:\\MyStuff\\Java\\f.txt");
        if (memeUrl == null) {
            String[] convert = new ImageToAscii(10).convert(MemeGrabber.getMeme());
            for (String s : convert) {
                fw.write(s);
                fw.write("\n");
            }
        } else {
            String[] convert = new ImageToAscii(10).convert(MemeGrabber.getImageFromUrl(memeUrl));
            for (String s : convert) {
                fw.write(s);
                fw.write("\n");
            }
        }
        fw.flush();
        fw.close();
    }
    
}
