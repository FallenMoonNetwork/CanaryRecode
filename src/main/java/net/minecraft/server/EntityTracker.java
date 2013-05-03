package net.minecraft.server;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;

import net.canarymod.api.CanaryEntityTracker;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.config.Configuration;


public class EntityTracker {

    private final WorldServer a;
    private Set b = new HashSet();
    private IntHashMap c = new IntHashMap();
    private int d;

    private CanaryEntityTracker canaryTracker;

    public EntityTracker(WorldServer worldserver) {
        this.a = worldserver;
        this.d = worldserver.p().ad().a();
        canaryTracker = new CanaryEntityTracker(this, worldserver.getCanaryWorld());
    }

    public void a(Entity entity) {
        if (entity instanceof EntityPlayerMP) {
            this.a(entity, 512, 2);
            EntityPlayerMP entityplayermp = (EntityPlayerMP) entity;
            Iterator iterator = this.b.iterator();

            while (iterator.hasNext()) {
                EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

                if (entitytrackerentry.a != entityplayermp) {
                    entitytrackerentry.b(entityplayermp);
                }
            }
        } else if (entity instanceof EntityFishHook) {
            this.a(entity, 64, 5, true);
        } else if (entity instanceof EntityArrow) {
            this.a(entity, 64, 20, false);
        } else if (entity instanceof EntitySmallFireball) {
            this.a(entity, 64, 10, false);
        } else if (entity instanceof EntityFireball) {
            this.a(entity, 64, 10, false);
        } else if (entity instanceof EntitySnowball) {
            this.a(entity, 64, 10, true);
        } else if (entity instanceof EntityEnderPearl) {
            this.a(entity, 64, 10, true);
        } else if (entity instanceof EntityEnderEye) {
            this.a(entity, 64, 4, true);
        } else if (entity instanceof EntityEgg) {
            this.a(entity, 64, 10, true);
        } else if (entity instanceof EntityPotion) {
            this.a(entity, 64, 10, true);
        } else if (entity instanceof EntityExpBottle) {
            this.a(entity, 64, 10, true);
        } else if (entity instanceof EntityFireworkRocket) {
            this.a(entity, 64, 10, true);
        } else if (entity instanceof EntityItem) {
            this.a(entity, 64, 20, true);
        } else if (entity instanceof EntityMinecart) {
            this.a(entity, 80, 3, true);
        } else if (entity instanceof EntityBoat) {
            this.a(entity, 80, 3, true);
        } else if (entity instanceof EntitySquid) {
            this.a(entity, 64, 3, true);
        } else if (entity instanceof EntityWither) {
            this.a(entity, 80, 3, false);
        } else if (entity instanceof EntityBat) {
            this.a(entity, 80, 3, false);
        } else if (entity instanceof IAnimals) {
            this.a(entity, 80, 3, true);
        } else if (entity instanceof EntityDragon) {
            this.a(entity, 160, 3, true);
        } else if (entity instanceof EntityTNTPrimed) {
            this.a(entity, 160, 10, true);
        } else if (entity instanceof EntityFallingSand) {
            this.a(entity, 160, 20, true);
        } else if (entity instanceof EntityPainting) {
            this.a(entity, 160, Integer.MAX_VALUE, false);
        } else if (entity instanceof EntityXPOrb) {
            this.a(entity, 160, 20, true);
        } else if (entity instanceof EntityEnderCrystal) {
            this.a(entity, 256, Integer.MAX_VALUE, false);
        } else if (entity instanceof EntityItemFrame) {
            this.a(entity, 160, Integer.MAX_VALUE, false);
        }
    }

    public void a(Entity entity, int i0, int i1) {
        this.a(entity, i0, i1, false);
    }

    public void a(Entity entity, int i0, int i1, boolean flag0) {
        if (i0 > this.d) {
            i0 = this.d;
        }

        try {
            if (this.c.b(entity.k)) {
                throw new IllegalStateException("Entity is already tracked!");
            }

            EntityTrackerEntry entitytrackerentry = new EntityTrackerEntry(entity, i0, i1, flag0);

            this.b.add(entitytrackerentry);
            this.c.a(entity.k, entitytrackerentry);
            CanaryWorld dim = entity.getCanaryWorld();

            entitytrackerentry.b(dim.getHandle().h);
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Adding entity to track");
            CrashReportCategory crashreportcategory = crashreport.a("Entity To Track");

            crashreportcategory.a("Tracking range", (i0 + " blocks"));
            crashreportcategory.a("Update interval", (Callable) (new CallableEntityTracker(this, i1)));
            entity.a(crashreportcategory);
            CrashReportCategory crashreportcategory1 = crashreport.a("Entity That Is Already Tracked");

            ((EntityTrackerEntry) this.c.a(entity.k)).a.a(crashreportcategory1);

            try {
                throw new ReportedException(crashreport);
            } catch (ReportedException reportedexception) {
                // CanaryMod only dump this in debug mode
                // That exception is of informational nature and has no side-effects
                if (Configuration.getServerConfig().isDebugMode()) {
                    System.err.println("\"Silently\" catching entity tracking error.");
                    reportedexception.printStackTrace();
                }
            }
        }
    }

    public void b(Entity entity) {
        if (entity instanceof EntityPlayerMP) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP) entity;
            Iterator iterator = this.b.iterator();

            while (iterator.hasNext()) {
                EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

                entitytrackerentry.a(entityplayermp);
            }
        }

        EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry) this.c.d(entity.k);

        if (entitytrackerentry1 != null) {
            this.b.remove(entitytrackerentry1);
            entitytrackerentry1.a();
        }
    }

    public void a() {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

            entitytrackerentry.a(this.a.h);
            if (entitytrackerentry.n && entitytrackerentry.a instanceof EntityPlayerMP) {
                arraylist.add((EntityPlayerMP) entitytrackerentry.a);
            }
        }

        for (int i0 = 0; i0 < arraylist.size(); ++i0) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP) arraylist.get(i0);
            Iterator iterator1 = this.b.iterator();

            while (iterator1.hasNext()) {
                EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry) iterator1.next();

                if (entitytrackerentry1.a != entityplayermp) {
                    entitytrackerentry1.b(entityplayermp);
                }
            }
        }
    }

    public void a(Entity entity, Packet packet) {
        EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) this.c.a(entity.k);

        if (entitytrackerentry != null) {
            entitytrackerentry.a(packet);
        }
    }

    public void b(Entity entity, Packet packet) {
        EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) this.c.a(entity.k);

        if (entitytrackerentry != null) {
            entitytrackerentry.b(packet);
        }
    }

    public void a(EntityPlayerMP entityplayermp) {
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

            entitytrackerentry.c(entityplayermp);
        }
    }

    public void a(EntityPlayerMP entityplayermp, Chunk chunk) {
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry) iterator.next();

            if (entitytrackerentry.a != entityplayermp && entitytrackerentry.a.aj == chunk.g && entitytrackerentry.a.al == chunk.h) {
                entitytrackerentry.b(entityplayermp);
            }
        }
    }

    /**
     * Get the CanaryEntityTracker wrapper
     * @return
     */
    public CanaryEntityTracker getCanaryEntityTracker() {
        return canaryTracker;
    }

    /**
     * Get the HashSet of tracked entity entries
     * @return
     */
    public Set<EntityTrackerEntry> getTrackedEntities() {
        return b;
    }
}
