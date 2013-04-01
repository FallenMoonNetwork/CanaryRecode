package net.canarymod.api.entity.living.humanoid;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import net.canarymod.CanaryMod;
import net.canarymod.api.CanaryServer;
import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.position.Location;
import net.minecraft.server.DamageSource;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.INetworkManager;
import net.minecraft.server.ItemInWorldManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NetHandler;
import net.minecraft.server.NetServerHandler;
import net.minecraft.server.Packet;
import net.minecraft.server.Packet0KeepAlive;
import net.minecraft.server.Packet101CloseWindow;
import net.minecraft.server.Packet102WindowClick;
import net.minecraft.server.Packet106Transaction;
import net.minecraft.server.Packet107CreativeSetSlot;
import net.minecraft.server.Packet108EnchantItem;
import net.minecraft.server.Packet10Flying;
import net.minecraft.server.Packet130UpdateSign;
import net.minecraft.server.Packet14BlockDig;
import net.minecraft.server.Packet15Place;
import net.minecraft.server.Packet16BlockItemSwitch;
import net.minecraft.server.Packet18Animation;
import net.minecraft.server.Packet19EntityAction;
import net.minecraft.server.Packet202PlayerAbilities;
import net.minecraft.server.Packet203AutoComplete;
import net.minecraft.server.Packet204ClientInfo;
import net.minecraft.server.Packet205ClientCommand;
import net.minecraft.server.Packet250CustomPayload;
import net.minecraft.server.Packet255KickDisconnect;
import net.minecraft.server.Packet3Chat;
import net.minecraft.server.Packet7UseEntity;
import net.minecraft.server.Packet9Respawn;

public final class EntityNonPlayableCharacter extends EntityPlayerMP {
    private static MinecraftServer mcserv = ((CanaryServer) CanaryMod.getServer()).getHandle();

    public EntityNonPlayableCharacter(String name, Location location) {
        super(mcserv, ((CanaryWorld) location.getWorld()).getHandle(), name, new ItemInWorldManager(((CanaryWorld) location.getWorld()).getHandle()));
        this.a = new NonNetServerHandler(this);
        this.a(location.getX(), location.getY(), location.getZ(), location.getRotation(), location.getPitch());
    }
    @Override
    public void c(int i0) {
        // NO PORTAL USE
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        // NO NBTTag yet
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {
        // NO NBTTag yet
    }

    @Override
    public void l_() {
        super.l_();
        ((CanaryNonPlayableCharacter) entity).update();
    }
    
    @Override
    public void a(DamageSource damagesource) {
        this.b.ad().k(this.bt.b());
        if (!this.q.M().b("keepInventory")) {
            this.bK.m();
        }

        // Skip ScoreBoard stuff

        EntityLiving entityliving = this.bN();

        if (entityliving != null) {
            entityliving.c(this, this.aM);
        }
    }

    public boolean a_(EntityPlayer entityplayer) { // RightClicked
        if(((CanaryEntityLiving)entityplayer.getCanaryEntity()).isPlayer()){
            ((CanaryNonPlayableCharacter) entity).clicked(((EntityPlayerMP) entityplayer).getPlayer());
            return true;
        }
        return false;
    }

    void setNPC(CanaryNonPlayableCharacter cnpc){
        this.entity = cnpc;
    }

    @Override
    public CanaryPlayer getPlayer() {
        return null; // Not a Player now are we
    }

    /**
     * Override the NetServerHandler for NonPlayableCharacters.
     * 
     * @author Jason (darkdiplomat)
     */
    private final static class NonNetServerHandler extends NetServerHandler {

        private final static NonNetworkManager herp = new NonNetworkManager();

        public NonNetServerHandler(EntityNonPlayableCharacter npc) {
            super(mcserv, herp, npc);
        }

        public void d(){}

        public void c(String s){}

        public void a(Packet10Flying opacket10flying) {}

        @SuppressWarnings("unused")
        public void a(double d0, double d1, double d2, float f, float f1){
            this.c.a(d0, d1, d2, f, f1);
        }

        public void a(Packet14BlockDig opacket14blockdig) {}

        public void a(Packet15Place opacket15place) {}

        public void a(String s, Object[] aobject){}

        public void a(Packet opacket) {}

        public void b(Packet opacket) {}

        public void a(Packet16BlockItemSwitch opacket16blockitemswitch) {}

        public void a(Packet3Chat opacket3chat) {}

        public void a(Packet18Animation opacket18animation) {}

        public void a(Packet19EntityAction opacket19entityaction) {}

        public void a(Packet255KickDisconnect opacket255kickdisconnect) {}

        public int e(){
            return -1;
        }

        public void a(Packet7UseEntity opacket7useentity) {}

        public void a(Packet205ClientCommand opacket205clientcommand) {}

        public boolean b(){
            return true;
        }

        public void a(Packet9Respawn opacket9respawn) {}

        public void a(Packet101CloseWindow opacket101closewindow) {}

        public void a(Packet102WindowClick opacket102windowclick) {}

        public void a(Packet108EnchantItem opacket108enchantitem) {}

        public void a(Packet107CreativeSetSlot opacket107creativesetslot) {}

        public void a(Packet106Transaction opacket106transaction) {}

        public void a(Packet130UpdateSign opacket130updatesign) {}

        public void a(Packet0KeepAlive opacket0keepalive) {}

        public boolean a(){
            return true;
        }

        public void a(Packet202PlayerAbilities opacket202playerabilities) {}

        public void a(Packet203AutoComplete opacket203autocomplete) {}

        public void a(Packet204ClientInfo opacket204clientinfo) {}

        public void a(Packet250CustomPayload opacket250custompayload) {}
    }

    /**
     * Overrides the INetworkManager of NonPlayableCharacters
     * 
     * @author Jason (darkdiplomat)
     */
    private final static class NonNetworkManager implements INetworkManager {

        private final SocketAddress herp = new InetSocketAddress("127.0.0.1", 0);

        @Override
        public void a(){}

        @Override
        public void a(NetHandler arg0) {}

        @Override
        public void a(Packet arg0) {}

        @Override
        public void a(String arg0, Object... arg1){}

        @Override
        public void b(){}

        @Override
        public SocketAddress c(){
            return herp;
        }

        @Override
        public void d(){}

        @Override
        public int e(){
            return 0;
        }
    }
}