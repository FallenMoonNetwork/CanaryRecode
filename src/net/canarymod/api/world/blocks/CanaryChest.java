package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.Container;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OTileEntityChest;

public class CanaryChest extends CanaryComplexBlock implements Chest, Container<OItemStack>{

    public CanaryChest(OTileEntityChest tileentity) {
        super(tileentity);
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory(this);
    }

    @Override
    public boolean hasAttachedChest() {
//        Block block = getBlock();
//        DoubleChest result;
//
//        result = tryAttachedChest(block, BlockFace.NORTH);
//        if (result != null) {
//            return result;
//        }
//
//        result = tryAttachedChest(block, Block.Face.Back);
//        if (result != null) {
//            return result;
//        }
//
//        result = tryAttachedChest(block, Block.Face.Left);
//        if (result != null) {
//            return result;
//        }
//
//          result = tryAttachedChest(block, Block.Face.Right);
//          if (result != null) {
//              return result;
//          }
//
//          return result != null;
        return false;
    }
    
//    private DoubleChest tryAttachedChest(Block origin, BlockFace face) {
//        Block block = origin.getFace(face);
//
//        if (block.blockType == Block.Type.Chest) {
//            ComplexBlock cblock = getWorld().getOnlyComplexBlock(block);
//
//           if ((cblock != null) && (cblock instanceof Chest)) {
//                Chest chest = (Chest) cblock;
//
//                return new DoubleChest(new OInventoryLargeChest(getName(), container, chest.container));
//            }
//        }
//
//        return null;
//    }
    
    @Override
    public void clearContents() {
        ((OTileEntityChest)tileentity).clearContents();
    }

    @Override
    public OItemStack decreaseItemStackSize(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OItemStack[] getContents() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getInventoryName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getInventorySize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getInventoryStackLimit() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Item getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OItemStack getSlot(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasItem(int arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasItemStack(OItemStack arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Item removeItem(Item arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item removeItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setContents(OItemStack[] arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setInventoryName(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setSlot(int arg0, OItemStack arg1) {
        // TODO Auto-generated method stub
        
    }
}
