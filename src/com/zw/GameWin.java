package com.zw;

import com.zw.obj.BgObj;
import com.zw.obj.EnemyObj;
import com.zw.obj.PlaneObj;
import com.zw.obj.ShellObj;
import com.zw.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Zhaojie
 */
public class GameWin extends JFrame {

    //status: 0 default, 1 in game, 2 pause, 3 failure, 4 successful
    static int state = 0;
    //frame size
    int width = 600;
    int height = 600;

    //count times
    int count = 1;

    //double thread
    Image offScreenImage = null;

    //get bg obj
    BgObj bgObj = new BgObj(GameUtils.bgImg, 0, -2000, 2);
    //get plane obj
    PlaneObj planeObj = new PlaneObj(GameUtils.planeImg, 290, 550, 20, 30, 0, this);

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
            gImage.setColor(Color.yellow);
            gImage.setFont(new Font("Default", Font.BOLD, 40));
            gImage.drawString("Click to Start", 180, 300);
        }

        if (1 == state) {
            for (int i = 0; i < GameUtils.gameObjList.size(); i++) {
                GameUtils.gameObjList.get(i).paintSelf(gImage);
            }
        }

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
        }
    }
}
