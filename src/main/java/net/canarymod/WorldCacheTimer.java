package net.canarymod;

import net.canarymod.api.world.CanaryWorld;
import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.TaskManager;

/**
 * This timed task will re-schedule itself every x minutes,
 * depending on configuration, to evaluate if the contained world
 * can be removed from the world manager.
 *
 * This Runnable will also only execute if the server is configured to use it.
 *
 * @author Chris (damagefilter)
 *
 */
public class WorldCacheTimer implements Runnable {
    CanaryWorld world;
    boolean scheduledForRemove = false;

    public WorldCacheTimer(CanaryWorld world) {
        this.world = world;
    }

    @Override
    public void run() {
        boolean resched = false;
        if(scheduledForRemove) {
            if(this.world.getPlayerList().isEmpty()) {
                Canary.getServer().getWorldManager().unloadWorld(world.getName(), world.getType(), false);
            } else {
                scheduledForRemove = false;
                resched = true;
            }
        }
        if(this.world.getPlayerList().isEmpty()) {
            scheduledForRemove = true;
            resched = true;
        }
        if(resched) {
            TaskManager.scheduleDelayedTaskInMinutes(this, Configuration.getServerConfig().getWorldCacheTimeout());
        }
    }

}
