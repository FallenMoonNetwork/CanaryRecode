package net.canarymod.api.world.blocks;


import net.minecraft.server.Material;
import net.minecraft.server.TileEntityNote;


/**
 * NoteBlock wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryNoteBlock extends CanaryComplexBlock implements NoteBlock {

    /**
     * Constructs a new wrapper for TileEntityChest
     * 
     * @param tileentity
     *            the TileEntityChest to be wrapped
     */
    public CanaryNoteBlock(TileEntityNote tileentity) {
        super(tileentity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte getInstrument() {
        Material material = tileentity.az().g(getX(), getY() - 1, getZ());
        byte instrument = 0;

        if (material == Material.e) {
            instrument = 1;
        }

        if (material == Material.p) {
            instrument = 2;
        }

        if (material == Material.r) {
            instrument = 3;
        }

        if (material == Material.d) {
            instrument = 4;
        }

        return instrument;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte getNote() {
        return getHandle().a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void play() {
        getHandle().a(tileentity.az(), getX(), getY(), getZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNote(byte note) {
        if (note < 0) {
            note = 0;
        }
        if (note > 24) {
            note = 24;
        }

        getHandle().a = note;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TileEntityNote getHandle() {
        return (TileEntityNote) tileentity;
    }

}
