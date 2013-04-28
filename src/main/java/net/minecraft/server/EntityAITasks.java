package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.canarymod.api.ai.CanaryAIManager;

public class EntityAITasks {

    public List a = new ArrayList(); // CanaryMod: private to public
    public List b = new ArrayList(); // CanaryMod: private to public
    private final Profiler c;
    private int d = 0;
    private int e = 3;

    private CanaryAIManager manager = new CanaryAIManager(this);

    public EntityAITasks(Profiler profiler) {
        this.c = profiler;
    }

    public void a(int i0, EntityAIBase entityaibase) {
        this.a.add(new EntityAITaskEntry(this, i0, entityaibase));
    }

    public void a(EntityAIBase entityaibase) {
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            EntityAITaskEntry entityaitaskentry = (EntityAITaskEntry) iterator.next();
            EntityAIBase entityaibase1 = entityaitaskentry.a;

            if (entityaibase1 == entityaibase) {
                if (this.b.contains(entityaitaskentry)) {
                    entityaibase1.d();
                    this.b.remove(entityaitaskentry);
                }

                iterator.remove();
            }
        }
    }

    public void a() {
        ArrayList arraylist = new ArrayList();
        Iterator iterator;
        EntityAITaskEntry entityaitaskentry;

        if (this.d++ % this.e == 0) {
            iterator = this.a.iterator();

            while (iterator.hasNext()) {
                entityaitaskentry = (EntityAITaskEntry) iterator.next();
                boolean flag0 = this.b.contains(entityaitaskentry);

                if (flag0) {
                    if (this.b(entityaitaskentry) && this.a(entityaitaskentry)) {
                        continue;
                    }

                    entityaitaskentry.a.d();
                    this.b.remove(entityaitaskentry);
                }

                if (this.b(entityaitaskentry) && entityaitaskentry.a.a()) {
                    arraylist.add(entityaitaskentry);
                    this.b.add(entityaitaskentry);
                }
            }
        } else {
            iterator = this.b.iterator();

            while (iterator.hasNext()) {
                entityaitaskentry = (EntityAITaskEntry) iterator.next();
                if (!entityaitaskentry.a.b()) {
                    entityaitaskentry.a.d();
                    iterator.remove();
                }
            }
        }

        this.c.a("goalStart");
        iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            entityaitaskentry = (EntityAITaskEntry) iterator.next();
            this.c.a(entityaitaskentry.a.getClass().getSimpleName());
            entityaitaskentry.a.c();
            this.c.b();
        }

        this.c.b();
        this.c.a("goalTick");
        iterator = this.b.iterator();

        while (iterator.hasNext()) {
            entityaitaskentry = (EntityAITaskEntry) iterator.next();
            entityaitaskentry.a.e();
        }

        this.c.b();
    }

    private boolean a(EntityAITaskEntry entityaitaskentry) {
        this.c.a("canContinue");
        boolean flag0 = entityaitaskentry.a.b();

        this.c.b();
        return flag0;
    }

    private boolean b(EntityAITaskEntry entityaitaskentry) {
        this.c.a("canUse");
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            EntityAITaskEntry entityaitaskentry1 = (EntityAITaskEntry) iterator.next();

            if (entityaitaskentry1 != entityaitaskentry) {
                if (entityaitaskentry.b >= entityaitaskentry1.b) {
                    if (this.b.contains(entityaitaskentry1) && !this.a(entityaitaskentry, entityaitaskentry1)) {
                        this.c.b();
                        return false;
                    }
                } else if (this.b.contains(entityaitaskentry1) && !entityaitaskentry1.a.i()) {
                    this.c.b();
                    return false;
                }
            }
        }

        this.c.b();
        return true;
    }

    private boolean a(EntityAITaskEntry entityaitaskentry, EntityAITaskEntry entityaitaskentry1) {
        return (entityaitaskentry.a.j() & entityaitaskentry1.a.j()) == 0;
    }

    public CanaryAIManager getAIManager() {
        return this.manager;
    }
}
