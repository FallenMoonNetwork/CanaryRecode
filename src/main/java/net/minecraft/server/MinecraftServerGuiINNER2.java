package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import net.canarymod.Canary;

class MinecraftServerGuiINNER2 implements ActionListener {

    final JTextField a;

    final MinecraftServerGui b;

    MinecraftServerGuiINNER2(MinecraftServerGui minecraftservergui, JTextField jtextfield) {
        this.b = minecraftservergui;
        this.a = jtextfield;
    }

    public void actionPerformed(ActionEvent actionevent) {
        String s0 = this.a.getText().trim();

        if (s0.length() > 0) {
            // CanaryMod: Redirect commands
            // MinecraftServerGui.a(this.b).a(s0, (ICommandSender) MinecraftServer.F());
            Canary.getServer().consoleCommand(s0);
        }

        this.a.setText("");
    }
}
