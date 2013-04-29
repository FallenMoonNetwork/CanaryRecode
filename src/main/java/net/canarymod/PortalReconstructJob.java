package net.canarymod;

import java.util.ArrayList;

import net.canarymod.api.CanaryPacket;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.tasks.ServerTask;
import net.minecraft.server.Packet53BlockChange;

/**
 * Portal Reconstruct Job
 * <p>
 * Task for delaying the rebuilding of a Portal so the PortalDestroyHook isn't spammed
 *
 * @author YLivay (Phillip)
 * @author Jason (darkdiplomat)
 */
public final class PortalReconstructJob extends ServerTask {
    private ArrayList<Integer[]> portalBlocks;
    private World world;

    public PortalReconstructJob(World world, int portalX, int portalY, int portalZ, boolean portalXAxis) {
        super(Canary.instance(), 1, false);
        portalBlocks = new ArrayList<Integer[]>();
        this.world = world;

        int portalXOffset = (portalXAxis == true) ? 1 : 0;
        int portalZOffset = 1 - portalXOffset;
        int portalId = BlockType.Portal.getId();
        int obsidianId = BlockType.Obsidian.getId();

        portalBlocks.add(new Integer[]{ portalX, portalY, portalZ, portalId });
        portalBlocks.add(new Integer[]{ portalX + portalXOffset, portalY, portalZ + portalZOffset, portalId });
        portalBlocks.add(new Integer[]{ portalX, portalY - 1, portalZ, portalId });
        portalBlocks.add(new Integer[]{ portalX + portalXOffset, portalY - 1, portalZ + portalZOffset, portalId });
        portalBlocks.add(new Integer[]{ portalX, portalY - 2, portalZ, portalId });
        portalBlocks.add(new Integer[]{ portalX + portalXOffset, portalY - 2, portalZ + portalZOffset, portalId });
        portalBlocks.add(new Integer[]{ portalX, portalY + 1, portalZ, obsidianId });
        portalBlocks.add(new Integer[]{ portalX + portalXOffset, portalY + 1, portalZ + portalZOffset, obsidianId });
        portalBlocks.add(new Integer[]{ portalX + portalXOffset * 2, portalY, portalZ + portalZOffset * 2, obsidianId });
        portalBlocks.add(new Integer[]{ portalX + portalXOffset * 2, portalY - 1, portalZ + portalZOffset * 2, obsidianId });
        portalBlocks.add(new Integer[]{ portalX + portalXOffset * 2, portalY - 2, portalZ + portalZOffset * 2, obsidianId });
        portalBlocks.add(new Integer[]{ portalX - portalXOffset, portalY, portalZ - portalZOffset, obsidianId });
        portalBlocks.add(new Integer[]{ portalX - portalXOffset, portalY - 1, portalZ - portalZOffset, obsidianId });
        portalBlocks.add(new Integer[]{ portalX - portalXOffset, portalY - 2, portalZ - portalZOffset, obsidianId });
        portalBlocks.add(new Integer[]{ portalX, portalY - 3, portalZ, obsidianId });
        portalBlocks.add(new Integer[]{ portalX + portalXOffset, portalY - 3, portalZ + portalZOffset, obsidianId });
    }

    @Override
    public final void run() {
        for (Integer[] frameCoord : portalBlocks) {
            world.getChunk(frameCoord[0] >> 4, frameCoord[2] >> 4).setBlockTypeAt(frameCoord[0] & 15, frameCoord[1], frameCoord[2] & 15, frameCoord[3]);
            for (Player player : world.getPlayerList()) {
                player.sendPacket(new CanaryPacket(new Packet53BlockChange(frameCoord[0], frameCoord[1], frameCoord[2], ((CanaryWorld) world).getHandle())));
            }
        }
    }
}
