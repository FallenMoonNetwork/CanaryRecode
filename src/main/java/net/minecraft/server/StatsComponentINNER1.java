package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StatsComponentINNER1 implements ActionListener {

    final StatsComponent a;

    StatsComponentINNER1(StatsComponent statscomponent) {
        this.a = statscomponent;
    }

    public void actionPerformed(ActionEvent actionevent) {
        StatsComponent.a(this.a);
    }
}
