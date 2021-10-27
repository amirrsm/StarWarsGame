package controller;

import java.awt.*;

public class GameConfigController {

    public static boolean nameController(String name) {
        String pattern = "[a-zA-Z0-9_*]{4,12}";
        return name.matches(pattern);
    }

    //players should enter two different name.
    public static boolean nameDiffController(String name1, String name2) {
        return name1.equals(name2);
    }

    //players should choose teo different color.
    public static boolean colorController(Color color1, Color color2) {
        return color1.equals(color2);
    }
}