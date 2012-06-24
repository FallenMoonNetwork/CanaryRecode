package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.server.OCompressedStreamTools;
import net.minecraft.server.OIntHashMap;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket0KeepAlive;
import net.minecraft.server.OPacket100OpenWindow;
import net.minecraft.server.OPacket101CloseWindow;
import net.minecraft.server.OPacket102WindowClick;
import net.minecraft.server.OPacket103SetSlot;
import net.minecraft.server.OPacket104WindowItems;
import net.minecraft.server.OPacket105UpdateProgressbar;
import net.minecraft.server.OPacket106Transaction;
import net.minecraft.server.OPacket107CreativeSetSlot;
import net.minecraft.server.OPacket108EnchantItem;
import net.minecraft.server.OPacket10Flying;
import net.minecraft.server.OPacket11PlayerPosition;
import net.minecraft.server.OPacket12PlayerLook;
import net.minecraft.server.OPacket130UpdateSign;
import net.minecraft.server.OPacket131MapData;
import net.minecraft.server.OPacket132TileEntityData;
import net.minecraft.server.OPacket13PlayerLookMove;
import net.minecraft.server.OPacket14BlockDig;
import net.minecraft.server.OPacket15Place;
import net.minecraft.server.OPacket16BlockItemSwitch;
import net.minecraft.server.OPacket17Sleep;
import net.minecraft.server.OPacket18Animation;
import net.minecraft.server.OPacket19EntityAction;
import net.minecraft.server.OPacket1Login;
import net.minecraft.server.OPacket200Statistic;
import net.minecraft.server.OPacket201PlayerInfo;
import net.minecraft.server.OPacket202PlayerAbilities;
import net.minecraft.server.OPacket20NamedEntitySpawn;
import net.minecraft.server.OPacket21PickupSpawn;
import net.minecraft.server.OPacket22Collect;
import net.minecraft.server.OPacket23VehicleSpawn;
import net.minecraft.server.OPacket24MobSpawn;
import net.minecraft.server.OPacket250CustomPayload;
import net.minecraft.server.OPacket254ServerPing;
import net.minecraft.server.OPacket255KickDisconnect;
import net.minecraft.server.OPacket25EntityPainting;
import net.minecraft.server.OPacket26EntityExpOrb;
import net.minecraft.server.OPacket28EntityVelocity;
import net.minecraft.server.OPacket29DestroyEntity;
import net.minecraft.server.OPacket2Handshake;
import net.minecraft.server.OPacket30Entity;
import net.minecraft.server.OPacket31RelEntityMove;
import net.minecraft.server.OPacket32EntityLook;
import net.minecraft.server.OPacket33RelEntityMoveLook;
import net.minecraft.server.OPacket34EntityTeleport;
import net.minecraft.server.OPacket35EntityHeadRotation;
import net.minecraft.server.OPacket38EntityStatus;
import net.minecraft.server.OPacket39AttachEntity;
import net.minecraft.server.OPacket3Chat;
import net.minecraft.server.OPacket40EntityMetadata;
import net.minecraft.server.OPacket41EntityEffect;
import net.minecraft.server.OPacket42RemoveEntityEffect;
import net.minecraft.server.OPacket43Experience;
import net.minecraft.server.OPacket4UpdateTime;
import net.minecraft.server.OPacket50PreChunk;
import net.minecraft.server.OPacket51MapChunk;
import net.minecraft.server.OPacket52MultiBlockChange;
import net.minecraft.server.OPacket53BlockChange;
import net.minecraft.server.OPacket54PlayNoteBlock;
import net.minecraft.server.OPacket5PlayerInventory;
import net.minecraft.server.OPacket60Explosion;
import net.minecraft.server.OPacket61DoorChange;
import net.minecraft.server.OPacket6SpawnPosition;
import net.minecraft.server.OPacket70Bed;
import net.minecraft.server.OPacket71Weather;
import net.minecraft.server.OPacket7UseEntity;
import net.minecraft.server.OPacket8UpdateHealth;
import net.minecraft.server.OPacket9Respawn;
import net.minecraft.server.OPacketCount;

public abstract class OPacket {

    public static OIntHashMap j = new OIntHashMap();
    private static Map a = new HashMap();
    private static Set b = new HashSet();
    private static Set c = new HashSet();
    public final long k = System.currentTimeMillis();
    public static long l;
    public static long m;
    public static long n;
    public static long o;
    public boolean p = false;

    public OPacket() {
        super();
    }

    static void a(int var0, boolean var1, boolean var2, Class var3) {
        if (j.b(var0)) {
            throw new IllegalArgumentException("Duplicate packet id:" + var0);
        } else if (a.containsKey(var3)) {
            throw new IllegalArgumentException("Duplicate packet class:" + var3);
        } else {
            j.a(var0, var3);
            a.put(var3, Integer.valueOf(var0));
            if (var1) {
                b.add(Integer.valueOf(var0));
            }

            if (var2) {
                c.add(Integer.valueOf(var0));
            }

        }
    }

    public static OPacket a(int var0) {
        try {
            Class var1 = (Class) j.a(var0);
            return var1 == null ? null : (OPacket) var1.newInstance();
        } catch (Exception var2) {
            var2.printStackTrace();
            System.out.println("Skipping packet with id " + var0);
            return null;
        }
    }

    public final int b() {
        return ((Integer) a.get(this.getClass())).intValue();
    }

    public static OPacket a(DataInputStream var0, boolean var1) throws IOException {
        boolean var2 = false;
        OPacket var3 = null;

        int var6;
        try {
            var6 = var0.read();
            if (var6 == -1) {
                return null;
            }

            if (var1 && !c.contains(Integer.valueOf(var6)) || !var1 && !b.contains(Integer.valueOf(var6))) {
                throw new IOException("Bad packet id " + var6);
            }

            var3 = a(var6);
            if (var3 == null) {
                throw new IOException("Bad packet id " + var6);
            }

            var3.a(var0);
            ++l;
            m += var3.a();
        } catch (EOFException var5) {
            System.out.println("Reached end of stream");
            return null;
        }

        OPacketCount.a(var6, var3.a());
        ++l;
        m += var3.a();
        return var3;
    }

    public static void a(OPacket var0, DataOutputStream var1) throws IOException {
        var1.write(var0.b());
        var0.a(var1);
        ++n;
        o += var0.a();
    }

    public static void a(String var0, DataOutputStream var1) throws IOException {
        if (var0.length() > 32767) {
            throw new IOException("String too big");
        } else {
            var1.writeShort(var0.length());
            var1.writeChars(var0);
        }
    }

    public static String a(DataInputStream var0, int var1) throws IOException {
        short var2 = var0.readShort();
        if (var2 > var1) {
            throw new IOException("Received string length longer than maximum allowed (" + var2 + " > " + var1 + ")");
        } else if (var2 < 0) {
            throw new IOException("Received string length is less than zero! Weird string!");
        } else {
            StringBuilder var3 = new StringBuilder();

            for (int var4 = 0; var4 < var2; ++var4) {
                var3.append(var0.readChar());
            }

            return var3.toString();
        }
    }

    public abstract void a(DataInputStream var1) throws IOException;

    public abstract void a(DataOutputStream var1) throws IOException;

    public abstract void a(ONetHandler var1);

    public abstract int a();

    protected OItemStack b(DataInputStream var1) throws IOException {
        OItemStack var2 = null;
        short var3 = var1.readShort();
        if (var3 >= 0) {
            byte var4 = var1.readByte();
            short var5 = var1.readShort();
            var2 = new OItemStack(var3, var4, var5);
            if (OItem.d[var3].g() || OItem.d[var3].i()) {
                var2.d = this.c(var1);
            }
        }

        return var2;
    }

    protected void a(OItemStack var1, DataOutputStream var2) throws IOException {
        if (var1 == null) {
            var2.writeShort(-1);
        } else {
            var2.writeShort(var1.c);
            var2.writeByte(var1.a);
            var2.writeShort(var1.h());
            if (var1.a().g() || var1.a().i()) {
                this.a(var1.d, var2);
            }
        }

    }

    protected ONBTTagCompound c(DataInputStream var1) throws IOException {
        short var2 = var1.readShort();
        if (var2 < 0) {
            return null;
        } else {
            byte[] var3 = new byte[var2];
            var1.readFully(var3);
            return OCompressedStreamTools.a(var3);
        }
    }

    protected void a(ONBTTagCompound var1, DataOutputStream var2) throws IOException {
        if (var1 == null) {
            var2.writeShort(-1);
        } else {
            byte[] var3 = OCompressedStreamTools.a(var1);
            var2.writeShort((short) var3.length);
            var2.write(var3);
        }

    }

    static {
        a(0, true, true, OPacket0KeepAlive.class);
        a(1, true, true, OPacket1Login.class);
        a(2, true, true, OPacket2Handshake.class);
        a(3, true, true, OPacket3Chat.class);
        a(4, true, false, OPacket4UpdateTime.class);
        a(5, true, false, OPacket5PlayerInventory.class);
        a(6, true, false, OPacket6SpawnPosition.class);
        a(7, false, true, OPacket7UseEntity.class);
        a(8, true, false, OPacket8UpdateHealth.class);
        a(9, true, true, OPacket9Respawn.class);
        a(10, true, true, OPacket10Flying.class);
        a(11, true, true, OPacket11PlayerPosition.class);
        a(12, true, true, OPacket12PlayerLook.class);
        a(13, true, true, OPacket13PlayerLookMove.class);
        a(14, false, true, OPacket14BlockDig.class);
        a(15, false, true, OPacket15Place.class);
        a(16, false, true, OPacket16BlockItemSwitch.class);
        a(17, true, false, OPacket17Sleep.class);
        a(18, true, true, OPacket18Animation.class);
        a(19, false, true, OPacket19EntityAction.class);
        a(20, true, false, OPacket20NamedEntitySpawn.class);
        a(21, true, false, OPacket21PickupSpawn.class);
        a(22, true, false, OPacket22Collect.class);
        a(23, true, false, OPacket23VehicleSpawn.class);
        a(24, true, false, OPacket24MobSpawn.class);
        a(25, true, false, OPacket25EntityPainting.class);
        a(26, true, false, OPacket26EntityExpOrb.class);
        a(28, true, false, OPacket28EntityVelocity.class);
        a(29, true, false, OPacket29DestroyEntity.class);
        a(30, true, false, OPacket30Entity.class);
        a(31, true, false, OPacket31RelEntityMove.class);
        a(32, true, false, OPacket32EntityLook.class);
        a(33, true, false, OPacket33RelEntityMoveLook.class);
        a(34, true, false, OPacket34EntityTeleport.class);
        a(35, true, false, OPacket35EntityHeadRotation.class);
        a(38, true, false, OPacket38EntityStatus.class);
        a(39, true, false, OPacket39AttachEntity.class);
        a(40, true, false, OPacket40EntityMetadata.class);
        a(41, true, false, OPacket41EntityEffect.class);
        a(42, true, false, OPacket42RemoveEntityEffect.class);
        a(43, true, false, OPacket43Experience.class);
        a(50, true, false, OPacket50PreChunk.class);
        a(51, true, false, OPacket51MapChunk.class);
        a(52, true, false, OPacket52MultiBlockChange.class);
        a(53, true, false, OPacket53BlockChange.class);
        a(54, true, false, OPacket54PlayNoteBlock.class);
        a(60, true, false, OPacket60Explosion.class);
        a(61, true, false, OPacket61DoorChange.class);
        a(70, true, false, OPacket70Bed.class);
        a(71, true, false, OPacket71Weather.class);
        a(100, true, false, OPacket100OpenWindow.class);
        a(101, true, true, OPacket101CloseWindow.class);
        a(102, false, true, OPacket102WindowClick.class);
        a(103, true, false, OPacket103SetSlot.class);
        a(104, true, false, OPacket104WindowItems.class);
        a(105, true, false, OPacket105UpdateProgressbar.class);
        a(106, true, true, OPacket106Transaction.class);
        a(107, true, true, OPacket107CreativeSetSlot.class);
        a(108, false, true, OPacket108EnchantItem.class);
        a(130, true, true, OPacket130UpdateSign.class);
        a(131, true, false, OPacket131MapData.class);
        a(132, true, false, OPacket132TileEntityData.class);
        a(200, true, false, OPacket200Statistic.class);
        a(201, true, false, OPacket201PlayerInfo.class);
        a(202, true, true, OPacket202PlayerAbilities.class);
        a(250, true, true, OPacket250CustomPayload.class);
        a(254, false, true, OPacket254ServerPing.class);
        a(255, true, true, OPacket255KickDisconnect.class);
    }
}
