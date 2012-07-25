package net.canarymod.api.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.canarymod.CanaryMod;
import net.canarymod.api.CanaryServer;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OEntityTrackerEntry;
import net.minecraft.server.OItemInWorldManager;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.OPacket29DestroyEntity;

public class CanaryNpc extends CanaryEntityLiving implements Npc{
    private OEntityTrackerEntry handler;
    private static CanaryServer server = (CanaryServer)CanaryMod.getServer();
    private static OMinecraftServer mcserv = server.getHandle();

    public CanaryNpc(OEntityPlayerMP user){
        super(user);
        handler = new OEntityTrackerEntry(user, 512, 1, true);
    }
    
    public CanaryNpc(String name, World world, int dim, double x, double y, double z, float rotation, float pitch, Item itemInHand) {
        super(new OEntityPlayerMP(mcserv, ((CanaryWorld)world).getHandle(), name, new OItemInWorldManager(((CanaryWorld)world).getHandle())));
        handler = new OEntityTrackerEntry(entity, 512, 1, true);
        teleportTo((int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z));
        setItemInHand(itemInHand);
    }
    
    @Override
    public String getName(){
        return ((OEntityPlayerMP)entity).v;
    }
    
    @Override
    public void setName(String name) {
        ((OEntityPlayerMP)entity).v = name;
    }

    @Override
    public Npc despawn() {
        for (Iterator<Player> playerit = ((List<Player>)server.getPlayerList()).iterator(); playerit.hasNext();) {
            Player player = playerit.next();
            this.handler.o.remove(player);
        }
        return this;
    }

    @Override
    public void teleportTo(Vector3D arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void teleportTo(Location arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void teleportTo(int arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public Item getItemInHand() {
        return ((OEntityPlayerMP)entity).k.a[0] != null ? new CanaryItem(((OEntityPlayerMP)entity).y()[0]) : null;
    }
    
    @Override
    public void setItemInHand(Item item) {
        ((OEntityPlayerMP)entity).y()[0] = null;
        
    }

    @Override
    public void ghost(Player arg0) {
        for (Iterator<OEntityPlayerMP> playerit = ((List<OEntityPlayerMP>)mcserv.h.b).iterator(); playerit.hasNext();) {
            OEntityPlayerMP player = playerit.next();
            player.a.b(new OPacket29DestroyEntity(this.handler.a.bd));
        }
    }

    @Override
    public void haunt(Player player) {
        ArrayList<OEntityPlayerMP> list = new ArrayList<OEntityPlayerMP>();
        list.add((OEntityPlayerMP) player.getPlayer());
        this.handler.b(list);
        this.handler.a(list);
    }

    @Override
    public void lookAt(Player player) {
        double myX = player.getX();
        double myY = player.getY();
        double myZ = player.getZ();
        double targX = getX();
        double targY = getY();
        double targZ = getZ();
        double dist = distance(targX, targY, targZ, myX, myY, myZ);
        if (dist < 25){
            //yaw 
            double adjyaw = myX - targX; 
            double oppyaw = myZ - targZ; 
            double yaw = Math.atan2(oppyaw, adjyaw); 
            double rota = yaw*180/Math.PI;
            setRotation ((float) rota - 90);
            //pitch 
            double adjpitch = distance(targX, targZ, myX, myZ); 
            double opppitch = targY - myY; 
            double thepitch = (Math.atan2(opppitch, adjpitch)); 
            double pit =  thepitch*180/Math.PI;
            setPitch ((float)pit);
            ((OEntityPlayerMP)entity).ay = (float)pit; //set head position
            ((OEntityPlayerMP)entity).e(); //Update head position
            spawn();
        }
    }
    
    private double distance(double x1, double z1, double x2, double z2) {
        return Math.sqrt(Math.pow((x1-x2),2)+Math.pow((z1-z2),2));
    }
    private double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2)+Math.pow((z1-z2),2));
    }

    @Override
    public void lookAtNearest() {
        //Find nearest player then call lookAt(player)
    }

    @Override
    public void spawn() {
        this.handler.b(mcserv.h.b);
        this.handler.a(mcserv.h.b);
    }
}
