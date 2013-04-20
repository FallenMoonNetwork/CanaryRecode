package net.canarymod.api.entity.living.humanoid;


import java.net.InetSocketAddress;
import java.net.SocketAddress;
import net.canarymod.CanaryMod;
import net.canarymod.api.CanaryServer;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.position.Location;
import net.minecraft.server.Block;
import net.minecraft.server.DamageSource;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityPlayerMP;
import net.minecraft.server.INetworkManager;
import net.minecraft.server.ItemInWorldManager;
import net.minecraft.server.MathHelper;
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
    protected boolean isJumping;

    public EntityNonPlayableCharacter(String name, Location location) {
        super(mcserv, ((CanaryWorld) location.getWorld()).getHandle(), name, new ItemInWorldManager(((CanaryWorld) location.getWorld()).getHandle()));
        this.a = new NonNetServerHandler(this);
        this.a(location.getX(), location.getY(), location.getZ(), location.getRotation(), location.getPitch());
    }

    @Override
    public void c(int i0) {// NO PORTAL USE
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {// NO NBTTag yet
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {// NO NBTTag yet
    }

    @Override
    public void l_() {
        if (!this.isJumping || this.q.getCanaryWorld().getBlockAt((int)(this.u + x), (int)(this.v + y - 1), (int)(this.w + z)).getData() == 0) {
            this.y = -1;
        }
        super.l_();
        if (this.isJumping) {
            this.isJumping = false;
            this.y = -2;
            this.ce.a = true;
            this.e(0.0F, 0.0F);
            this.ce.a = false;
        }
        super.x(); // EntityLiving.x() fixes damage taking (no idea yet as to why x() isn't called normally)
        if (this.entity != null) {
            if (!this.M) {
                try {
                    ((CanaryNonPlayableCharacter) entity).update();
                } catch (Exception ex) {// For some reason an exception gets thrown after death and nulled CanaryEntity...
                }
            }
        }
    }

//    @Override
//    public void c() {
//        try {
//            if (!this.F) {
//                if (!(this.y < 0.0F)) {
//                    Canary.logInfo(String.valueOf(this.getPlayer().getWorld().getBlockAt((int)(u + x), (int)(v + y - 1), (int)(w + z)).getData())+"blockinfo");
//                    if (this.getPlayer().getWorld().getBlockAt((int)(u + x), (int)(v + y - 1), (int)(w + z)).getData() == 0) {
//                        this.y = -1.0F;
//                    }
//                }
//            }
//        } catch(Exception ex) {
//        }
//        super.c();
//    }

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

    @Override
    public boolean a_(EntityPlayer entityplayer) { // RightClicked
        if (this.entity != null) {
            if (((CanaryEntityLiving) entityplayer.getCanaryEntity()).isPlayer()) {
                ((CanaryNonPlayableCharacter) entity).clicked(((EntityPlayerMP) entityplayer).getPlayer());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean a(DamageSource damagesource, int i0) {
        boolean toRet = super.a(damagesource, i0);

        if (toRet && this.entity != null && damagesource.i() != null) {
            CanaryEntity atk = damagesource.i().getCanaryEntity();

            ((CanaryNonPlayableCharacter) entity).attack(atk);
        }
        return toRet;
    }

    /*
     * This is necessary to fix the no gravity issue.
     * make it the same as it is in Entity.java
     */
    @Override
    protected void a(double d0, boolean flag0) {
        if (!this.G()) {
            this.H();
        }

        if (flag0 && this.T > 0.0F) {
            int i0 = MathHelper.c(this.u);
            int i1 = MathHelper.c(this.v - 0.20000000298023224D - (double) this.N);
            int i2 = MathHelper.c(this.w);
            int i3 = this.q.a(i0, i1, i2);

            if (i3 == 0) {
                int i4 = this.q.e(i0, i1 - 1, i2);

                if (i4 == 11 || i4 == 32 || i4 == 21) {
                    i3 = this.q.a(i0, i1 - 1, i2);
                }
            }

            if (i3 > 0) {
                Block.r[i3].a(this.q, i0, i1, i2, this, this.T);
            }
        }
        if (flag0) {
            if (this.T > 0.0F) {
                this.a(this.T);
                this.T = 0.0F;
            }
        } else if (d0 < 0.0D) {
            this.T = (float) ((double) this.T - d0);
        }
    }

//    /*
//     * This is necessary to fix the no gravity issue.
//     * make it the same as it is in EntityLiving.java
//     */
//    @Override
//    public void e(float f0, float f1) {
//        double d0;
//
//        if (this.G()) {
//            d0 = this.v;
//            this.a(f0, f1, this.bh() ? 0.04F : 0.02F);
//            this.d(this.x, this.y, this.z);
//            this.x *= 0.800000011920929D;
//            this.y *= 0.800000011920929D;
//            this.z *= 0.800000011920929D;
//            this.y -= 0.02D;
//            if (this.G && this.c(this.x, this.y + 0.6000000238418579D - this.v + d0, this.z)) {
//                this.y = 0.30000001192092896D;
//            }
//        } else if (this.I()) {
//            d0 = this.v;
//            this.a(f0, f1, 0.02F);
//            this.d(this.x, this.y, this.z);
//            this.x *= 0.5D;
//            this.y *= 0.5D;
//            this.z *= 0.5D;
//            this.y -= 0.02D;
//            if (this.G && this.c(this.x, this.y + 0.6000000238418579D - this.v + d0, this.z)) {
//                this.y = 0.30000001192092896D;
//            }
//        } else {
//            float f2 = 0.91F;
//
//            if (this.F) {
//                f2 = 0.54600006F;
//                int i0 = this.q.a(MathHelper.c(this.u), MathHelper.c(this.E.b) - 1, MathHelper.c(this.w));
//
//                if (i0 > 0) {
//                    f2 = Block.r[i0].cP * 0.91F;
//                }
//            }
//
//            float f3 = 0.16277136F / (f2 * f2 * f2);
//            float f4;
//
//            if (this.F) {
//                if (this.bh()) {
//                    f4 = this.aI();
//                } else {
//                    f4 = this.aO;
//                }
//
//                f4 *= f3;
//            } else {
//                f4 = this.aP;
//            }
//
//            this.a(f0, f1, f4);
//            f2 = 0.91F;
//            if (this.F) {
//                f2 = 0.54600006F;
//                int i1 = this.q.a(MathHelper.c(this.u), MathHelper.c(this.E.b) - 1, MathHelper.c(this.w));
//
//                if (i1 > 0) {
//                    f2 = Block.r[i1].cP * 0.91F;
//                }
//            }
//
//            if (this.g_()) {
//                float f5 = 0.15F;
//
//                if (this.x < (double) (-f5)) {
//                    this.x = (double) (-f5);
//                }
//
//                if (this.x > (double) f5) {
//                    this.x = (double) f5;
//                }
//
//                if (this.z < (double) (-f5)) {
//                    this.z = (double) (-f5);
//                }
//
//                if (this.z > (double) f5) {
//                    this.z = (double) f5;
//                }
//
//                this.T = 0.0F;
//                if (this.y < -0.15D) {
//                    this.y = -0.15D;
//                }
//
//                boolean flag0 = this.ag();
//
//                if (flag0 && this.y < 0.0D) {
//                    this.y = 0.0D;
//                }
//            }
//
//            this.d(this.x, this.y, this.z);
//            if (this.G && this.g_()) {
//                this.y = 0.2D;
//            }
//
//            if (this.q.I && (!this.q.f((int) this.u, 0, (int) this.w) || !this.q.d((int) this.u, (int) this.w).d)) {
//                if (this.v > 0.0D) {
//                    this.y = -0.1D;
//                } else {
//                    this.y = 0.0D;
//                }
//            } else {
//                this.y -= 0.08D;
//            }
//
//            this.y *= 0.9800000190734863D;
//            this.x *= (double) f2;
//            this.z *= (double) f2;
//        }
//
//        this.bh = this.bi;
//        d0 = this.u - this.r;
//        double d1 = this.w - this.t;
//        float f6 = MathHelper.a(d0 * d0 + d1 * d1) * 4.0F;
//
//        if (f6 > 1.0F) {
//            f6 = 1.0F;
//        }
//
//        this.bi += (f6 - this.bi) * 0.4F;
//        this.bj += this.bi;
//    }
//
//    public void d(double d0, double d1, double d2) {
//        if (this.Z) {
//            this.E.d(d0, d1, d2);
//            this.u = (this.E.a + this.E.d) / 2.0D;
//            this.v = this.E.b + (double) this.N - (double) this.X;
//            this.w = (this.E.c + this.E.f) / 2.0D;
//        } else {
//            this.q.C.a("move");
//            this.X *= 0.4F;
//            double d3 = this.u;
//            double d4 = this.v;
//            double d5 = this.w;
//
//            if (this.K) {
//                this.K = false;
//                d0 *= 0.25D;
//                d1 *= 0.05000000074505806D;
//                d2 *= 0.25D;
//                this.x = 0.0D;
//                this.y = 0.0D;
//                this.z = 0.0D;
//            }
//
//            double d6 = d0;
//            double d7 = d1;
//            double d8 = d2;
//            AxisAlignedBB axisalignedbb = this.E.c();
//            boolean flag0 = false;
//
//            if (flag0) {
//                double d9;
//
//                for (d9 = 0.05D; d0 != 0.0D && this.q.a(this, this.E.c(d0, -1.0D, 0.0D)).isEmpty(); d6 = d0) {
//                    if (d0 < d9 && d0 >= -d9) {
//                        d0 = 0.0D;
//                    } else if (d0 > 0.0D) {
//                        d0 -= d9;
//                    } else {
//                        d0 += d9;
//                    }
//                }
//
//                for (; d2 != 0.0D && this.q.a(this, this.E.c(0.0D, -1.0D, d2)).isEmpty(); d8 = d2) {
//                    if (d2 < d9 && d2 >= -d9) {
//                        d2 = 0.0D;
//                    } else if (d2 > 0.0D) {
//                        d2 -= d9;
//                    } else {
//                        d2 += d9;
//                    }
//                }
//
//                while (d0 != 0.0D && d2 != 0.0D && this.q.a(this, this.E.c(d0, -1.0D, d2)).isEmpty()) {
//                    if (d0 < d9 && d0 >= -d9) {
//                        d0 = 0.0D;
//                    } else if (d0 > 0.0D) {
//                        d0 -= d9;
//                    } else {
//                        d0 += d9;
//                    }
//
//                    if (d2 < d9 && d2 >= -d9) {
//                        d2 = 0.0D;
//                    } else if (d2 > 0.0D) {
//                        d2 -= d9;
//                    } else {
//                        d2 += d9;
//                    }
//
//                    d6 = d0;
//                    d8 = d2;
//                }
//            }
//
//            List list = this.q.a(this, this.E.a(d0, d1, d2));
//
//            for (int i0 = 0; i0 < list.size(); ++i0) {
//                d1 = ((AxisAlignedBB) list.get(i0)).b(this.E, d1);
//            }
//
//            this.E.d(0.0D, d1, 0.0D);
//            if (!this.L && d7 != d1) {
//                d2 = 0.0D;
//                d1 = 0.0D;
//                d0 = 0.0D;
//            }
//
//            boolean flag1 = this.F || d7 != d1 && d7 < 0.0D;
//
//            int i1;
//
//            for (i1 = 0; i1 < list.size(); ++i1) {
//                d0 = ((AxisAlignedBB) list.get(i1)).a(this.E, d0);
//            }
//
//            this.E.d(d0, 0.0D, 0.0D);
//            if (!this.L && d6 != d0) {
//                d2 = 0.0D;
//                d1 = 0.0D;
//                d0 = 0.0D;
//            }
//
//            for (i1 = 0; i1 < list.size(); ++i1) {
//                d2 = ((AxisAlignedBB) list.get(i1)).c(this.E, d2);
//            }
//
//            this.E.d(0.0D, 0.0D, d2);
//            if (!this.L && d8 != d2) {
//                d2 = 0.0D;
//                d1 = 0.0D;
//                d0 = 0.0D;
//            }
//
//            double d10;
//            double d11;
//            double d12;
//            int i2;
//
//            if (this.Y > 0.0F && flag1 && (flag0 || this.X < 0.05F) && (d6 != d0 || d8 != d2)) {
//                d10 = d0;
//                d11 = d1;
//                d12 = d2;
//                d0 = d6;
//                d1 = (double) this.Y;
//                d2 = d8;
//                AxisAlignedBB axisalignedbb1 = this.E.c();
//
//                this.E.c(axisalignedbb);
//                list = this.q.a(this, this.E.a(d6, d1, d8));
//
//                for (i2 = 0; i2 < list.size(); ++i2) {
//                    d1 = ((AxisAlignedBB) list.get(i2)).b(this.E, d1);
//                }
//
//                this.E.d(0.0D, d1, 0.0D);
//                if (!this.L && d7 != d1) {
//                    d2 = 0.0D;
//                    d1 = 0.0D;
//                    d0 = 0.0D;
//                }
//
//                for (i2 = 0; i2 < list.size(); ++i2) {
//                    d0 = ((AxisAlignedBB) list.get(i2)).a(this.E, d0);
//                }
//
//                this.E.d(d0, 0.0D, 0.0D);
//                if (!this.L && d6 != d0) {
//                    d2 = 0.0D;
//                    d1 = 0.0D;
//                    d0 = 0.0D;
//                }
//
//                for (i2 = 0; i2 < list.size(); ++i2) {
//                    d2 = ((AxisAlignedBB) list.get(i2)).c(this.E, d2);
//                }
//
//                this.E.d(0.0D, 0.0D, d2);
//                if (!this.L && d8 != d2) {
//                    d2 = 0.0D;
//                    d1 = 0.0D;
//                    d0 = 0.0D;
//                }
//
//                if (!this.L && d7 != d1) {
//                    d2 = 0.0D;
//                    d1 = 0.0D;
//                    d0 = 0.0D;
//                } else {
//                    d1 = (double) (-this.Y);
//
//                    for (i2 = 0; i2 < list.size(); ++i2) {
//                        d1 = ((AxisAlignedBB) list.get(i2)).b(this.E, d1);
//                    }
//
//                    this.E.d(0.0D, d1, 0.0D);
//                }
//
//                if (d10 * d10 + d12 * d12 >= d0 * d0 + d2 * d2) {
//                    d0 = d10;
//                    d1 = d11;
//                    d2 = d12;
//                    this.E.c(axisalignedbb1);
//                }
//            }
//
//            this.q.C.b();
//            this.q.C.a("rest");
//            this.u = (this.E.a + this.E.d) / 2.0D;
//            this.v = this.E.b + (double) this.N - (double) this.X;
//            this.w = (this.E.c + this.E.f) / 2.0D;
//            this.G = d6 != d0 || d8 != d2;
//            this.H = d7 != d1;
//            this.F = d7 != d1 && d7 < 0.0D;
//            this.I = this.G || this.H;
//            this.a(d1, this.F);
//            if (d6 != d0) {
//                this.x = 0.0D;
//            }
//
//            if (d7 != d1) {
//                this.y = 0.0D;
//            }
//
//            if (d8 != d2) {
//                this.z = 0.0D;
//            }
//
//            d10 = this.u - d3;
//            d11 = this.v - d4;
//            d12 = this.w - d5;
//            if (this.f_() && !flag0 && this.o == null) {
//                int i3 = MathHelper.c(this.u);
//
//                i2 = MathHelper.c(this.v - 0.20000000298023224D - (double) this.N);
//                int i4 = MathHelper.c(this.w);
//                int i5 = this.q.a(i3, i2, i4);
//
//                if (i5 == 0) {
//                    int i6 = this.q.e(i3, i2 - 1, i4);
//
//                    if (i6 == 11 || i6 == 32 || i6 == 21) {
//                        i5 = this.q.a(i3, i2 - 1, i4);
//                    }
//                }
//
//                if (i5 != Block.aJ.cz) {
//                    d11 = 0.0D;
//                }
//
//                this.R = (float) ((double) this.R + (double) MathHelper.a(d10 * d10 + d12 * d12) * 0.6D);
//                this.S = (float) ((double) this.S + (double) MathHelper.a(d10 * d10 + d11 * d11 + d12 * d12) * 0.6D);
//
//            }
//
//            this.C();
//            boolean flag2 = this.F();
//
//            if (this.q.e(this.E.e(0.001D, 0.001D, 0.001D))) {
//                this.e(1);
//                if (!flag2) {
//                    ++this.d;
//                    if (this.d == 0) {
//                        this.d(8);
//                    }
//                }
//            } else if (this.d <= 0) {
//                this.d = -this.ad;
//            }
//
//            if (flag2 && this.d > 0) {
//                this.a("random.fizz", 0.7F, 1.6F + (this.ab.nextFloat() - this.ab.nextFloat()) * 0.4F);
//                this.d = -this.ad;
//            }
//
//            this.q.C.b();
//        }
//    }

    @Override
    public void w() {
        super.w();
        ((CanaryNonPlayableCharacter) entity).destoryed();
    }

    void setNPC(CanaryNonPlayableCharacter cnpc) {
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

        public void d() {}

        public void c(String s) {}

        public void a(Packet10Flying opacket10flying) {}

        @SuppressWarnings("unused")
        public void a(double d0, double d1, double d2, float f, float f1) {
            this.c.a(d0, d1, d2, f, f1);
        }

        public void a(Packet14BlockDig opacket14blockdig) {}

        public void a(Packet15Place opacket15place) {}

        public void a(String s, Object[] aobject) {}

        public void a(Packet opacket) {}

        public void b(Packet opacket) {}

        public void a(Packet16BlockItemSwitch opacket16blockitemswitch) {}

        public void a(Packet3Chat opacket3chat) {}

        public void a(Packet18Animation opacket18animation) {}

        public void a(Packet19EntityAction opacket19entityaction) {}

        public void a(Packet255KickDisconnect opacket255kickdisconnect) {}

        public int e() {
            return -1;
        }

        public void a(Packet7UseEntity opacket7useentity) {}

        public void a(Packet205ClientCommand opacket205clientcommand) {}

        public boolean b() {
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

        public boolean a() {
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
        public void a() {}

        @Override
        public void a(NetHandler arg0) {}

        @Override
        public void a(Packet arg0) {}

        @Override
        public void a(String arg0, Object... arg1) {}

        @Override
        public void b() {}

        @Override
        public SocketAddress c() {
            return herp;
        }

        @Override
        public void d() {}

        @Override
        public int e() {
            return 0;
        }
    }
}
