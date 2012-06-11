package net.canarymod.api.inventory;

import net.minecraft.server.OInventoryPlayer;
import net.minecraft.server.OItemStack;

public class CanaryPlayerInventory extends CanaryInventory implements PlayerInventory{
    
    public CanaryPlayerInventory(OInventoryPlayer playerInventory){
        super(playerInventory);
    }
    
    public Item getArmorSlot(int slot){
        //return ((OInventoryPlayer)inventory).getArmorSlot(slot);
        return null;
    }
    
    public void setArmorSlot(Item item){
        
    }
    
    public OItemStack getItemInHand() {
        return ((OInventoryPlayer)container).d();
    }

}
