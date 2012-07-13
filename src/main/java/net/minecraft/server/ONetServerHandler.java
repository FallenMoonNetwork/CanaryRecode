package net.minecraft.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

import net.canarymod.Canary;
import net.canarymod.Colors;
import net.canarymod.config.Configuration; 
import net.canarymod.api.CanaryNetServerHandler;
import net.canarymod.api.entity.CanaryPlayer;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.CanaryDimension;
import net.canarymod.api.world.Dimension;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.api.world.blocks.CanarySign;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.Hook;
import net.canarymod.hook.player.ConnectionHook;
import net.canarymod.hook.player.LeftClickHook;
import net.canarymod.hook.player.PlayerMoveHook;
import net.canarymod.hook.player.PlayerRespawnHook;
import net.canarymod.hook.player.RightClickHook;
import net.canarymod.hook.player.TeleportHook;
import net.canarymod.hook.world.SignHook;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OChatAllowedCharacters;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OICommandListener;
import net.minecraft.server.OIntHashMap;
import net.minecraft.server.OInventoryPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.ONetworkManager;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket0KeepAlive;
import net.minecraft.server.OPacket101CloseWindow;
import net.minecraft.server.OPacket102WindowClick;
import net.minecraft.server.OPacket103SetSlot;
import net.minecraft.server.OPacket106Transaction;
import net.minecraft.server.OPacket107CreativeSetSlot;
import net.minecraft.server.OPacket108EnchantItem;
import net.minecraft.server.OPacket10Flying;
import net.minecraft.server.OPacket130UpdateSign;
import net.minecraft.server.OPacket13PlayerLookMove;
import net.minecraft.server.OPacket14BlockDig;
import net.minecraft.server.OPacket15Place;
import net.minecraft.server.OPacket16BlockItemSwitch;
import net.minecraft.server.OPacket18Animation;
import net.minecraft.server.OPacket19EntityAction;
import net.minecraft.server.OPacket202PlayerAbilities;
import net.minecraft.server.OPacket255KickDisconnect;
import net.minecraft.server.OPacket3Chat;
import net.minecraft.server.OPacket53BlockChange;
import net.minecraft.server.OPacket7UseEntity;
import net.minecraft.server.OPacket9Respawn;
import net.minecraft.server.OSlot;
import net.minecraft.server.OTileEntity;
import net.minecraft.server.OTileEntitySign;
import net.minecraft.server.OWorldServer;

public class ONetServerHandler extends ONetHandler implements OICommandListener {

    public static Logger a = Logger.getLogger("Minecraft");
    public ONetworkManager b;
    public boolean c = false;
    private OMinecraftServer d;
    private OEntityPlayerMP e;
    private int f;
    private int g;
    private boolean h;
    private int i;
    private long j;
    private static Random k = new Random();
    private long l;
    private int m = 0;
    private int n = 0;
    private double o;
    private double p;
    private double q;
    private boolean r = true;
    private OIntHashMap s = new OIntHashMap();
    private CanaryPlayer player;
    private CanaryNetServerHandler nsh;

    public ONetServerHandler(OMinecraftServer var1, ONetworkManager var2, OEntityPlayerMP var3) {
        super();
        this.d = var1;
        this.b = var2;
        var2.a(this);
        this.e = var3;
        var3.a = this;
        player = e.getPlayer();
        nsh = new CanaryNetServerHandler(this);
        e.setServerHandler(nsh);
    }

    /**
     * CanaryMod return the NSH wrapper 
     * @return
     */
    public CanaryNetServerHandler getCanaryNetServerHandler() {
        return nsh;
    }
    public CanaryPlayer getUser() {
        return player;
    }
    public void setUser(CanaryPlayer newPlayer) {
        this.player = newPlayer;
        this.e = player.getHandle();
    }
    public void a() {
        this.h = false;
        ++this.f;
        this.b.b();
        if (this.f - this.l > 20L) {
            this.l = this.f;
            this.j = System.nanoTime() / 1000000L;
            this.i = k.nextInt();
            this.b((new OPacket0KeepAlive(this.i)));
        }

        if (this.m > 0) {
            --this.m;
        }

        if (this.n > 0) {
            --this.n;
        }

    }

    public void a(String var1) {
        if (!this.c) {
            this.e.I();
            this.b((new OPacket255KickDisconnect(var1)));
            this.b.d();
          //CanaryMod handle disconnect world stuff
            this.e.bi.getCanaryDimension().getEntityTracker().untrackPlayerSymmetrics(this.e.getPlayer());
            this.e.bi.getCanaryDimension().getEntityTracker().untrackEntity(this.e.getPlayer());
            this.e.bi.getCanaryDimension().getPlayerManager().removePlayer(this.e.getPlayer());
            //            etc.getServer().getPlayerManager(this.e.bi.world).removePlayer(this.e);
            ConnectionHook hook = new ConnectionHook(getUser(), var1, Colors.Yellow + getUser().getName() + " left the game.");
            Canary.hooks().callHook(hook);
            if (!hook.isHidden()) {
                this.d.h.sendPacketToAll((new OPacket3Chat(hook.getMessage())));
            }
            this.d.h.e(this.e);
            this.c = true;
        }
    }

    @Override
    public void a(OPacket10Flying var1) {
        OWorldServer var2 = (OWorldServer) ((CanaryDimension)this.e.getDimension()).getHandle();
        this.h = true;
        if (!this.e.j) {
            double var3;
            if (!this.r) {
                var3 = var1.b - this.p;
                if (var1.a == this.o && var3 * var3 < 0.01D && var1.c == this.q) {
                    this.r = true;
                }
            }
            
            //CanaryMod start - onPlayerMove
            if(Math.floor(o) != Math.floor(player.getX()) || Math.floor(p) != Math.floor(player.getY()) || Math.floor(q) != Math.floor(player.getZ())){
                Location from = new Location(player.getDimension(), o, p, q, player.getRotation(), player.getPitch());
                Canary.hooks().callHook(new PlayerMoveHook(player, from, player.getLocation()));
            }
            //CanaryMod end

            if (this.r) {
                double var7;
                double var9;
                double var11;
                double var15;
                if (this.e.bh != null) {
                    float var5 = this.e.bs;
                    float var6 = this.e.bt;
                    this.e.bh.i_();
                    var7 = this.e.bm;
                    var9 = this.e.bn;
                    var11 = this.e.bo;
                    double var13 = 0.0D;
                    var15 = 0.0D;
                    if (var1.i) {
                        var5 = var1.e;
                        var6 = var1.f;
                    }

                    if (var1.h && var1.b == -999.0D && var1.d == -999.0D) {
                        if (var1.a > 1.0D || var1.c > 1.0D) {
                            System.err.println(this.e.v + " was caught trying to crash the server with an invalid position.");
                            this.a("Nope!");
                            return;
                        }

                        var13 = var1.a;
                        var15 = var1.c;
                    }

                    this.e.bx = var1.g;
                    this.e.a(true);
                    this.e.a(var13, 0.0D, var15);
                    this.e.b(var7, var9, var11, var5, var6);
                    this.e.bp = var13;
                    this.e.br = var15;
                    if (this.e.bh != null) {
                        var2.b(this.e.bh, true);
                    }

                    if (this.e.bh != null) {
                        this.e.bh.i_();
                    }

                    this.d.h.d(this.e);
                    this.o = this.e.bm;
                    this.p = this.e.bn;
                    this.q = this.e.bo;
                    var2.g(this.e);
                    return;
                }

                if (this.e.Z()) {
                    this.e.a(true);
                    this.e.b(this.o, this.p, this.q, this.e.bs, this.e.bt);
                    var2.g(this.e);
                    return;
                }

                var3 = this.e.bn;
                this.o = this.e.bm;
                this.p = this.e.bn;
                this.q = this.e.bo;
                var7 = this.e.bm;
                var9 = this.e.bn;
                var11 = this.e.bo;
                float var17 = this.e.bs;
                float var18 = this.e.bt;
                if (var1.h && var1.b == -999.0D && var1.d == -999.0D) {
                    var1.h = false;
                }

                if (var1.h) {
                    var7 = var1.a;
                    var9 = var1.b;
                    var11 = var1.c;
                    var15 = var1.d - var1.b;
                    if (!this.e.Z() && (var15 > 1.65D || var15 < 0.1D)) {
                        this.a("Illegal stance");
                        a.warning(this.e.v + " had an illegal stance: " + var15);
                        return;
                    }

                    if (Math.abs(var1.a) > 3.2E7D || Math.abs(var1.c) > 3.2E7D) {
                        this.a("Illegal position");
                        return;
                    }
                }

                if (var1.i) {
                    var17 = var1.e;
                    var18 = var1.f;
                }

                this.e.a(true);
                this.e.bO = 0.0F;
                this.e.b(this.o, this.p, this.q, var17, var18);
                if (!this.r) {
                    return;
                }

                var15 = var7 - this.e.bm;
                double var19 = var9 - this.e.bn;
                double var21 = var11 - this.e.bo;
                double var23 = var15 * var15 + var19 * var19 + var21 * var21;
                if (var23 > 100.0D) {
                    a.warning(this.e.v + " moved too quickly!");
                    this.a("You moved too quickly :( (Hacking?)");
                    return;
                }

                float var25 = 0.0625F;
                boolean var26 = var2.a(this.e, this.e.bw.b().e(var25, var25, var25)).size() == 0;
                if (this.e.bx && !var1.g && var19 > 0.0D) {
                    this.e.c(0.2F);
                }

                this.e.a(var15, var19, var21);
                this.e.bx = var1.g;
                this.e.b(var15, var19, var21);
                double var27 = var19;
                var15 = var7 - this.e.bm;
                var19 = var9 - this.e.bn;
                if (var19 > -0.5D || var19 < 0.5D) {
                    var19 = 0.0D;
                }

                var21 = var11 - this.e.bo;
                var23 = var15 * var15 + var19 * var19 + var21 * var21;
                boolean var29 = false;
                if (var23 > 0.0625D && !this.e.Z() && !this.e.c.b()) {
                    var29 = true;
                    a.warning(this.e.v + " moved wrongly!");
                    System.out.println("Got position " + var7 + ", " + var9 + ", " + var11);
                    System.out.println("Expected " + this.e.bm + ", " + this.e.bn + ", " + this.e.bo);
                }

                this.e.b(var7, var9, var11, var17, var18);
                boolean var30 = var2.a(this.e, this.e.bw.b().e(var25, var25, var25)).size() == 0;
                // 30 // true if not collides AFTER
                // 26 // true if not collides BEFORE
                if (var26 && (var29 || !var30) && !this.e.Z()) {
                    this.a(this.o, this.p, this.q, var17, var18, this.e.w, this.e.bi.getCanaryDimension().getName());
                    return;
                }

                OAxisAlignedBB var31 = this.e.bw.b().b(var25, var25, var25).a(0.0D, -0.55D, 0.0D);
                if (!this.d.r && !this.e.c.b() && !var2.b(var31)) {
                    if (var27 >= -0.03125D) {
                        ++this.g;
                        if (this.g > 80) {
                            a.warning(this.e.v + " was kicked for floating too long!");
                            this.a("Flying is not enabled on this server");
                            return;
                        }
                    }
                } else {
                    this.g = 0;
                }

                this.e.bx = var1.g;
                this.d.h.d(this.e);
                this.e.b(this.e.bn - var3, var1.g);
            }

        }
    }

    //CanaryMod changed signature to additionally define world destination
    public void a(double var1, double var3, double var5, float var7, float var8, int dimension, String world) {
        // CanaryMod - Teleport hook.
        Dimension dim = Canary.getServer().getWorld(world).getDimension(Dimension.Type.fromId(dimension));
        Location location = new Location(dim, var1, var3, var5, var8, var7);
        TeleportHook hook = new TeleportHook(getUser(), location, false);
        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return;
        }
        // CanaryMod - end.
        this.r = false;
        this.o = var1;
        this.p = var3;
        this.q = var5;
        this.e.b(var1, var3, var5, var7, var8);
        this.e.a.b((new OPacket13PlayerLookMove(var1, var3 + 1.6200000047683716D, var3, var5, var7, var8, false)));
    }
    
    @Override
    public void a(OPacket14BlockDig var1) {
        OWorldServer var2 = (OWorldServer) ((CanaryDimension)this.e.getDimension()).getHandle();
        if (var1.e == 4) {
            this.e.S();
        } else if (var1.e == 5) {
            this.e.N();
        } else {
            boolean spawnBuild = var2.H = var2.t.g != 0 || this.d.h.isOperator(this.e.v) || getUser().isAdmin() || getUser().hasPermission("canary.world.spawnbuild"); //CanaryMod - Check Spawn Build permissions
            boolean var4 = false;
            if (var1.e == 0) {
                var4 = true;
            }

            if (var1.e == 2) {
                var4 = true;
            }

            int var5 = var1.a;
            int var6 = var1.b;
            int var7 = var1.c;
            if (var4) {
                double var8 = this.e.bm - (var5 + 0.5D);
                double var10 = this.e.bn - (var6 + 0.5D) + 1.5D;
                double var12 = this.e.bo - (var7 + 0.5D);
                double var14 = var8 * var8 + var10 * var10 + var12 * var12;
                if (var14 > 36.0D) {
                    return;
                }

                if (var6 >= this.d.t) {
                    return;
                }
            }

            OChunkCoordinates var16 = var2.p();
            int var17 = OMathHelper.a(var5 - var16.a);
            int var18 = OMathHelper.a(var7 - var16.c);
            if (var17 > var18) {
                var18 = var17;
            }

            if (var1.e == 0) {
                if(!getUser().canBuild()){ //CanaryMod - no build rights, no digging
                    return; //TempDisable XXX
                }
                
                //CanaryMod start - onBlockLeftClick
                Block block = var2.getCanaryDimension().getBlockAt(var5, var6, var7);
                //Call hook
                LeftClickHook hook = new LeftClickHook(getUser(), block);
                Canary.hooks().callHook(hook);
                if ((var18 <= Configuration.getWorldConfig(var2.getCanaryDimension().getName()).getSpawnProtectionSize() && !spawnBuild) || hook.isCanceled()) {
                    this.e.a.b((new OPacket53BlockChange(var5, var6, var7, var2)));
                } else {
                    this.e.c.a(var5, var6, var7, var1.d);
                }
            } else if (var1.e == 2) {
                this.e.c.a(var5, var6, var7);
                if (var2.a(var5, var6, var7) != 0) {
                    this.e.a.b((new OPacket53BlockChange(var5, var6, var7, var2)));
                }
            } else if (var1.e == 3) {
                double var19 = this.e.bm - (var5 + 0.5D);
                double var21 = this.e.bn - (var6 + 0.5D);
                double var23 = this.e.bo - (var7 + 0.5D);
                double var25 = var19 * var19 + var21 * var21 + var23 * var23;
                if (var25 < 256.0D) {
                    this.e.a.b((new OPacket53BlockChange(var5, var6, var7, var2)));
                }
            }

            var2.H = false;
        }
    }

    // CanaryMod: Store the blocks between blockPlaced packets
    private Block lastRightClicked;
    
    @Override
    public void a(OPacket15Place var1) {
        OWorldServer var2 = (OWorldServer) ((CanaryDimension)this.e.getDimension()).getHandle();
        OItemStack var3 = this.e.k.d();
        boolean var4 = false;
        int var5 = var1.a;
        int var6 = var1.b;
        int var7 = var1.c;
        int var8 = var1.d;
        boolean spawnBuild = var2.H = var2.t.g != 0 || this.d.h.isOperator(this.e.v) || getUser().isAdmin() || getUser().hasPermission("canary.world.spawnbuild"); //CanaryMod - Check spawn build permission
        
        // CanaryMod: Store block data to call hooks
        // CanaryMod START
        Block blockClicked;
        Block blockPlaced = null;
        
        if (var1.d == 255) {
            // ITEM_USE -- if we have a lastRightClicked then it could be a
            // usable location
            blockClicked = lastRightClicked;
            lastRightClicked = null;
        } else {
            // RIGHTCLICK or BLOCK_PLACE .. or nothing
            blockClicked = var2.getCanaryDimension().getBlockAt(var1.a, var1.b, var1.c);
            blockClicked.setFaceClicked(BlockFace.fromByte((byte) var1.d));
            
            lastRightClicked = blockClicked;
        }
        
         // If we clicked on something then we also have a location to place the block
        if (blockClicked != null && var3 != null) {
            blockPlaced = new CanaryBlock((short)var1.d, (byte)0, blockClicked.getX(), blockClicked.getY(), blockClicked.getZ());
            switch (var1.d) {
                case 0:
                    blockPlaced.setY(blockPlaced.getY() - 1);
                    break;

                case 1:
                    blockPlaced.setY(blockPlaced.getY() + 1);
                    break;

                case 2:
                    blockPlaced.setZ(blockPlaced.getZ() - 1);
                    break;

                case 3:
                    blockPlaced.setZ(blockPlaced.getZ() + 1);
                    break;

                case 4:
                    blockPlaced.setX(blockPlaced.getX() - 1);
                    break;

                case 5:
                    blockPlaced.setX(blockPlaced.getX() + 1);
                    break;
            }
        }

        // CanaryMod: END
        
        if (var1.d == 255) {
            if (var3 == null) {
                return;
            }

            this.e.c.itemUsed(this.e, var2, var3, blockPlaced, blockPlaced); //CanaryMod redirected to our itemUsed
        } else if (var1.b >= this.d.t - 1 && (var1.d == 1 || var1.b >= this.d.t)) {
            this.e.a.b((new OPacket3Chat("\u00a77Height limit for building is " + this.d.t)));
            var4 = true;
        } else {
            OChunkCoordinates var10 = var2.p();
            int var11 = OMathHelper.a(var5 - var10.a);
            int var12 = OMathHelper.a(var7 - var10.c);
            if (var11 > var12) {
                var12 = var11;
            }
            
            // CanaryMod - onBlockRightClicked
            Item item = (var3 != null) ? var3.getCanaryItem() : new CanaryItem(new OItemStack(0, 0, 0));
            RightClickHook hook = new RightClickHook(getUser(), blockPlaced, blockClicked, item, null, Hook.Type.BLOCK_RIGHTCLICKED);
            Canary.hooks().callHook(hook);
            if (this.r && this.e.e(var5 + 0.5D, var6 + 0.5D, var7 + 0.5D) < 64.0D && (var12 > Configuration.getWorldConfig(var2.getCanaryDimension().getName()).getSpawnProtectionSize() || spawnBuild) && getUser().canBuild() && !hook.isCanceled()) { //XXX
                this.e.c.a(this.e, var2, var3, var5, var6, var7, var8);
            }
            else {
                // CanaryMod: No point sending the client to update the blocks, you weren't allowed to place!
                this.e.a.b((OPacket) (new OPacket53BlockChange(var5, var6, var7, var2)));
                var2.y = false;
                return;
            }

            var4 = true;
        }

        if (var4) {
            this.e.a.b((new OPacket53BlockChange(var5, var6, var7, var2)));
            if (var8 == 0) {
                --var6;
            }

            if (var8 == 1) {
                ++var6;
            }

            if (var8 == 2) {
                --var7;
            }

            if (var8 == 3) {
                ++var7;
            }

            if (var8 == 4) {
                --var5;
            }

            if (var8 == 5) {
                ++var5;
            }

            this.e.a.b((new OPacket53BlockChange(var5, var6, var7, var2)));
        }

        var3 = this.e.k.d();
        if (var3 != null && var3.a == 0) {
            this.e.k.a[this.e.k.c] = null;
            var3 = null;
        }

        if (var3 == null || var3.l() == 0) {
            this.e.h = true;
            this.e.k.a[this.e.k.c] = OItemStack.b(this.e.k.a[this.e.k.c]);
            OSlot var13 = this.e.m.a((OIInventory)this.e.k, this.e.k.c);
            this.e.m.a();
            this.e.h = false;
            if (!OItemStack.b(this.e.k.d(), var1.e)) {
                this.b((new OPacket103SetSlot(this.e.m.f, var13.c, this.e.k.d())));
            }
        }

        var2.H = false;
    }

    @Override
    public void a(String var1, Object[] var2) {
        a.info(this.e.v + " lost connection: " + var1);
        //CanaryMod start - onPlayerDisconnect
        ConnectionHook hook = new ConnectionHook(getUser(), Colors.Yellow + getUser().getName() + " left the game.", var1);
        Canary.hooks().callHook(hook);
        if (!hook.isHidden()) {
            this.d.h.sendPacketToAll((new OPacket3Chat(hook.getMessage())));
        }
        //CanaryMod end
        this.d.h.e(this.e);
        this.c = true;
    }

    @Override
    public void a(OPacket var1) {
        a.warning(this.getClass() + " wasn\'t prepared to deal with a " + var1.getClass());
        this.a("Protocol error, unexpected packet");
    }

    public void b(OPacket var1) {
        this.b.a(var1);
    }

    @Override
    public void a(OPacket16BlockItemSwitch var1) {
        if (var1.a >= 0 && var1.a < OInventoryPlayer.h()) {
            this.e.k.c = var1.a;
        } else {
            a.warning(this.e.v + " tried to set an invalid carried item");
        }
    }

    /**
     * Makes player chat
     */
    @Override
    public void playerChat(OPacket3Chat var1) {
        //Re-route to Canary chat
        player.chat(var1.a);
    }
    
    /**
     * Sends a message to the player
     * 
     * @param msg
     */
    public void sendMessage(String msg) {
        if (msg.length() >= 119) {
            String cutMsg = msg.substring(0, 118);
            int finalCut = cutMsg.lastIndexOf(" ");
            String subCut = cutMsg.substring(0, finalCut);
            String newMsg = msg.substring(finalCut + 1);

            b(new OPacket3Chat(subCut));
            sendMessage(newMsg);
        } else {
            b(new OPacket3Chat(msg));
        }
    }

//    private void c(String var1) {
//        if (var1.toLowerCase().startsWith("/me ")) {
//            var1 = "* " + this.e.v + " " + var1.substring(var1.indexOf(" ")).trim();
//            a.info(var1);
//            this.d.h.a((new OPacket3Chat(var1)));
//        } else if (var1.toLowerCase().startsWith("/kill")) {
//            this.e.a(ODamageSource.k, 1000);
//        } else if (var1.toLowerCase().startsWith("/tell ")) {
//            String[] var2 = var1.split(" ");
//            if (var2.length >= 3) {
//                var1 = var1.substring(var1.indexOf(" ")).trim();
//                var1 = var1.substring(var1.indexOf(" ")).trim();
//                var1 = "\u00a77" + this.e.v + " whispers " + var1;
//                a.info(var1 + " to " + var2[1]);
//                if (!this.d.h.a(var2[1], (new OPacket3Chat(var1)))) {
//                    this.b((new OPacket3Chat("\u00a7cThere\'s no player by that name online.")));
//                }
//            }
//        } else {
//            String var3;
//            if (this.d.h.h(this.e.v)) {
//                var3 = var1.substring(1);
//                a.info(this.e.v + " issued server command: " + var3);
//                this.d.a(var3, this);
//            } else {
//                var3 = var1.substring(1);
//                a.info(this.e.v + " tried command: " + var3);
//            }
//        }
//
//    }

    @Override
    public void a(OPacket18Animation var1) {
        if (var1.b == 1) {
            this.e.C_();
        }

    }

    @Override
    public void a(OPacket19EntityAction var1) {
        if (var1.b == 1) {
            this.e.g(true);
        } else if (var1.b == 2) {
            this.e.g(false);
        } else if (var1.b == 4) {
            this.e.h(true);
        } else if (var1.b == 5) {
            this.e.h(false);
        } else if (var1.b == 3) {
            this.e.a(false, true, true);
            this.r = false;
        }

    }

    @Override
    public void a(OPacket255KickDisconnect var1) {
        this.b.a("disconnect.quitting", new Object[0]);
    }

    public int b() {
        return this.b.e();
    }

    @Override
    public void b(String var1) {
        this.b((new OPacket3Chat("\u00a77" + var1)));
    }

    @Override
    public String d() {
        return this.e.v;
    }

    @Override
    public void a(OPacket7UseEntity var1) {
        OWorldServer var2 = (OWorldServer) ((CanaryDimension)this.e.getDimension()).getHandle();
        OEntity var3 = var2.a(var1.b);
        if (var3 != null) {
            boolean var4 = this.e.h(var3);
            double var5 = 36.0D;
            if (!var4) {
                var5 = 9.0D;
            }

            if (this.e.j(var3) < var5) {
                if (var1.c == 0) {
                    this.e.e(var3);
                } else if (var1.c == 1) {
                    this.e.f(var3);
                }
            }
        }

    }

    @Override
    public void a(OPacket9Respawn var1) {
        // CanaryMod: onPlayerRespawn
        Location respawnLocation = getUser().getDimension().getSpawnLocation();
        if (this.e.j) {
            PlayerRespawnHook hook = new PlayerRespawnHook(e.getPlayer(), respawnLocation);
            Canary.hooks().callHook(hook);
            this.e = this.d.h.a(this.e, 0, true, hook.getRespawnLocation());
        } else {
            if (this.e.aD() > 0) {
                return;
            }
            PlayerRespawnHook hook = new PlayerRespawnHook(e.getPlayer(), respawnLocation);
            Canary.hooks().callHook(hook);
            this.e = this.d.h.a(this.e, 0, false, hook.getRespawnLocation());
        }

    }

    @Override
    public void a(OPacket101CloseWindow var1) {
        this.e.H();
    }

    @Override
    public void a(OPacket102WindowClick var1) {
        if (this.e.m.f == var1.a && this.e.m.c(this.e)) {
            OItemStack var2 = this.e.m.a(var1.b, var1.c, var1.f, this.e);
            if (OItemStack.b(var1.e, var2)) {
                this.e.a.b((new OPacket106Transaction(var1.a, var1.d, true)));
                this.e.h = true;
                this.e.m.a();
                this.e.G();
                this.e.h = false;
            } else {
                this.s.a(this.e.m.f, Short.valueOf(var1.d));
                this.e.a.b((new OPacket106Transaction(var1.a, var1.d, false)));
                this.e.m.a(this.e, false);
                ArrayList var3 = new ArrayList();

                for (int var4 = 0; var4 < this.e.m.e.size(); ++var4) {
                    var3.add(((OSlot) this.e.m.e.get(var4)).b());
                }

                this.e.a(this.e.m, var3);
            }
        }

    }

    @Override
    public void a(OPacket108EnchantItem var1) {
        if (this.e.m.f == var1.a && this.e.m.c(this.e)) {
            this.e.m.a(this.e, var1.b);
            this.e.m.a();
        }

    }

    @Override
    public void a(OPacket107CreativeSetSlot var1) {
        if (this.e.c.b()) {
            boolean var2 = var1.a < 0;
            OItemStack var3 = var1.b;
            boolean var4 = var1.a >= 36 && var1.a < 36 + OInventoryPlayer.h();
            boolean var5 = var3 == null || var3.c < OItem.d.length && var3.c >= 0 && OItem.d[var3.c] != null;
            boolean var6 = var3 == null || var3.h() >= 0 && var3.h() >= 0 && var3.a <= 64 && var3.a > 0;
            if (var4 && var5 && var6) {
                if (var3 == null) {
                    this.e.l.a(var1.a, (OItemStack) null);
                } else {
                    this.e.l.a(var1.a, var3);
                }

                this.e.l.a(this.e, true);
            } else if (var2 && var5 && var6 && this.n < 200) {
                this.n += 20;
                OEntityItem var7 = this.e.b(var3);
                if (var7 != null) {
                    var7.k();
                }
            }
        }

    }

    @Override
    public void a(OPacket106Transaction var1) {
        Short var2 = (Short) this.s.a(this.e.m.f);
        if (var2 != null && var1.b == var2.shortValue() && this.e.m.f == var1.a && !this.e.m.c(this.e)) {
            this.e.m.a(this.e, true);
        }

    }

    @Override
    public void a(OPacket130UpdateSign var1) {
        OWorldServer var2 = (OWorldServer) ((CanaryDimension)this.e.getDimension()).getHandle();
        if (var2.i(var1.a, var1.b, var1.c)) {
            OTileEntity var3 = var2.b(var1.a, var1.b, var1.c);
            if (var3 instanceof OTileEntitySign) {
                OTileEntitySign var4 = (OTileEntitySign) var3;
                if (!var4.c()) {
                    this.d.c("Player " + this.e.v + " just tried to change non-editable sign");
                    return;
                }
            }

            int var6;
            int var9;
            for (var9 = 0; var9 < 4; ++var9) {
                boolean var5 = true;
                if (var1.d[var9].length() > 15) {
                    var5 = false;
                } else {
                    for (var6 = 0; var6 < var1.d[var9].length(); ++var6) {
                        if (OChatAllowedCharacters.a.indexOf(var1.d[var9].charAt(var6)) < 0) {
                            var5 = false;
                        }
                    }
                }

                if (!var5) {
                    var1.d[var9] = "!?";
                }
            }

                    
            if (var3 instanceof OTileEntitySign) {
                var9 = var1.a;
                int var10 = var1.b;
                var6 = var1.c;
                OTileEntitySign var7 = (OTileEntitySign) var3;


                // CanaryMod: Copy the old line text
                String[] old = Arrays.copyOf(var7.a, var7.a.length);
                           
                for (int var8 = 0; var8 < 4; ++var8) {
                    var7.a[var8] = var1.d[var8];
                }
                
                //CanaryMod start - onSignChange
                CanarySign sign = new CanarySign(var7);
                SignHook hook = new SignHook(getUser(), sign, true);
                Canary.hooks().callHook(hook);
                if(hook.isCanceled()){
                    var7.a = Arrays.copyOf(old, old.length);
                }
                //CanaryMod end - onSignChange
                
                var7.G_();
                var2.j(var9, var10, var6);
            }
        }

    }

    @Override
    public void a(OPacket0KeepAlive var1) {
        if (var1.a == this.i) {
            int var2 = (int) (System.nanoTime() / 1000000L - this.j);
            this.e.i = (this.e.i * 3 + var2) / 4;
        }

    }

    @Override
    public boolean c() {
        return true;
    }

    @Override
    public void a(OPacket202PlayerAbilities var1) {
        this.e.L.b = var1.b && this.e.L.c;
    }
}
