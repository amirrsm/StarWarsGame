package graphics.menus;

import graphics.GameConfig;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class StartMenu extends JFrame {

    public StartMenu() {

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(null);
        setSize(400, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("StarWars");

        JButton startButton = new JButton();
        startButton.setBounds(100, 200, 200, 100);
        ImageIcon startImg = new ImageIcon("src/pictures/Start.png");
        startButton.setIcon(startImg);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GameConfig();
            }
        });
        add(startButton);

        JButton settingButton = new JButton();
        settingButton.setBounds(100, 325, 200, 100);
        ImageIcon settingImg = new ImageIcon("src/pictures/Setting.png");
        settingButton.setIcon(settingImg);
        add(settingButton);

        JButton exitButton = new JButton();
        exitButton.setBounds(100, 450, 200, 100);
        ImageIcon exitImg = new ImageIcon("src/pictures/Exit.png");
        exitButton.setIcon(exitImg);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame exitMessage = new JFrame();
                int order = JOptionPane.showConfirmDialog(exitMessage, "Are you sure?");
                if (order == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        add(exitButton);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage resized = null;
        try {
            BufferedImage image = ImageIO.read(new File("src/Pictures/StartMenuBackG.png"));
            resized = new BufferedImage(400, 650, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = resized.createGraphics();
            graphics2D.drawImage(image, 0, 0, 400, 650, this);
            graphics2D.dispose();

        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(resized, 0, 0, this);
    }

}