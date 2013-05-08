package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import net.canarymod.Canary;

class ServerGuiCommandListener implements ActionListener {

    final JTextField a;

    final ServerGUI b;

    ServerGuiCommandListener(ServerGUI servergui, JTextField jtextfield) {
        this.b = servergui;
        this.a = jtextfield;
    }

    @Override
    public void actionPerformed(ActionEvent actionevent) {
        String s0 = this.a.getText().trim();

        if (s0.length() > 0) {
            // ServerGUI.a(this.b).a(s0, (ICommandSender) MinecraftServer.D());
            Canary.getServer().consoleCommand(s0);
        }

        this.a.setText("");
    }
}
