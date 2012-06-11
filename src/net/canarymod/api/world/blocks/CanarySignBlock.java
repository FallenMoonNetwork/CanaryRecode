package net.canarymod.api.world.blocks;

import net.minecraft.server.OTileEntitySign;

public class CanarySignBlock extends CanaryBlock implements SignBlock{

    private OTileEntitySign sign;
    
    public CanarySignBlock(OTileEntitySign sign){
        super(sign.l, sign.m, sign.n);
        this.sign = sign;
    }

    @Override
    public Block getBlock() {
        return (CanaryBlock) this;
    }

    @Override
    public String[] getText() {
        return sign.a;
    }

    @Override
    public String getTextOnLine(int line) {
        if(line > 0 && line < 3){
            return sign.a[line];
        }
        return null;
    }

    @Override
    public void setText(String[] text) {
        sign.a = text;
    }

    @Override
    public void setTextOnLine(String text, int line) {
        if(line > 0 && line < 3){
            sign.a[line] = text;
        }
    }
}
