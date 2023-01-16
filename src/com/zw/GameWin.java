package com.zw;

import com.zw.obj.*;
import com.zw.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Zhaojie
 */
public class GameWin extends JFrame {

    //status: 0 default, 1 in game, 2 pause, 3 failure, 4 successful
    public static int state = 0;
    //frame size
    int width = 600;
    int height = 600;
    //count times
    int count = 1;
    //double thread
    Image offScreenImage = null;
    //game score
    public static int score = 0;

    //get bg obj
    BgObj bgObj = new BgObj(GameUtils.bgImg, 0, -2000, 2);
    //get plane obj
    public PlaneObj planeObj = new PlaneObj(GameUtils.planeImg, 290, 550, 20, 30, 0, this);
    //plane times
    int enemyCount = 0;
    //get boss obj
    public BossObj bossObj = null;

    /**
     * frame launch
     */
    public void launch() {
        //set frame visible
        this.setVisible(true);

        //set size
        this.setSize(width, height);

        //set position
        this.setLocationRelativeTo(null);

        //set title
        this.setTitle("Plane War");

        GameUtils.gameObjList.add(bgObj);
        GameUtils.gameObjList.add(planeObj);

        //add mouse listener
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1 && state == 0) {
                    state = 1;
                    repaint();
                }
            }
        });

        //add pause method
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 32) {
                    switch (state) {
                        case 1:
                            state = 2;
                            break;
                        case 2:
                            state = 1;
                            break;
                        default:
                    }
                }
            }
        });

        //bg move
        while (true) {
            if (state == 1) {
                creatObject();
                repaint();
            }

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(width, height);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0, 0, width, height);

        if (0 == state) {
            repaint();
            gImage.drawImage(GameUtils.bgImg, 0, 0, null);
            gImage.drawImage(GameUtils.bossImg, 250, 120, null);
            gImage.drawImage(GameUtils.explodeImg, 270, 350, null);

            GameUtils.drawWord(gImage, "Click to Start", Color.YELLOW, 40, 166, 300);
        }

        if (1 == state) {
            GameUtils.gameObjList.addAll(GameUtils.explodeObjList);

            for (int i = 0; i < GameUtils.gameObjList.size(); i++) {
                GameUtils.gameObjList.get(i).paintSelf(gImage);
            }

            GameUtils.gameObjList.removeAll(GameUtils.removeList);
        }

        if (3 == state) {
            gImage.drawImage(GameUtils.explodeImg, planeObj.getX() - 25, planeObj.getY() - 50, null);

            GameUtils.drawWord(gImage, "GAME OVER", Color.RED, 40, 180, 300);
        }

        if (4 == state) {
            gImage.drawImage(GameUtils.explodeImg, bossObj.getX() + 20, bossObj.getY() + 20, null);

            GameUtils.drawWord(gImage, "Successful!", Color.GREEN, 40, 180, 300);
        }

        GameUtils.drawWord(gImage, "Score: " + score, Color.GREEN, 40, 30, 100);
        g.drawImage(offScreenImage, 0, 0, null);
        count++;
    }

    void creatObject() {
        //get my shell obj
        if (count % 15 == 0) {
            GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImg, planeObj.getX() + 3, planeObj.getY() - 16, 14, 29, 5, this));
            GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
        }

        //get enemy plane obj
        if (count % 15 == 0) {
            GameUtils.enemyObjList.add(new EnemyObj(GameUtils.enemyImg, (int) (Math.random() * 12) * 50, 0, 49, 36, 5, this));
            GameUtils.gameObjList.add(GameUtils.enemyObjList.get(GameUtils.enemyObjList.size() - 1));
            enemyCount++;
        }

        //get enemy bullet obj
        if (count % 15 == 0 && bossObj != null) {
            GameUtils.bulletObjList.add(new BulletObj(GameUtils.bulletImg, bossObj.getX() + 76, bossObj.getY() + 80, 15, 25, 5, this));
            GameUtils.gameObjList.add(GameUtils.bulletObjList.get(GameUtils.bulletObjList.size() - 1));
        }

        //create boss
        if (enemyCount > 30 && bossObj == null) {
            bossObj = new BossObj(GameUtils.bossImg, 250, 35, 155, 100, 5, this);
            GameUtils.gameObjList.add(bossObj);
        }
    }
}
