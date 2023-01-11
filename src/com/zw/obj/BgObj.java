package com.zw.obj;

import java.awt.*;

/**
 * @author Zhaojie
 */
public class BgObj extends GameObj {

    public BgObj() {
        super();
    }

    public BgObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public Image getImg() {
        return super.getImg();
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;

        if(y >= 0) {
            y = -2000;
        }
    }
}
