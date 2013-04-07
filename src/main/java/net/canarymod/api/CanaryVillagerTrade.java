package net.canarymod.api;


import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.api.nbt.CompoundTag;
import net.minecraft.server.MerchantRecipe;


/**
 * VillagerTrade wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryVillagerTrade implements VillagerTrade {
    private MerchantRecipe recipe;

    /**
     * Constructs a new wrapper for MerchantRecipe
     * 
     * @param recipe
     *            the MerchantRecipe to wrap
     */
    public CanaryVillagerTrade(MerchantRecipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Constructs a new wrapper for and new MerchantRecipe
     * 
     * @param buying
     *            the {@link Item} to set for buying
     * @param selling
     *            the {@link Item} to set for selling
     */
    public CanaryVillagerTrade(Item buying, Item selling) {
        this.recipe = new MerchantRecipe(((CanaryItem) buying).getHandle(), ((CanaryItem) selling).getHandle());
    }

    /**
     * Constructs a new wrapper for and new MerchantRecipe
     * 
     * @param buying1
     *            the {@link Item} to set for buying slot 1
     * @param buying2
     *            the {@link Item} to set for buying slot 2
     * @param selling
     *            the {@link Item} to set for selling
     */
    public CanaryVillagerTrade(Item buying1, Item buying2, Item selling) {
        this.recipe = new MerchantRecipe(((CanaryItem) buying1).getHandle(), ((CanaryItem) buying2).getHandle(), ((CanaryItem) selling).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getBuyingOne() {
        return getRecipe().a().getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBuyingOne(Item item) {
        getRecipe().a = ((CanaryItem) item).getHandle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getBuyingTwo() {
        return getRecipe().b().getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBuyingTwo(Item item) {
        getRecipe().b = ((CanaryItem) item).getHandle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean requiresTwoItems() {
        return getRecipe().c();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getSelling() {
        return getRecipe().d().getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelling(Item item) {
        getRecipe().c = ((CanaryItem) item).getHandle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void use() {
        getRecipe().f();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseMaxUses(int increase) {
        getRecipe().a(increase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUsedUp() {
        return getRecipe().g();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompoundTag getDataAsTag() {
        return new CanaryCompoundTag(getRecipe().i());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readFromTag(CompoundTag tag) {
        getRecipe().a(((CanaryCompoundTag) tag).getHandle());
    }

    public MerchantRecipe getRecipe() {
        return this.recipe;
    }

    @Override
    public String toString() {
        return String.format("VillagerTrade[buying1=%s, buying2=%s, selling=%s]", getBuyingOne(), getBuyingTwo(), getSelling());
    }

}
