package net.canarymod.api.world;


import java.util.ArrayList;
import net.canarymod.Canary;
import net.canarymod.api.CanaryEntityTracker;
import net.canarymod.api.CanaryPlayerManager;
import net.canarymod.api.EntityTracker;
import net.canarymod.api.PlayerManager;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.entity.living.monster.EntityMob;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.api.world.blocks.Chest;
import net.canarymod.api.world.blocks.ComplexBlock;
import net.canarymod.api.world.effects.AuxiliarySoundEffect;
import net.canarymod.api.world.effects.Particle;
import net.canarymod.api.world.effects.SoundEffect;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.minecraft.server.EntityLightningBolt;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.EnumSkyBlock;
import net.minecraft.server.ItemStack;
import net.minecraft.server.TileEntity;
import net.minecraft.server.TileEntityBeacon;
import net.minecraft.server.TileEntityBrewingStand;
import net.minecraft.server.TileEntityChest;
import net.minecraft.server.TileEntityCommandBlock;
import net.minecraft.server.TileEntityComparator;
import net.minecraft.server.TileEntityDaylightDetector;
import net.minecraft.server.TileEntityDispenser;
import net.minecraft.server.TileEntityDropper;
import net.minecraft.server.TileEntityFurnace;
import net.minecraft.server.TileEntityHopper;
import net.minecraft.server.TileEntityMobSpawner;
import net.minecraft.server.TileEntityNote;
import net.minecraft.server.TileEntityRecordPlayer;
import net.minecraft.server.TileEntitySign;
import net.minecraft.server.TileEntitySkull;
import net.minecraft.server.WorldInfo;
import net.minecraft.server.WorldServer;


public class CanaryWorld implements World {
    private WorldServer world;
    private DimensionType type;
    private CanaryChunkProviderServer chunkProvider;
    private CanaryEntityTracker entityTracker;
    public long[] nanoTicks;
    private boolean enabled;

    private CanaryPlayerManager playerManager;

    /**
     * The world name
     */
    private String name;

    public CanaryWorld(String name, WorldServer dimension, DimensionType type) {
        this.name = name;
        world = dimension;

        playerManager = dimension.r().getPlayerManager();
        entityTracker = dimension.getEntityTracker();
        this.type = type;
        // Init nanotick size
        nanoTicks = new long[100];

        chunkProvider = new CanaryChunkProviderServer(dimension.b);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getFqName() {
        return this.name + "_" + this.type.getName();
    }

    @Override
    public void setNanoTick(int tickIndex, long tick) {
        nanoTicks[tickIndex] = tick;
    }

    @Override
    public long getNanoTick(int tickIndex) {
        return nanoTicks[tickIndex];
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
    public boolean canEnterWorld(Player player) { //TODO: this is not used
        return player.hasPermission("canary.world.traveling." + name + ".enter") && isEnabled();
    }

    @Override
    public boolean canLeaveWorld(Player player) { //TODO: This is not used
        return player.hasPermission("canary.world.traveling." + name + ".leave");
    }

    @Override
    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>(Canary.getServer().getMaxPlayers());

        players.addAll(getPlayerList());
        return players;
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
    public EntityItem dropItem(Position position, Item item) {
        return dropItem((int) position.getX(), (int) position.getY(), (int) position.getZ(), item);
    }

    @Override
    public EntityItem dropItem(int x, int y, int z, int itemId, int amount, int damage) {
        double d1 = world.s.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d2 = world.s.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d3 = world.s.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;

        net.minecraft.server.EntityItem oei = new net.minecraft.server.EntityItem(world, x + d1, y + d2, z + d3, new ItemStack(itemId, amount, damage));

        oei.c = 10;
        world.d(oei);
        return oei.getEntityItem();
    }

    @Override
    public EntityItem dropItem(int x, int y, int z, Item item) {
        double d1 = world.s.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d2 = world.s.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;
        double d3 = world.s.nextFloat() * 0.7F + (1.0F - 0.7F) * 0.5D;

        net.minecraft.server.EntityItem oei = new net.minecraft.server.EntityItem(world, x + d1, y + d2, z + d3, new ItemStack(item.getId(), item.getAmount(), item.getDamage()));

        oei.c = 10;
        world.d(oei);
        return oei.getEntityItem();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public ArrayList<EntityAnimal> getAnimalList() {
        ArrayList<EntityAnimal> animals = new ArrayList<EntityAnimal>();

        for (Object o : (ArrayList) ((ArrayList) world.e).clone()) {
            if (((net.minecraft.server.Entity) o).getCanaryEntity() instanceof EntityAnimal) {
                animals.add((EntityAnimal) ((net.minecraft.server.Entity) o).getCanaryEntity());
            }
        }
        return animals;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public ArrayList<EntityMob> getMobList() {
        ArrayList<EntityMob> mobs = new ArrayList<EntityMob>();

        for (Object o : (ArrayList) ((ArrayList) world.e).clone()) {
            if (((net.minecraft.server.Entity) o).getCanaryEntity() instanceof EntityMob) {
                mobs.add((EntityMob) ((net.minecraft.server.Entity) o).getCanaryEntity());
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
        short data = getDataAt(x, y, z);

        return new CanaryBlock(id, data, x, y, z, this);
    }

    @Override
    public short getDataAt(Position position) {
        return getDataAt((int) position.getX(), (int) position.getY(), (int) position.getZ());
    }

    @Override
    public Block getBlockAt(Position position) {
        return getBlockAt((int) position.getX(), (int) position.getY(), (int) position.getZ());
    }

    @Override
    public short getDataAt(int x, int y, int z) {
        return (short) world.h(x, y, z);
    }

    @Override
    public void setBlock(Block block) {
        setBlockAt(block.getX(), block.getY(), block.getZ(), block.getTypeId(), block.getData());
    }

    @Override
    public void setBlockAt(Position vector, Block block) {
        setBlockAt((int) vector.getX(), (int) vector.getY(), (int) vector.getZ(), block.getTypeId(), block.getData());
    }

    @Override
    public void setBlockAt(int x, int y, int z, short type) {
        setBlockAt(x, y, z, type, (byte) 0);

    }

    @Override
    public void setBlockAt(Position position, short type) {
        setBlockAt((int) position.getX(), (int) position.getY(), (int) position.getZ(), (short) type);
    }

    @Override
    public void setBlockAt(Position position, short type, short data) {
        setBlockAt((int) position.getX(), (int) position.getY(), (int) position.getZ(), type, data);
    }

    @Override
    public void setBlockAt(int x, int y, int z, short type, short data) {
        world.c(x, y, z, type);
        setDataAt(x, y, z, data);
    }

    @Override
    public void setDataAt(int x, int y, int z, short data) {
        world.b(x, y, z, data, 0x2);
    }

    @Override
    public void markBlockNeedsUpdate(int x, int y, int z) {
        world.j(x, y, z);
    }

    @Override
    public Player getClosestPlayer(double x, double y, double z, double distance) {
        EntityPlayer user = world.a(x, y, z, distance);

        if ((user != null) && user instanceof EntityPlayerMP) {
            return (Player) user.getCanaryEntity();
        }
        return null;
    }

    @Override
    public Player getClosestPlayer(Entity entity, int distance) {
        EntityPlayer user = world.a(((CanaryEntity) entity).getHandle(), distance);

        if ((user != null) && user.getCanaryEntity() instanceof Player) {
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
        chunkProvider.isChunkLoaded(block.getX() >> 4, block.getZ() >> 4);
        return false;
    }

    @Override
    public boolean isChunkLoaded(int x, int y, int z) {
        return chunkProvider.isChunkLoaded(x >> 4, z >> 4);
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return chunkProvider.isChunkLoaded(x, z);
    }

    @Override
    public int getHeight() {
        return 256;
    }

    @Override
    public int getHighestBlockAt(int x, int z) {
        for (int i = 0 ; i < this.getHeight() ; i++) {
            if (world.l(x, i, z)) {
                return i;
            }
        }
        return 256;
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

        getHandle().b(newTime);
    }

    @Override
    public long getRelativeTime() {
        return world.H();
    }

    @Override
    public long getRawTime() {
        return world.G();
    }

    @Override
    public int getLightLevelAt(int x, int y, int z) {
        return world.b(EnumSkyBlock.a, x, y, z);
    }

    @Override
    public void setLightLevelOnBlockMap(int x, int y, int z, int newLevel) {
        world.b(EnumSkyBlock.b, x, y, z, newLevel);
    }

    @Override
    public void setLightLevelOnSkyMap(int x, int y, int z, int newLevel) {
        world.b(EnumSkyBlock.a, x, y, z, newLevel);
    }

    @Override
    public Chunk loadChunk(int x, int z) {
        return chunkProvider.loadChunk(x, z);
    }

    @Override
    public Chunk loadChunk(Location location) {
        return chunkProvider.loadChunk((int) location.getX(), (int) location.getZ());
    }

    @Override
    public Chunk loadChunk(Position vec3d) {
        return chunkProvider.loadChunk((int) vec3d.getX(), (int) vec3d.getZ());
    }

    @Override
    public Chunk getChunk(int x, int z) {
        if (isChunkLoaded(x, z)) {
            return chunkProvider.provideChunk(x, z);
        } else {
            return null;
        }
    }

    public net.minecraft.server.World getHandle() {
        return world;
    }

    @Override
    public DimensionType getType() {
        return this.type;
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

    @Override
    public void playSound(SoundEffect effect) {
        world.a(effect.x, effect.y, effect.z, effect.type.getMcName(), effect.volume, effect.pitch);
    }

    @Override
    public void playAUXEffect(AuxiliarySoundEffect effect) {
        world.e(effect.type.getDigits(), effect.x, effect.y, effect.z, effect.extra);
    }

    @Override
    public void playAUXEffectAt(Player player, AuxiliarySoundEffect effect) {
        world.a(player != null ? ((CanaryPlayer) player).getHandle() : null, effect.type.getDigits(), effect.x, effect.y, effect.z, effect.extra);
    }

    @Override
    public boolean isBlockPowered(Block block) {
        return isBlockPowered(block.getX(), block.getY(), block.getZ());
    }

    @Override
    public boolean isBlockPowered(Position position) {
        return isBlockPowered((int) position.getX(), (int) position.getY(), (int) position.getZ());
    }

    @Override
    public boolean isBlockPowered(int x, int y, int z) {
        return world.w(x, y, z);
    }

    @Override
    public boolean isBlockIndirectlyPowered(Block block) {
        return isBlockIndirectlyPowered(block.getX(), block.getY(), block.getZ());
    }

    @Override
    public boolean isBlockIndirectlyPowered(Position position) {
        return isBlockIndirectlyPowered((int) position.getX(), (int) position.getY(), (int) position.getZ());
    }

    @Override
    public boolean isBlockIndirectlyPowered(int x, int y, int z) {
        return world.x(x, y, z);
    }

    @Override
    public void setThundering(boolean thundering) {
        // TODO: add thunder_change hook here?
        world.x.a(thundering);

        // Thanks to Bukkit for figuring out these numbers
        if (thundering) {
            setThunderTime(world.s.nextInt(12000) + 3600);
        } else {
            setThunderTime(world.s.nextInt(168000) + 12000);
        }

    }

    @Override
    public void setThunderTime(int ticks) {
        world.x.f(ticks);
    }

    @Override
    public void setRaining(boolean downfall) {
        // TODO: Add weather change hook
        world.x.b(downfall);

        // Thanks to Bukkit for figuring out these numbers
        if (downfall) {
            setRainTime(world.s.nextInt(12000) + 3600);
        } else {
            setRainTime(world.s.nextInt(168000) + 12000);
        }
    }

    @Override
    public void setRainTime(int ticks) {
        world.x.g(ticks);
    }

    @Override
    public boolean isRaining() {
        return world.x.p();
    }

    @Override
    public boolean isThundering() {
        return world.x.n();
    }

    @Override
    public int getRainTicks() {
        return world.x.q();
    }

    @Override
    public int getThunderTicks() {
        return world.x.o();
    }

    @Override
    public void makeLightningBolt(int x, int y, int z) {
        world.c(new EntityLightningBolt(world, x, y, z));
    }

    @Override
    public void makeLightningBolt(Position position) {
        world.c(new EntityLightningBolt(world, (int) position.getX(), (int) position.getY(), (int) position.getZ()));
    }

    @Override
    public void makeExplosion(Entity exploder, double x, double y, double z, float power, boolean damageBlocks) {
        world.a(((CanaryEntity) exploder).getHandle(), x, y, z, power, damageBlocks);
    }

    @Override
    public void makeExplosion(Entity exploder, Position position, float power, boolean damageBlocks) {
        world.a(((CanaryEntity) exploder).getHandle(), position.getX(), position.getY(), position.getZ(), power, damageBlocks);
    }

    @Override
    public long getWorldSeed() {
        return world.F();
    }

    @Override
    public void removePlayerFromWorld(Player player) {
        world.f((net.minecraft.server.Entity) ((CanaryPlayer) player).getHandle());

    }

    @Override
    public void addPlayerToWorld(Player player) {
        world.d((net.minecraft.server.Entity) ((CanaryPlayer) player).getHandle());
    }

    @Override
    public Location getSpawnLocation() {
        // More structure ftw
        WorldInfo info = world.x;
        Location spawn = new Location(0, 0, 0);

        spawn.setX(info.c() + 0.5D);
        spawn.setY(info.d());
        spawn.setZ(info.e() + 0.5D);
        spawn.setRotation(0.0F);
        spawn.setPitch(0.0F);
        spawn.setType(type);
        spawn.setWorldName(world.getCanaryWorld().getName());
        return spawn;
    }

    @Override
    public void setSpawnLocation(Location p) {
        world.x.a((int) p.getX(), (int) p.getY(), (int) p.getZ());
    }

    @Override
    public ComplexBlock getComplexBlock(Block block) {
        return getComplexBlockAt(block.getX(), block.getY(), block.getZ());
    }

    @Override
    public ComplexBlock getComplexBlockAt(int x, int y, int z) {
        ComplexBlock result = getOnlyComplexBlockAt(x, y, z);

        if (result != null) {
            if (result instanceof Chest) {
                Chest chest = (Chest) result;

                if (chest.hasAttachedChest()) {
                    return chest.getDoubleChest();
                }
            }
        }

        return result;
    }

    @Override
    public ComplexBlock getOnlyComplexBlock(Block block) {
        return getOnlyComplexBlockAt(block.getX(), block.getY(), block.getZ());
    }

    @Override
    public ComplexBlock getOnlyComplexBlockAt(int x, int y, int z) {
        TileEntity tileentity = world.r(x, y, z);

        if (tileentity != null) {
            if (tileentity instanceof TileEntityBrewingStand) {
                return ((TileEntityBrewingStand) tileentity).getCanaryBrewingStand();
            } else if (tileentity instanceof TileEntityBeacon) {
                return ((TileEntityBeacon) tileentity).getCanaryBeacon();
            } else if (tileentity instanceof TileEntityChest) {
                return ((TileEntityChest) tileentity).getCanaryChest();
            } else if (tileentity instanceof TileEntityCommandBlock) {
                return ((TileEntityCommandBlock) tileentity).getCanaryCommandBlock();
            } else if (tileentity instanceof TileEntityComparator) {
                return ((TileEntityComparator) tileentity).getCanaryComparator();
            } else if (tileentity instanceof TileEntityDaylightDetector) {
                return ((TileEntityDaylightDetector) tileentity).getCanaryDaylightDetector();
            } else if (tileentity instanceof TileEntityDispenser) {
                return ((TileEntityDispenser) tileentity).getCanaryDispenser();
            } else if (tileentity instanceof TileEntityDropper) {
                return ((TileEntityDropper) tileentity).getCanaryDropper();
            } else if (tileentity instanceof TileEntityFurnace) {
                return ((TileEntityFurnace) tileentity).getCanaryFurnace();
            } else if (tileentity instanceof TileEntityHopper) {
                return ((TileEntityHopper) tileentity).getCanaryHopper();
            } else if (tileentity instanceof TileEntityMobSpawner) {
                return ((TileEntityMobSpawner) tileentity).getCanaryMobSpawner();
            } else if (tileentity instanceof TileEntityNote) {
                return ((TileEntityNote) tileentity).getCanaryNoteBlock();
            } else if (tileentity instanceof TileEntityRecordPlayer) {
                return ((TileEntityRecordPlayer) tileentity).getCanaryJukebox();
            } else if (tileentity instanceof TileEntitySign) {
                return ((TileEntitySign) tileentity).getCanarySign();
            } else if (tileentity instanceof TileEntitySkull) {
                return ((TileEntitySkull) tileentity).getCanarySkull();
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object ob) {

        if (!(ob instanceof CanaryWorld)) {
            return false;
        }
        CanaryWorld test = (CanaryWorld) ob;

        return test == this;
    }

    @Override
    public BiomeType getBiomeType(int x, int z) {
        CanaryChunk c = (CanaryChunk) getChunk(x, z);
        if(c == null) {
            return BiomeType.fromId((byte)0);
        }
        return BiomeType.fromId(c.getBiomeByteData()[z << 4 | x]);
    }

    @Override
    public void setBiome(int x, int z, BiomeType biome) {
        CanaryChunk c = (CanaryChunk) getChunk(x, z);
        if(c == null) {
            return;
        }
        byte[] bytes = c.getBiomeByteData();
        bytes[z << 4 | x] = biome.getId();
        c.setBiomeData(bytes);
    }
}
