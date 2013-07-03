package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;
import net.canarymod.api.CanaryPlayerManager;

public class PlayerManager {

    private final WorldServer a;
    private final List b = new ArrayList();
    private final LongHashMap c = new LongHashMap();
    private final List d = new ArrayList();
    private final List e = new ArrayList();
    private final int f;
    private long g;
    private final int[][] h = new int[][]{ { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    // CanaryMod
    private CanaryPlayerManager playerManager;

    //
    public PlayerManager(WorldServer worldserver, int i0) {
        if (i0 > 15) {
            throw new IllegalArgumentException("Too big view radius!");
        } else if (i0 < 3) {
            throw new IllegalArgumentException("Too small view radius!");
        } else {
            this.f = i0;
            this.a = worldserver;
        }
        playerManager = new CanaryPlayerManager(this, worldserver.getCanaryWorld());
    }

    public WorldServer a() {
        return this.a;
    }

    public void b() {
        long i0 = this.a.I();
        int i1;
        PlayerInstance playerinstance;

        if (i0 - this.g > 8000L) {
            this.g = i0;

            for (i1 = 0; i1 < this.e.size(); ++i1) {
                playerinstance = (PlayerInstance) this.e.get(i1);
                playerinstance.b();
                playerinstance.a();
            }
        } else {
            for (i1 = 0; i1 < this.d.size(); ++i1) {
                playerinstance = (PlayerInstance) this.d.get(i1);
                playerinstance.b();
            }
        }

        this.d.clear();
        if (this.b.isEmpty()) {
            WorldProvider worldprovider = this.a.t;

            if (!worldprovider.e()) {
                this.a.b.a();
            }
        }
    }

    private PlayerInstance a(int i0, int i1, boolean flag0) {
        long i2 = (long) i0 + 2147483647L | (long) i1 + 2147483647L << 32;
        PlayerInstance playerinstance = (PlayerInstance) this.c.a(i2);

        if (playerinstance == null && flag0) {
            playerinstance = new PlayerInstance(this, i0, i1);
            this.c.a(i2, playerinstance);
            this.e.add(playerinstance);
        }

        return playerinstance;
    }

    public void a(int i0, int i1, int i2) {
        int i3 = i0 >> 4;
        int i4 = i2 >> 4;
        PlayerInstance playerinstance = this.a(i3, i4, false);

        if (playerinstance != null) {
            playerinstance.a(i0 & 15, i1, i2 & 15);
        }
    }

    public void a(EntityPlayerMP entityplayermp) {
        int i0 = (int) entityplayermp.u >> 4;
        int i1 = (int) entityplayermp.w >> 4;

        entityplayermp.d = entityplayermp.u;
        entityplayermp.e = entityplayermp.w;

        for (int i2 = i0 - this.f; i2 <= i0 + this.f; ++i2) {
            for (int i3 = i1 - this.f; i3 <= i1 + this.f; ++i3) {
                this.a(i2, i3, true).a(entityplayermp);
            }
        }

        this.b.add(entityplayermp);
        this.b(entityplayermp);
    }

    public void b(EntityPlayerMP entityplayermp) {
        ArrayList arraylist = new ArrayList(entityplayermp.f);
        int i0 = 0;
        int i1 = this.f;
        int i2 = (int) entityplayermp.u >> 4;
        int i3 = (int) entityplayermp.w >> 4;
        int i4 = 0;
        int i5 = 0;
        ChunkCoordIntPair chunkcoordintpair = PlayerInstance.a(this.a(i2, i3, true));

        entityplayermp.f.clear();
        if (arraylist.contains(chunkcoordintpair)) {
            entityplayermp.f.add(chunkcoordintpair);
        }

        int i6;

        for (i6 = 1; i6 <= i1 * 2; ++i6) {
            for (int i7 = 0; i7 < 2; ++i7) {
                int[] aint = this.h[i0++ % 4];

                for (int i8 = 0; i8 < i6; ++i8) {
                    i4 += aint[0];
                    i5 += aint[1];
                    chunkcoordintpair = PlayerInstance.a(this.a(i2 + i4, i3 + i5, true));
                    if (arraylist.contains(chunkcoordintpair)) {
                        entityplayermp.f.add(chunkcoordintpair);
                    }
                }
            }
        }

        i0 %= 4;

        for (i6 = 0; i6 < i1 * 2; ++i6) {
            i4 += this.h[i0][0];
            i5 += this.h[i0][1];
            chunkcoordintpair = PlayerInstance.a(this.a(i2 + i4, i3 + i5, true));
            if (arraylist.contains(chunkcoordintpair)) {
                entityplayermp.f.add(chunkcoordintpair);
            }
        }
    }

    public void c(EntityPlayerMP entityplayermp) {
        int i0 = (int) entityplayermp.d >> 4;
        int i1 = (int) entityplayermp.e >> 4;

        for (int i2 = i0 - this.f; i2 <= i0 + this.f; ++i2) {
            for (int i3 = i1 - this.f; i3 <= i1 + this.f; ++i3) {
                PlayerInstance playerinstance = this.a(i2, i3, false);

                if (playerinstance != null) {
                    playerinstance.b(entityplayermp);
                }
            }
        }

        this.b.remove(entityplayermp);
    }

    private boolean a(int i0, int i1, int i2, int i3, int i4) {
        int i5 = i0 - i2;
        int i6 = i1 - i3;

        return i5 >= -i4 && i5 <= i4 ? i6 >= -i4 && i6 <= i4 : false;
    }

    public void d(EntityPlayerMP entityplayermp) {
        int i0 = (int) entityplayermp.u >> 4;
        int i1 = (int) entityplayermp.w >> 4;
        double d0 = entityplayermp.d - entityplayermp.u;
        double d1 = entityplayermp.e - entityplayermp.w;
        double d2 = d0 * d0 + d1 * d1;

        if (d2 >= 64.0D) {
            int i2 = (int) entityplayermp.d >> 4;
            int i3 = (int) entityplayermp.e >> 4;
            int i4 = this.f;
            int i5 = i0 - i2;
            int i6 = i1 - i3;

            if (i5 != 0 || i6 != 0) {
                for (int i7 = i0 - i4; i7 <= i0 + i4; ++i7) {
                    for (int i8 = i1 - i4; i8 <= i1 + i4; ++i8) {
                        if (!this.a(i7, i8, i2, i3, i4)) {
                            this.a(i7, i8, true).a(entityplayermp);
                        }

                        if (!this.a(i7 - i5, i8 - i6, i0, i1, i4)) {
                            PlayerInstance playerinstance = this.a(i7 - i5, i8 - i6, false);

                            if (playerinstance != null) {
                                playerinstance.b(entityplayermp);
                            }
                        }
                    }
                }

                this.b(entityplayermp);
                entityplayermp.d = entityplayermp.u;
                entityplayermp.e = entityplayermp.w;
            }
        }
    }

    public boolean a(EntityPlayerMP entityplayermp, int i0, int i1) {
        PlayerInstance playerinstance = this.a(i0, i1, false);

        return playerinstance == null ? false : PlayerInstance.b(playerinstance).contains(entityplayermp) && !entityplayermp.f.contains(PlayerInstance.a(playerinstance));
    }

    public static int a(int i0) {
        return i0 * 16 - 16;
    }

    static WorldServer a(PlayerManager playermanager) {
        return playermanager.a;
    }

    static LongHashMap b(PlayerManager playermanager) {
        return playermanager.c;
    }

    static List c(PlayerManager playermanager) {
        return playermanager.e;
    }

    static List d(PlayerManager playermanager) {
        return playermanager.d;
    }

    /**
     * Get the canary player manager
     * 
     * @return
     */
    public CanaryPlayerManager getPlayerManager() {
        return playerManager;
    }

    // CanaryMod
    public List<EntityPlayerMP> getManagedPlayers() {
        return b;
    }

    public int getPlayerViewRadius() {
        return f;
    }
    //
}
