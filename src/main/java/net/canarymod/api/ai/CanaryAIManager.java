package net.canarymod.api.ai;

import java.util.Iterator;
import net.canarymod.Canary;
import net.minecraft.server.EntityAITaskEntry;
import net.minecraft.server.EntityAITasks;

/**
 *
 * @author Somners
 */
public class CanaryAIManager implements AIManager {

    private EntityAITasks tasks;

    public CanaryAIManager(EntityAITasks tasks) {
        this.tasks = tasks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addTask(int priority, Class<? extends AIBase> ai) {
        if (!this.hasTask(ai)) {
            try {
                tasks.a(priority, new EntityAICanary(ai.newInstance()));
                return true;
            } catch (InstantiationException ex) {
                Canary.logStacktrace("Exception adding new AI taks in Canary's AIManager: ", ex);
            } catch (IllegalAccessException ex) {
                Canary.logStacktrace("Exception adding new AI taks in Canary's AIManager: ", ex);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeTask(Class<? extends AIBase> ai) {
        if (this.hasTask(ai)) {
            Iterator<?> it = tasks.a.iterator();
            while (it.hasNext()) {
                EntityAITaskEntry entry = (EntityAITaskEntry)it.next();
                if (entry.a instanceof EntityAICanary) {
                    if (((EntityAICanary)entry.a).getAIBase().getClass().equals(ai)) {
                        tasks.a(entry.a);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasTask(Class<? extends AIBase> ai) {
        Iterator<?> it = tasks.a.iterator();
        while (it.hasNext()) {
            EntityAITaskEntry entry = (EntityAITaskEntry)it.next();
            if (entry.a instanceof EntityAICanary) {
                if (((EntityAICanary)entry.a).getAIBase().getClass().equals(ai)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AIBase getTask(Class<? extends AIBase> ai) {
        Iterator<?> it = tasks.a.iterator();
        while (it.hasNext()) {
            EntityAITaskEntry entry = (EntityAITaskEntry)it.next();
            if (entry.a instanceof EntityAICanary) {
                if (((EntityAICanary)entry.a).getAIBase().getClass().equals(ai)) {
                    return ((EntityAICanary)entry.a).getAIBase();
                }
            }
        }
        return null;
    }

    @Override
    public boolean addTask(int priority, AIBase ai) {
        if (!this.hasTask(ai.getClass())) {
            tasks.a(priority, new EntityAICanary(ai));
            return true;
        }
        return false;
    }
}
