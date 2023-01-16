package com.zw.obj;

import com.zw.GameWin;
import com.zw.utils.GameUtils;

import java.awt.*;

/**
 * @author Zhaojie
 */
public class EnemyObj extends GameObj {
    public EnemyObj() {
        super();
    }

    public EnemyObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;

        //check my plane has crash with enemy
        if (this.getRec().intersects(this.frame.planeObj.getRec())) {
            GameWin.state = 3;
        }

        if (y > 600) {
            this.x = -200;
            this.y = 200;
            GameUtils.removeList.add(this);
        }

        //check enemy is hit
        for (ShellObj shellObj : GameUtils.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())) {
                ExplodeObj explodeObj = new ExplodeObj(x, y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);

                shellObj.setX(-100);
                shellObj.setY(100);
                this.x = -200;
                this.y = 200;
                GameUtils.removeList.add(shellObj);
                GameUtils.removeList.add(this);
                GameWin.score++;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
