package net.canarymod.api.factory;

import net.canarymod.Canary;
import net.canarymod.api.CanaryDataWatcher;
import net.canarymod.api.DataWatcher;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.CanaryXPOrb;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.XPOrb;
import net.canarymod.api.entity.hanging.CanaryPainting;
import net.canarymod.api.entity.hanging.Painting;
import net.canarymod.api.entity.living.CanaryLivingBase;
import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.api.entity.living.humanoid.CanaryHuman;
import net.canarymod.api.entity.living.humanoid.CanaryPlayer;
import net.canarymod.api.entity.living.humanoid.Human;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.packet.CanaryBlockChangePacket;
import net.canarymod.api.packet.CanaryPacket;
import net.canarymod.api.packet.InvalidPacketConstructionException;
import net.canarymod.api.packet.Packet;
import net.canarymod.api.potion.CanaryPotionEffect;
import net.canarymod.api.potion.PotionEffect;
import net.canarymod.api.world.CanaryChunk;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.Chunk;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Position;
import net.canarymod.api.world.position.Vector3D;
import net.minecraft.server.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Packet Factory implementation
 *
 * @author Jason (darkdiplomat)
 */
public class CanaryPacketFactory implements PacketFactory {
    private final static String toofewargs = "Not enough arguments (Expected: %d Got: %d)";

    @SuppressWarnings({"unchecked"})
    public Packet createPacket(int id, Object... args) throws Exception {
        if (args == null || args.length < 1) {
            throw new IllegalArgumentException("Arguments cannot be null or empty");
        }
        switch (id) {
            case 0x00:
                throw new InvalidPacketConstructionException(0, "0x00", "Keep Alive", "Keep Alive should not be messed with.");
            case 0x01:
                throw new InvalidPacketConstructionException(1, "0x01", "Login", "Login should only be sent when a Player logs in.");
            case 0x02:
                throw new InvalidPacketConstructionException(2, "0x02", "Handshake", "Handshake should only be sent when a Player attempts login.");
            case 0x03:
                throw new InvalidPacketConstructionException(3, "0x03", "Chat Message", "Use MessageReceiver#message instead");
            case 0x04:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                return new CanaryPacket(new Packet4UpdateTime((Long) args[0], (Long) args[1], true));
            case 0x05:
                if (args.length < 3) {
                    throw new IllegalArgumentException(String.format(toofewargs, 3, args.length));
                }
                return new CanaryPacket(new Packet5PlayerInventory((Integer) args[0], (Integer) args[1], ((CanaryItem) args[2]).getHandle()));
            case 0x06:
                if (args.length < 3) {
                    throw new IllegalArgumentException(String.format(toofewargs, 3, args.length));
                }
                return new CanaryPacket(new Packet6SpawnPosition((Integer) args[0], (Integer) args[1], (Integer) args[2]));
            case 0x07:
                throw new InvalidPacketConstructionException(7, "0x07", "Use Entity", "Client to Server only.");
            case 0x08:
                if (args.length < 3) {
                    throw new IllegalArgumentException(String.format(toofewargs, 3, args.length));
                }
                return new CanaryPacket(new Packet8UpdateHealth((Float) args[0], (Integer) args[1], (Float) args[2]));
            case 0x09:
                if (args.length < 5) {
                    throw new IllegalArgumentException(String.format(toofewargs, 5, args.length));
                }
                /*
                 * Listed on wiki.vg as int Dimension, byte Difficulty, byte GameMode, int World Height, String WorldType
                 * Constructor as: int dimension, byte difficulty, WorldType worldtype, int height, EnumGameType enumgametype
                 * Order from wiki for convenience
                 */
                return new CanaryPacket(new Packet9Respawn((Integer) args[0], (Byte) args[1], net.minecraft.server.WorldType.a((String) args[4]), (Integer) args[3], EnumGameType.a((Byte) args[2])));
            case 0x0A:
                throw new InvalidPacketConstructionException(10, "0x0A", "Player", "Client to Server only.");
            case 0x0B:
                throw new InvalidPacketConstructionException(11, "0x0B", "Player Position", "Client to Server only.");
            case 0x0C:
                throw new InvalidPacketConstructionException(12, "0x0C", "Player Look", "Client to Server only.");
            case 0x0D:
                if (args.length < 7) {
                    throw new IllegalArgumentException(String.format(toofewargs, 7, args.length));
                }
                // X StanceY Y Z yaw pitch onGround
                return new CanaryPacket(new Packet13PlayerLookMove((Double) args[0], (Double) args[1], (Double) args[2], (Double) args[3], (Float) args[4], (Float) args[5], (Boolean) args[6]));
            case 0x0E:
                throw new InvalidPacketConstructionException(14, "0x0E", "Player Digging", "Client to Server only.");
            case 0x0F:
                throw new InvalidPacketConstructionException(15, "0x0F", "Player Block Placement", "Client to Server only.");
            case 0x10:
                return new CanaryPacket(new Packet16BlockItemSwitch((Integer) args[0]));
            case 0x11:
                if (args.length < 4) {
                    throw new IllegalArgumentException(String.format(toofewargs, 4, args.length));
                }
                return new CanaryPacket(new Packet17Sleep(((CanaryPlayer) args[0]).getHandle(), 0, (Integer) args[1], (Byte) args[2], (Integer) args[3]));
            case 0x12:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                return new CanaryPacket(new Packet18Animation(((CanaryPlayer) args[0]).getHandle(), (Integer) args[1]));
            case 0x13:
                throw new InvalidPacketConstructionException(19, "0x15", "Entity Action", "Client to Server only.");
            case 0x14:
                return new CanaryPacket(new Packet20NamedEntitySpawn(((CanaryHuman) args[0]).getHandle()));
//          case 0x15: // There is currently no Packet 21
            case 0x16:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                return new CanaryPacket(new Packet22Collect((Integer) args[0], (Integer) args[1]));
            case 0x17:
                if (args.length < 3) {
                    throw new IllegalArgumentException(String.format(toofewargs, 3, args.length));
                }
                return new CanaryPacket(new Packet23VehicleSpawn(((CanaryEntity) args[0]).getHandle(), (Integer) args[1], (Integer) args[2]));
            case 0x18:
                return new CanaryPacket(new Packet24MobSpawn(((CanaryLivingBase) args[0]).getHandle()));
            case 0x19:
                return new CanaryPacket(new Packet25EntityPainting(((CanaryPainting) args[0]).getHandle()));
            case 0x1A:
                return new CanaryPacket(new Packet26EntityExpOrb(((CanaryXPOrb) args[0]).getHandle()));
            case 0x1B:
                throw new InvalidPacketConstructionException(27, "0x1B", "Steer Vehicle", "Client to Server only.");
            case 0x1C:
                return new CanaryPacket(new Packet28EntityVelocity((Integer) args[0], (Double) args[1], (Double) args[2], (Double) args[3]));
            case 0x1D:
                return new CanaryPacket(new Packet29DestroyEntity((int[]) args[0]));
            case 0x1E:
                throw new InvalidPacketConstructionException(30, "0x1E", "Entity", "No actual use/effect.");
            case 0x1F:
                if (args.length < 4) {
                    throw new IllegalArgumentException(String.format(toofewargs, 4, args.length));
                }
                return new CanaryPacket(new Packet31RelEntityMove((Integer) args[0], (Byte) args[1], (Byte) args[2], (Byte) args[3]));
            case 0x20:
                if (args.length < 3) {
                    throw new IllegalArgumentException(String.format(toofewargs, 3, args.length));
                }
                return new CanaryPacket(new Packet32EntityLook((Integer) args[0], (Byte) args[1], (Byte) args[2]));
            case 0x21:
                if (args.length < 6) {
                    throw new IllegalArgumentException(String.format(toofewargs, 6, args.length));
                }
                return new CanaryPacket(new Packet33RelEntityMoveLook((Integer) args[0], (Byte) args[1], (Byte) args[2], (Byte) args[3], (Byte) args[4], (Byte) args[5]));
            case 0x22:
                if (args.length < 6) {
                    throw new IllegalArgumentException(String.format(toofewargs, 6, args.length));
                }
                return new CanaryPacket(new Packet34EntityTeleport((Integer) args[0], (Integer) args[1], (Integer) args[2], (Integer) args[3], (Byte) args[4], (Byte) args[5]));
            case 0x23:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                return new CanaryPacket(new Packet35EntityHeadRotation((Integer) args[0], (Byte) args[1]));
//          case 0x24: // There is currently no Packet 36
//          case 0x25: // There is currently no Packet 37
            case 0x26:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                return new CanaryPacket(new Packet38EntityStatus((Integer) args[0], (Byte) args[1]));
            case 0x27:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                return new CanaryPacket(new Packet39AttachEntity((Integer) args[0], ((CanaryEntity) args[1]).getHandle(), ((CanaryEntity) args[2]).getHandle()));
            case 0x28:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                return new CanaryPacket(new Packet40EntityMetadata((Integer) args[0], ((CanaryDataWatcher) args[1]).getHandle(), true));
            case 0x29:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                return new CanaryPacket(new Packet41EntityEffect((Integer) args[0], ((CanaryPotionEffect) args[1]).getHandle()));
            case 0x2A:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                return new CanaryPacket(new Packet42RemoveEntityEffect((Integer) args[0], ((CanaryPotionEffect) args[1]).getHandle()));
            case 0x2B:
                if (args.length < 3) {
                    throw new IllegalArgumentException(String.format(toofewargs, 3, args.length));
                }
                return new CanaryPacket(new Packet43Experience((Float) args[0], (Integer) args[1], (Integer) args[2]));
            case 0x2C:
                // Can't do this one at this time
                throw new IllegalArgumentException("Unimplemented Packet ID");
//          case 0x2D: // There is currently no Packet 45
//          case 0x2E: // There is currently no Packet 46
//          case 0x2F: // There is currently no Packet 47
//          case 0x30: // There is currently no Packet 48
//          case 0x31: // There is currently no Packet 49
//          case 0x32: // There is currently no Packet 50
            case 0x33:
                if (args.length < 3) {
                    throw new IllegalArgumentException(String.format(toofewargs, 3, args.length));
                }
                return new CanaryPacket(new Packet51MapChunk(((CanaryChunk) args[0]).getHandle(), (Boolean) args[1], (Integer) args[2]));
            case 0x34:
                if (args.length < 5) {
                    throw new IllegalArgumentException(String.format(toofewargs, 5, args.length));
                }
                return new CanaryPacket(new Packet52MultiBlockChange((Integer) args[0], (Integer) args[1], (short[]) args[2], (Integer) args[3], ((CanaryWorld) args[4]).getHandle()));
            case 0x35:
                if (args.length < 5) {
                    throw new IllegalArgumentException(String.format(toofewargs, 5, args.length));
                }
                return new CanaryBlockChangePacket((Integer) args[0], (Integer) args[1], (Integer) args[2], (Integer) args[3], (Integer) args[4]);
            case 0x36:
                if (args.length < 6) {
                    throw new IllegalArgumentException(String.format(toofewargs, 6, args.length));
                }
                return new CanaryPacket(new Packet54PlayNoteBlock((Integer) args[0], (Integer) args[1], (Integer) args[2], (Integer) args[3], (Integer) args[4], (Integer) args[5]));
            case 0x37:
                if (args.length < 5) {
                    throw new IllegalArgumentException(String.format(toofewargs, 5, args.length));
                }
                return new CanaryPacket(new Packet55BlockDestroy((Integer) args[0], (Integer) args[1], (Integer) args[2], (Integer) args[3], (Integer) args[4]));
            case 0x38:
                ArrayList<net.minecraft.server.Chunk> nmsChunks = new ArrayList<net.minecraft.server.Chunk>();
                for (Chunk chunk : (List<Chunk>) args[0]) {
                    nmsChunks.add(((CanaryChunk) chunk).getHandle());
                }
                return new CanaryPacket(new Packet56MapChunks(nmsChunks));
//          case 0x39: // There is currently no Packet 57
//          case 0x3A: // There is currently no Packet 58
//          case 0x3B: // There is currently no Packet 59
            case 0x3C:
                if (args.length < 5) {
                    throw new IllegalArgumentException(String.format(toofewargs, 5, args.length));
                }
                ArrayList<ChunkPosition> cp = new ArrayList<ChunkPosition>();
                for (Position position : (List<Position>) args[4]) {
                    cp.add(new ChunkPosition(position.getBlockX(), position.getBlockY(), position.getBlockZ()));
                }
                Vector3D v3D = (Vector3D) args[5];
                return new CanaryPacket(new Packet60Explosion((Double) args[0], (Double) args[1], (Double) args[2], (Float) args[3], cp, Vec3.a(v3D.getX(), v3D.getY(), v3D.getZ())));
            case 0x3D:
                return new CanaryPacket(new Packet61DoorChange((Integer) args[0], (Integer) args[1], (Integer) args[2], (Integer) args[3], (Integer) args[4], (Boolean) args[5]));
            case 0x3E:
                return new CanaryPacket(new Packet62LevelSound((String) args[0], (Double) args[1], (Double) args[2], (Double) args[3], (Float) args[4], (Float) args[5]));
            case 0x3F:
                throw new InvalidPacketConstructionException(63, "0x3F", "World Effect", "Use the Particle Class instead.");
//          case 0x40: // There is currently no Packet 64
//          case 0x41: // There is currently no Packet 65
//          case 0x42: // There is currently no Packet 66
//          case 0x43: // There is currently no Packet 67
//          case 0x44: // There is currently no Packet 68
//          case 0x45: // There is currently no Packet 69
            case 0x46:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                return new CanaryPacket(new Packet70GameEvent((Integer) args[0], (Integer) args[1]));
            case 0x47:
                return new CanaryPacket(new Packet71Weather(((CanaryEntity) args[0]).getHandle()));
//          case 0x48: // There is currently no Packet 72
//          case 0x49: // There is currently no Packet 73
//          case 0x4A: // There is currently no Packet 74
//          case 0x4B: // There is currently no Packet 75
//          case 0x4C: // There is currently no Packet 76
//          case 0x4D: // There is currently no Packet 77
//          case 0x4E: // There is currently no Packet 78
//          case 0x4F: // There is currently no Packet 79
//          case 0x50: // There is currently no Packet 80
//          case 0x51: // There is currently no Packet 81
//          case 0x52: // There is currently no Packet 82
//          case 0x53: // There is currently no Packet 83
//          case 0x54: // There is currently no Packet 84
//          case 0x55: // There is currently no Packet 85
//          case 0x56: // There is currently no Packet 86
//          case 0x57: // There is currently no Packet 87
//          case 0x58: // There is currently no Packet 88
//          case 0x59: // There is currently no Packet 89
//          case 0x5A: // There is currently no Packet 90
//          case 0x5B: // There is currently no Packet 91
//          case 0x5C: // There is currently no Packet 92
//          case 0x5D: // There is currently no Packet 93
//          case 0x5E: // There is currently no Packet 94
//          case 0x5F: // There is currently no Packet 95
//          case 0x60: // There is currently no Packet 96
//          case 0x61: // There is currently no Packet 97
//          case 0x62: // There is currently no Packet 98
//          case 0x63: // There is currently no Packet 99
            case 0x64:
                if (args.length < 5) {
                    throw new IllegalArgumentException(String.format(toofewargs, 5, args.length));
                }
                return new CanaryPacket(new Packet100OpenWindow((Integer) args[0], (Integer) args[1], (String) args[2], (Integer) args[3], (Boolean) args[4]));
            case 0x65:
                return new CanaryPacket(new Packet101CloseWindow((Integer) args[0]));
            case 0x66:
                throw new InvalidPacketConstructionException(102, "0x66", "Click Window", "Client to Server only.");
            case 0x67:
                if (args.length < 3) {
                    throw new IllegalArgumentException(String.format(toofewargs, 3, args.length));
                }
                return new CanaryPacket(new Packet103SetSlot((Integer) args[0], (Integer) args[1], ((CanaryItem) args[3]).getHandle()));
            case 0x68:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                ArrayList<ItemStack> nmsItems = new ArrayList<ItemStack>();
                for (Item item : (List<Item>) args[1]) {
                    if (item != null) {
                        nmsItems.add(((CanaryItem) item).getHandle());
                    } else {
                        nmsItems.add(null);
                    }
                }
                return new CanaryPacket(new Packet104WindowItems((Integer) args[0], nmsItems));
            case 0x69:
                if (args.length < 3) {
                    throw new IllegalArgumentException(String.format(toofewargs, 3, args.length));
                }
                return new CanaryPacket(new Packet105UpdateProgressbar((Integer) args[0], (Integer) args[1], (Integer) args[2]));
            case 0x6A:
                throw new InvalidPacketConstructionException(106, "0x6A", "Confirm Transaction", "No effect");
            case 0x6B:
                throw new InvalidPacketConstructionException(107, "0x6B", "Creative Inventory Action", "No available constructor.");
            case 0x6C:
                throw new InvalidPacketConstructionException(108, "0x6C", "Enchant Item", "Client to Server only.");
//          case 0x6D: // There is currently no Packet 109
//          case 0x6E: // There is currently no Packet 110
//          case 0x6F: // There is currently no Packet 111
//          case 0x70: // There is currently no Packet 112
//          case 0x71: // There is currently no Packet 113
//          case 0x72: // There is currently no Packet 114
//          case 0x73: // There is currently no Packet 115
//          case 0x74: // There is currently no Packet 116
//          case 0x75: // There is currently no Packet 117
//          case 0x76: // There is currently no Packet 118
//          case 0x77: // There is currently no Packet 119
//          case 0x78: // There is currently no Packet 120
//          case 0x79: // There is currently no Packet 121
//          case 0x7A: // There is currently no Packet 122
//          case 0x7B: // There is currently no Packet 123
//          case 0x7C: // There is currently no Packet 124
//          case 0x7D: // There is currently no Packet 125
//          case 0x7E: // There is currently no Packet 126
//          case 0x7F: // There is currently no Packet 127
//          case 0x80: // There is currently no Packet 128
//          case 0x81: // There is currently no Packet 129
            case 0x82:
                if (args.length < 4) {
                    throw new IllegalArgumentException(String.format(toofewargs, 4, args.length));
                }
                return new CanaryPacket(new Packet130UpdateSign((Integer) args[0], (Integer) args[1], (Integer) args[2], (String[]) args[3]));
            case 0x83:
                if (args.length < 3) {
                    throw new IllegalArgumentException(String.format(toofewargs, 3, args.length));
                }
                return new CanaryPacket(new Packet131MapData((Short) args[0], (Short) args[1], (byte[]) args[2]));
            case 0x84:
                if (args.length < 5) {
                    throw new IllegalArgumentException(String.format(toofewargs, 5, args.length));
                }
                return new CanaryPacket(new Packet132TileEntityData((Integer) args[0], (Integer) args[1], (Integer) args[2], (Integer) args[3], ((CanaryCompoundTag) args[4]).getHandle()));
            case 0x85:
                if (args.length < 4) {
                    throw new IllegalArgumentException(String.format(toofewargs, 4, args.length));
                }
                return new CanaryPacket(new Packet133TileEditorOpen((Integer) args[0], (Integer) args[1], (Integer) args[2], (Integer) args[3]));
//          case 0x86: // There is currently no Packet 134
//          case 0x87: // There is currently no Packet 135
//          case 0x88: // There is currently no Packet 136
//          case 0x89: // There is currently no Packet 137
//          case 0x8A: // There is currently no Packet 138
//          case 0x8B: // There is currently no Packet 139
//          case 0x8C: // There is currently no Packet 140
//          case 0x8D: // There is currently no Packet 141
//          case 0x8E: // There is currently no Packet 142
//          case 0x8F: // There is currently no Packet 143
//          case 0x90: // There is currently no Packet 144
//          case 0x91: // There is currently no Packet 145
//          case 0x92: // There is currently no Packet 146
//          case 0x93: // There is currently no Packet 147
//          case 0x94: // There is currently no Packet 148
//          case 0x95: // There is currently no Packet 149
//          case 0x96: // There is currently no Packet 150
//          case 0x97: // There is currently no Packet 151
//          case 0x98: // There is currently no Packet 152
//          case 0x99: // There is currently no Packet 153
//          case 0x9A: // There is currently no Packet 154
//          case 0x9B: // There is currently no Packet 155
//          case 0x9C: // There is currently no Packet 156
//          case 0x9D: // There is currently no Packet 157
//          case 0x9E: // There is currently no Packet 158
//          case 0x9F: // There is currently no Packet 159
//          case 0xA0: // There is currently no Packet 160
//          case 0xA1: // There is currently no Packet 161
//          case 0xA2: // There is currently no Packet 162
//          case 0xA3: // There is currently no Packet 163
//          case 0xA4: // There is currently no Packet 164
//          case 0xA5: // There is currently no Packet 165
//          case 0xA6: // There is currently no Packet 166
//          case 0xA7: // There is currently no Packet 167
//          case 0xA8: // There is currently no Packet 168
//          case 0xA9: // There is currently no Packet 169
//          case 0xAA: // There is currently no Packet 170
//          case 0xAB: // There is currently no Packet 171
//          case 0xAC: // There is currently no Packet 172
//          case 0xAD: // There is currently no Packet 173
//          case 0xAE: // There is currently no Packet 174
//          case 0xAF: // There is currently no Packet 175
//          case 0xB0: // There is currently no Packet 176
//          case 0xB1: // There is currently no Packet 177
//          case 0xB2: // There is currently no Packet 178
//          case 0xB3: // There is currently no Packet 179
//          case 0xB4: // There is currently no Packet 180
//          case 0xB5: // There is currently no Packet 181
//          case 0xB6: // There is currently no Packet 182
//          case 0xB7: // There is currently no Packet 183
//          case 0xB8: // There is currently no Packet 184
//          case 0xB9: // There is currently no Packet 185
//          case 0xBA: // There is currently no Packet 186
//          case 0xBB: // There is currently no Packet 187
//          case 0xBC: // There is currently no Packet 188
//          case 0xBD: // There is currently no Packet 189
//          case 0xBE: // There is currently no Packet 190
//          case 0xBF: // There is currently no Packet 191
//          case 0xC0: // There is currently no Packet 192
//          case 0xC1: // There is currently no Packet 193
//          case 0xC2: // There is currently no Packet 194
//          case 0xC3: // There is currently no Packet 195
//          case 0xC4: // There is currently no Packet 196
//          case 0xC5: // There is currently no Packet 197
//          case 0xC6: // There is currently no Packet 198
//          case 0xC7: // There is currently no Packet 199
            case 0xC8:
                if (args.length < 2) {
                    throw new IllegalArgumentException(String.format(toofewargs, 2, args.length));
                }
                return new CanaryPacket(new Packet200Statistic((Integer)args[0], (Integer)args[1]));
            case 0xC9:
                if (args.length < 3) {
                    throw new IllegalArgumentException(String.format(toofewargs, 3, args.length));
                }
                return new CanaryPacket(new Packet201PlayerInfo((String)args[0], (Boolean)args[1], (Integer)args[2]));
            case 0xCA:
                throw new InvalidPacketConstructionException(202, "0xCA", "Player Abilities", "Use Player#updateCapabilities instead.");
            case 0xCB:
                throw new InvalidPacketConstructionException(203, "0xCB", "AutoComplete", "No effect outside intended use in command system.");
            case 0xCC:
                throw new InvalidPacketConstructionException(204, "0xCC", "Client Settings", "Client to Server only.");
            case 0xCD:
                throw new InvalidPacketConstructionException(205, "0xCD", "Client Statuses", "Client to Server only.");
            case 0xCE:
                throw new InvalidPacketConstructionException(206, "0xCE", "Scoreboard Objective", "Use the Scoreboard API instead.");
            case 0xCF:
                throw new InvalidPacketConstructionException(207, "0xCF", "Update Score", "Use the Scoreboard API instead.");
            case 0xD0:
                throw new InvalidPacketConstructionException(208, "0xD0", "Display Scoreboard", "Use the Scoreboard API instead.");
            case 0xD1:
                throw new InvalidPacketConstructionException(209, "0xD1", "Teams", "Use the Scoreboard API instead.");
//          case 0xD2: // There is currently no Packet 210
//          case 0xD3: // There is currently no Packet 211
//          case 0xD4: // There is currently no Packet 212
//          case 0xD5: // There is currently no Packet 213
//          case 0xD6: // There is currently no Packet 214
//          case 0xD7: // There is currently no Packet 215
//          case 0xD8: // There is currently no Packet 216
//          case 0xD9: // There is currently no Packet 217
//          case 0xDA: // There is currently no Packet 218
//          case 0xDB: // There is currently no Packet 219
//          case 0xDC: // There is currently no Packet 220
//          case 0xDD: // There is currently no Packet 221
//          case 0xDE: // There is currently no Packet 222
//          case 0xDF: // There is currently no Packet 223
//          case 0xE0: // There is currently no Packet 224
//          case 0xE1: // There is currently no Packet 225
//          case 0xE2: // There is currently no Packet 226
//          case 0xE3: // There is currently no Packet 227
//          case 0xE4: // There is currently no Packet 228
//          case 0xE5: // There is currently no Packet 229
//          case 0xE6: // There is currently no Packet 230
//          case 0xE7: // There is currently no Packet 231
//          case 0xE8: // There is currently no Packet 232
//          case 0xE9: // There is currently no Packet 233
//          case 0xEA: // There is currently no Packet 234
//          case 0xEB: // There is currently no Packet 235
//          case 0xEC: // There is currently no Packet 236
//          case 0xED: // There is currently no Packet 237
//          case 0xEE: // There is currently no Packet 238
//          case 0xEF: // There is currently no Packet 239
//          case 0xF0: // There is currently no Packet 240
//          case 0xF1: // There is currently no Packet 241
//          case 0xF2: // There is currently no Packet 242
//          case 0xF3: // There is currently no Packet 243
//          case 0xF4: // There is currently no Packet 244
//          case 0xF5: // There is currently no Packet 245
//          case 0xF6: // There is currently no Packet 246
//          case 0xF7: // There is currently no Packet 247
//          case 0xF8: // There is currently no Packet 248
//          case 0xF9: // There is currently no Packet 249
            case 0xFA:
                throw new InvalidPacketConstructionException(250, "0xFA", "Plugin Message", "Use ChannelManager instead.");
//          case 0xFB:  // There is currently no Packet 251
            case 0xFC:
                throw new InvalidPacketConstructionException(252, "0xFC", "Encryption Key Response", "Encryption packets are best left to the native code.");
            case 0xFD:
                throw new InvalidPacketConstructionException(253, "0xFD", "Encryption Key Request", "Encryption packets are best left to the native code.");
            case 0xFE:
                throw new InvalidPacketConstructionException(254, "0xFE", "Server List Ping", "Client to Server only.");
            case 0xFF:
                return new CanaryPacket(new Packet255KickDisconnect((String) args[0]));
            default:
                throw new IllegalArgumentException("Unknown/Unimplemented Packet ID");
        }
    }

    @Override
    public Packet updateTime(long world_age, long time) {
        try {
            return createPacket(4, world_age, time);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a UpdateTime packet", ex);
            return null;
        }
    }

    @Override
    public Packet playerEquipment(int entityID, int slot, Item item) {
        try {
            return createPacket(5, entityID, slot, item);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a PlayerEquipment packet", ex);
            return null;
        }
    }

    @Override
    public Packet spawnPosition(int x, int y, int z) {
        try {
            return createPacket(6, x, y, z);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a SpawnPosition packet", ex);
            return null;
        }
    }

    @Override
    public Packet updateHealth(float health, int foodLevel, float saturation) {
        try {
            return createPacket(8, health, foodLevel, saturation);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a UpdateHealth packet", ex);
            return null;
        }
    }

    @Override
    public Packet playerPositionLook(double x, double stance, double y, double z, float yaw, float pitch, boolean onGround) {
        try {
            return createPacket(13, x, stance, y, z, yaw, pitch, onGround);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a PlayerPositionLook packet", ex);
            return null;
        }
    }

    @Override
    public Packet heldItemChange(int slot) {
        try {
            return createPacket(16, slot);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a HeldItemChange packet", ex);
            return null;
        }
    }

    @Override
    public Packet useBed(Player player, int x, int y, int z) {
        try {
            return createPacket(17, player, x, y, z);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a UseBed packet", ex);
            return null;
        }
    }

    @Override
    public Packet animation(Player player, int animation) {
        try {
            return createPacket(18, player, animation);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a Animation packet", ex);
            return null;
        }
    }

    @Override
    public Packet spawnNamedEntity(Human human) {
        try {
            return createPacket(20, human);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a SpawnNamedEntity packet", ex);
            return null;
        }
    }

    @Override
    public Packet collectItem(int entityItemID, int collectorID) {
        try {
            return createPacket(22, entityItemID, collectorID);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a CollectItem packet", ex);
            return null;
        }
    }

    @Override
    public Packet spawnObjectVehicle(Entity entity, int objectID, int throwerID) {
        try {
            return createPacket(23, entity, objectID, throwerID);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a SpawnObjectVehicle packet", ex);
            return null;
        }
    }

    @Override
    public Packet spawnMob(LivingBase livingbase) {
        try {
            return createPacket(24, livingbase);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a SpawnMob packet", ex);
            return null;
        }
    }

    @Override
    public Packet spawnPainting(Painting painting) {
        try {
            return createPacket(25, painting);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a SpawnPainting packet", ex);
            return null;
        }
    }

    @Override
    public Packet spawnXPOrb(XPOrb xporb) {
        try {
            return createPacket(26, xporb);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a SpawnXPOrb packet", ex);
            return null;
        }
    }

    @Override
    public Packet entityVelocity(int entityID, double motX, double motY, double motZ) {
        try {
            return createPacket(28, entityID, motX, motY, motZ);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a EntityVelocity packet", ex);
            return null;
        }
    }

    @Override
    public Packet destroyEntity(int... ids) {
        try {
            return createPacket(29, ids);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a DestroyEntity packet", ex);
            return null;
        }
    }

    @Override
    public Packet entityRelativeMove(int entityID, byte x, byte y, byte z) {
        try {
            return createPacket(31, entityID, x, y, z);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a EntityRelativeMove packet", ex);
            return null;
        }
    }

    @Override
    public Packet entityLook(int entityID, byte yaw, byte pitch) {
        try {
            return createPacket(32, entityID, yaw, pitch);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a EntityLook packet", ex);
            return null;
        }
    }

    @Override
    public Packet entityLookRelativeMove(int entityID, byte x, byte y, byte z, byte yaw, byte pitch) {
        try {
            return createPacket(33, entityID, x, y, z, yaw, pitch);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a EntityLookRelativeMove packet", ex);
            return null;
        }
    }

    @Override
    public Packet entityTeleport(int entityID, int x, int y, int z, byte yaw, byte pitch) {
        try {
            return createPacket(34, entityID, x, y, z, yaw, pitch);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a EntityTeleport packet", ex);
            return null;
        }
    }

    @Override
    public Packet entityHeadLook(int entityID, byte yaw) {
        try {
            return createPacket(35, entityID, yaw);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a EntityHeadLook packet", ex);
            return null;
        }
    }

    @Override
    public Packet entityStatus(int entityID, byte status) {
        try {
            return createPacket(38, entityID, status);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a EntityStatus packet", ex);
            return null;
        }
    }

    @Override
    public Packet attachEntity(int leashId, Entity attaching, Entity vehicle) {
        try {
            return createPacket(39, leashId, attaching, vehicle);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a AttachEntity packet", ex);
            return null;
        }
    }

    @Override
    public Packet entityMetaData(int entityID, DataWatcher watcher) {
        try {
            return createPacket(40, entityID, watcher);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a EntityMetaData packet", ex);
            return null;
        }
    }

    @Override
    public Packet entityEffect(int entityID, PotionEffect effect) {
        try {
            return createPacket(41, entityID, effect);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a EntityEffect packet", ex);
            return null;
        }
    }

    @Override
    public Packet removeEntityEffect(int entityID, PotionEffect effect) {
        try {
            return createPacket(42, entityID, effect);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a RemoveEntityEffect packet", ex);
            return null;
        }
    }

    @Override
    public Packet setExperience(float bar, int level, int totalXp) {
        try {
            return createPacket(43, bar, level, totalXp);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a SetExperience packet", ex);
            return null;
        }
    }

    @Override
    public Packet chunkData(Chunk chunk, boolean initialize, int bitflag) {
        try {
            return createPacket(51, chunk, initialize, bitflag);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a ChunkData packet", ex);
            return null;
        }
    }

    @Override
    public Packet multiBlockChange(int chunkX, int chunkZ, short[] chunkBlocks, int size, World world) {
        try {
            return createPacket(52, chunkX, chunkZ, chunkBlocks, size, world);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a MultiBlockChange packet", ex);
            return null;
        }
    }

    @Override
    public Packet blockChange(int x, int y, int z, int typeId, int data) {
        try {
            return createPacket(53, x, y, z, typeId, data);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a BlockChange packet", ex);
            return null;
        }
    }

    @Override
    public Packet blockAction(int x, int y, int z, int stat1, int stat2, int targetId) {
        try {
            return createPacket(54, x, y, z, stat1, stat2, targetId);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a BlockAction packet", ex);
            return null;
        }
    }

    @Override
    public Packet blockBreakAnimation(int entityId, int x, int y, int z, int state) {
        try {
            return createPacket(55, entityId, x, y, z, state);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a BlockBreakAnimation packet", ex);
            return null;
        }
    }

    @Override
    public Packet mapChunkBulk(List<Chunk> chunks) {
        try {
            return createPacket(56, chunks);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a MapChunkBulk packet", ex);
            return null;
        }
    }

    @Override
    public Packet explosion(double explodeX, double explodeY, double explodeZ, float power, List<Position> affectedPositions, Vector3D playerVelocity) {
        try {
            return createPacket(60, explodeX, explodeY, explodeZ, power, affectedPositions, playerVelocity);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a Explosion packet", ex);
            return null;
        }
    }

    @Override
    public Packet soundParticleEffect(int sfxID, int x, int y, int z, int aux, boolean disableRelVol) {
        try {
            return createPacket(61, sfxID, x, y, z, aux, disableRelVol);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a SoundParticleEffect packet", ex);
            return null;
        }
    }

    @Override
    public Packet namedSoundEffect(String name, double x, double y, double z, float volume, float pitch) {
        try {
            return createPacket(62, name, x, y, z, volume, pitch);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a NamedSoundEffect packet", ex);
            return null;
        }
    }

    @Override
    public Packet changeGameState(int state, int mode) {
        try {
            return createPacket(70, state, mode);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a ChangeGameState packet", ex);
            return null;
        }
    }

    @Override
    public Packet spawnGlobalEntity(Entity entity) {
        try {
            return createPacket(71, entity);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a UpdateTime packet", ex);
            return null;
        }
    }

    @Override
    public Packet openWindow(int windowId, int type, String title, int slots, boolean useTitle) {
        try {
            return createPacket(100, windowId, type, title, slots, useTitle);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a OpenWindow packet", ex);
            return null;
        }
    }

    @Override
    public Packet closeWindow(int windowId) {
        try {
            return createPacket(101, windowId);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a CloseWindow packet", ex);
            return null;
        }
    }

    @Override
    public Packet setSlot(int windowId, int slotId, Item item) {
        try {
            return createPacket(103, windowId, slotId, item);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a SetSlot packet", ex);
            return null;
        }
    }

    @Override
    public Packet setWindowItems(int windowId, List<Item> items) {
        try {
            return createPacket(104, windowId, items);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a SetWindowItems packet", ex);
            return null;
        }
    }

    @Override
    public Packet updateWindowProperty(int windowId, int bar, int value) {
        try {
            return createPacket(105, windowId, bar, value);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a UpdateWindowProperty packet", ex);
            return null;
        }
    }

    @Override
    public Packet updateSign(int x, int y, int z, String[] text) {
        try {
            return createPacket(130, x, y, z, text);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a UpdateSign packet", ex);
            return null;
        }
    }

    @Override
    public Packet itemData(short itemId, short uniqueId, byte[] data) {
        try {
            return createPacket(131, itemId, uniqueId, data);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a ItemData packet", ex);
            return null;
        }
    }

    @Override
    public Packet updateTileEntity(int x, int y, int z, int action, CompoundTag compoundTag) {
        try {
            return createPacket(132, x, y, z, action, compoundTag);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a UpdateTileEntity packet", ex);
            return null;
        }
    }

    @Override
    public Packet tileEditorOpen(int id, int x, int y, int z) {
        try {
            return createPacket(133, id, x, y, z);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a TileEditorOpen packet", ex);
            return null;
        }
    }

    @Override
    public Packet incrementStatistic(int statId, int amount) {
        try {
            return createPacket(200, statId, amount);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a IncrementStatistic packet", ex);
            return null;
        }
    }

    @Override
    public Packet playerInfo(String name, boolean connected, int ping) {
        try {
            return createPacket(201, name, connected, ping);
        } catch (Exception ex) {
            Canary.logDebug("Failed to construct a PlayerInfo packet", ex);
            return null;
        }
    }
}
