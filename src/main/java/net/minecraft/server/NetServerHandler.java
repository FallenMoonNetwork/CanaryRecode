package net.minecraft.server;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import net.canarymod.AutocompleteUtils;
import net.canarymod.Canary;
import net.canarymod.LineTracer;
import net.canarymod.ToolBox;
import net.canarymod.api.CanaryNetServerHandler;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.api.world.position.Location;
import net.canarymod.channels.CustomPayloadChannelException;
import net.canarymod.config.Configuration;
import net.canarymod.hook.player.BlockLeftClickHook;
import net.canarymod.hook.player.BlockRightClickHook;
import net.canarymod.hook.player.DisconnectionHook;
import net.canarymod.hook.player.PlayerLeftClickHook;
import net.canarymod.hook.player.PlayerMoveHook;
import net.canarymod.hook.player.SignChangeHook;
import net.canarymod.hook.player.TeleportHook;


public class NetServerHandler extends NetHandler {

    public final INetworkManager a;
    public final MinecraftServer d;//private to public
    public boolean b = false;
    public EntityPlayerMP c;
    private int e;
    private int f;
    private boolean g;
    private int h;
    private long i;
    private static Random j = new Random();
    private long k;
    private int l = 0;
    private int m = 0;
    private double n;
    private double o;
    private double p;
    private boolean q = true;
    private IntHashMap r = new IntHashMap();

    // CanaryMod
    protected CanaryNetServerHandler serverHandler;
    //

    public NetServerHandler(MinecraftServer minecraftserver, INetworkManager inetworkmanager, EntityPlayerMP entityplayermp) {
        this.d = minecraftserver;
        this.a = inetworkmanager;
        inetworkmanager.a((NetHandler) this);
        this.c = entityplayermp;
        entityplayermp.a = this;
        serverHandler = new CanaryNetServerHandler(this);
    }

    public void d() {
        this.g = false;
        ++this.e;
        this.d.a.a("packetflow");
        this.a.b();
        this.d.a.c("keepAlive");
        if ((long) this.e - this.k > 20L) {
            this.k = (long) this.e;
            this.i = System.nanoTime() / 1000000L;
            this.h = j.nextInt();
            this.b(new Packet0KeepAlive(this.h));
        }

        if (this.l > 0) {
            --this.l;
        }

        if (this.m > 0) {
            --this.m;
        }

        this.d.a.c("playerTick");
        this.d.a.b();
    }

    public void c(String s0) {
        // CanaryMod disconnect hook
        DisconnectionHook hook = new DisconnectionHook(serverHandler.getUser(), s0, EnumChatFormatting.o + this.c.ax() + " left the game.");

        Canary.hooks().callHook(hook);
        if (!this.b) {
            this.c.k();
            this.b(new Packet255KickDisconnect(s0));
            this.a.d();
            // CanaryMod hook data
            if (!hook.isHidden()) {
                this.d.ad().a((Packet) (new Packet3Chat(hook.getLeaveMessage())));
            }
            // //
            this.d.ad().e(this.c);
            this.b = true;
        }
    }

    @Override
    public void a(Packet10Flying packet10flying) {
        WorldServer worldserver = (WorldServer) this.c.getCanaryWorld().getHandle(); // this.d.a(this.c.ar);

        this.g = true;
        if (!this.c.j) {
            double d0;

            if (!this.q) {
                d0 = packet10flying.b - this.o;
                if (packet10flying.a == this.n && d0 * d0 < 0.01D && packet10flying.c == this.p) {
                    this.q = true;
                }
            }

            // CanaryMod: PlayerMoveHook
            if (Math.floor(n) != Math.floor(c.getPlayer().getX()) || Math.floor(o) != Math.floor(c.getPlayer().getY()) || Math.floor(p) != Math.floor(c.getPlayer().getZ())) {
                Location from = new Location(c.getPlayer().getWorld(), n, o, p, c.getPlayer().getRotation(), c.getPlayer().getPitch());
                PlayerMoveHook hook = new PlayerMoveHook(c.getPlayer(), from, c.getPlayer().getLocation());

                Canary.hooks().callHook(hook);
                if (hook.isCanceled()) {
                    // Return the player to their previous position gracefully, hopefully bypassing the TeleportHook and not going derp.
                    this.c.a.b(new Packet13PlayerLookMove(from.getX(), from.getY() + 1.6200000047683716D, from.getY(), from.getZ(), from.getRotation(), from.getPitch(), false));
                    this.c.b(from.getX(), from.getY(), from.getZ()); // correct position server side to, or get BoUnCy
                    return;
                }
            }
            //

            if (this.q) {
                double d1;
                double d2;
                double d3;
                double d4;

                if (this.c.o != null) {
                    float f0 = this.c.A;
                    float f1 = this.c.B;

                    this.c.o.U();
                    d1 = this.c.u;
                    d2 = this.c.v;
                    d3 = this.c.w;
                    double d5 = 0.0D;

                    d4 = 0.0D;
                    if (packet10flying.i) {
                        f0 = packet10flying.e;
                        f1 = packet10flying.f;
                    }

                    if (packet10flying.h && packet10flying.b == -999.0D && packet10flying.d == -999.0D) {
                        if (Math.abs(packet10flying.a) > 1.0D || Math.abs(packet10flying.c) > 1.0D) {
                            System.err.println(this.c.bS + " was caught trying to crash the server with an invalid position.");
                            this.c("Nope!");
                            return;
                        }

                        d5 = packet10flying.a;
                        d4 = packet10flying.c;
                    }

                    this.c.F = packet10flying.g;
                    this.c.g();
                    this.c.d(d5, 0.0D, d4);
                    this.c.a(d1, d2, d3, f0, f1);
                    this.c.x = d5;
                    this.c.z = d4;
                    if (this.c.o != null) {
                        worldserver.b(this.c.o, true);
                    }

                    if (this.c.o != null) {
                        this.c.o.U();
                    }

                    this.d.ad().d(this.c);
                    this.n = this.c.u;
                    this.o = this.c.v;
                    this.p = this.c.w;
                    worldserver.g(this.c);
                    return;
                }

                if (this.c.bz()) {
                    this.c.g();
                    this.c.a(this.n, this.o, this.p, this.c.A, this.c.B);
                    worldserver.g(this.c);
                    return;
                }

                d0 = this.c.v;
                this.n = this.c.u;
                this.o = this.c.v;
                this.p = this.c.w;
                d1 = this.c.u;
                d2 = this.c.v;
                d3 = this.c.w;
                float f2 = this.c.A;
                float f3 = this.c.B;

                if (packet10flying.h && packet10flying.b == -999.0D && packet10flying.d == -999.0D) {
                    packet10flying.h = false;
                }

                if (packet10flying.h) {
                    d1 = packet10flying.a;
                    d2 = packet10flying.b;
                    d3 = packet10flying.c;
                    d4 = packet10flying.d - packet10flying.b;
                    if (!this.c.bz() && (d4 > 1.65D || d4 < 0.1D)) {
                        this.c("Illegal stance");
                        this.d.al().b(this.c.bS + " had an illegal stance: " + d4);
                        return;
                    }

                    if (Math.abs(packet10flying.a) > 3.2E7D || Math.abs(packet10flying.c) > 3.2E7D) {
                        this.c("Illegal position");
                        return;
                    }
                }

                if (packet10flying.i) {
                    f2 = packet10flying.e;
                    f3 = packet10flying.f;
                }

                this.c.g();
                this.c.X = 0.0F;
                this.c.a(this.n, this.o, this.p, f2, f3);
                if (!this.q) {
                    return;
                }

                d4 = d1 - this.c.u;
                double d6 = d2 - this.c.v;
                double d7 = d3 - this.c.w;
                double d8 = Math.min(Math.abs(d4), Math.abs(this.c.x));
                double d9 = Math.min(Math.abs(d6), Math.abs(this.c.y));
                double d10 = Math.min(Math.abs(d7), Math.abs(this.c.z));
                double d11 = d8 * d8 + d9 * d9 + d10 * d10;

                if (d11 > 100.0D && (!this.d.I() || !this.d.H().equals(this.c.bS))) {
                    this.d.al().b(this.c.bS + " moved too quickly! " + d4 + "," + d6 + "," + d7 + " (" + d8 + ", " + d9 + ", " + d10 + ")");
                    this.a(this.n, this.o, this.p, this.c.A, this.c.B, c.getCanaryWorld().getType().getId(), c.getCanaryWorld().getName());
                    return;
                }

                float f4 = 0.0625F;
                boolean flag0 = worldserver.a(this.c, this.c.E.c().e((double) f4, (double) f4, (double) f4)).isEmpty();

                if (this.c.F && !packet10flying.g && d6 > 0.0D) {
                    this.c.j(0.2F);
                }

                this.c.d(d4, d6, d7);
                this.c.F = packet10flying.g;
                this.c.j(d4, d6, d7);
                double d12 = d6;

                d4 = d1 - this.c.u;
                d6 = d2 - this.c.v;
                if (d6 > -0.5D || d6 < 0.5D) {
                    d6 = 0.0D;
                }

                d7 = d3 - this.c.w;
                d11 = d4 * d4 + d6 * d6 + d7 * d7;
                boolean flag1 = false;

                if (d11 > 0.0625D && !this.c.bz() && !this.c.c.d()) {
                    flag1 = true;
                    this.d.al().b(this.c.bS + " moved wrongly!");
                }

                this.c.a(d1, d2, d3, f2, f3);
                boolean flag2 = worldserver.a(this.c, this.c.E.c().e((double) f4, (double) f4, (double) f4)).isEmpty();

                if (flag0 && (flag1 || !flag2) && !this.c.bz()) {
                    this.a(this.n, this.o, this.p, f2, f3, c.getCanaryWorld().getType().getId(), c.getCanaryWorld().getName());
                    return;
                }

                AxisAlignedBB axisalignedbb = this.c.E.c().b((double) f4, (double) f4, (double) f4).a(0.0D, -0.55D, 0.0D);

                // CanaryMod: check on flying capability instead of mode
                // moved allow-flight to per-world config
                if (!Configuration.getWorldConfig(c.getCanaryWorld().getFqName()).isFlightAllowed() && !this.c.c.d() && !worldserver.c(axisalignedbb)) {
                    if (d12 >= -0.03125D) {
                        ++this.f;
                        if (this.f > 80) {
                            this.d.al().b(this.c.bS + " was kicked for floating too long!");
                            this.c("Flying is not enabled on this server");
                            return;
                        }
                    }
                } else {
                    this.f = 0;
                }

                this.c.F = packet10flying.g;
                this.d.ad().d(this.c);
                this.c.b(this.c.v - d0, packet10flying.g);
            }
        }
    }

    public void a(double d0, double d1, double d2, float f0, float f1, int dimension, String world) {
        // CanaryMod: TeleportHook
        net.canarymod.api.world.World dim = Canary.getServer().getWorldManager().getWorld(world, net.canarymod.api.world.DimensionType.fromId(dimension), true);
        Location location = new Location(dim, d0, d1, d2, f0, f1);
        TeleportHook hook = new TeleportHook(c.getPlayer(), location, false);

        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return;
        }
        //
        this.q = false;
        this.n = d0;
        this.o = d1;
        this.p = d2;
        this.c.a(d0, d1, d2, f0, f1);
        this.c.a.b(new Packet13PlayerLookMove(d0, d1 + 1.6200000047683716D, d1, d2, f0, f1, false));
    }

    @Override
    public void a(Packet14BlockDig packet14blockdig) {
        WorldServer worldserver = (WorldServer) this.c.getCanaryWorld().getHandle(); // this.d.a(this.c.ar);

        if (packet14blockdig.e == 4) {
            // TODO: crash from rapid item drop prevention
            this.c.a(false);
        } else if (packet14blockdig.e == 3) {
            this.c.a(true);
        } else if (packet14blockdig.e == 5) {
            this.c.bZ();
        } else {
            boolean flag0 = false;

            if (packet14blockdig.e == 0) {
                flag0 = true;
            }

            if (packet14blockdig.e == 1) {
                flag0 = true;
            }

            if (packet14blockdig.e == 2) {
                flag0 = true;
            }

            int i0 = packet14blockdig.a;
            int i1 = packet14blockdig.b;
            int i2 = packet14blockdig.c;

            if (flag0) {
                double d0 = this.c.u - ((double) i0 + 0.5D);
                double d1 = this.c.v - ((double) i1 + 0.5D) + 1.5D;
                double d2 = this.c.w - ((double) i2 + 0.5D);
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d3 > 36.0D) {
                    return;
                }

                if (i1 >= this.d.ab()) {
                    return;
                }
            }

            // CanaryMod: BlockLeftClickHook
            Block block = worldserver.getCanaryWorld().getBlockAt(i0, i1, i2);
            BlockLeftClickHook hook = new BlockLeftClickHook(c.getPlayer(), block);

            if (packet14blockdig.e == 0) {
                if ((!this.d.a(worldserver, i0, i1, i2, this.c) || c.getPlayer().hasPermission("canary.world.spawnbuild")) && c.getPlayer().canBuild()) {
                    block.setStatus((byte) 0); // Set Status
                    Canary.hooks().callHook(hook); // Call Hook
                    if (!hook.isCanceled()) {
                        this.c.c.a(i0, i1, i2, packet14blockdig.d);
                    } else {
                        this.c.a.b(new Packet53BlockChange(i0, i1, i2, worldserver));
                    }
                }
            } else if (packet14blockdig.e == 2) {
                block.setStatus((byte) 2); // Set Status
                Canary.hooks().callHook(hook); // Call Hook
                this.c.c.a(i0, i1, i2);
                if (worldserver.a(i0, i1, i2) != 0) {
                    this.c.a.b(new Packet53BlockChange(i0, i1, i2, worldserver));
                }
            } else if (packet14blockdig.e == 1) {
                block.setStatus((byte) 1); // Set Status
                Canary.hooks().callHook(hook); // And, Call Hook
                this.c.c.c(i0, i1, i2);
                if (worldserver.a(i0, i1, i2) != 0) {
                    this.c.a.b(new Packet53BlockChange(i0, i1, i2, worldserver));
                }
            }
            //
        }
    }

    @Override
    public void a(Packet15Place packet15place) {
        WorldServer worldserver = (WorldServer) this.c.getCanaryWorld().getHandle(); // this.d.a(this.c.ar);
        ItemStack itemstack = this.c.bK.h();
        boolean flag0 = false;
        int i0 = packet15place.d();
        int i1 = packet15place.f();
        int i2 = packet15place.g();
        int i3 = packet15place.h();

        // CanaryMod: BlockRightClick/ItemUse
        CanaryBlock blockClicked = (CanaryBlock) worldserver.getCanaryWorld().getBlockAt(i0, i1, i2);

        blockClicked.setFaceClicked(BlockFace.fromByte((byte) i3));

        if (packet15place.h() == 255) {
            if (itemstack == null) {
                return;
            }
            // Correct coordinates on block
            LineTracer trace = new LineTracer(this.c.getPlayer(), 6, 0.2);

            blockClicked = (CanaryBlock) trace.getTargetBlock();
            blockClicked = blockClicked != null ? blockClicked : new CanaryBlock((short) 0, (short) 0, ToolBox.floorToBlock(this.n), ToolBox.floorToBlock(this.o), ToolBox.floorToBlock(this.p), this.c.getCanaryWorld());
            //
            this.c.c.itemUsed(this.c.getPlayer(), worldserver, itemstack, blockClicked); // CanaryMod: Redirect through ItemInWorldManager.itemUsed
        } else if (packet15place.f() >= this.d.ab() - 1 && (packet15place.h() == 1 || packet15place.f() >= this.d.ab())) {
            this.c.a.b(new Packet3Chat("" + EnumChatFormatting.h + "Height limit for building is " + this.d.ab()));
            flag0 = true;
        } else {
            if (this.q && this.c.e((double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D) < 64.0D && (!this.d.a(worldserver, i0, i1, i2, this.c) || c.getPlayer().hasPermission("canary.world.spawnbuild")) && c.getPlayer().canBuild()) {
                // CanaryMod: BlockRightClicked
                BlockRightClickHook hook = new BlockRightClickHook(c.getPlayer(), blockClicked);

                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    this.c.c.a(this.c, worldserver, itemstack, i0, i1, i2, i3, packet15place.j(), packet15place.k(), packet15place.l());
                } else {
                    // CanaryMod: No point telling the client to update the blocks that they weren't allowed to place!
                    this.c.a.b(new Packet53BlockChange(i0, i1, i2, worldserver));
                    return;
                }
            }
            //

            flag0 = true;
        }

        if (flag0) {
            this.c.a.b(new Packet53BlockChange(i0, i1, i2, worldserver));
            if (i3 == 0) {
                --i1;
            }

            if (i3 == 1) {
                ++i1;
            }

            if (i3 == 2) {
                --i2;
            }

            if (i3 == 3) {
                ++i2;
            }

            if (i3 == 4) {
                --i0;
            }

            if (i3 == 5) {
                ++i0;
            }

            this.c.a.b(new Packet53BlockChange(i0, i1, i2, worldserver));
        }

        itemstack = this.c.bK.h();
        if (itemstack != null && itemstack.a == 0) {
            this.c.bK.a[this.c.bK.c] = null;
            itemstack = null;
        }

        if (itemstack == null || itemstack.n() == 0) {
            this.c.h = true;
            this.c.bK.a[this.c.bK.c] = ItemStack.b(this.c.bK.a[this.c.bK.c]);
            Slot slot = this.c.bM.a((IInventory) this.c.bK, this.c.bK.c);

            this.c.bM.b();
            this.c.h = false;
            if (!ItemStack.b(this.c.bK.h(), packet15place.i())) {
                this.b(new Packet103SetSlot(this.c.bM.d, slot.g, this.c.bK.h()));
            }
        }
    }

    @Override
    public void a(String s0, Object[] aobject) {
        // CanaryMod: DisconnectionHook
        DisconnectionHook hook = new DisconnectionHook(c.getPlayer(), s0, EnumChatFormatting.o + this.c.ax() + " left the game.");

        Canary.hooks().callHook(hook);
        this.d.al().a(this.c.bS + " lost connection: " + s0);
        this.d.ad().a((Packet) (new Packet3Chat(hook.getLeaveMessage())));
        //
        this.d.ad().e(this.c);
        this.b = true;
        if (this.d.I() && this.c.bS.equals(this.d.H())) {
            this.d.al().a("Stopping singleplayer server as player logged out");
            this.d.n();
        }
    }

    @Override
    public void a(Packet packet) {
        this.d.al().b(this.getClass() + " wasn\'t prepared to deal with a " + packet.getClass());
        this.c("Protocol error, unexpected packet");
    }

    public void b(Packet packet) {
        if (packet instanceof Packet3Chat) {
            Packet3Chat packet3chat = (Packet3Chat) packet;
            int i0 = this.c.t();

            if (i0 == 2) {
                return;
            }

            if (i0 == 1 && !packet3chat.d()) {
                return;
            }
        }

        try {
            this.a.a(packet);
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Sending packet");
            CrashReportCategory crashreportcategory = crashreport.a("Packet being sent");

            crashreportcategory.a("Packet ID", (Callable) (new CallablePacketID(this, packet)));
            crashreportcategory.a("Packet class", (Callable) (new CallablePacketClass(this, packet)));
            throw new ReportedException(crashreport);
        }
    }

    @Override
    public void a(Packet16BlockItemSwitch packet16blockitemswitch) {
        if (packet16blockitemswitch.a >= 0 && packet16blockitemswitch.a < InventoryPlayer.i()) {
            this.c.bK.c = packet16blockitemswitch.a;
        } else {
            this.d.al().b(this.c.bS + " tried to set an invalid carried item");
        }
    }

    @Override
    public void a(Packet3Chat packet3chat) {

        /* Diff visibility funkyness
         if (this.c.t() == 2) {
         this.b(new Packet3Chat("Cannot send chat message."));
         } else {
         String s0 = packet3chat.b;

         if (s0.length() > 100) {
         this.c("Chat message too long");
         } else {
         s0 = s0.trim();

         for (int i0 = 0; i0 < s0.length(); ++i0) {
         if (!ChatAllowedCharacters.a(s0.charAt(i0))) {
         this.c("Illegal characters in chat");
         return;
         }
         }

         if (s0.startsWith("/")) {
         this.d(s0);
         } else {
         if (this.c.t() == 1) {
         this.b(new Packet3Chat("Cannot send chat message."));
         return;
         }

         s0 = "<" + this.c.ax() + "> " + s0;
         this.d.al().a(s0);
         this.d.ad().a((Packet) (new Packet3Chat(s0, false)));
         }

         this.l += 20;
         if (this.l > 200 && !this.d.ad().e(this.c.bS)) {
         this.c("disconnect.spam");
         }
         }
         } */
        // CanaryMod: Re-route to Player chat
        this.c.getPlayer().chat(packet3chat.b);
    }

    private void d(String s0) {
        this.d.E().a(this.c, s0);
    }

    @Override
    public void a(Packet18Animation packet18animation) {
        if (packet18animation.b == 1) {
            // CanaryMod: PlayerLeftClick
            Canary.hooks().callHook(new PlayerLeftClickHook(this.c.getPlayer()));
            //
            this.c.bK();
        }
    }

    @Override
    public void a(Packet19EntityAction packet19entityaction) {
        if (packet19entityaction.b == 1) {
            this.c.b(true);
        } else if (packet19entityaction.b == 2) {
            this.c.b(false);
        } else if (packet19entityaction.b == 4) {
            this.c.c(true);
        } else if (packet19entityaction.b == 5) {
            this.c.c(false);
        } else if (packet19entityaction.b == 3) {
            this.c.a(false, true, true);
            this.q = false;
        }
    }

    @Override
    public void a(Packet255KickDisconnect packet255kickdisconnect) {
        this.a.a("disconnect.quitting", new Object[0]);
    }

    public int e() {
        return this.a.e();
    }

    @Override
    public void a(Packet7UseEntity packet7useentity) {
        WorldServer worldserver = (WorldServer) this.c.getCanaryWorld().getHandle(); // this.d.a(this.c.ar);
        Entity entity = worldserver.a(packet7useentity.b);

        if (entity != null) {
            boolean flag0 = this.c.n(entity);
            double d0 = 36.0D;

            if (!flag0) {
                d0 = 9.0D;
            }

            if (this.c.e(entity) < d0) {
                if (packet7useentity.c == 0) {
                    this.c.p(entity);
                } else if (packet7useentity.c == 1) {
                    this.c.q(entity);
                }
            }
        }
    }

    @Override
    public void a(Packet205ClientCommand packet205clientcommand) {
        if (packet205clientcommand.a == 1) {
            if (this.c.j) {
                this.c = this.d.ad().a(this.c, 0, true);
            } else if (this.c.o().M().t()) {
                if (this.d.I() && this.c.bS.equals(this.d.H())) {
                    this.c.a.c("You have died. Game over, man, it\'s game over!");
                    this.d.P();
                } else {
                    //CanaryMod use our Ban System instead
                    Canary.bans().issueBan(this.c.getPlayer(), "Death in Hardcore");
                    this.c.a.c("You have died. Game over, man, it\'s game over!");
                }
            } else {
                if (this.c.aX() > 0) {
                    return;
                }

                this.c = this.d.ad().a(this.c, 0, false);
            }
        }
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public void a(Packet9Respawn packet9respawn) {}

    @Override
    public void a(Packet101CloseWindow packet101closewindow) {
        this.c.j();
    }

    @Override
    public void a(Packet102WindowClick packet102windowclick) {
        if (this.c.bM.d == packet102windowclick.a && this.c.bM.c(this.c)) {
            ItemStack itemstack = this.c.bM.a(packet102windowclick.b, packet102windowclick.c, packet102windowclick.f, this.c);

            if (ItemStack.b(packet102windowclick.e, itemstack)) {
                this.c.a.b(new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, true));
                this.c.h = true;
                this.c.bM.b();
                this.c.i();
                this.c.h = false;
            } else {
                this.r.a(this.c.bM.d, Short.valueOf(packet102windowclick.d));
                this.c.a.b(new Packet106Transaction(packet102windowclick.a, packet102windowclick.d, false));
                this.c.bM.a(this.c, false);
                ArrayList arraylist = new ArrayList();

                for (int i0 = 0; i0 < this.c.bM.c.size(); ++i0) {
                    arraylist.add(((Slot) this.c.bM.c.get(i0)).c());
                }

                this.c.a(this.c.bM, (List) arraylist);
            }
        }
    }

    @Override
    public void a(Packet108EnchantItem packet108enchantitem) {
        if (this.c.bM.d == packet108enchantitem.a && this.c.bM.c(this.c)) {
            this.c.bM.a((EntityPlayer) this.c, packet108enchantitem.b);
            this.c.bM.b();
        }
    }

    @Override
    public void a(Packet107CreativeSetSlot packet107creativesetslot) {
        if (this.c.c.d()) {
            boolean flag0 = packet107creativesetslot.a < 0;
            ItemStack itemstack = packet107creativesetslot.b;
            boolean flag1 = packet107creativesetslot.a >= 1 && packet107creativesetslot.a < 36 + InventoryPlayer.i();
            boolean flag2 = itemstack == null || itemstack.c < Item.f.length && itemstack.c >= 0 && Item.f[itemstack.c] != null;
            boolean flag3 = itemstack == null || itemstack.k() >= 0 && itemstack.k() >= 0 && itemstack.a <= 64 && itemstack.a > 0;

            if (flag1 && flag2 && flag3) {
                if (itemstack == null) {
                    this.c.bL.a(packet107creativesetslot.a, (ItemStack) null);
                } else {
                    this.c.bL.a(packet107creativesetslot.a, itemstack);
                }

                this.c.bL.a(this.c, true);
            } else if (flag0 && flag2 && flag3 && this.m < 200) {
                this.m += 20;
                EntityItem entityitem = this.c.c(itemstack);

                if (entityitem != null) {
                    entityitem.c();
                }
            }
        }
    }

    @Override
    public void a(Packet106Transaction packet106transaction) {
        Short oshort = (Short) this.r.a(this.c.bM.d);

        if (oshort != null && packet106transaction.b == oshort.shortValue() && this.c.bM.d == packet106transaction.a && !this.c.bM.c(this.c)) {
            this.c.bM.a(this.c, true);
        }
    }

    @Override
    public void a(Packet130UpdateSign packet130updatesign) {
        WorldServer worldserver = (WorldServer) this.c.getCanaryWorld().getHandle(); // this.d.a(this.c.ar);

        if (worldserver.f(packet130updatesign.a, packet130updatesign.b, packet130updatesign.c)) {
            TileEntity tileentity = worldserver.r(packet130updatesign.a, packet130updatesign.b, packet130updatesign.c);

            if (tileentity instanceof TileEntitySign) {
                TileEntitySign tileentitysign = (TileEntitySign) tileentity;

                if (!tileentitysign.a()) {
                    this.d.g("Player " + this.c.bS + " just tried to change non-editable sign");
                    return;
                }
            }

            int i0;
            int i1;

            for (i1 = 0; i1 < 4; ++i1) {
                boolean flag0 = true;

                if (packet130updatesign.d[i1].length() > 15) {
                    flag0 = false;
                } else {
                    for (i0 = 0; i0 < packet130updatesign.d[i1].length(); ++i0) {
                        if (ChatAllowedCharacters.a.indexOf(packet130updatesign.d[i1].charAt(i0)) < 0) {
                            flag0 = false;
                        }
                    }
                }

                if (!flag0) {
                    packet130updatesign.d[i1] = "!?";
                }
            }

            if (tileentity instanceof TileEntitySign) {
                i1 = packet130updatesign.a;
                int i2 = packet130updatesign.b;

                i0 = packet130updatesign.c;
                TileEntitySign tileentitysign1 = (TileEntitySign) tileentity;

                // CanaryMod: Copy the old line text
                String[] old = Arrays.copyOf(tileentitysign1.a, tileentitysign1.a.length);

                //

                System.arraycopy(packet130updatesign.d, 0, tileentitysign1.a, 0, 4);

                // CanaryMod: SignChange Hook
                SignChangeHook hook = new SignChangeHook(c.getPlayer(), tileentitysign1.getCanarySign());

                Canary.hooks().callHook(hook);
                if (hook.isCanceled()) {
                    System.arraycopy(old, 0, tileentitysign1.a, 0, 4); // Restore old text
                }
                //

                tileentitysign1.k_();
                worldserver.j(i1, i2, i0);
            }
        }
    }

    @Override
    public void a(Packet0KeepAlive packet0keepalive) {
        if (packet0keepalive.a == this.h) {
            int i0 = (int) (System.nanoTime() / 1000000L - this.i);

            this.c.i = (this.c.i * 3 + i0) / 4;
        }
    }

    @Override
    public boolean a() {
        return true;
    }

    @Override
    public void a(Packet202PlayerAbilities packet202playerabilities) {
        this.c.ce.b = packet202playerabilities.f() && this.c.ce.c;
    }

    @Override
    public void a(Packet203AutoComplete packet203autocomplete) {
        //CanaryMod start replace with our logic instead
        /*StringBuilder stringbuilder = new StringBuilder();

        String s0;

        for (Iterator iterator = this.d.a((ICommandSender) this.c, packet203autocomplete.d()).iterator(); iterator.hasNext(); stringbuilder.append(s0)) {
            s0 = (String) iterator.next();
            if (stringbuilder.length() > 0) {
                stringbuilder.append("\u0000");
            }
        }*/

        StringBuilder result = AutocompleteUtils.autoComplete(packet203autocomplete.d(), c.getPlayer());
        //CanaryMod end
        this.c.a.b(new Packet203AutoComplete(result.toString()));
    }

    @Override
    public void a(Packet204ClientInfo packet204clientinfo) {
        this.c.a(packet204clientinfo);
    }

    @Override
    public void a(Packet250CustomPayload packet250custompayload) {
        DataInputStream datainputstream;
        ItemStack itemstack;
        ItemStack itemstack1;

        if ("MC|BEdit".equals(packet250custompayload.a)) {
            try {
                datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.c));
                itemstack = Packet.c(datainputstream);
                if (!ItemWritableBook.a(itemstack.q())) {
                    throw new IOException("Invalid book tag!");
                }

                itemstack1 = this.c.bK.h();
                if (itemstack != null && itemstack.c == Item.bG.cp && itemstack.c == itemstack1.c) {
                    itemstack1.a("pages", (NBTBase) itemstack.q().m("pages"));
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if ("MC|BSign".equals(packet250custompayload.a)) {
            try {
                datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.c));
                itemstack = Packet.c(datainputstream);
                if (!ItemEditableBook.a(itemstack.q())) {
                    throw new IOException("Invalid book tag!");
                }

                itemstack1 = this.c.bK.h();
                if (itemstack != null && itemstack.c == Item.bH.cp && itemstack1.c == Item.bG.cp) {
                    itemstack1.a("author", (NBTBase) (new NBTTagString("author", this.c.bS)));
                    itemstack1.a("title", (NBTBase) (new NBTTagString("title", itemstack.q().i("title"))));
                    itemstack1.a("pages", (NBTBase) itemstack.q().m("pages"));
                    itemstack1.c = Item.bH.cp;
                }
            } catch (Exception exception1) {
                exception1.printStackTrace();
            }
        } else {
            int i0;

            if ("MC|TrSel".equals(packet250custompayload.a)) {
                try {
                    datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.c));
                    i0 = datainputstream.readInt();
                    Container container = this.c.bM;

                    if (container instanceof ContainerMerchant) {
                        ((ContainerMerchant) container).e(i0);
                    }
                } catch (Exception exception2) {
                    exception2.printStackTrace();
                }
            } else {
                int i1;

                if ("MC|AdvCdm".equals(packet250custompayload.a)) {
                    if (!this.d.Z()) {
                        this.c.a(this.c.a("advMode.notEnabled", new Object[0]));
                    } else if (this.c.a(2, "")/* && this.c.ce.d*/) { // CanaryMod: First check for Command Block Permission ignore Creative
                        try {
                            datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.c));
                            i0 = datainputstream.readInt();
                            i1 = datainputstream.readInt();
                            int i2 = datainputstream.readInt();
                            String s0 = Packet.a(datainputstream, 256);
                            TileEntity tileentity = this.c.q.r(i0, i1, i2);

                            if (tileentity != null && tileentity instanceof TileEntityCommandBlock) {
                                if (this.c.a(0, s0)) { // CanaryMod: Check for Command Permission
                                    ((TileEntityCommandBlock) tileentity).b(s0);
                                    this.c.q.j(i0, i1, i2);
                                    this.c.a("Command set: " + s0);
                                } else {
                                    this.c.a("No Permission to Command: " + s0); // CanaryMod: No Permission (locale?)
                                }
                            }
                        } catch (Exception exception3) {
                            exception3.printStackTrace();
                        }
                    } else {
                        this.c.a(this.c.a("advMode.notAllowed", new Object[0])); // TODO: Change message?
                    }
                } else if ("MC|Beacon".equals(packet250custompayload.a)) {
                    if (this.c.bM instanceof ContainerBeacon) {
                        try {
                            datainputstream = new DataInputStream(new ByteArrayInputStream(packet250custompayload.c));
                            i0 = datainputstream.readInt();
                            i1 = datainputstream.readInt();
                            ContainerBeacon containerbeacon = (ContainerBeacon) this.c.bM;
                            Slot slot = containerbeacon.a(0);

                            if (slot.d()) {
                                slot.a(1);
                                TileEntityBeacon tileentitybeacon = containerbeacon.e();

                                tileentitybeacon.d(i0);
                                tileentitybeacon.e(i1);
                                tileentitybeacon.k_();
                            }
                        } catch (Exception exception4) {
                            exception4.printStackTrace();
                        }
                    }
                } else if ("MC|ItemName".equals(packet250custompayload.a) && this.c.bM instanceof ContainerRepair) {
                    ContainerRepair containerrepair = (ContainerRepair) this.c.bM;

                    if (packet250custompayload.c != null && packet250custompayload.c.length >= 1) {
                        String s1 = ChatAllowedCharacters.a(new String(packet250custompayload.c));

                        if (s1.length() <= 30) {
                            containerrepair.a(s1);
                        }
                    } else {
                        containerrepair.a("");
                    }
                }
            }
        }

        // CanaryMod: Custom Payload implementation!
        if ("REGISTER".equals(packet250custompayload.a)) {
            try {
                String channel = new String(packet250custompayload.c, Charset.forName("utf-8"));
                for(String chan : channel.split("\0")) {
                    Canary.channels().registerClient(chan, this.serverHandler);
                }
                Canary.logInfo(String.format("Player '%s' registered Custom Payload on channel(s) '%s'", this.c.getPlayer().getName(), Arrays.toString(channel.split("\0"))));
            } catch (Exception ex) {
                try {
                    throw new CustomPayloadChannelException("Error receiving 'Packet250CustomPayload': " + ex.getMessage());
                } catch (CustomPayloadChannelException ex1) {
                    Canary.logStackTrace(ex1.getMessage(), ex);
                }
            }
        } else if ("UNREGISTER".equals(packet250custompayload.a)) {
            try {
                String channel = new String(packet250custompayload.c, "UTF-8");
                Canary.channels().unregisterClient(channel, this.serverHandler);
                Canary.logInfo(String.format("Player '%s' unregistered Custom Payload on channel '%s'", this.c.getPlayer().getName(), channel));
            } catch (Exception ex) {
                try {
                    throw new CustomPayloadChannelException("Error receiving 'Packet250CustomPayload': " + ex.getMessage());
                } catch (CustomPayloadChannelException ex1) {
                    Canary.logStackTrace(ex1.getMessage(), ex);
                }
            }
        } else {
            try {
                Canary.channels().sendCustomPayloadToListeners(packet250custompayload.a, packet250custompayload.c, this.c.getPlayer());
            } catch (Exception ex) {
                try {
                    throw new CustomPayloadChannelException("Error receiving 'Packet250CustomPayload': " + ex.getMessage());
                } catch (CustomPayloadChannelException ex1) {
                    Canary.logStackTrace(ex1.getMessage(), ex);
                }
            }
        }// CanaryMod: End


    }

    /**
     * gets the CanaryNetServerHandler wrapper
     * @return
     */
    public CanaryNetServerHandler getCanaryServerHandler() {
        return serverHandler;
    }
}
