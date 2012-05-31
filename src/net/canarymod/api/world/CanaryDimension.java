package net.canarymod.api.world;

import java.util.ArrayList;

import net.canarymod.api.CanaryEntityTracker;
import net.canarymod.api.CanaryPlayerManager;
import net.canarymod.api.EntityTracker;
import net.canarymod.api.Particle;
import net.canarymod.api.PlayerManager;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityAnimal;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.EntityMob;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OEnumSkyBlock;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldServer;

/**
 * Wraps an OWorldServer object for world access. In OWorldServer there is no
 * _handler instance as we need the WorldContainer for instantiating a new World
 * wrapper. This is an exceptional thing
 * 
 * @author Chris
 * 
 */
public class CanaryDimension implements Dimension {

    private OWorldServer world;
    private CanaryChunkProviderServer chunkProvider;
    private CanaryWorld parent;
    private CanaryPlayerManager playerManager;
    private CanaryEntityTracker entityTracker;
    Type type;

    public CanaryDimension(OWorldServer world, CanaryWorld parent, Type type) {
        this.world = world; // We're not getting any other world types, right?
        chunkProvider = this.world.G.getCanaryChunkProvider(); // get the chunk provider wrapper
        this.parent = parent;
        world.setCanaryDimension(this);
        playerManager = world.getPlayerManager();
        this.type = type;
    }

    @Override
    public CanaryEntityTracker getEntityTracker() {
        return entityTracker;
    }

    @Override
    public void setEntityTracker(EntityTracker entityTracker) {
        this.entityTracker = (CanaryEntityTracker) entityTracker;
    }

    @Override
    public World getWorld() {
        return parent;
    }

    @Override
    public EntityItem dropItem(int x, int y, int z, int itemId, int amount, int damage) {
        double d1 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d2 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d3 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;

        OEntityItem oei = new OEntityItem(world, x + d1, y + d2, z + d3,
                new OItemStack(itemId, amount, damage));

        oei.c = 10;
        world.b(oei);
        return oei.getItem();
    }

    @Override
    public EntityItem dropItem(int x, int y, int z, Item item) {
        double d1 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d2 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d3 = world.r.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;

        OEntityItem oei = new OEntityItem(world, x + d1, y + d2, z + d3, new OItemStack(item.getId(), item.getAmount(), item.getDamage()));

        oei.c = 10;
        world.b(oei);
        return oei.getItem();
    }

    @Override
    public ArrayList<EntityAnimal> getAnimalList() {
        ArrayList<EntityAnimal> animals = new ArrayList<EntityAnimal>();
        for(Entity entity : getEntityTracker().getTrackedEntities()) {
            if(entity instanceof EntityAnimal) {
                animals.add((EntityAnimal) entity);
            }
        }
        return animals;
    }

    @Override
    public ArrayList<EntityMob> getMobList() {
        ArrayList<EntityMob> mobs = new ArrayList<EntityMob>();
        for(Entity entity : getEntityTracker().getTrackedEntities()) {
            if(entity instanceof EntityMob) {
                mobs.add((EntityMob) entity);
            }
        }
        return mobs;
    }

    @Override
    public ArrayList<Player> getPlayerList() {
        return playerManager.getManagedPlayers();
    }

    @Override
    public Block getBlockAt(int x, int y, int z) {
        short id = (short) world.a(x, y, z);
        byte data = getDataAt(x, y, z);
        return new CanaryBlock(id, data, x, y, z, this);
    }

    @Override
    public byte getDataAt(int x, int y, int z) {
        return (byte) world.c(x, y, z);
    }

    @Override
    public void setBlock(Block block) {
        setBlockAt(block.getX(), block.getY(), block.getZ(), block.getType(), block.getData());
    }
    
    @Override
    public void setBlockAt(Vector3D vector, Block block) {
        setBlockAt((int)vector.getX(), (int)vector.getY(), (int)vector.getZ(), block.getType(), block.getData());
    }

    @Override
    public void setBlockAt(int x, int y, int z, short type) {
        setBlockAt(x, y, z, type, (byte) 0);

    }

    @Override
    public void setBlockAt(int x, int y, int z, short type, byte data) {
        world.b(x, y, z, type, data);

    }

    @Override
    public void setDataAt(byte data, int x, int y, int z) {
        world.d(x, y, z, data);
    }

    @Override
    public void markBlockNeedsUpdate(int x, int y, int z) {
        world.j(x, y, z);
    }

    @Override
    public Player getClosestPlayer(double x, double y, double z, double distance) {
        OEntityPlayer user = world.a(x, y, z, distance);
        if((user != null) && user instanceof OEntityPlayerMP) {
            return (Player) user.getCanaryEntity();
        }
        return null;
    }

    @Override
    public Player getClosestPlayer(Entity entity, int distance) {
        OEntityPlayer user = world.a(((CanaryEntity)entity).getHandle(), distance);
        if((user != null) && user instanceof OEntityPlayerMP) {
            return (Player) user.getCanaryEntity();
        }
        return null;
    }

    @Override
    public ChunkProviderServer getChunkProvider() {
        return chunkProvider;
    }

    @Override
    public boolean isChunkLoaded(Block block) {
        chunkProvider.isChunkLoaded(block.getX(), block.getZ());
        return false;
    }

    @Override
    public boolean isChunkLoaded(int x, int y, int z) {
        return chunkProvider.isChunkLoaded(x, z);
    }

    @Override
    public boolean isChunkLoaded(int x, int z) { //TODO: remove?
        return chunkProvider.isChunkLoaded(x, z);
    }

    @Override
    public int getHeight() {
        return 256;
    }

    @Override
    public int getYHeighestBlockAt(int x, int z) {
        return world.e(x, z);
    }

    @Override
    public void playNoteAt(int x, int y, int z, int instrument, byte notePitch) {
        world.e(x, y, y, instrument, notePitch);
    }

    @Override
    public void setTime(long time) {
        long margin = (time - getRawTime()) % 24000;

        // Java modulus is stupid.
        if (margin < 0) {
            margin += 24000;
        }
        long newTime = getRawTime() + margin;
        
        //Set time for every dimension to make sure it's all synced
        for (Dimension dim : parent.getDimensions()) {
            ((CanaryDimension) dim).getHandle().a(newTime);
        }
    }

    @Override
    public long getRelativeTime() {
        long time = (getRawTime() % 24000);

        // Java modulus is stupid.
        if (time < 0) {
            time += 24000;
        }
        return time;
    }

    @Override
    public long getRawTime() {
        return world.o();
    }

    @Override
    public int getLightLevelAt(int x, int y, int z) {
        return world.n(x, y, z);
    }

    @Override
    public void setLightLevelOnBlockMap(int x, int y, int z, int newLevel) {
        world.a(OEnumSkyBlock.b, x, y, z, newLevel);
    }

    @Override
    public void setLightLevelOnSkyMap(int x, int y, int z, int newLevel) {
        world.a(OEnumSkyBlock.a, x, y, z, newLevel);
    }
    
    @Override
    public Chunk loadChunk(int x, int z) {
        return chunkProvider.loadChunk(x, z);
    }

    @Override
    public Chunk loadChunk(Location location) {
        return chunkProvider.loadChunk((int)location.getX(), (int)location.getZ());
    }

    @Override
    public Chunk loadChunk(Vector3D vec3d) {
        return chunkProvider.loadChunk((int)vec3d.getX(), (int)vec3d.getZ());
    }

    public OWorld getHandle() {
        return world;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return parent.getName();
    }

    @Override
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void setPlayerManager(PlayerManager pm) {
        this.playerManager = (CanaryPlayerManager) pm;
    }

    @Override
    public void spawnParticle(Particle particle) {
        world.a(particle.type.getMcName(), particle.x, particle.y, particle.z, particle.velocityX, particle.velocityY, particle.velocityZ);
    }
}
