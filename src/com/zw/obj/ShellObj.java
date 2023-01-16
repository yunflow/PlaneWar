package com.zw.obj;

import com.zw.GameWin;
import com.zw.utils.GameUtils;

import java.awt.*;

/**
 * @author Zhaojie
 */
public class ShellObj extends GameObj {
    public ShellObj() {
        super();
    }

    public ShellObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y -= speed;

        if (y < 0) {
            this.x = -100;
            this.y = 100;
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
