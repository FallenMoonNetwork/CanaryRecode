package net.canarymod.api.statistics;

import net.canarymod.api.statistics.Stat;
import net.minecraft.server.StatBase;

/**
 * Stat wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryStat implements Stat {
    private StatBase statbase;

    /**
     * Constructs a new StatBase wrapper
     * 
     * @param statbase
     *            the StatBase to wrap
     */
    public CanaryStat(StatBase statbase) {
        this.statbase = statbase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return statbase.e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return statbase.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIndependent() {
        return statbase.f;
    }

    public StatBase getHandle() {
        return statbase;
    }
}
