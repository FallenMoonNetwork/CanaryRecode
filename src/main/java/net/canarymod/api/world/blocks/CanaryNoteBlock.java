package net.canarymod.api.world.blocks;

import net.minecraft.server.OMaterial;
import net.minecraft.server.OTileEntityNote;

public class CanaryNoteBlock extends CanaryComplexBlock implements NoteBlock {

    public CanaryNoteBlock(OTileEntityNote tileentity) {
        super(tileentity);
    }

    @Override
    public byte getInstrument() {
        OMaterial material = tileentity.k.d(getX(), getY() - 1, getZ());
        byte instrument = 0;
        if (material == OMaterial.e) {
            instrument = 1;
        }
        if (material == OMaterial.o) {
            instrument = 2;
        }
        if (material == OMaterial.q) {
            instrument = 3;
        }
        if (material == OMaterial.d) {
            instrument = 4;
        }
        return instrument;
    }

    @Override
    public byte getNote() {
        return ((OTileEntityNote) tileentity).a;
    }

    @Override
    public void play() {
        ((OTileEntityNote) tileentity).a(tileentity.k, getX(), getY(), getZ());
    }

    @Override
    public void setNote(byte note) {
        if (note < 0) {
            note = 0;
        }
        if (note > 24) {
            note = 24;
        }

        ((OTileEntityNote) tileentity).a = note;
    }

}
