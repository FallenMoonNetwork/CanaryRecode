package net.canarymod.api.factory;


public class CanaryFactory implements Factory {

    private final ItemFactory itemFactory = new CanaryItemFactory();
    private final PotionFactory potionFactory = new CanaryPotionFactory();
    private final EntityFactory entityFactory = new CanaryEntityFactory();
    private final ObjectFactory objFactory = new CanaryObjectFactory();
    private final NBTFactory nbtFactory = new CanaryNBTFactory();

    @Override
    public ItemFactory getItemFactory() {
        return itemFactory;
    }

    @Override
    public PotionFactory getPotionFactory() {
        return potionFactory;
    }

    @Override
    public EntityFactory getEntityFactory() {
        return entityFactory;
    }

    @Override
    public ObjectFactory getObjectFactory() {
        return objFactory;
    }

    @Override
    public NBTFactory getNBTFactory() {
        return nbtFactory;
    }

}
