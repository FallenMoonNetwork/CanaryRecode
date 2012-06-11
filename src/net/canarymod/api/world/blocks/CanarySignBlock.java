package net.canarymod.api.world.blocks;

import net.minecraft.server.OTileEntitySign;

public class CanarySignBlock extends CanaryComplexBlock implements SignBlock{

    public CanarySignBlock(OTileEntitySign sign){
        super(sign);
    }

    @Override
    public String[] getText() {
        return ((OTileEntitySign)tileentity).a;
    }

    @Override
    public String getTextOnLine(int line) {
        if(line > 0 && line < 3){
            return ((OTileEntitySign)tileentity).a[line];
        }
        return null;
    }

    @Override
    public void setText(String[] text) {
        ((OTileEntitySign)tileentity).a = text;
    }

    @Override
    public void setTextOnLine(String text, int line) {
        if(line > 0 && line < 3){
            ((OTileEntitySign)tileentity).a[line] = text;
        }
    }
}
