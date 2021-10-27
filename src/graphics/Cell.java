package graphics;

import assets.elements.SpeedBump;
import assets.elements.Star;
import assets.elements.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static controller.GameRules.*;
import static graphics.Board.*;

public class Cell extends JButton {

    private static int dimension;
    private int kindOfElement = 0;

    public void setKindOfElement(int kindOfElement) {
        this.kindOfElement = kindOfElement;
    }

    public int getKindOfElement() {
        return kindOfElement;
    }

    public Cell(int i, int j, Color color1, Color color2, int dimension) {
        Cell.dimension = dimension;

        setLayout(null);
        setBounds((j * dimension), (i * dimension), dimension, dimension);
        if ((i + j) % 2 == 0)
            setBackground(color1);
        else
            setBackground(color2);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (state == 0) {
                    JPopupMenu itemSetting = new JPopupMenu();
                    itemSetting.setSize(200, 200);
                    JMenuItem star = new JMenuItem("Star  " + Board.getStars());
                    JMenuItem speedBump = new JMenuItem("SpeedBump  " + Board.getSpeedBumps());
                    JMenuItem wall = new JMenuItem("Wall  " + Board.getWalls());
                    JMenuItem player1 = new JMenuItem(Board.getPlayer1().getUsername());
                    if (Board.getPlayer1Set() == 0)
                        player1.setText(Board.getPlayer1().getUsername() + "   set");
                    JMenuItem player2 = new JMenuItem(Board.getPlayer2().getUsername());
                    if (Board.getPlayer2Set() == 0)
                        player2.setText(Board.getPlayer2().getUsername() + "   set");
                    itemSetting.add(star);
                    itemSetting.add(wall);
                    itemSetting.add(speedBump);
                    itemSetting.add(player1);
                    itemSetting.add(player2);

                    star.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            if (Board.getStars() > 0 && kindOfElement == 0) {
                                Board.getStar()[Star.getFixed()] = new Star(i, j);
                                kindOfElement = 1;
                                Board.setStars(Board.getStars() - 1);
                                ImageIcon imgStar = new ImageIcon("src/pictures/star.png");
                                setIcon(imgStar);
                            }
                        }
                    });
                    wall.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            if (Board.getWalls() > 0 && kindOfElement == 0) {
                                Board.getWall()[Wall.getFixed()] = new Wall(i, j);
                                kindOfElement = 2;
                                Board.setWalls(Board.getWalls() - 1);
                                ImageIcon imgWall = new ImageIcon("src/pictures/wall.png");
                                setIcon(imgWall);
                            }
                        }
                    });
                    speedBump.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            int limit = -1;

                            if (Board.getSpeedBumps() > 0 && kindOfElement == 0) {

                                do {
                                    JFrame limitInput = new JFrame();
                                    String x = JOptionPane.showInputDialog(limitInput, "Enter limit of speedBump", "1");
                                    limit = Integer.parseInt(x);
                                } while (limit <= 0 || limit > 16);

                                Board.getSpeedBump()[SpeedBump.getFixed()] = new SpeedBump(i, j, limit);
                                kindOfElement = 3;
                                Board.setSpeedBumps(Board.getSpeedBumps() - 1);
                                ImageIcon imgSpeedBump = new ImageIcon("src/pictures/speedBump.png");
                                setIcon(imgSpeedBump);
                                setText(String.valueOf(Board.getSpeedBump()[SpeedBump.getFixed() - 1].getLimit()));
                                setHorizontalTextPosition(SwingConstants.CENTER);
                                setVerticalTextPosition(SwingConstants.CENTER);
                            }
                        }
                    });
                    player1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            if (Board.getPlayer1Set() != 0 && kindOfElement == 0) {
                                kindOfElement = 4;
                                Board.getPlayer1().setLastX(i);
                                Board.getPlayer1().setLastY(j);
                                Board.setPlayer1Set(Board.getPlayer1Set() - 1);
                                ImageIcon imgPlayer1 = new ImageIcon("src/pictures/Esteghlal.png");
                                setIcon(imgPlayer1);
                            }
                        }
                    });
                    player2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            if (Board.getPlayer2Set() != 0 && kindOfElement == 0) {
                                kindOfElement = 5;
                                Board.getPlayer1().setLastX(i);
                                Board.getPlayer2().setLastY(j);
                                Board.setPlayer2Set(Board.getPlayer2Set() - 1);
                                ImageIcon imgPlayer2 = new ImageIcon("src/pictures/Persepolis.png");
                                setIcon(imgPlayer2);
                            }
                        }
                    });
                    setComponentPopupMenu(itemSetting);
                }
            }
        });


        /* ---------------Player1 movement--------------- */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                //select rules
                if (Board.state == 1 && getTurn() == 1 && kindOfElement == 4 && !getPlayer1().isSelected) {
                    getPlayer1().setLastX(i);
                    getPlayer1().setLastY(j);
                    setIcon(null);
                    ImageIcon imgPlayer1 = new ImageIcon("src/pictures/selectedEsteghlal.jpg");
                    setIcon(imgPlayer1);
                    getPlayer1().isSelected = true;
                } else if (Board.state == 1 && getTurn() == 1 && kindOfElement == 4 && getPlayer1().isSelected) {
                    ImageIcon imgPlayer1 = new ImageIcon("src/pictures/Esteghlal.png");
                    setIcon(imgPlayer1);
                    getPlayer1().isSelected = false;
                }

                //movement rules
                else if (Board.state == 1 && getTurn() == 1 && getPlayer1().isSelected) {
                    if ((i != getPlayer1().getLastX() && j != getPlayer1().getLastY())) {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Only movement in rows or columns is allowed!", "Forbidden movement", JOptionPane.ERROR_MESSAGE);
                    } else if (!wallRules(i, j, getPlayer1())) {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Wall is on the way!", "Forbidden movement", JOptionPane.ERROR_MESSAGE);
                    } else if (!speedBumpRules(i, j, getPlayer1())) {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "You can't move more than your limit!\n" +
                                "Please watch your limits.", "Forbidden movement", JOptionPane.ERROR_MESSAGE);
                    } else if (movingRules(i, j, getPlayer2())) {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "both players, can't be on same cell!", "Forbidden movement", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Board.getBoard2D()[getPlayer1().getLastX()][getPlayer1().getLastY()].setIcon(null);
                        Board.getBoard2D()[i][j].setIcon(null);
                        Board.getBoard2D()[i][j].setText(null);
                        turnPlayer1(getPlayer1(), getPlayer2(), i, j);
                        ImageIcon imgPlayer1 = new ImageIcon("src/pictures/Esteghlal.png");
                        setIcon(imgPlayer1);
                        getPlayer1().isSelected = false;

                        if (checkEndGame()) {
                            JFrame winner = new JFrame();
                            if (getPlayer1().getPoint() > getPlayer2().getPoint()) {
                                JOptionPane.showMessageDialog(winner, getPlayer1().getUsername() + ": " + getPlayer1().getPoint() + "\n" +
                                        getPlayer2().getUsername() + ": " + getPlayer2().getPoint() + "\n" +
                                        "Winner:  ✵" + getPlayer1().getUsername() + "✵");
                            } else if (getPlayer2().getPoint() > getPlayer1().getPoint()) {
                                JOptionPane.showMessageDialog(winner, getPlayer1().getUsername() + ": " + getPlayer1().getPoint() + "\n" +
                                        getPlayer2().getUsername() + ": " + getPlayer2().getPoint() + "\n" +
                                        "Winner:  ✵" + getPlayer2().getUsername() + "✵");
                            } else if (getPlayer2().getPoint() == getPlayer1().getPoint()) {
                                JOptionPane.showMessageDialog(winner, getPlayer1().getUsername() + ": " + getPlayer1().getPoint() + "\n" +
                                        getPlayer2().getUsername() + ": " + getPlayer2().getPoint() + "\n" +
                                        "                 DRAW");
                            }
                        }
                    }
                }
            }
        });


        /* ---------------Player2 movement--------------- */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                //select rules
                if (Board.state == 1 && getTurn() == 2 && kindOfElement == 5 && !getPlayer2().isSelected) {

                    getPlayer2().setLastX(i);
                    getPlayer2().setLastY(j);
                    setIcon(null);
                    ImageIcon imgPlayer2 = new ImageIcon("src/pictures/selectedPersepolis.jpg");
                    setIcon(imgPlayer2);
                    getPlayer2().isSelected = true;
                } else if (Board.state == 1 && getTurn() == 2 && kindOfElement == 5 && getPlayer2().isSelected) {
                    ImageIcon imgPlayer2 = new ImageIcon("src/pictures/Persepolis.png");
                    setIcon(imgPlayer2);
                    getPlayer2().isSelected = false;
                }

                //movement rules
                else if (Board.state == 1 && getTurn() == 2 && getPlayer2().isSelected) {
                    if ((i != getPlayer2().getLastX() && j != getPlayer2().getLastY())) {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Only movement in rows or columns is allowed!", "Forbidden movement", JOptionPane.ERROR_MESSAGE);
                    } else if (!wallRules(i, j, getPlayer2())) {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "Wall is on the way!", "Forbidden movement", JOptionPane.ERROR_MESSAGE);
                    } else if (!speedBumpRules(i, j, getPlayer2())) {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "You can't move more than your limit!\n" +
                                "Please watch your limits.", "Forbidden movement", JOptionPane.ERROR_MESSAGE);
                    } else if (movingRules(i, j, getPlayer1())) {
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error, "both players, can't be on same cell!", "Forbidden movement", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Board.getBoard2D()[getPlayer2().getLastX()][getPlayer2().getLastY()].setIcon(null);
                        Board.getBoard2D()[i][j].setIcon(null);
                        Board.getBoard2D()[i][j].setText(null);
                        turnPlayer2(getPlayer1(), getPlayer2(), i, j);
                        ImageIcon imgPlayer2 = new ImageIcon("src/pictures/Persepolis.png");
                        setIcon(imgPlayer2);
                        getPlayer2().isSelected = false;

                        if (checkEndGame()) {
                            JFrame winner = new JFrame();
                            if (getPlayer1().getPoint() > getPlayer2().getPoint()) {
                                JOptionPane.showMessageDialog(winner, getPlayer1().getUsername() + ": " + getPlayer1().getPoint() + "\n" +
                                        getPlayer2().getUsername() + ": " + getPlayer2().getPoint() + "\n" +
                                        "Winner:  ✵" + getPlayer1().getUsername() + "✵");
                            } else if (getPlayer2().getPoint() > getPlayer1().getPoint()) {
                                JOptionPane.showMessageDialog(winner, getPlayer1().getUsername() + ": " + getPlayer1().getPoint() + "\n" +
                                        getPlayer2().getUsername() + ": " + getPlayer2().getPoint() + "\n" +
                                        "Winner:  ✵" + getPlayer2().getUsername() + "✵");
                            } else if (getPlayer2().getPoint() == getPlayer1().getPoint()) {
                                JOptionPane.showMessageDialog(winner, getPlayer1().getUsername() + ": " + getPlayer1().getPoint() + "\n" +
                                        getPlayer2().getUsername() + ": " + getPlayer2().getPoint() + "\n" +
                                        "                 DRAW");
                            }
                        }
                    }
                }

            }
        });

    }

}