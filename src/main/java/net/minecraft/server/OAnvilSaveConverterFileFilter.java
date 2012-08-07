package net.minecraft.server;


import java.io.File;
import java.io.FilenameFilter;
import net.minecraft.server.OAnvilSaveConverter;


class OAnvilSaveConverterFileFilter implements FilenameFilter {

    // $FF: synthetic field
    final OAnvilSaveConverter a;

    OAnvilSaveConverterFileFilter(OAnvilSaveConverter var1) {
        super();
        this.a = var1;
    }

    @Override
    public boolean accept(File var1, String var2) {
        return var2.endsWith(".mcr");
    }
}
