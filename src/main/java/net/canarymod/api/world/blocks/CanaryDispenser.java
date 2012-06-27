package net.canarymod.api.world.blocks;

import java.util.Random;

import net.canarymod.api.entity.Entity;
import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Container;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityArrow;
import net.minecraft.server.OEntityEgg;
import net.minecraft.server.OEntityExpBottle;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPotion;
import net.minecraft.server.OEntitySmallFireball;
import net.minecraft.server.OEntitySnowball;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemMonsterPlacer;
import net.minecraft.server.OItemPotion;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OTileEntityDispenser;

public class CanaryDispenser extends CanaryComplexBlock implements Dispenser, Container<Item>{
    private Random random = new Random();

    public CanaryDispenser(OTileEntityDispenser tileentity) {
        super(tileentity);
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory(((OTileEntityDispenser)tileentity));
    }
    
    public Entity activate(){
        return dispense(null);
    }
    
    public Entity dispenseFromSlot(int slot){
        OItemStack stack = ((OTileEntityDispenser)tileentity).getSlot(slot);
        if(stack != null){
            return dispense(stack);
        }
        else {
            tileentity.k.f(1001, this.getX(), this.getY(), this.getZ(), 0);
        }
        return null;
    }
    
    private Entity dispense(OItemStack item){
        int data = tileentity.k.c(this.getX(), this.getY(), this.getZ());
        byte offsetX = 0;
        byte offsetZ = 0;
        if (data == 3) {
            offsetX = 1;
        } else if (data == 2) {
            offsetZ = -1;
        } else if (data == 5) {
            offsetX = 1;
        } else {
            offsetZ = -1;
        }
        
        OItemStack stack = item != null ? item : ((OTileEntityDispenser)tileentity).p_();
        OEntity entity = null;
        double x = this.getX() + offsetX * 0.6D + 0.5D;
        double y = this.getY() + 0.5D;
        double z = this.getZ() + offsetZ * 0.6D + 0.5D;
        if (stack == null) {
            tileentity.k.f(1001, this.getX(), this.getY(), this.getZ(), 0);
        } 
        else {
            if (stack.c == OItem.k.bP) {
                OEntityArrow arrow = new OEntityArrow(tileentity.k, x, y, z);
                arrow.a(offsetX, 0.10000000149011612D, offsetZ, 1.1F, 6.0F);
                arrow.a = true;
                tileentity.k.b(arrow);
                tileentity.k.f(1002, this.getX(), this.getY(), this.getZ(), 0);
                entity = arrow;
            } 
            else if (stack.c == OItem.aO.bP) {
                OEntityEgg egg = new OEntityEgg(tileentity.k, x, y, z);
                egg.a(offsetX, 0.10000000149011612D, offsetZ, 1.1F, 6.0F);
                tileentity.k.b(egg);
                tileentity.k.f(1002, this.getX(), this.getY(), this.getZ(), 0);
                entity = egg;
            } 
            else if (stack.c == OItem.aC.bP) {
                OEntitySnowball snowball = new OEntitySnowball(tileentity.k, x, y, z);
                snowball.a(offsetX, 0.10000000149011612D, offsetZ, 1.1F, 6.0F);
                tileentity.k.b(snowball);
                tileentity.k.f(1002, this.getX(), this.getY(), this.getZ(), 0);
                entity = snowball;
            } 
            else if (stack.c == OItem.br.bP && OItemPotion.c(stack.h())) {
                OEntityPotion potion = new OEntityPotion(tileentity.k, x, y, z, stack.h());
                potion.a(offsetX, 0.10000000149011612D, offsetZ, 1.375F, 3.0F);
                tileentity.k.b(potion);
                tileentity.k.f(1002, this.getX(), this.getY(), this.getZ(), 0);
                entity = potion;
            } 
            else if (stack.c == OItem.bC.bP) {
                OEntityExpBottle expbottle = new OEntityExpBottle(tileentity.k, x, y, z);
                expbottle.a(offsetX, 0.10000000149011612D, offsetZ, 1.375F, 3.0F);
                tileentity.k.b(expbottle);
                tileentity.k.f(1002, this.getX(), this.getY(), this.getZ(), 0);
                entity = expbottle;
            } 
            else if (stack.c == OItem.bB.bP) {
                OItemMonsterPlacer.a(tileentity.k, stack.h(), x + offsetX * 0.3D, y - 0.3D, z + offsetZ * 0.3D);
                tileentity.k.f(1002, this.getX(), this.getY(), this.getZ(), 0);
            } 
            else if (stack.c == OItem.bD.bP) {
                OEntitySmallFireball smallfireball = new OEntitySmallFireball(tileentity.k, x + offsetX * 0.3D, y, z + offsetZ * 0.3D, offsetX +  random.nextGaussian() * 0.05D, random.nextGaussian() * 0.05D, offsetZ + random.nextGaussian() * 0.05D);
                tileentity.k.b(smallfireball);
                tileentity.k.f(1009, this.getX(), this.getY(), this.getZ(), 0);
                entity = smallfireball;
            } 
            else {
                OEntityItem entityitem = new OEntityItem(tileentity.k, x, y - 0.3D, z, stack);
                double rd = random.nextDouble() * 0.1D + 0.2D;
                entityitem.bp = offsetX * rd;
                entityitem.bq = 0.20000000298023224D;
                entityitem.br = offsetZ * rd;
                entityitem.bp += random.nextGaussian() * 0.007499999832361937D * 6.0D;
                entityitem.bq += random.nextGaussian() * 0.007499999832361937D * 6.0D;
                entityitem.br += random.nextGaussian() * 0.007499999832361937D * 6.0D;
                tileentity.k.b(entityitem);
                tileentity.k.f(1000, this.getX(), this.getY(), this.getZ(), 0);
                entity = entityitem;
            }

            tileentity.k.f(2000, this.getX(), this.getY(), this.getZ(), offsetX + 1 + (offsetZ + 1) * 3);
        }
        return entity != null ? entity.getCanaryEntity() : null;
    }

    @Override
    public void clearContents() {
        ((OTileEntityDispenser)tileentity).clearContents();
    }

    @Override
    public CanaryItem decreaseItemStackSize(int arg0, int arg1) {
        return ((OTileEntityDispenser)tileentity).decreaseItemStackSize(arg0, arg1).getCanaryItem();
    }

    @Override
    public CanaryItem[] getContents() {
        OItemStack[] oStacks = ((OTileEntityDispenser)tileentity).getContents();
        CanaryItem[] items = new CanaryItem[oStacks.length];
        for(int i = 0; i < oStacks.length; i++) {
            items[i] = new CanaryItem(oStacks[i]);
        }
        
        return items;
    }

    @Override
    public String getInventoryName() {
        return ((OTileEntityDispenser)tileentity).getInventoryName();
    }

    @Override
    public int getInventorySize() {
        return ((OTileEntityDispenser)tileentity).getInventorySize();
    }

    @Override
    public int getInventoryStackLimit() {
        return ((OTileEntityDispenser)tileentity).getInventoryStackLimit();
    }

    @Override
    public Item getItem(int id) {
        return ((OTileEntityDispenser)tileentity).getItem(id);
    }

    @Override
    public Item getItem(int id, int amount) {
       return ((OTileEntityDispenser)tileentity).getItem(id, amount);
    }

    @Override
    public Item getSlot(int index) {
        return ((OTileEntityDispenser)tileentity).getSlot(index).getCanaryItem();
    }

    @Override
    public boolean hasItem(int itemId) {
        return ((OTileEntityDispenser)tileentity).hasItem(itemId);
    }

    @Override
    public boolean hasItemStack(Item item) {
       return ((OTileEntityDispenser)tileentity).hasItemStack(((CanaryItem)item).getHandle());
    }

    @Override
    public Item removeItem(Item item) {
        return ((OTileEntityDispenser)tileentity).removeItem(item);
    }

    @Override
    public Item removeItem(int id) {
        return ((OTileEntityDispenser)tileentity).removeItem(id);
    }

    @Override
    public void setContents(Item[] items) {
        OItemStack[] oStacks = new OItemStack[items.length];
        for(int i = 0; i < items.length; i++) {
            if(items[i] != null) {
                oStacks[i] = ((CanaryItem)items[i]).getHandle();
            }
            else {
                oStacks[i] = null;
            }
        }
        ((OTileEntityDispenser)tileentity).setContents(oStacks);
    }

    @Override
    public void setInventoryName(String value) {
        ((OTileEntityDispenser)tileentity).setInventoryName(value);
    }

    @Override
    public void setSlot(int index, Item value) {
        ((OTileEntityDispenser)tileentity).setSlot(index, ((CanaryItem)value).getHandle());
    }
    
    public OTileEntityDispenser getHandle(){
        return (OTileEntityDispenser)tileentity;
    }

    @Override
    public void addItem(int itemId, int amount) {
        ((OTileEntityDispenser)tileentity).addItem(itemId, amount);
    }

    @Override
    public void addItem(Item item) {
        ((OTileEntityDispenser)tileentity).addItem(item);
    }

    @Override
    public int getEmptySlot() {
        return ((OTileEntityDispenser)tileentity).getEmptySlot();
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return ((OTileEntityDispenser)tileentity).hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return ((OTileEntityDispenser)tileentity).hasItemStack(itemId, minAmount, maxAmount);
    }
}
