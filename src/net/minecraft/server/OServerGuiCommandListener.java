package net.minecraft.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
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
        if (var2.length() > 0) {
            OServerGUI.a(this.b).a(var2, this.b);
        }

        this.a.setText("");
    }
}
