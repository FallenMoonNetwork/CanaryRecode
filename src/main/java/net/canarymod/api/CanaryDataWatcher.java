package net.canarymod.api;

public class CanaryDataWatcher implements DataWatcher {

    private net.minecraft.server.DataWatcher dataWatcher;

    public CanaryDataWatcher(net.minecraft.server.DataWatcher dw) {
        dataWatcher = dw;
    }

    @Override
    public void addObject(int index, Object object) {
        dataWatcher.a(index, object);

    }

    @Override
    public void updateObject(int index, Object object) {
        dataWatcher.b(index, object);

    }

    @Override
    public byte getByte(int index) {
        return dataWatcher.a(index);
    }

    @Override
    public short getShort(int index) {
        return dataWatcher.b(index);
    }

    @Override
    public int getInt(int index) {
        return dataWatcher.c(index);
    }

    @Override
    public String getString(int index) {
        return dataWatcher.d(index);
    }

}
