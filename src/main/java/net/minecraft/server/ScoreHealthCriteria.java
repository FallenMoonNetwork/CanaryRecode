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

        EntityPlayer entityplayer;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); f0 += entityplayer.aM() + entityplayer.bm()) {
            entityplayer = (EntityPlayer) iterator.next();
        }

        if (list.size() > 0) {
            f0 /= (float) list.size();
        }

        return MathHelper.f(f0);
    }

    public boolean b() {
        return true;
    }
}
