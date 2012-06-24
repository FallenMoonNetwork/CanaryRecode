package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.server.OGuiStatsComponent;

class OGuiStatsListener implements ActionListener {

    // $FF: synthetic field
    final OGuiStatsComponent a;

    OGuiStatsListener(OGuiStatsComponent var1) {
        super();
        this.a = var1;
    }

    @Override
    public void actionPerformed(ActionEvent var1) {
        OGuiStatsComponent.a(this.a);
    }
}
