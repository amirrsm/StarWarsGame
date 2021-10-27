package controller;

import assets.Player;
import assets.elements.SpeedBump;
import assets.elements.Star;
import graphics.Board;

public class GameRules {

    public static boolean checkEndGame() {
        return Star.getNumberOfStars() == 0;
    }

    public static boolean movingRules(int i, int j, Player player) {
        return (i == player.getLastX() && j == player.getLastY());
    }

    public static boolean wallRules(int i, int j, Player player) {
        if (i != player.getLastX()) {
            if (i - player.getLastX() > 0) {
                for (int k = player.getLastX() + 1; k <= i; k++) {
                    if (Board.getBoard2D()[k][j].getKindOfElement() == 2)
                        return false;
                }
            } else {
                for (int k = player.getLastX() - 1; k >= i; k--) {
                    if (Board.getBoard2D()[k][j].getKindOfElement() == 2)
                        return false;
                }
            }
        } else {
            if (j - player.getLastY() > 0) {
                for (int k = player.getLastY() + 1; k <= j; k++) {
                    if (Board.getBoard2D()[i][k].getKindOfElement() == 2)
                        return false;
                }
            } else {
                for (int k = player.getLastY() - 1; k >= j; k--) {
                    if (Board.getBoard2D()[i][k].getKindOfElement() == 2)
                        return false;
                }
            }
        }
        return true; // it means no wall on the way
    }

    public static boolean speedBumpRules(int i, int j, Player player) {
        for (int k = 0; k < player.getFindSpeedBump(); k++) {
            if (player.getSpeedBump()[k] > 0) {
                if (i != player.getLastX()) {
                    if (player.getSpeedBump()[k] < Math.abs(player.getLastX() - i))
                        return false;
                } else if (j != player.getLastY()) {
                    if (player.getSpeedBump()[k] < Math.abs(player.getLastY() - j))
                        return false;
                }

                player.getSpeedBump()[k] = -1; // --> speedBump applied
                return true;
            }
        }
        return true;
    }

    public static void turnPlayer1(Player player1, Player player2, int i, int j) {

        if (i != player1.getLastX()) {

            if (i - player1.getLastX() > 0) {
                for (int k = player1.getLastX() + 1; k <= i; k++) {
                    if (Board.getBoard2D()[k][j].getKindOfElement() == 1) {
                        player1.setPoint(player1.getPoint() + 1);
                        Star.setNumberOfStars(Star.getNumberOfStars() - 1);
                        Board.getBoard2D()[k][j].setKindOfElement(0);
                        Board.getBoard2D()[k][j].setIcon(null);
                    }
                }
            } else {
                for (int k = player1.getLastX() - 1; k >= i; k--) {
                    if (Board.getBoard2D()[k][j].getKindOfElement() == 1) {
                        player1.setPoint(player1.getPoint() + 1);
                        Star.setNumberOfStars(Star.getNumberOfStars() - 1);
                        Board.getBoard2D()[k][j].setKindOfElement(0);
                        Board.getBoard2D()[k][j].setIcon(null);
                    }
                }
            }

            if (i - player1.getLastX() > 0) {
                for (int k = player1.getLastX() + 1; k <= i; k++) {
                    if (Board.getBoard2D()[k][j].getKindOfElement() == 3) {
                        for (int l = 0; l < SpeedBump.getNumberOfSpeedBumps(); l++) {
                            if (Board.getSpeedBump()[l].getX() == k && Board.getSpeedBump()[l].getY() == j) {
                                player2.getSpeedBump()[player2.getFindSpeedBump()] = Board.getSpeedBump()[l].getLimit();
                                Board.getBoard2D()[k][j].setKindOfElement(0);
                                Board.getBoard2D()[k][j].setText(null);
                                Board.getBoard2D()[k][j].setIcon(null);
                                player2.setFindSpeedBump(player2.getFindSpeedBump() + 1);
                            }
                        }

                    }
                }
            } else {
                for (int k = player1.getLastX() - 1; k >= i; k--) {
                    if (Board.getBoard2D()[k][j].getKindOfElement() == 3) {
                        for (int l = 0; l < SpeedBump.getNumberOfSpeedBumps(); l++) {
                            if (Board.getSpeedBump()[l].getX() == k && Board.getSpeedBump()[l].getY() == j) {
                                player2.getSpeedBump()[player2.getFindSpeedBump()] = Board.getSpeedBump()[l].getLimit();
                                Board.getBoard2D()[k][j].setKindOfElement(0);
                                Board.getBoard2D()[k][j].setText(null);
                                Board.getBoard2D()[k][j].setIcon(null);
                                player2.setFindSpeedBump(player2.getFindSpeedBump() + 1);
                            }
                        }
                    }
                }
            }

        } else {

            if (j - player1.getLastY() > 0) {
                for (int k = player1.getLastY() + 1; k <= j; k++) {
                    if (Board.getBoard2D()[i][k].getKindOfElement() == 1) {
                        player1.setPoint(player1.getPoint() + 1);
                        Star.setNumberOfStars(Star.getNumberOfStars() - 1);
                        Board.getBoard2D()[i][k].setKindOfElement(0);
                        Board.getBoard2D()[i][k].setIcon(null);
                    }
                }
            } else {
                for (int k = player1.getLastY() - 1; k >= j; k--) {
                    if (Board.getBoard2D()[i][k].getKindOfElement() == 1) {
                        player1.setPoint(player1.getPoint() + 1);
                        Star.setNumberOfStars(Star.getNumberOfStars() - 1);
                        Board.getBoard2D()[i][k].setKindOfElement(0);
                        Board.getBoard2D()[i][k].setIcon(null);
                    }
                }
            }

            if (j - player1.getLastY() > 0) {
                for (int k = player1.getLastY() + 1; k <= j; k++) {
                    if (Board.getBoard2D()[i][k].getKindOfElement() == 3) {
                        for (int l = 0; l < SpeedBump.getNumberOfSpeedBumps(); l++) {
                            if (Board.getSpeedBump()[l].getX() == i && Board.getSpeedBump()[l].getY() == k) {
                                player2.getSpeedBump()[player2.getFindSpeedBump()] = Board.getSpeedBump()[l].getLimit();
                                Board.getBoard2D()[i][k].setKindOfElement(0);
                                Board.getBoard2D()[i][k].setText(null);
                                Board.getBoard2D()[i][k].setIcon(null);
                                player2.setFindSpeedBump(player2.getFindSpeedBump() + 1);
                            }
                        }
                    }
                }
            } else {
                for (int k = player1.getLastY() - 1; k >= j; k--) {
                    if (Board.getBoard2D()[i][k].getKindOfElement() == 3) {
                        for (int l = 0; l < SpeedBump.getNumberOfSpeedBumps(); l++) {
                            if (Board.getSpeedBump()[l].getX() == i && Board.getSpeedBump()[l].getY() == k) {
                                player2.getSpeedBump()[player2.getFindSpeedBump()] = Board.getSpeedBump()[l].getLimit();
                                Board.getBoard2D()[i][k].setKindOfElement(0);
                                Board.getBoard2D()[i][k].setText(null);
                                Board.getBoard2D()[i][k].setIcon(null);
                                player2.setFindSpeedBump(player2.getFindSpeedBump() + 1);
                            }
                        }
                    }
                }
            }

        }

        Board.getBoard2D()[player1.getLastX()][player1.getLastY()].setKindOfElement(0);
        player1.setLastX(i);
        player1.setLastY(j);
        Board.getBoard2D()[player1.getLastX()][player1.getLastY()].setKindOfElement(4);
        Board.setTurn(2);
    }

    public static void turnPlayer2(Player player1, Player player2, int i, int j) {

        if (i != player2.getLastX()) {
            if (i - player2.getLastX() > 0) {
                for (int k = player2.getLastX() + 1; k <= i; k++) {
                    if (Board.getBoard2D()[k][j].getKindOfElement() == 1) {
                        player2.setPoint(player2.getPoint() + 1);
                        Star.setNumberOfStars(Star.getNumberOfStars() - 1);
                        Board.getBoard2D()[k][j].setKindOfElement(0);
                        Board.getBoard2D()[k][j].setIcon(null);
                    }
                }
            } else {
                for (int k = player2.getLastX() - 1; k >= i; k--) {
                    if (Board.getBoard2D()[k][j].getKindOfElement() == 1) {
                        player2.setPoint(player2.getPoint() + 1);
                        Star.setNumberOfStars(Star.getNumberOfStars() - 1);
                        Board.getBoard2D()[k][j].setKindOfElement(0);
                        Board.getBoard2D()[k][j].setIcon(null);
                    }
                }
            }

            if (i - player2.getLastX() > 0) {
                for (int k = player2.getLastX() + 1; k <= i; k++) {
                    if (Board.getBoard2D()[k][j].getKindOfElement() == 3) {
                        for (int l = 0; l < SpeedBump.getNumberOfSpeedBumps(); l++) {
                            if (Board.getSpeedBump()[l].getX() == k && Board.getSpeedBump()[l].getY() == j) {
                                player1.getSpeedBump()[player1.getFindSpeedBump()] = Board.getSpeedBump()[l].getLimit();
                                Board.getBoard2D()[k][j].setKindOfElement(0);
                                Board.getBoard2D()[k][j].setText(null);
                                Board.getBoard2D()[k][j].setIcon(null);
                                player1.setFindSpeedBump(player1.getFindSpeedBump() + 1);
                            }
                        }
                    }
                }
            } else {
                for (int k = player2.getLastX() - 1; k >= i; k--) {
                    if (Board.getBoard2D()[k][j].getKindOfElement() == 3) {
                        for (int l = 0; l < SpeedBump.getNumberOfSpeedBumps(); l++) {
                            if (Board.getSpeedBump()[l].getX() == k && Board.getSpeedBump()[l].getY() == j) {
                                player1.getSpeedBump()[player1.getFindSpeedBump()] = Board.getSpeedBump()[l].getLimit();
                                Board.getBoard2D()[k][j].setKindOfElement(0);
                                Board.getBoard2D()[k][j].setText(null);
                                Board.getBoard2D()[k][j].setIcon(null);
                                player1.setFindSpeedBump(player1.getFindSpeedBump() + 1);
                            }
                        }
                    }
                }
            }

        } else {
            if (j - player2.getLastY() > 0) {
                for (int k = player2.getLastY() + 1; k <= j; k++) {
                    if (Board.getBoard2D()[i][k].getKindOfElement() == 1) {
                        player2.setPoint(player2.getPoint() + 1);
                        Star.setNumberOfStars(Star.getNumberOfStars() - 1);
                        Board.getBoard2D()[i][k].setKindOfElement(0);
                        Board.getBoard2D()[i][k].setIcon(null);
                    }
                }
            } else {
                for (int k = player2.getLastY() - 1; k >= j; k--) {
                    if (Board.getBoard2D()[i][k].getKindOfElement() == 1) {
                        player2.setPoint(player2.getPoint() + 1);
                        Star.setNumberOfStars(Star.getNumberOfStars() - 1);
                        Board.getBoard2D()[i][k].setKindOfElement(0);
                        Board.getBoard2D()[i][k].setIcon(null);
                    }
                }
            }

            if (j - player2.getLastY() > 0) {
                for (int k = player2.getLastY() + 1; k <= j; k++) {
                    if (Board.getBoard2D()[i][k].getKindOfElement() == 3) {
                        for (int l = 0; l < SpeedBump.getNumberOfSpeedBumps(); l++) {
                            if (Board.getSpeedBump()[l].getX() == i && Board.getSpeedBump()[l].getY() == k) {
                                player1.getSpeedBump()[player1.getFindSpeedBump()] = Board.getSpeedBump()[l].getLimit();
                                Board.getBoard2D()[i][k].setKindOfElement(0);
                                Board.getBoard2D()[i][k].setText(null);
                                Board.getBoard2D()[i][k].setIcon(null);
                                player1.setFindSpeedBump(player1.getFindSpeedBump() + 1);
                            }
                        }
                    }
                }
            } else {
                for (int k = player2.getLastY() - 1; k >= j; k--) {
                    if (Board.getBoard2D()[i][k].getKindOfElement() == 3) {
                        for (int l = 0; l < SpeedBump.getNumberOfSpeedBumps(); l++) {
                            if (Board.getSpeedBump()[l].getX() == i && Board.getSpeedBump()[l].getY() == k) {
                                player1.getSpeedBump()[player1.getFindSpeedBump()] = Board.getSpeedBump()[l].getLimit();
                                Board.getBoard2D()[i][k].setKindOfElement(0);
                                Board.getBoard2D()[i][k].setText(null);
                                Board.getBoard2D()[i][k].setIcon(null);
                                player1.setFindSpeedBump(player1.getFindSpeedBump() + 1);
                            }
                        }
                    }
                }
            }

        }

        Board.getBoard2D()[player2.getLastX()][player2.getLastY()].setKindOfElement(0);
        player2.setLastX(i);
        player2.setLastY(j);
        Board.getBoard2D()[player2.getLastX()][player2.getLastY()].setKindOfElement(5);
        Board.setTurn(1);
    }
}