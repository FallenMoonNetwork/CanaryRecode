package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

class PlayerInstance {

    private final List b;
    private final ChunkCoordIntPair c;
    private short[] d;
    private int e;
    private int f;
    private long g;

    final PlayerManager a;

    public PlayerInstance(PlayerManager playermanager, int i0, int i1) {
        this.a = playermanager;
        this.b = new ArrayList();
        this.d = new short[64];
        this.c = new ChunkCoordIntPair(i0, i1);
        playermanager.a().b.c(i0, i1);
    }

    public void a(EntityPlayerMP entityplayermp) {
        if (this.b.contains(entityplayermp)) {
            throw new IllegalStateException("Failed to add player. " + entityplayermp + " already is in chunk " + this.c.a + ", " + this.c.b);
        }
        else {
            if (this.b.isEmpty()) {
                this.g = PlayerManager.a(this.a).I();
            }

            this.b.add(entityplayermp);
            entityplayermp.f.add(this.c);
        }
    }

    public void b(EntityPlayerMP entityplayermp) {
        if (this.b.contains(entityplayermp)) {
            Chunk chunk = PlayerManager.a(this.a).e(this.c.a, this.c.b);

            entityplayermp.a.b(new Packet51MapChunk(chunk, true, 0));
            this.b.remove(entityplayermp);
            entityplayermp.f.remove(this.c);
            if (this.b.isEmpty()) {
                long i0 = (long)this.c.a + 2147483647L | (long)this.c.b + 2147483647L << 32;

                this.a(chunk);
                PlayerManager.b(this.a).d(i0);
                PlayerManager.c(this.a).remove(this);
                if (this.e > 0) {
                    PlayerManager.d(this.a).remove(this);
                }

                this.a.a().b.b(this.c.a, this.c.b);
            }
        }
    }

    public void a() {
        this.a(PlayerManager.a(this.a).e(this.c.a, this.c.b));
    }

    private void a(Chunk chunk) {
        chunk.q += PlayerManager.a(this.a).I() - this.g;
        this.g = PlayerManager.a(this.a).I();
    }

    public void a(int i0, int i1, int i2) {
        if (this.e == 0) {
            PlayerManager.d(this.a).add(this);
        }

        this.f |= 1 << (i1 >> 4);
        if (this.e < 64) {
            short short1 = (short)(i0 << 12 | i2 << 8 | i1);

            for (int i3 = 0; i3 < this.e; ++i3) {
                if (this.d[i3] == short1) {
                    return;
                }
            }

            this.d[this.e++] = short1;
        }
    }

    public void a(Packet packet) {
        for (int i0 = 0; i0 < this.b.size(); ++i0) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)this.b.get(i0);

            if (!entityplayermp.f.contains(this.c)) {
                entityplayermp.a.b(packet);
            }
        }
    }

    public void b() {
        if (this.e != 0) {
            int i0;
            int i1;
            int i2;

            if (this.e == 1) {
                i0 = this.c.a * 16 + (this.d[0] >> 12 & 15);
                i1 = this.d[0] & 255;
                i2 = this.c.b * 16 + (this.d[0] >> 8 & 15);
                this.a((Packet)(new Packet53BlockChange(i0, i1, i2, PlayerManager.a(this.a))));
                if (PlayerManager.a(this.a).d(i0, i1, i2)) {
                    this.a(PlayerManager.a(this.a).r(i0, i1, i2));
                }
            }
            else {
                int i3;

                if (this.e == 64) {
                    i0 = this.c.a * 16;
                    i1 = this.c.b * 16;
                    this.a((Packet)(new Packet51MapChunk(PlayerManager.a(this.a).e(this.c.a, this.c.b), false, this.f)));

                    for (i2 = 0; i2 < 16; ++i2) {
                        if ((this.f & 1 << i2) != 0) {
                            i3 = i2 << 4;
                            List list = PlayerManager.a(this.a).c(i0, i3, i1, i0 + 16, i3 + 16, i1 + 16);

                            for (int i4 = 0; i4 < list.size(); ++i4) {
                                this.a((TileEntity)list.get(i4));
                            }
                        }
                    }
                }
                else {
                    this.a((Packet)(new Packet52MultiBlockChange(this.c.a, this.c.b, this.d, this.e, PlayerManager.a(this.a))));

                    for (i0 = 0; i0 < this.e; ++i0) {
                        i1 = this.c.a * 16 + (this.d[i0] >> 12 & 15);
                        i2 = this.d[i0] & 255;
                        i3 = this.c.b * 16 + (this.d[i0] >> 8 & 15);
                        if (PlayerManager.a(this.a).d(i1, i2, i3)) {
                            this.a(PlayerManager.a(this.a).r(i1, i2, i3));
                        }
                    }
                }
            }

            this.e = 0;
            this.f = 0;
        }
    }

    private void a(TileEntity tileentity) {
        if (tileentity != null) {
            Packet packet = tileentity.m();

            if (packet != null) {
                this.a(packet);
            }
        }
    }

    static ChunkCoordIntPair a(PlayerInstance playerinstance) {
        return playerinstance.c;
    }

    static List b(PlayerInstance playerinstance) {
        return playerinstance.b;
    }
}
