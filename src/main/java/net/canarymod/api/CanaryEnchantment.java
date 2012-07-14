package net.canarymod.api;

import net.canarymod.api.entity.EntityLiving;
import net.minecraft.server.OEnchantment;

public class CanaryEnchantment implements Enchantment {
    private Type type;
    private int level;
    private OEnchantment handle;

    public CanaryEnchantment(Type type, int level) {
        this.type = type;
        this.level = level;
    }
    
    public CanaryEnchantment(OEnchantment handle) {
        this.handle = handle;
    }
    
    /**
     * Get the OEnchantment for this wrapper
     * @return
     */
    public OEnchantment getHandle() {
        return handle;
    }

    @Override
    public boolean canStack(Enchantment ench) {
        return handle.a(((CanaryEnchantment) ench).handle);
    }

    @Override
    public int getDamageDone(EntityLiving arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDamageModifier(Object arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Enchantment getEnchantment(Type type) { // You know, this seems kind
                                                   // of pointless actually
        OEnchantment ench = OEnchantment.b[type.getId()];
        if (ench != null) {
            return new CanaryEnchantment(ench);
        }
        return null;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getMaxEnchantmentLevel() {
        return handle.a();
    }

    @Override
    public int getMinEnchantmentLevel() {
        return handle.c();
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public int getWeight() {
        return handle.b();
    }

    @Override
    public boolean isValid() {
        return level >= getMinEnchantmentLevel()
                && level <= getMaxEnchantmentLevel();
    }

    @Override
    public void setType(Type type) {
        OEnchantment ench = OEnchantment.b[type.getId()];
        this.type = type;
        this.handle = ench;
    }

    @Override
    public void setType(int type) {
        if (type > OEnchantment.b.length || type < 0)
            return;
        OEnchantment ench = OEnchantment.b[type];
        if (ench != null) {
            handle = ench;
        }
    }
}
