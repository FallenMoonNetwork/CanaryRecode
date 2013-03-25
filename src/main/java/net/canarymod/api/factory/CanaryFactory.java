package net.canarymod.api.factory;


public class CanaryFactory implements Factory {

    ItemFactory items = new CanaryItemFactory();
    BlockFactory blocks = new CanaryBlockFactory();
    PotionFactory potions = new CanaryPotionFactory();
    EntityLivingFactory livingEntities = new CanaryEntityLivingFactory();

    @Override
    public ItemFactory itemFactory() {
        return items;
    }

    @Override
    public BlockFactory blockFactory() {
        return blocks;
    }

    @Override
    public PotionFactory potionFactory() {
        return potions;
    }

    @Override
    public EntityLivingFactory entityLivingFactory() {
        return livingEntities;
    }

}
