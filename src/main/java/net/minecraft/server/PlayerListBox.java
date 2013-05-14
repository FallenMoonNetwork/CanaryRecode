package net.minecraft.server;

import java.util.Vector;

import javax.swing.JList;

import net.canarymod.Canary;
import net.canarymod.api.CanaryServer;

public class PlayerListBox extends JList implements IUpdatePlayerListBox {

    private MinecraftServer a;
    private int b = 0;

    public PlayerListBox() {
        MinecraftServer minecraftserver = ((CanaryServer) Canary.getServer()).getHandle();
        this.a = minecraftserver;
        minecraftserver.a((IUpdatePlayerListBox) this);
    }

    @Override
    public void a() {
        if (this.b++ % 20 == 0) {
            Vector vector = new Vector();

            for (int i0 = 0; i0 < this.a.ad().a.size(); ++i0) {
                vector.add(((EntityPlayerMP) this.a.ad().a.get(i0)).bS);
            }

            this.setListData(vector);
        }
    }
}
