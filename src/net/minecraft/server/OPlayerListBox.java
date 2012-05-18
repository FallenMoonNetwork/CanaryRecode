package net.minecraft.server;

import java.util.Vector;
import javax.swing.JList;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OIUpdatePlayerListBox;
import net.minecraft.server.OMinecraftServer;

public class OPlayerListBox extends JList implements OIUpdatePlayerListBox {

    private OMinecraftServer a;
    private int b = 0;

    public OPlayerListBox(OMinecraftServer var1) {
        super();
        this.a = var1;
        var1.a(this);
    }

    public void a() {
        if (this.b++ % 20 == 0) {
            Vector var1 = new Vector();

            for (int var2 = 0; var2 < this.a.h.b.size(); ++var2) {
                var1.add(((OEntityPlayerMP) this.a.h.b.get(var2)).v);
            }

            this.setListData(var1);
        }

    }
}
