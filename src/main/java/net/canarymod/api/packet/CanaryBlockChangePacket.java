package net.canarymod.api.packet;

import net.canarymod.Canary;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.api.world.position.Position;
import net.minecraft.server.Packet53BlockChange;

/**
 * BlockChangePacket implementation
 *
 * @author Jason (darkdiplomat)
 */
public class CanaryBlockChangePacket extends CanaryPacket implements BlockChangePacket {

    public CanaryBlockChangePacket(Packet53BlockChange packet) {
        super(packet);
    }

    public CanaryBlockChangePacket(int x, int y, int z, int typeID, int data) {
        this(new Packet53BlockChange(x, y, z, ((CanaryWorld) Canary.getServer().getDefaultWorld()).getHandle())); // World doesn't mean shit really but required in the constructor
        this.setTypeId(typeID);
        this.setData(data);
    }

    public CanaryBlockChangePacket(Block block) {
        this(block.getX(), block.getY(), block.getZ(), block.getTypeId(), block.getData());
    }

    @Override
    public int getX() {
        return getPacket().a;
    }

    @Override
    public void setX(int x) {
        getPacket().a = x;
    }

    @Override
    public int getY() {
        return getPacket().b;
    }

    @Override
    public void setY(int y) {
        getPacket().b = y;
    }

    @Override
    public int getZ() {
        return getPacket().c;
    }

    @Override
    public void setZ(int z) {
        getPacket().c = z;
    }

    @Override
    public Position getPosition() {
        return new Position(getX(), getY(), getZ());
    }

    @Override
    public void setPosition(Position position) {
        this.setX(position.getBlockX());
        this.setY(position.getBlockY());
        this.setZ(position.getBlockZ());
    }

    @Override
    public BlockType getType() {
        return BlockType.fromIdAndData(this.getTypeId(), this.getData());
    }

    @Override
    public void setType(BlockType type) {
        this.setTypeId(type.getId());
        this.setData(type.getData());
    }

    @Override
    public int getTypeId() {
        return getPacket().d;
    }

    @Override
    public void setTypeId(int id) {
        getPacket().d = id;
    }

    @Override
    public int getData() {
        return getPacket().e;
    }

    @Override
    public void setData(int data) {
        getPacket().e = data;
    }

    @Override
    public Block getBlock() {
        return new CanaryBlock((short) getTypeId(), (short) getData(), getX(), getY(), getZ(), Canary.getServer().getDefaultWorld());
    }

    @Override
    public void setBlock(Block block) {
        this.setX(block.getX());
        this.setY(block.getY());
        this.setZ(block.getZ());
        this.setTypeId(block.getTypeId());
        this.setData(block.getData());
    }

    @Override
    public Packet53BlockChange getPacket() {
        return (Packet53BlockChange) packet;
    }
}
