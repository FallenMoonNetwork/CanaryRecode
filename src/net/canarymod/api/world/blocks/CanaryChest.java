package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.Inventory;
import net.minecraft.server.OTileEntityChest;

public class CanaryChest extends CanaryComplexBlock implements Chest{

    public CanaryChest(OTileEntityChest tileentity) {
        super(tileentity);
    }

    @Override
    public Inventory getInventory() {
        return new CanaryInventory((OTileEntityChest)tileentity);
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
}
