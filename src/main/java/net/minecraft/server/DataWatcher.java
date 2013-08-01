package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.canarymod.api.CanaryDataWatcher;

public class DataWatcher {

    private boolean a = true;
    private static final HashMap b = new HashMap();
    private final Map c = new HashMap();
    private boolean d;
    private ReadWriteLock e = new ReentrantReadWriteLock();

    // CanaryMod
    protected CanaryDataWatcher watcher;

    //

    public DataWatcher() {
        watcher = new CanaryDataWatcher(this);
    }

    public void a(int i0, Object object) {
        Integer integer = (Integer) b.get(object.getClass());

        if (integer == null) {
            throw new IllegalArgumentException("Unknown data type: " + object.getClass());
        } else if (i0 > 31) {
            throw new IllegalArgumentException("Data value id is too big with " + i0 + "! (Max is " + 31 + ")");
        } else if (this.c.containsKey(Integer.valueOf(i0))) {
            throw new IllegalArgumentException("Duplicate id value for " + i0 + "!");
        } else {
            WatchableObject watchableobject = new WatchableObject(integer.intValue(), i0, object);

            this.e.writeLock().lock();
            this.c.put(Integer.valueOf(i0), watchableobject);
            this.e.writeLock().unlock();
            this.a = false;
        }
    }

    public void a(int i0, int i1) {
        WatchableObject watchableobject = new WatchableObject(i1, i0, null);

        this.e.writeLock().lock();
        this.c.put(Integer.valueOf(i0), watchableobject);
        this.e.writeLock().unlock();
        this.a = false;
    }

    public byte a(int i0) {
        return ((Byte) this.i(i0).b()).byteValue();
    }

    public short b(int i0) {
        return ((Short) this.i(i0).b()).shortValue();
    }

    public int c(int i0) {
        return ((Integer) this.i(i0).b()).intValue();
    }

    public float d(int i0) {
        return ((Float) this.i(i0).b()).floatValue();
    }

    public String e(int i0) {
        return (String) this.i(i0).b();
    }

    public ItemStack f(int i0) {
        return (ItemStack) this.i(i0).b();
    }

    private WatchableObject i(int i0) {
        this.e.readLock().lock();

        WatchableObject watchableobject;

        try {
            watchableobject = (WatchableObject) this.c.get(Integer.valueOf(i0));
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Getting synched entity data");
            CrashReportCategory crashreportcategory = crashreport.a("Synched entity data");

            crashreportcategory.a("Data ID", Integer.valueOf(i0));
            throw new ReportedException(crashreport);
        }

        this.e.readLock().unlock();
        return watchableobject;
    }

    public void b(int i0, Object object) {
        WatchableObject watchableobject = this.i(i0);

        if (!object.equals(watchableobject.b())) {
            watchableobject.a(object);
            watchableobject.a(true);
            this.d = true;
        }
    }

    public void h(int i0) {
        WatchableObject.a(this.i(i0), true);
        this.d = true;
    }

    public boolean a() {
        return this.d;
    }

    public static void a(List list, DataOutput dataoutput) throws IOException {
        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                WatchableObject watchableobject = (WatchableObject) iterator.next();

                a(dataoutput, watchableobject);
            }
        }

        dataoutput.writeByte(127);
    }

    public List b() {
        ArrayList arraylist = null;

        if (this.d) {
            this.e.readLock().lock();
            Iterator iterator = this.c.values().iterator();

            while (iterator.hasNext()) {
                WatchableObject watchableobject = (WatchableObject) iterator.next();

                if (watchableobject.d()) {
                    watchableobject.a(false);
                    if (arraylist == null) {
                        arraylist = new ArrayList();
                    }

                    arraylist.add(watchableobject);
                }
            }

            this.e.readLock().unlock();
        }

        this.d = false;
        return arraylist;
    }

    public void a(DataOutput dataoutput) throws IOException {
        this.e.readLock().lock();
        Iterator iterator = this.c.values().iterator();

        while (iterator.hasNext()) {
            WatchableObject watchableobject = (WatchableObject) iterator.next();

            a(dataoutput, watchableobject);
        }

        this.e.readLock().unlock();
        dataoutput.writeByte(127);
    }

    public List c() {
        ArrayList arraylist = null;

        this.e.readLock().lock();

        WatchableObject watchableobject;

        for (Iterator iterator = this.c.values().iterator(); iterator.hasNext(); arraylist.add(watchableobject)) {
            watchableobject = (WatchableObject) iterator.next();
            if (arraylist == null) {
                arraylist = new ArrayList();
            }
        }

        this.e.readLock().unlock();
        return arraylist;
    }

    private static void a(DataOutput dataoutput, WatchableObject watchableobject) throws IOException {
        int i0 = (watchableobject.c() << 5 | watchableobject.a() & 31) & 255;

        dataoutput.writeByte(i0);
        switch (watchableobject.c()) {
            case 0:
                dataoutput.writeByte(((Byte) watchableobject.b()).byteValue());
                break;

            case 1:
                dataoutput.writeShort(((Short) watchableobject.b()).shortValue());
                break;

            case 2:
                dataoutput.writeInt(((Integer) watchableobject.b()).intValue());
                break;

            case 3:
                dataoutput.writeFloat(((Float) watchableobject.b()).floatValue());
                break;

            case 4:
                Packet.a((String) watchableobject.b(), dataoutput);
                break;

            case 5:
                ItemStack itemstack = (ItemStack) watchableobject.b();

                Packet.a(itemstack, dataoutput);
                break;

            case 6:
                ChunkCoordinates chunkcoordinates = (ChunkCoordinates) watchableobject.b();

                dataoutput.writeInt(chunkcoordinates.a);
                dataoutput.writeInt(chunkcoordinates.b);
                dataoutput.writeInt(chunkcoordinates.c);
        }
    }

    public static List a(DataInput datainput) throws IOException {
        ArrayList arraylist = null;

        for (byte b0 = datainput.readByte(); b0 != 127; b0 = datainput.readByte()) {
            if (arraylist == null) {
                arraylist = new ArrayList();
            }

            int i0 = (b0 & 224) >> 5;
            int i1 = b0 & 31;
            WatchableObject watchableobject = null;

            switch (i0) {
                case 0:
                    watchableobject = new WatchableObject(i0, i1, Byte.valueOf(datainput.readByte()));
                    break;

                case 1:
                    watchableobject = new WatchableObject(i0, i1, Short.valueOf(datainput.readShort()));
                    break;

                case 2:
                    watchableobject = new WatchableObject(i0, i1, Integer.valueOf(datainput.readInt()));
                    break;

                case 3:
                    watchableobject = new WatchableObject(i0, i1, Float.valueOf(datainput.readFloat()));
                    break;

                case 4:
                    watchableobject = new WatchableObject(i0, i1, Packet.a(datainput, 64));
                    break;

                case 5:
                    watchableobject = new WatchableObject(i0, i1, Packet.c(datainput));
                    break;

                case 6:
                    int i2 = datainput.readInt();
                    int i3 = datainput.readInt();
                    int i4 = datainput.readInt();

                    watchableobject = new WatchableObject(i0, i1, new ChunkCoordinates(i2, i3, i4));
            }

            arraylist.add(watchableobject);
        }

        return arraylist;
    }

    public boolean d() {
        return this.a;
    }

    public void e() {
        this.d = false;
    }

    static {
        b.put(Byte.class, Integer.valueOf(0));
        b.put(Short.class, Integer.valueOf(1));
        b.put(Integer.class, Integer.valueOf(2));
        b.put(Float.class, Integer.valueOf(3));
        b.put(String.class, Integer.valueOf(4));
        b.put(ItemStack.class, Integer.valueOf(5));
        b.put(ChunkCoordinates.class, Integer.valueOf(6));
    }

    /**
     * get the CanaryMod DataWatcher wrapper
     * 
     * @return
     */
    public CanaryDataWatcher getCanaryDataWatcher() {
        return watcher;
    }
}
