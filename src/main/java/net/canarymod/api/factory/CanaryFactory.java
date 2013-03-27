package net.canarymod.api.factory;


public class CanaryFactory implements Factory {

    private final ItemFactory itemFactory = new CanaryItemFactory();
    private final BlockFactory blockFactory = new CanaryBlockFactory();
    private final PotionFactory potionFactory = new CanaryPotionFactory();
    private final EntityFactory entityFactory = new CanaryEntityFactory();

    @Override
    public ItemFactory getItemFactory() {
        return itemFactory;
    }

    @Override
    public BlockFactory getBlockFactory() {
        return blockFactory;
    }

    @Override
    public PotionFactory getPotionFactory() {
        return potionFactory;
    }

    @Override
    public EntityFactory getEntityFactory() {
        return entityFactory;
    }

}
