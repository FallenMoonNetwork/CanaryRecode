package net.minecraft.server;

import java.util.ArrayList;

class ServerBlockEventList extends ArrayList {

    private ServerBlockEventList() {}

    //CanaryMod - visibility default -> public
    public ServerBlockEventList(ServerBlockEvent serverblockevent) {
        this();
    }
}
