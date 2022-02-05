package com.WillCodes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BestTemp {
    private JTextArea textArea1;
    private JPanel panel;
    private JTextField textField1;
    private JButton resetButton;

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, InterruptedException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        var frame = new JFrame("Today it's:");
        frame.setContentPane(new BestTemp().panel);
        frame.setVisible(true);
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(ImageIO.read(new File("src/Icon.png")));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dark-sky.p.rapidapi.com/%7Blatitude%7D,%7Blongitude%7D?units=auto&lang=en"))
                .header("x-rapidapi-host", "dark-sky.p.rapidapi.com")
                .header("x-rapidapi-key", "Your api key")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
