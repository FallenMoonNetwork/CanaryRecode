package net.minecraft.server;


import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import net.minecraft.server.OServerGUI;


class OServerGuiFocusAdapter extends FocusAdapter {

    // $FF: synthetic field
    final OServerGUI a;

    OServerGuiFocusAdapter(OServerGUI var1) {
        super();
        this.a = var1;
    }

    @Override
    public void focusGained(FocusEvent var1) {}
}
