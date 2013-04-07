package net.canarymod.api.factory;


import net.canarymod.api.CanaryVillagerTrade;
import net.canarymod.api.VillagerTrade;
import net.canarymod.api.inventory.Item;


public class CanaryObjectFactory implements ObjectFactory {

    @Override
    public VillagerTrade newVillagerTrade(Item buying, Item selling) {
        return new CanaryVillagerTrade(buying, selling);
    }

    @Override
    public VillagerTrade newVillagerTrade(Item buyingOne, Item buyingTwo, Item selling) {
        return new CanaryVillagerTrade(buyingOne, buyingTwo, selling);
    }

}
