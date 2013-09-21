package net.minecraft.server;

import javax.swing.*;
import java.util.Vector;

public class PlayerListComponent extends JList implements IUpdatePlayerListBox {

    private MinecraftServer a;
    private int b;

    public PlayerListComponent(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
        minecraftserver.a((IUpdatePlayerListBox)this);
    }

    public void a() {
        if (this.b++ % 20 == 0) {
            Vector vector = new Vector();

            for (int i0 = 0; i0 < this.a.af().a.size(); ++i0) {
                vector.add(((EntityPlayerMP)this.a.af().a.get(i0)).c_());
            }

            this.setListData(vector);
        }
    }
}
