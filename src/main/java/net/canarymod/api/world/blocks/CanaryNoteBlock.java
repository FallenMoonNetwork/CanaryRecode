package net.canarymod.api.world.blocks;


import net.minecraft.server.Material;
import net.minecraft.server.TileEntityNote;


public class CanaryNoteBlock extends CanaryComplexBlock implements NoteBlock {

    public CanaryNoteBlock(TileEntityNote tileentity) {
        super(tileentity);
    }

    @Override
    public byte getInstrument() {
        Material material = tileentity.k.d(getX(), getY() - 1, getZ());
        byte instrument = 0;

        if (material == Material.e) {
            instrument = 1;
        }
        if (material == Material.o) {
            instrument = 2;
        }
        if (material == Material.q) {
            instrument = 3;
        }
        if (material == Material.d) {
            instrument = 4;
        }
        return instrument;
    }

    @Override
    public byte getNote() {
        return ((TileEntityNote) tileentity).a;
    }

    @Override
    public void play() {
        ((TileEntityNote) tileentity).a(tileentity.k, getX(), getY(), getZ());
    }

    @Override
    public void setNote(byte note) {
        if (note < 0) {
            note = 0;
        }
        if (note > 24) {
            note = 24;
        }

        ((TileEntityNote) tileentity).a = note;
    }

}
