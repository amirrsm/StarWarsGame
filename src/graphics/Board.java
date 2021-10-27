package graphics;

import assets.Player;
import assets.elements.SpeedBump;
import assets.elements.Star;
import assets.elements.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame {

    //state of game:  0-->setElements  1-->Start Game
    static int state = 0;
    final private static int CELL_DIMENSION = 48;
    static Color color1;
    static Color color2;
    private static Player player1;
    private static Player player2;
    private static int turn;
    private static Star[] star;
    private static Wall[] wall;
    private static SpeedBump[] speedBump;
    private static Cell[][] board2D;

    private static int stars;
    private static int walls;
    private static int speedBumps;
    private static int player1Set = 1;
    private static int player2Set = 1;

    public Board(int rows, int columns, Color color11, Color color22, Player player1, Player player2, int turn) {

        int FRAME_WIDTH = (columns * CELL_DIMENSION) + 300;
        int FRAME_HEIGHT = (rows * CELL_DIMENSION) + 40;
        color1 = color11;
        color2 = color22;
        Board.player1 = player1;
        Board.player2 = player2;
        board2D = new Cell[rows][columns];
        Board.turn = turn;


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("GAME");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board2D[i][j] = new Cell(i, j, color1, color2, CELL_DIMENSION);
                add(board2D[i][j], 0);
            }
        }

        JButton startGame = new JButton();
        startGame.setBounds((columns * CELL_DIMENSION) + 75, (rows - 1) * CELL_DIMENSION, 150, 25);
        startGame.setText("ُSTART GAME");
        startGame.setHorizontalAlignment(SwingConstants.CENTER);
        add(startGame);

        JButton points = new JButton();
        points.setBounds((columns * CELL_DIMENSION) + 100, 30, 100, 25);
        points.setText("POINTS");
        points.setHorizontalAlignment(SwingConstants.CENTER);
        points.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame point = new JFrame();
                JOptionPane.showMessageDialog(point, Board.points(), "points", JOptionPane.OK_OPTION);
            }
        });

        JButton speedBump = new JButton();
        speedBump.setBounds((columns * CELL_DIMENSION) + 75, 80, 150, 25);
        speedBump.setText("SPEEDBUMPS");
        speedBump.setHorizontalAlignment(SwingConstants.CENTER);
        speedBump.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame speedbump = new JFrame();
                JOptionPane.showMessageDialog(speedbump, Board.speedBump(), "speedBumps", JOptionPane.OK_OPTION);
            }
        });


        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (stars == 0 && walls == 0 && speedBumps == 0 && player1Set == 0 && player2Set == 0) {
                    state = 1;
                    add(points);
                    add(speedBump);
                    startGame.setVisible(false);
                } else {
                    JFrame starNumError = new JFrame();
                    JOptionPane.showMessageDialog(starNumError, "PLease set location of all elements!",
                            "error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        setVisible(true);
        numberOfElements(rows, columns);
        Board.wall = new Wall[walls];
        Board.star = new Star[stars];
        Board.speedBump = new SpeedBump[speedBumps];
        Wall.setNumberOfWalls(walls);
        Star.setNumberOfStars(stars);
        SpeedBump.setNumberOfSpeedBumps(speedBumps);
    }

    static void numberOfElements(int row, int column) {

        int stars = -1;
        int walls = -1;
        int speedBumps = -1;
        int allAvailableCells = (row * column) - 2;

        do {
            JFrame number = new JFrame();
            String x = JOptionPane.showInputDialog(number, "Input number of stars(1 - " + allAvailableCells + ")", 1);
            if (Integer.parseInt(x) >= 1 && Integer.parseInt(x) <= allAvailableCells) {
                stars = Integer.parseInt(x);
            }

        } while (stars <= 0);

        do {
            JFrame number = new JFrame();
            String x = JOptionPane.showInputDialog(number, "Input number of walls(0 - " + (allAvailableCells - stars) + ")", 0);
            if (Integer.parseInt(x) >= 0 && Integer.parseInt(x) <= allAvailableCells - stars) {
                walls = Integer.parseInt(x);
            }

        } while (walls < 0);

        do {
            JFrame number = new JFrame();
            String x = JOptionPane.showInputDialog(number, "Input number of speedBumps(0 - " + (allAvailableCells - stars - walls) + ")", 0);
            if (Integer.parseInt(x) >= 0 && Integer.parseInt(x) <= allAvailableCells - stars - walls) {
                speedBumps = Integer.parseInt(x);
            }

        } while (speedBumps < 0);

        Board.stars = stars;
        Board.walls = walls;
        Board.speedBumps = speedBumps;
        player1.setSpeedBump(new int[Board.speedBumps]);
        player2.setSpeedBump(new int[Board.speedBumps]);
    }

    static String points() {
        return player1.getUsername() + ": " + player1.getPoint() + "\n" +
                player2.getUsername() + ": " + player2.getPoint();
    }

    static String speedBump() {
        StringBuilder sp1 = new StringBuilder();
        StringBuilder sp2 = new StringBuilder();
        sp1.append(player1.getUsername());
        sp1.append(": ");
        sp2.append(player2.getUsername());
        sp2.append(": ");

        for (int i = 0; i < SpeedBump.getNumberOfSpeedBumps(); i++) {
            if (player1.getSpeedBump()[i] != -1 && player1.getSpeedBump()[i] != 0) {
                sp1.append(player1.getSpeedBump()[i]);
                sp1.append("  ");
            }
            if (player2.getSpeedBump()[i] != -1 && player2.getSpeedBump()[i] != 0) {
                sp2.append(player2.getSpeedBump()[i]);
                sp2.append("  ");
            }
        }

        String sp11 = sp1.toString();
        String sp22 = sp2.toString();

        return sp11 + "\n" + sp22;

    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int turn) {
        Board.turn = turn;
    }

    public static int getStars() {
        return stars;
    }

    public static int getWalls() {
        return walls;
    }

    public static int getSpeedBumps() {
        return speedBumps;
    }

    public static void setStars(int stars) {
        Board.stars = stars;
    }

    public static void setWalls(int walls) {
        Board.walls = walls;
    }

    public static void setSpeedBumps(int speedBumps) {
        Board.speedBumps = speedBumps;
    }

    public static int getPlayer1Set() {
        return player1Set;
    }

    public static int getPlayer2Set() {
        return player2Set;
    }

    public static void setPlayer1Set(int player1Set) {
        Board.player1Set = player1Set;
    }

    public static void setPlayer2Set(int player2Set) {
        Board.player2Set = player2Set;
    }

    public static Star[] getStar() {
        return star;
    }

    public static Wall[] getWall() {
        return wall;
    }

    public static SpeedBump[] getSpeedBump() {
        return speedBump;
    }

    public static Cell[][] getBoard2D() {
        return board2D;
    }
}