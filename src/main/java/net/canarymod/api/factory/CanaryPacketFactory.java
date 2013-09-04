package net.canarymod.api.factory;

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
import net.canarymod.api.packet.CanaryPacket;
import net.canarymod.api.packet.InvalidPacketConstructionException;
import net.canarymod.api.packet.Packet;
import net.canarymod.api.potion.CanaryPotionEffect;
import net.canarymod.api.potion.PotionEffect;
import net.minecraft.server.EnumGameType;
import net.minecraft.server.Packet13PlayerLookMove;
import net.minecraft.server.Packet16BlockItemSwitch;
import net.minecraft.server.Packet17Sleep;
import net.minecraft.server.Packet18Animation;
import net.minecraft.server.Packet20NamedEntitySpawn;
import net.minecraft.server.Packet22Collect;
import net.minecraft.server.Packet23VehicleSpawn;
import net.minecraft.server.Packet24MobSpawn;
import net.minecraft.server.Packet25EntityPainting;
import net.minecraft.server.Packet26EntityExpOrb;
import net.minecraft.server.Packet28EntityVelocity;
import net.minecraft.server.Packet29DestroyEntity;
import net.minecraft.server.Packet31RelEntityMove;
import net.minecraft.server.Packet32EntityLook;
import net.minecraft.server.Packet33RelEntityMoveLook;
import net.minecraft.server.Packet34EntityTeleport;
import net.minecraft.server.Packet35EntityHeadRotation;
import net.minecraft.server.Packet38EntityStatus;
import net.minecraft.server.Packet39AttachEntity;
import net.minecraft.server.Packet40EntityMetadata;
import net.minecraft.server.Packet41EntityEffect;
import net.minecraft.server.Packet42RemoveEntityEffect;
import net.minecraft.server.Packet43Experience;
import net.minecraft.server.Packet4UpdateTime;
import net.minecraft.server.Packet5PlayerInventory;
import net.minecraft.server.Packet6SpawnPosition;
import net.minecraft.server.Packet8UpdateHealth;
import net.minecraft.server.Packet9Respawn;

/**
 * Packet Factory implementation
 *
 * @author Jason (darkdiplomat)
 */
public class CanaryPacketFactory implements PacketFactory {
    private final static String toofewargs = "Not enough arguments (Expected: %d Got: %d)";

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
            // case 0x15: // There is currently no Packet 21 (0x15)
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
            // case 0x24: // There is currently no Packet 36 (0x24)
            // case 0x25: // There is currently no Packet 37 (0x25)
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
                // case 0x2D: // There is currently no Packet 45 (0x2D)
                // case 0x2E: // There is currently no Packet 46 (0x2E)
                // case 0x2F: // There is currently no Packet 47 (0x2F)
                // case 0x30: // There is currently no Packet 48 (0x30)
                // case 0x31: // There is currently no Packet 49 (0x31)
                // case 0x32: // There is currently no Packet 50 (0x32)
            case 0x33:
            case 0x34:
            case 0x35:
            case 0x36:
            case 0x37:
            case 0x38:
                throw new IllegalArgumentException("Unimplemented Packet ID");
                // case 0x39: // There is currently no Packet 57 (0x39)
                // case 0x3A: // There is currently no Packet 58 (0x3A)
                // case 0x3B: // There is currently no Packet 59 (0x3B)
            case 0x3C:
            case 0x3D:
            case 0x3E:
            case 0x3F:
                throw new IllegalArgumentException("Unimplemented Packet ID");
                // case 0x40: // There is currently no Packet 64 (0x40)
                // case 0x41: // There is currently no Packet 65 (0x41)
                // case 0x42: // There is currently no Packet 66 (0x42)
                // case 0x43: // There is currently no Packet 67 (0x43)
                // case 0x44: // There is currently no Packet 68 (0x44)
                // case 0x45: // There is currently no Packet 69 (0x45)
            case 0x46:
            case 0x47:
                throw new IllegalArgumentException("Unimplemented Packet ID");
            case 0x64:
            case 0x65:
                throw new IllegalArgumentException("Unimplemented Packet ID");
            case 0x66:
                throw new InvalidPacketConstructionException(102, "0x66", "Click Window", "Client to Server only.");
            case 0x6C:
                throw new InvalidPacketConstructionException(108, "0x6C", "Enchant Item", "Client to Server only.");
            case 0xCC:
                throw new InvalidPacketConstructionException(0xCC, "0xCC", "Client Settings", "Client to Server only.");
            case 0xCD:
                throw new InvalidPacketConstructionException(0xCD, "0xCD", "Client Statuses", "Client to Server only.");
            case 0xFE:
                throw new InvalidPacketConstructionException(254, "0xFE", "Server List Ping", "Client to Server only.");
            default:
                throw new IllegalArgumentException("Unknown/Unimplemented Packet ID");
        }
    }

    @Override
    public Packet updateTime(long world_age, long time) {
        try {
            return createPacket(4, world_age, time);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet playerEquipment(int entityID, int slot, Item item) {
        try {
            return createPacket(5, entityID, slot, item);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet spawnPosition(int x, int y, int z) {
        try {
            return createPacket(6, x, y, z);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet updateHealth(float health, int foodLevel, float saturation) {
        try {
            return createPacket(8, health, foodLevel, saturation);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet playerPositionLook(double x, double stance, double y, double z, float yaw, float pitch, boolean onGround) {
        try {
            return createPacket(13, x, stance, y, z, yaw, pitch, onGround);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet heldItemChange(int slot) {
        try {
            return createPacket(16, slot);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet useBed(Player player, int x, int y, int z) {
        try {
            return createPacket(17, player, x, y, z);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet animation(Player player, int animation) {
        try {
            return createPacket(18, player, animation);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet spawnNamedEntity(Human human) {
        try {
            return createPacket(20, human);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet collectItem(int entityItemID, int collectorID) {
        try {
            return createPacket(22, entityItemID, collectorID);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet spawnObjectVehicle(Entity entity, int objectID, int throwerID) {
        try {
            return createPacket(23, entity, objectID, throwerID);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet spawnMob(LivingBase livingbase) {
        try {
            return createPacket(24, livingbase);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet spawnPainting(Painting painting) {
        try {
            return createPacket(25, painting);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet spawnXPOrb(XPOrb xporb) {
        try {
            return createPacket(26, xporb);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet entityVelocity(int entityID, double motX, double motY, double motZ) {
        try {
            return createPacket(28, entityID, motX, motY, motZ);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet destroyEntity(int... ids) {
        try {
            return createPacket(29, ids);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet entityRelativeMove(int entityID, byte x, byte y, byte z) {
        try {
            return createPacket(31, entityID, x, y, z);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet entityLook(int entityID, byte yaw, byte pitch) {
        try {
            return createPacket(32, entityID, yaw, pitch);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet entityLookRelativeMove(int entityID, byte x, byte y, byte z, byte yaw, byte pitch) {
        try {
            return createPacket(33, entityID, x, y, z, yaw, pitch);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet entityTeleport(int entityID, int x, int y, int z, byte yaw, byte pitch) {
        try {
            return createPacket(34, entityID, x, y, z, yaw, pitch);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet entityHeadLook(int entityID, byte yaw) {
        try {
            return createPacket(35, entityID, yaw);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet entityStatus(int entityID, byte status) {
        try {
            return createPacket(38, entityID, status);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet attachEntity(int leashId, Entity attaching, Entity vehicle) {
        try {
            return createPacket(39, leashId, attaching, vehicle);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet entityMetaData(int entityID, DataWatcher watcher) {
        try {
            return createPacket(40, entityID, watcher);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet entityEffect(int entityID, PotionEffect effect) {
        try {
            return createPacket(41, entityID, effect);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet removeEntityEffect(int entityID, PotionEffect effect) {
        try {
            return createPacket(42, entityID, effect);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Packet setExperience(float bar, int level, int totalXp) {
        try {
            return createPacket(43, bar, level, totalXp);
        } catch (Exception ex) {
            return null;
        }
    }
}
