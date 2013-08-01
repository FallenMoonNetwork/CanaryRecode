package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.api.world.blocks.CanaryCommandBlock;
import net.canarymod.hook.command.CommandBlockCommandHook;

public class TileEntityCommandBlock extends TileEntity implements ICommandSender {

    private int a = 0;
    private String b = "";
    private String c = "@";

    public TileEntityCommandBlock() {
        this.complexBlock = new CanaryCommandBlock(this); // CanaryMod: wrap tile entity
    }

    public void a(String s0) {
        this.b = s0;
        this.e();
    }

    public int a(World world) {
        if (world.I) {
            return 0;
        } else {
            MinecraftServer minecraftserver = MinecraftServer.F();

            if (minecraftserver != null && minecraftserver.ab()) {
                // CanaryMod: CommandBlockCommand
                // ICommandManager icommandmanager = minecraftserver.G();
                new CommandBlockCommandHook(getCanaryCommandBlock(), this.b.split(" ")).call();
                if (Canary.getServer().consoleCommand(this.b, this.getCanaryCommandBlock())) { // Redirect for Canary Console Commands too
                    return 1;
                }
                // return icommandmanager.a(this, this.b);
                //
            }
            return 0;
        }
    }

    public String c_() {
        return this.c;
    }

    public void b(String s0) {
        this.c = s0;
    }

    public void a(ChatMessageComponent chatmessagecomponent) {}

    public boolean a(int i0, String s0) {
        return i0 <= 2;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Command", this.b);
        nbttagcompound.a("SuccessCount", this.a);
        nbttagcompound.a("CustomName", this.c);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.b = nbttagcompound.i("Command");
        this.a = nbttagcompound.e("SuccessCount");
        if (nbttagcompound.b("CustomName")) {
            this.c = nbttagcompound.i("CustomName");
        }
    }

    public ChunkCoordinates b() {
        return new ChunkCoordinates(this.l, this.m, this.n);
    }

    public World f_() {
        return this.az();
    }

    public Packet m() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.b(nbttagcompound);
        return new Packet132TileEntityData(this.l, this.m, this.n, 2, nbttagcompound);
    }

    public int f() {
        return this.a;
    }

    public void a(int i0) {
        this.a = i0;
    }

    public String getCommand() { // CanaryMod: allows us to access the command stored
        return this.b;
    }

    // CanaryMod
    public CanaryCommandBlock getCanaryCommandBlock() {
        return (CanaryCommandBlock) complexBlock;
    }
}
