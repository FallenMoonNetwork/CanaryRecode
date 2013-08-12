package net.canarymod.api.world;

import java.util.Random;
import net.minecraft.server.BiomeGenBase;

/**
 *
 * @author Somners
 */
public class CanaryBiome implements Biome {

    private BiomeGenBase handle;

    public CanaryBiome(BiomeGenBase biome) {
        this.handle = biome;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canSpawnLightning() {
        return handle.d();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTropic() {
        return handle.e();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getSpawnChance() {
        throw new UnsupportedOperationException("Method 'getSpawnChance' in class 'CanaryBiome' is not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRainfall() {
        return handle.h();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getTemperature() {
        return handle.j();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decorate(World world, Random rnd, int x, int z) {
        handle.a(((CanaryWorld)world).getHandle(), rnd, x, z);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BiomeType getBiomeType() {
        return BiomeType.fromId((byte)handle.N);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTemperatureAndPrecipitation(float temp, float precipitation) {
        handle.setTemperatureAndPrecipitation(temp, precipitation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanSnow(boolean canSnow) {
        handle.setCanSnow(canSnow);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCanRain(boolean canRain) {
        handle.setCanRain(canRain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canSnow() {
       return handle.c();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canRain() {
        return handle.canRain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(String hexColor) {
        handle.a(Integer.parseInt(hexColor.replaceFirst("#", ""), 16));
    }

    /**
     * {@inheritDoc}
     */
    public BiomeGenBase getHandle() {
        return this.handle;
    }
}