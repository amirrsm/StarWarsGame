package graphics;

import assets.Player;
import controller.GameConfigController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameConfig extends JFrame {

    public GameConfig() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(400, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Board Setting");

        JLabel comment1 = new JLabel();
        comment1.setBounds(0, 25, 380, 20);
        comment1.setText("Please enter name of players");
        comment1.setHorizontalAlignment(SwingConstants.CENTER);
        add(comment1);
        JLabel comment2 = new JLabel();
        comment2.setBounds(0, 50, 380, 20);
        comment2.setText("Name should be between 4-12 characters");
        comment2.setHorizontalAlignment(SwingConstants.CENTER);
        add(comment2);
        JLabel comment3 = new JLabel();
        comment3.setBounds(0, 75, 380, 20);
        comment3.setText("Only a-z,A-Z,0-9,and underline allowed!");
        comment3.setHorizontalAlignment(SwingConstants.CENTER);
        add(comment3);

        JLabel player1 = new JLabel();
        player1.setBounds(125, 110, 50, 20);
        player1.setText("Player1:");
        player1.setHorizontalAlignment(SwingConstants.LEFT);
        add(player1);
        JTextField player1Username = new JTextField();
        player1Username.setBounds(175, 110, 100, 20);
        add(player1Username);

        JLabel player2 = new JLabel();
        player2.setBounds(125, 135, 50, 20);
        player2.setText("Player2:");
        player2.setHorizontalAlignment(SwingConstants.LEFT);
        add(player2);
        JTextField player2Username = new JTextField();
        player2Username.setBounds(175, 135, 100, 20);
        add(player2Username);

        JLabel starter = new JLabel();
        starter.setBounds(100, 170, 50, 20);
        starter.setText("Starter:");
        starter.setHorizontalAlignment(SwingConstants.LEFT);
        add(starter);
        JRadioButton choosePlayer1 = new JRadioButton("", true);
        choosePlayer1.setBounds(150, 170, 75, 20);
        choosePlayer1.setText("Player1");
        add(choosePlayer1);
        JRadioButton choosePlayer2 = new JRadioButton();
        choosePlayer2.setBounds(225, 170, 75, 20);
        choosePlayer2.setText("Player2");
        add(choosePlayer2);
        ButtonGroup chooseStarter = new ButtonGroup();
        chooseStarter.add(choosePlayer1);
        chooseStarter.add(choosePlayer2);

        JLabel boardDimension1 = new JLabel();
        boardDimension1.setBounds(50, 250, 300, 20);
        boardDimension1.setText("Please choose your board dimensions");
        boardDimension1.setHorizontalAlignment(SwingConstants.CENTER);
        add(boardDimension1);
        JLabel boardDimension2 = new JLabel();
        boardDimension2.setBounds(50, 275, 300, 20);
        boardDimension2.setText("You can choose number between 4-16");
        boardDimension2.setHorizontalAlignment(SwingConstants.CENTER);
        add(boardDimension2);
        JLabel row = new JLabel();
        row.setBounds(75, 300, 50, 20);
        row.setText("Rows:");
        row.setHorizontalAlignment(SwingConstants.LEFT);
        add(row);
        SpinnerModel rows = new SpinnerNumberModel(4, 4, 16, 2);
        JSpinner rowValue = new JSpinner(rows);
        rowValue.setBounds(125, 300, 50, 25);
        add(rowValue);
        JLabel column = new JLabel();
        column.setBounds(225, 300, 75, 20);
        column.setText("Columns:");
        column.setHorizontalAlignment(SwingConstants.LEFT);
        add(column);
        SpinnerModel columns = new SpinnerNumberModel(4, 4, 16, 2);
        JSpinner columnValue = new JSpinner(columns);
        columnValue.setBounds(300, 300, 50, 25);
        add(columnValue);

        JLabel pallet = new JLabel();
        pallet.setBounds(50, 370, 300, 20);
        pallet.setText("Please choose board pattern");
        pallet.setHorizontalAlignment(SwingConstants.CENTER);
        add(pallet);
        JButton color1 = new JButton();
        color1.setBounds(115, 400, 75, 30);
        color1.setText("Color1");
        color1.setBackground(Color.CYAN);
        Color[] returnColor1 = {Color.CYAN};
        color1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color1Init = Color.CYAN;
                Color color = JColorChooser.showDialog(color1, "Choose color", color1Init);
                color1.setBackground(color);
                returnColor1[0] = color1.getBackground();
            }
        });
        add(color1);
        JButton color2 = new JButton();
        color2.setBounds(210, 400, 75, 30);
        color2.setText("Color2");
        color2.setBackground(Color.GREEN);
        Color[] returnColor2 = {Color.GREEN};
        color2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color1Init = Color.GREEN;
                Color color = JColorChooser.showDialog(color2, "Choose color", color1Init);
                color2.setBackground(color);
                returnColor2[0] = color2.getBackground();
            }
        });
        add(color2);
        JButton applyPattern = new JButton();
        applyPattern.setBounds(125, 450, 150, 30);
        applyPattern.setText("PREVIEW");
        applyPattern.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel[][] panel = new JPanel[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                panel[i][j] = new JPanel();
        JLabel boardPreview = new JLabel();
        applyPattern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                boardPreview.setBounds(50, 510, 300, 20);
                boardPreview.setText("Board preview");
                boardPreview.setHorizontalAlignment(SwingConstants.CENTER);
                final int WIDTH = 50;
                final int HEIGHT = 50;
                final int X = 125;
                final int Y = 530;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        panel[i][j].setBounds(X + (j * WIDTH), Y + (i * HEIGHT), WIDTH, HEIGHT);
                        if ((i + j) % 2 == 0)
                            panel[i][j].setBackground(returnColor1[0]);
                        else
                            panel[i][j].setBackground(returnColor2[0]);
                    }
                }
            }

        });
        add(applyPattern);
        add(boardPreview);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                add(panel[i][j]);

        JButton next = new JButton();
        next.setBounds(150, 710, 100, 30);
        next.setText("NEXT");
        next.setHorizontalAlignment(SwingConstants.CENTER);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //GameConfigController gcc=new GameConfigController();

                if (GameConfigController.nameDiffController(player1Username.getText(), player2Username.getText())) {
                    JFrame nameDiffError = new JFrame();
                    JOptionPane.showMessageDialog(nameDiffError, "Please enter two different name.",
                            "Same name", JOptionPane.ERROR_MESSAGE);
                } else if (!GameConfigController.nameController(player1Username.getText()) ||
                        !GameConfigController.nameController(player2Username.getText())) {
                    JFrame nameError = new JFrame();
                    JOptionPane.showMessageDialog(nameError, "Your name is invalid!",
                            "Invalid name", JOptionPane.ERROR_MESSAGE);
                }

                if (GameConfigController.colorController(returnColor1[0], returnColor2[0])) {
                    JFrame colorError = new JFrame();
                    JOptionPane.showMessageDialog(colorError, "Please choose 2 different colors!",
                            "Same color", JOptionPane.ERROR_MESSAGE);
                }

                if (!GameConfigController.nameDiffController(player1Username.getText(), player2Username.getText()) &&
                        GameConfigController.nameController(player1Username.getText()) &&
                        GameConfigController.nameController(player2Username.getText()) &&
                        !GameConfigController.colorController(returnColor1[0], returnColor2[0])) {
                    Player player1 = new Player(player1Username.getText());
                    Player player2 = new Player(player2Username.getText());
                    int rowsValue = Integer.parseInt(rowValue.getModel().getValue().toString());
                    int columnsValue = Integer.parseInt(columnValue.getModel().getValue().toString());
                    int turn = 1;

                    if (choosePlayer1.isSelected()) {
                        turn = 1;
                    } else if (choosePlayer2.isSelected()) {
                        turn = 2;
                    }
                    setVisible(false);
                    new Board(rowsValue, columnsValue, returnColor1[0], returnColor2[0], player1, player2, turn);
                }
            }
        });
        add(next);

        setVisible(true);
    }
}
