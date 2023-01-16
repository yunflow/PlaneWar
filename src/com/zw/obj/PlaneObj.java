package com.zw.obj;

import com.zw.GameWin;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Zhaojie
 */
public class PlaneObj extends GameObj {
    public PlaneObj() {
        super();
    }

    public PlaneObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);

        //mouse listener
        this.frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                PlaneObj.super.x = e.getX() - 11;
                PlaneObj.super.y = e.getY() - 16;
            }
        });

        //check crash
        if (this.frame.bossObj != null && this.getRec().intersects(this.frame.bossObj.getRec())) {
            GameWin.state = 3;
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
