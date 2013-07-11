package net.canarymod.api.entity.living.humanoid;

import net.canarymod.api.CanaryVillagerTrade;
import net.canarymod.api.VillagerTrade;
import net.canarymod.api.entity.CanaryEntity;
import net.canarymod.api.entity.EntityType;
import net.canarymod.api.entity.living.CanaryEntityLiving;
import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.api.world.CanaryVillage;
import net.canarymod.api.world.Village;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityVillager;
import net.minecraft.server.MerchantRecipe;
import net.minecraft.server.MerchantRecipeList;

/**
 * Villager wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryVillager extends CanaryEntityLiving implements Villager {

    /**
     * Constructs a new wrapper for EntityVillager
     * 
     * @param entity
     *            the EntityVillager to wrap
     */
    public CanaryVillager(EntityVillager entity) {
        super(entity);
    }

    public EntityType getEntityType() {
        return EntityType.VILLAGER;
    }

    @Override
    public String getFqName() {
        return "Villager";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Profession getProfession() {
        return Profession.fromId(getHandle().bT());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProfession(Profession profession) {
        getHandle().p(profession.ordinal());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMating() {
        return getHandle().bU();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMating(boolean isMating) {
        getHandle().j(isMating);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlaying() {
        return getHandle().bS();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlaying(boolean playing) {
        getHandle().i(playing);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRevengeTarget(LivingBase targetEntity) {
        getHandle().c((net.minecraft.server.EntityLivingBase) ((CanaryEntity) targetEntity).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCustomer() {
        return (Player) getHandle().m_().getCanaryEntity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCustomer() {
        return getHandle().m_() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCustomer(Player player) {
        getHandle().a(((CanaryPlayer) player).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Village getVillage() {
        return getHandle().bp.getCanaryVillage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVillage(Village village) {
        getHandle().bp = ((CanaryVillage) village).getHandle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGrowingAge() {
        return getHandle().b();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGrowingAge(int age) {
        getHandle().a(age);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VillagerTrade[] getTrades() {
        MerchantRecipeList list = getHandle().b((EntityPlayer) null);
        VillagerTrade[] rt = new VillagerTrade[list.size()];

        for (int i = 0; i < rt.length; i++) {
            rt[i] = new CanaryVillagerTrade((MerchantRecipe) list.get(i));
        }
        return rt;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addTrade(VillagerTrade trade) {
        getHandle().b((EntityPlayer) null).add(((CanaryVillagerTrade) trade).getRecipe());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTrade(int index) {
        getHandle().b((EntityPlayer) null).remove(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityVillager getHandle() {
        return (EntityVillager) entity;
    }
}
