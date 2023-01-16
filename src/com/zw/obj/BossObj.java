package com.zw.obj;

import com.zw.GameWin;
import com.zw.utils.GameUtils;

import java.awt.*;

/**
 * @author Zhaojie
 */
public class BossObj extends GameObj {
    int life = 10;

    public BossObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);

        if (x > 550 || x < -50) {
            speed = -speed;
        }
        x += speed;

        for (ShellObj shellObj : GameUtils.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())) {
                shellObj.setY(-150);
                shellObj.setY(150);
                GameUtils.removeList.add(shellObj);
                life--;
            }
            if (life <= 0) {
                GameWin.state = 4;
            }

            //boss blood bg
            gImage.setColor(Color.white);
            gImage.fillRect(20, 40, 100, 10);
            //boss blood left
            gImage.setColor(Color.RED);
            gImage.fillRect(20, 40, life * 100 / 10, 10);


        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
