package com.zw.obj;

import com.zw.GameWin;
import com.zw.utils.GameUtils;

import java.awt.*;

/**
 * @author Zhaojie
 */
public class BulletObj extends GameObj {
    public BulletObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;

        //check crash
        if (this.getRec().intersects(this.frame.planeObj.getRec())) {
            GameWin.state = 3;
        }

        if (y > 600) {
            this.x = -300;
            this.y = 300;
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
