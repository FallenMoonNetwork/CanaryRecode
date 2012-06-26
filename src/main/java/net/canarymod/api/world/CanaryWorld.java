package net.canarymod.api.world;

import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.CanaryServer;
import net.canarymod.api.CanaryEntityTracker;
import net.canarymod.api.CanaryPlayerManager;
import net.canarymod.api.EntityTracker;
import net.canarymod.api.entity.Player;
import net.minecraft.server.OEntityTracker;
import net.minecraft.server.OPlayerManager;
import net.minecraft.server.OWorldServer;

public class CanaryWorld implements World {

    private CanaryDimension normal, nether, end;
    private OWorldServer[] oDimensions;
    public long[][] nanoTicks;
    private boolean enabled;

    /**
     * The world name
     */
    private String name;

    public CanaryWorld(String name, OWorldServer[] dimensions) {
        this.name = name;
        
        //Create new dimensions for this world
        normal = new CanaryDimension(dimensions[0], this, Dimension.Type.NORMAL);
        nether = new CanaryDimension(dimensions[1], this, Dimension.Type.NETHER);
        end = new CanaryDimension(dimensions[2], this, Dimension.Type.END);
        
      //manually set player managers
        int viewDistance = 10; //TODO: Add config for view distance!
        normal.setPlayerManager(new CanaryPlayerManager(new OPlayerManager(((CanaryServer)Canary.getServer()).getHandle(), 0, viewDistance, normal), normal));
        nether.setPlayerManager(new CanaryPlayerManager(new OPlayerManager(((CanaryServer)Canary.getServer()).getHandle(), -1, viewDistance, nether), nether));
        end.setPlayerManager(new CanaryPlayerManager(new OPlayerManager(((CanaryServer)Canary.getServer()).getHandle(), 1, viewDistance, end), end));
        
        //Manually set new entity trackers
        normal.setEntityTracker(new CanaryEntityTracker(new OEntityTracker( ((CanaryServer)Canary.getServer()).getHandle(), 0, normal), normal) );
        nether.setEntityTracker(new CanaryEntityTracker(new OEntityTracker( ((CanaryServer)Canary.getServer()).getHandle(), -1, nether), nether) );
        end.setEntityTracker(new CanaryEntityTracker(new OEntityTracker( ((CanaryServer)Canary.getServer()).getHandle(), 1, end), end) );

        //Init nanotick sizes
        nanoTicks = new long[dimensions.length][100];
        
        //set worlds. That needs to be done here because the moment of world creation,
        //we have no recollection of the corresponding parent 
        dimensions[0].setCanaryDimension(normal);
        dimensions[1].setCanaryDimension(nether);
        dimensions[2].setCanaryDimension(end);
        oDimensions = dimensions;
    }

    public OWorldServer[] getDimensionArray() {
        return this.oDimensions;
    }

    /**
     * Return the name of this World (WorldContainer)
     * 
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Get the NORMAL dimension for this world container
     * 
     * @return
     */
    @Override
    public CanaryDimension getNormal() {
        return normal;
    }

    /**
     * Get the NETHER dimension for this world container
     * 
     * @return
     */
    @Override
    public CanaryDimension getNether() {
        return nether;
    }

    /**
     * Get the END dimension for this world container
     * 
     * @return
     */
    @Override
    public CanaryDimension getEnd() {
        return end;
    }

    @Override
    public Dimension getDimension(Dimension.Type dimension) {
        switch (dimension) {
        case NORMAL:
            return normal;
        case NETHER:
            return nether;
        case END:
            return end;
        default:
            return normal;
        }
    }

    @Override
    public void setNanoTick(Dimension.Type dimension, int tickIndex, long tick) {
        switch (dimension) {
            case NORMAL:
                nanoTicks[0][tickIndex] = tick;
                break;
            case NETHER:
                nanoTicks[1][tickIndex] = tick;
                break;
            case END:
                nanoTicks[2][tickIndex] = tick;
                break;
        }
    }
    
    @Override
    public long getNanoTick(Dimension.Type dimension, int tickIndex) {
        switch(dimension) {
            case NORMAL:
                return nanoTicks[0][tickIndex];
            case NETHER:
                return nanoTicks[1][tickIndex];
            case END:
                return nanoTicks[2][tickIndex];
            default:
                return 0L;
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled; // TODO: implement
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean canEnterWorld(Player player) {
        return player.hasPermission("canary.world.traveling." + name + ".enter")
                && isEnabled();
    }

    @Override
    public boolean canLeaveWorld(Player player) {
        return player.hasPermission("canary.world.traveling." + name + ".leave");
    }

    @Override
    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> players = new ArrayList<Player>(Canary.getServer().getMaxPlayers());
        players.addAll(normal.getPlayerList());
        players.addAll(nether.getPlayerList());
        players.addAll(end.getPlayerList());
        return players;
    }

    @Override
    public Dimension[] getDimensions() {
        return new Dimension[] {normal, nether, end};
    }

    @Override
    public EntityTracker[] getAllEntityTrackers() {
        EntityTracker[] toReturn = new EntityTracker[3];
        toReturn[0] = normal.getEntityTracker();
        toReturn[1] = nether.getEntityTracker();
        toReturn[2] = end.getEntityTracker();
        return toReturn;
    }
}
