package net.canarymod.api.world;

import java.util.ArrayList;

import net.canarymod.api.CanaryPlayerManager;
import net.canarymod.api.entity.EntityAnimal;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.EntityLiving;
import net.canarymod.api.entity.EntityMob;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;
import net.minecraft.server.OEntityItem;
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
    Type type;

    public CanaryDimension(OWorldServer world, CanaryWorld parent, Type type) {
        this.world = world; // We're not getting any other world types, right?
        chunkProvider = this.world.G.getHandler(); // get the chunk provider wrapper
        this.parent = parent;
        world.setCanaryDimension(this);
        playerManager = world.getPlayerManager();
        this.type = type;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<EntityMob> getMobList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Player> getPlayerList() {
        return playerManager.getManagedPlayers();
    }

    @Override
    public Block getBlockAt(int x, int y, int z) {
        short id = (short) world.a(x, y, z);
        byte data = getDataAt(x, y, z);
        return new CanaryBlock(id, data);
    }

    @Override
    public byte getDataAt(int x, int y, int z) {
        return (byte) world.c(x, y, z);
    }

    @Override
    public void setBlock(Block block) {
        setBlockAt(block.getX(), block.getY(), block.getZ(), block.getType(), block.getData());
        // _handle.b(block.getX(), block.getY(), block.getZ(), block.getType(),
        // block.getData());

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
        // TODO Auto-generated method stub

    }

    @Override
    public void updateBlockAt(int x, int y, int z) {
        // TODO Auto-generated method stub

    }

    @Override
    public Player getClosestPlayer(int x, int y, int z, int distance) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Player getClosestPlayer(EntityLiving entity, int distance) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChunkProviderServer getChunkProvider() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isChunkLoaded(Block block) {
        chunkProvider.chunkExists(block.getX(), block.getZ());
        return false;
    }

    @Override
    public boolean isChunkLoaded(int x, int y, int z) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getYHeighestBlockAt(int x, int z) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void playNoteAt(int x, int y, int z, int instrument, byte notePitch) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setTime(long time) {
        // TODO Auto-generated method stub

    }

    @Override
    public long getRelativeTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getRawTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getLightLevelAt(int x, int y, int z) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int setLightLevelAt(int x, int y, int z, int newLevel) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Chunk loadChunk(int x, int z) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Chunk loadChunk(Location location) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Chunk loadChunk(Vector3D vec3d) {
        // TODO Auto-generated method stub
        return null;
    }

    public OWorld getHandle() {
        return world;
    }

    @Override
    public Type getType() {
        return this.type;
    }
}
