package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Packet {

    public static IntHashMap l = new IntHashMap();
    private static Map a = new HashMap();
    private static Set b = new HashSet();
    private static Set c = new HashSet();
    protected ILogAgent m;
    public final long n = System.currentTimeMillis();
    public static long o;
    public static long p;
    public static long q;
    public static long r;
    public boolean s = false;

    public Packet() {}

    static void a(int i0, boolean flag0, boolean flag1, Class oclass0) {
        if (l.b(i0)) {
            throw new IllegalArgumentException("Duplicate packet id:" + i0);
        } else if (a.containsKey(oclass0)) {
            throw new IllegalArgumentException("Duplicate packet class:" + oclass0);
        } else {
            l.a(i0, oclass0);
            a.put(oclass0, Integer.valueOf(i0));
            if (flag0) {
                b.add(Integer.valueOf(i0));
            }

            if (flag1) {
                c.add(Integer.valueOf(i0));
            }
        }
    }

    public static Packet a(ILogAgent ilogagent, int i0) {
        try {
            Class oclass0 = (Class) l.a(i0);

            return oclass0 == null ? null : (Packet) oclass0.newInstance();
        } catch (Exception exception) {
            exception.printStackTrace();
            ilogagent.c("Skipping packet with id " + i0);
            return null;
        }
    }

    public static void a(DataOutputStream dataoutputstream, byte[] abyte) throws IOException {
        dataoutputstream.writeShort(abyte.length);
        dataoutputstream.write(abyte);
    }

    public static byte[] b(DataInputStream datainputstream) throws IOException {
        short short1 = datainputstream.readShort();

        if (short1 < 0) {
            throw new IOException("Key was smaller than nothing!  Weird key!");
        } else {
            byte[] abyte = new byte[short1];

            datainputstream.readFully(abyte);
            return abyte;
        }
    }

    public final int n() {
        return ((Integer) a.get(this.getClass())).intValue();
    }

    public static Packet a(ILogAgent ilogagent, DataInputStream datainputstream, boolean flag0, Socket socket) throws IOException {
        boolean flag1 = false;
        Packet packet = null;
        if(socket == null) {
            throw new IOException("Socket null!");
        }
        int i0 = socket.getSoTimeout();

        int i1;

        try {
            i1 = datainputstream.read();
            if (i1 == -1) {
                return null;
            }

            if (flag0 && !c.contains(Integer.valueOf(i1)) || !flag0 && !b.contains(Integer.valueOf(i1))) {
                throw new IOException("Bad packet id " + i1);
            }

            packet = a(ilogagent, i1);
            if (packet == null) {
                throw new IOException("Bad packet id " + i1);
            }

            packet.m = ilogagent;
            if (packet instanceof Packet254ServerPing) {
                socket.setSoTimeout(1500);
            }

            packet.a(datainputstream);
            ++o;
            p += (long) packet.a();
        } catch (EOFException eofexception) {
            ilogagent.c("Reached end of stream");
            return null;
        }

        PacketCount.a(i1, (long) packet.a());
        ++o;
        p += (long) packet.a();
        socket.setSoTimeout(i0);
        return packet;
    }

    public static void a(Packet packet, DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.write(packet.n());
        packet.a(dataoutputstream);
        ++q;
        r += (long) packet.a();
    }

    public static void a(String s0, DataOutputStream dataoutputstream) throws IOException {
        if (s0.length() > 32767) {
            throw new IOException("String too big");
        } else {
            dataoutputstream.writeShort(s0.length());
            dataoutputstream.writeChars(s0);
        }
    }

    public static String a(DataInputStream datainputstream, int i0) throws IOException {
        short short1 = datainputstream.readShort();

        if (short1 > i0) {
            throw new IOException("Received string length longer than maximum allowed (" + short1 + " > " + i0 + ")");
        } else if (short1 < 0) {
            throw new IOException("Received string length is less than zero! Weird string!");
        } else {
            StringBuilder stringbuilder = new StringBuilder();

            for (int i1 = 0; i1 < short1; ++i1) {
                stringbuilder.append(datainputstream.readChar());
            }

            return stringbuilder.toString();
        }
    }

    public abstract void a(DataInputStream var1) throws IOException;

    public abstract void a(DataOutputStream var1) throws IOException;

    public abstract void a(NetHandler nethandler);

    public abstract int a();

    public boolean e() {
        return false;
    }

    public boolean a(Packet packet) {
        return false;
    }

    public boolean a_() {
        return false;
    }

    public String toString() {
        String s0 = this.getClass().getSimpleName();

        return s0;
    }

    public static ItemStack c(DataInputStream datainputstream) throws IOException {
        ItemStack itemstack = null;
        short short1 = datainputstream.readShort();

        if (short1 >= 0) {
            byte b0 = datainputstream.readByte();
            short short2 = datainputstream.readShort();

            itemstack = new ItemStack(short1, b0, short2);
            itemstack.d = d(datainputstream);
        }

        return itemstack;
    }

    public static void a(ItemStack itemstack, DataOutputStream dataoutputstream) throws IOException {
        if (itemstack == null) {
            dataoutputstream.writeShort(-1);
        } else {
            dataoutputstream.writeShort(itemstack.c);
            dataoutputstream.writeByte(itemstack.a);
            dataoutputstream.writeShort(itemstack.k());
            NBTTagCompound nbttagcompound = null;

            if (itemstack.b().o() || itemstack.b().r()) {
                nbttagcompound = itemstack.d;
            }

            a(nbttagcompound, dataoutputstream);
        }
    }

    public static NBTTagCompound d(DataInputStream datainputstream) throws IOException {
        short short1 = datainputstream.readShort();

        if (short1 < 0) {
            return null;
        } else {
            byte[] abyte = new byte[short1];

            datainputstream.readFully(abyte);
            return CompressedStreamTools.a(abyte);
        }
    }

    protected static void a(NBTTagCompound nbttagcompound, DataOutputStream dataoutputstream) throws IOException {
        if (nbttagcompound == null) {
            dataoutputstream.writeShort(-1);
        } else {
            byte[] abyte = CompressedStreamTools.a(nbttagcompound);

            dataoutputstream.writeShort((short) abyte.length);
            dataoutputstream.write(abyte);
        }
    }

    static {
        a(0, true, true, Packet0KeepAlive.class);
        a(1, true, true, Packet1Login.class);
        a(2, false, true, Packet2ClientProtocol.class);
        a(3, true, true, Packet3Chat.class);
        a(4, true, false, Packet4UpdateTime.class);
        a(5, true, false, Packet5PlayerInventory.class);
        a(6, true, false, Packet6SpawnPosition.class);
        a(7, false, true, Packet7UseEntity.class);
        a(8, true, false, Packet8UpdateHealth.class);
        a(9, true, true, Packet9Respawn.class);
        a(10, true, true, Packet10Flying.class);
        a(11, true, true, Packet11PlayerPosition.class);
        a(12, true, true, Packet12PlayerLook.class);
        a(13, true, true, Packet13PlayerLookMove.class);
        a(14, false, true, Packet14BlockDig.class);
        a(15, false, true, Packet15Place.class);
        a(16, true, true, Packet16BlockItemSwitch.class);
        a(17, true, false, Packet17Sleep.class);
        a(18, true, true, Packet18Animation.class);
        a(19, false, true, Packet19EntityAction.class);
        a(20, true, false, Packet20NamedEntitySpawn.class);
        a(22, true, false, Packet22Collect.class);
        a(23, true, false, Packet23VehicleSpawn.class);
        a(24, true, false, Packet24MobSpawn.class);
        a(25, true, false, Packet25EntityPainting.class);
        a(26, true, false, Packet26EntityExpOrb.class);
        a(28, true, false, Packet28EntityVelocity.class);
        a(29, true, false, Packet29DestroyEntity.class);
        a(30, true, false, Packet30Entity.class);
        a(31, true, false, Packet31RelEntityMove.class);
        a(32, true, false, Packet32EntityLook.class);
        a(33, true, false, Packet33RelEntityMoveLook.class);
        a(34, true, false, Packet34EntityTeleport.class);
        a(35, true, false, Packet35EntityHeadRotation.class);
        a(38, true, false, Packet38EntityStatus.class);
        a(39, true, false, Packet39AttachEntity.class);
        a(40, true, false, Packet40EntityMetadata.class);
        a(41, true, false, Packet41EntityEffect.class);
        a(42, true, false, Packet42RemoveEntityEffect.class);
        a(43, true, false, Packet43Experience.class);
        a(51, true, false, Packet51MapChunk.class);
        a(52, true, false, Packet52MultiBlockChange.class);
        a(53, true, false, Packet53BlockChange.class);
        a(54, true, false, Packet54PlayNoteBlock.class);
        a(55, true, false, Packet55BlockDestroy.class);
        a(56, true, false, Packet56MapChunks.class);
        a(60, true, false, Packet60Explosion.class);
        a(61, true, false, Packet61DoorChange.class);
        a(62, true, false, Packet62LevelSound.class);
        a(63, true, false, Packet63WorldParticles.class);
        a(70, true, false, Packet70GameEvent.class);
        a(71, true, false, Packet71Weather.class);
        a(100, true, false, Packet100OpenWindow.class);
        a(101, true, true, Packet101CloseWindow.class);
        a(102, false, true, Packet102WindowClick.class);
        a(103, true, false, Packet103SetSlot.class);
        a(104, true, false, Packet104WindowItems.class);
        a(105, true, false, Packet105UpdateProgressbar.class);
        a(106, true, true, Packet106Transaction.class);
        a(107, true, true, Packet107CreativeSetSlot.class);
        a(108, false, true, Packet108EnchantItem.class);
        a(130, true, true, Packet130UpdateSign.class);
        a(131, true, false, Packet131MapData.class);
        a(132, true, false, Packet132TileEntityData.class);
        a(200, true, false, Packet200Statistic.class);
        a(201, true, false, Packet201PlayerInfo.class);
        a(202, true, true, Packet202PlayerAbilities.class);
        a(203, true, true, Packet203AutoComplete.class);
        a(204, false, true, Packet204ClientInfo.class);
        a(205, false, true, Packet205ClientCommand.class);
        a(206, true, false, Packet206SetObjective.class);
        a(207, true, false, Packet207SetScore.class);
        a(208, true, false, Packet208SetDisplayObjective.class);
        a(209, true, false, Packet209SetPlayerTeam.class);
        a(250, true, true, Packet250CustomPayload.class);
        a(252, true, true, Packet252SharedKey.class);
        a(253, true, false, Packet253ServerAuthData.class);
        a(254, false, true, Packet254ServerPing.class);
        a(255, true, true, Packet255KickDisconnect.class);
    }
}
