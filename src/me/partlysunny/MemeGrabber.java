package me.partlysunny;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MemeGrabber {

    public static BufferedImage getMeme() throws IOException {
        URL obj = new URL("https://meme-api.herokuapp.com/gimme");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(response.toString()).getAsJsonObject();
            if (!json.get("nsfw").getAsBoolean()) {
                String imageUrl = json.get("url").getAsString();
                BufferedImage image = getImageFromUrl(imageUrl);
                if (image != null) {
                    return image;
                }
            }
        } else {
            System.out.println("GET request not worked");
        }
        throw new IllegalArgumentException("GET Response got inappropriate or invalid image");
    }

    public static BufferedImage getImageFromUrl(String url) throws IOException {
        URL img = new URL(url);
        BufferedImage image = ImageIO.read(img);
        if (image != null) {
            return image;
        }
        return null;
    }

}
