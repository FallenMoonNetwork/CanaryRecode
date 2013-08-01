package net.canarymod.api.entity.living.humanoid.npchelpers;

import net.canarymod.api.entity.living.humanoid.EntityNonPlayableCharacter;

public class EntityNPCJumpHelper {

    private EntityNonPlayableCharacter a;
    private boolean b;

    public EntityNPCJumpHelper(EntityNonPlayableCharacter enpc) {
        this.a = enpc;
    }

    public void a() {
        this.b = true;
    }

    public void b() {
        this.a.f(this.b);
        this.b = false;
    }
}
