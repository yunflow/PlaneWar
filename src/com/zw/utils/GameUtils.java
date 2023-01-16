package com.zw.utils;

import com.zw.obj.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhaojie
 */
public class GameUtils {

    //background picture
    public static Image bgImg = Toolkit.getDefaultToolkit().getImage("imgs/space.jpg");
    //boss picture
    public static Image bossImg = Toolkit.getDefaultToolkit().getImage("imgs/boss.png");
    //explosion picture
    public static Image explodeImg = Toolkit.getDefaultToolkit().getImage("imgs/explode/e6.gif");
    //plane
    public static Image planeImg = Toolkit.getDefaultToolkit().getImage("imgs/plane.png");
    //my shell
    public static Image shellImg = Toolkit.getDefaultToolkit().getImage("imgs/bulletYellow.png");
    //enemy bullet
    public static Image bulletImg = Toolkit.getDefaultToolkit().getImage("imgs/bulletGreen.png");
    //enemy plane
    public static Image enemyImg = Toolkit.getDefaultToolkit().getImage("imgs/enemy.png");

    //all obj set
    public static List<GameObj> gameObjList = new ArrayList<>();
    //all deleted obj set
    public static List<GameObj> removeList = new ArrayList<>();
    //my shell set
    public static List<ShellObj> shellObjList = new ArrayList<>();
    //enemy bullet set
    public static List<BulletObj> bulletObjList = new ArrayList<>();
    //enemy plane set
    public static List<EnemyObj> enemyObjList = new ArrayList<>();
    //explode set
    public static List<ExplodeObj> explodeObjList = new ArrayList<>();

    //score method
    public static void drawWord(Graphics gImage, String str, Color color, int size, int x, int y) {
        gImage.setColor(color);
        gImage.setFont(new Font("Default", Font.BOLD, size));
        gImage.drawString(str, x, y);
    }
}
