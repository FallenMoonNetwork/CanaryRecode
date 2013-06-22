package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import net.canarymod.api.scoreboard.CanaryScoreHealthCriteria;

public class ScoreHealthCriteria extends ScoreDummyCriteria {

    public ScoreHealthCriteria(String s0) {
        super(s0);
        // Canarymod: set variable
        criteria = new CanaryScoreHealthCriteria(this);//
    }

    public int a(List list) {
        float f0 = 0.0F;

        int i0;
        float f1;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); f0 += (float) i0 / f1) {
            EntityPlayer entityplayer = (EntityPlayer) iterator.next();

            i0 = entityplayer.aX();
            f1 = (float) entityplayer.aW();
            if (i0 < 0) {
                i0 = 0;
            }

            if ((float) i0 > f1) {
                i0 = entityplayer.aW();
            }
        }

        if (list.size() > 0) {
            f0 /= (float) list.size();
        }

        return MathHelper.d(f0 * 19.0F) + (f0 > 0.0F ? 1 : 0);
    }

    public boolean b() {
        return true;
    }
}
