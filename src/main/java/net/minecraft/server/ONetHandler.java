package net.minecraft.server;

import net.minecraft.server.OPacket;
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
import net.minecraft.server.OPacket130UpdateSign;
import net.minecraft.server.OPacket131MapData;
import net.minecraft.server.OPacket132TileEntityData;
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

public abstract class ONetHandler {

    public ONetHandler() {
        super();
    }

    public abstract boolean c();

    public void a(OPacket51MapChunk var1) {
    }

    public void a(OPacket var1) {
    }

    public void a(String var1, Object[] var2) {
    }

    public void a(OPacket255KickDisconnect var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket1Login var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket10Flying var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket52MultiBlockChange var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket14BlockDig var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket53BlockChange var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket50PreChunk var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket20NamedEntitySpawn var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket30Entity var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket34EntityTeleport var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket15Place var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket16BlockItemSwitch var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket29DestroyEntity var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket21PickupSpawn var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket22Collect var1) {
        this.a((OPacket) var1);
    }

    public void playerChat(OPacket3Chat var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket23VehicleSpawn var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket18Animation var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket19EntityAction var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket2Handshake var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket24MobSpawn var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket4UpdateTime var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket6SpawnPosition var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket28EntityVelocity var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket40EntityMetadata var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket39AttachEntity var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket7UseEntity var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket38EntityStatus var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket8UpdateHealth var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket9Respawn var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket60Explosion var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket100OpenWindow var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket101CloseWindow var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket102WindowClick var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket103SetSlot var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket104WindowItems var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket130UpdateSign var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket105UpdateProgressbar var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket5PlayerInventory var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket106Transaction var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket25EntityPainting var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket54PlayNoteBlock var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket200Statistic var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket17Sleep var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket70Bed var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket71Weather var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket131MapData var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket61DoorChange var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket254ServerPing var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket41EntityEffect var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket42RemoveEntityEffect var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket201PlayerInfo var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket0KeepAlive var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket43Experience var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket107CreativeSetSlot var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket26EntityExpOrb var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket108EnchantItem var1) {
    }

    public void a(OPacket250CustomPayload var1) {
    }

    public void a(OPacket35EntityHeadRotation var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket132TileEntityData var1) {
        this.a((OPacket) var1);
    }

    public void a(OPacket202PlayerAbilities var1) {
        this.a((OPacket) var1);
    }
}
