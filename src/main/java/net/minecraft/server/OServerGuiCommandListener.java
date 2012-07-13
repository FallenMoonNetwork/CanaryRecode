package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import net.canarymod.Canary;
import net.minecraft.server.OServerGUI;

class OServerGuiCommandListener implements ActionListener {

    // $FF: synthetic field
    final JTextField a;
    // $FF: synthetic field
    final OServerGUI b;

    OServerGuiCommandListener(OServerGUI var1, JTextField var2) {
        super();
        this.b = var1;
        this.a = var2;
    }

    @Override
    public void actionPerformed(ActionEvent var1) {
        String var2 = this.a.getText().trim();
        //CanaryMod: Parse console commands from GUI
        if (var2.length() > 0 && Canary.instance() != null) {
            // CanaryMod start - onConsoleCommand
            if (!Canary.getServer().consoleCommand(var2)) {
                OServerGUI.a(this.b).a(var2, this.b);
            }
            // CanaryMod end - onConsoleCommand
        }
        //CanaryMod end

        this.a.setText("");
    }
}
