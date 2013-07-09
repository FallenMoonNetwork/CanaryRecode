package net.canarymod.api.scoreboard;

import java.util.ArrayList;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.minecraft.server.ScorePlayerTeam;
import net.minecraft.server.ServerScoreboard;

/**
 * @author Somners
 */
public class CanaryTeam implements Team {

    private ScorePlayerTeam handle;

    public CanaryTeam(ScorePlayerTeam handle) {
        this.handle = handle;
    }

    @Override
    public String getProtocolName() {
        return handle.b();
    }

    @Override
    public String getDisplayName() {
        return handle.c();
    }

    @Override
    public void setDisplayName(String name) {
        handle.a(name);
    }

    @Override
    public String getPrefix() {
        return handle.e();
    }

    @Override
    public void setPrefix(String prefix) {
        handle.b(prefix);
    }

    @Override
    public String getSuffix() {
        return handle.f();
    }

    @Override
    public void setSuffix(String suffix) {
        handle.c(suffix);
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> toRet = new ArrayList<Player>();
        for (Object o : handle.d()) {
            Player player = Canary.getServer().getPlayer((String) o);
            if (player != null) {
                toRet.add(player);
            }
        }
        return toRet;
    }

    @Override
    public List<String> getPlayerNames() {
        List<String> toRet = new ArrayList<String>();
        for (Object o : handle.d()) {
            toRet.add(((String) o));
        }
        return toRet;
    }

    @Override
    public void addPlayer(Player player) {
        handle.a.a(player.getName(), handle);
    }

    @Override
    public boolean hasPlayer(Player player) {
        return this.getPlayers().contains(player);
    }

    @Override
    public void removePlayer(Player player) {
        handle.a.b(player.getName(), handle);
    }

    @Override
    public Scoreboard getScoreboard() {
        return ((ServerScoreboard) handle.a).getCanaryScoreboard();
    }

    @Override
    public boolean getAllowFriendlyFire() {
        return handle.g();
    }

    @Override
    public void setAllowFriendlyFire(boolean bool) {
        handle.a(bool);
    }

    @Override
    public boolean getSeeFriendlyInvisibles() {
        return handle.h();
    }

    @Override
    public void setSeeFriendlyInvisibles(boolean bool) {
        handle.b(bool);
    }

    public ScorePlayerTeam getHandle() {
        return this.handle;
    }
}
