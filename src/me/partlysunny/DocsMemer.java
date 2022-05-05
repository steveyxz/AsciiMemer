package me.partlysunny;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class DocsMemer {

    private static final int numMemes = 3;
    private static final Point replaceBox = new Point(642, 398);
    private static final Point withBox = new Point(850, 401);
    private static final int startAt = 0;

    public static void main(String[] args) throws IOException, AWTException, InterruptedException {
        Thread.sleep(3000);
        for (int i = 0; i < numMemes; i++) {
            String[] convert = new ImageToAscii(40).convert(MemeGrabber.getMeme());
            StringBuilder sb = new StringBuilder();
            for (String s : convert) {
                sb.append(s);
                sb.append("\n");
            }
            Robot r = new Robot();
            StringSelection selection = new StringSelection("*meme<" + startAt + i + ">");
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            r.mouseMove(replaceBox.x, replaceBox.y);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_V);
            Thread.sleep(300);
            selection = new StringSelection(sb.toString());
            clipboard.setContents(selection, selection);
            r.mouseMove(withBox.x, withBox.y);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_V);
            Thread.sleep(300);
        }
        Thread.sleep(400);
    }
}
