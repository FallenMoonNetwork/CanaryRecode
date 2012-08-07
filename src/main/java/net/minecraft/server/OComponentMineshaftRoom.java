package net.minecraft.server;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureMineshaftPieces;
import net.minecraft.server.OWorld;

public class OComponentMineshaftRoom extends OStructureComponent {

    private LinkedList a = new LinkedList();

    public OComponentMineshaftRoom(int var1, Random var2, int var3, int var4) {
        super(var1);
        this.g = new OStructureBoundingBox(var3, 50, var4, var3 + 7 + var2.nextInt(6), 54 + var2.nextInt(6), var4 + 7 + var2.nextInt(6));
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
        int var4 = this.c();
        int var5 = this.g.c() - 3 - 1;
        if (var5 <= 0) {
            var5 = 1;
        }

        int var6;
        OStructureComponent var7;
        OStructureBoundingBox var8;
        for (var6 = 0; var6 < this.g.b(); var6 += 4) {
            var6 += var3.nextInt(this.g.b());
            if (var6 + 3 > this.g.b()) {
                break;
            }

            var7 = OStructureMineshaftPieces.a(var1, var2, var3, this.g.a + var6, this.g.b + var3.nextInt(var5) + 1, this.g.c - 1, 2, var4);
            if (var7 != null) {
                var8 = var7.b();
                this.a.add(new OStructureBoundingBox(var8.a, var8.b, this.g.c, var8.d, var8.e, this.g.c + 1));
            }
        }

        for (var6 = 0; var6 < this.g.b(); var6 += 4) {
            var6 += var3.nextInt(this.g.b());
            if (var6 + 3 > this.g.b()) {
                break;
            }

            var7 = OStructureMineshaftPieces.a(var1, var2, var3, this.g.a + var6, this.g.b + var3.nextInt(var5) + 1, this.g.f + 1, 0, var4);
            if (var7 != null) {
                var8 = var7.b();
                this.a.add(new OStructureBoundingBox(var8.a, var8.b, this.g.f - 1, var8.d, var8.e, this.g.f));
            }
        }

        for (var6 = 0; var6 < this.g.d(); var6 += 4) {
            var6 += var3.nextInt(this.g.d());
            if (var6 + 3 > this.g.d()) {
                break;
            }

            var7 = OStructureMineshaftPieces.a(var1, var2, var3, this.g.a - 1, this.g.b + var3.nextInt(var5) + 1, this.g.c + var6, 1, var4);
            if (var7 != null) {
                var8 = var7.b();
                this.a.add(new OStructureBoundingBox(this.g.a, var8.b, var8.c, this.g.a + 1, var8.e, var8.f));
            }
        }

        for (var6 = 0; var6 < this.g.d(); var6 += 4) {
            var6 += var3.nextInt(this.g.d());
            if (var6 + 3 > this.g.d()) {
                break;
            }

            var7 = OStructureMineshaftPieces.a(var1, var2, var3, this.g.d + 1, this.g.b + var3.nextInt(var5) + 1, this.g.c + var6, 3, var4);
            if (var7 != null) {
                var8 = var7.b();
                this.a.add(new OStructureBoundingBox(this.g.d - 1, var8.b, var8.c, this.g.d, var8.e, var8.f));
            }
        }

    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            this.a(var1, var3, this.g.a, this.g.b, this.g.c, this.g.d, this.g.b, this.g.f, OBlock.v.bO, 0, true);
            this.a(var1, var3, this.g.a, this.g.b + 1, this.g.c, this.g.d, Math.min(this.g.b + 3, this.g.e), this.g.f, 0, 0, false);
            Iterator var4 = this.a.iterator();

            while (var4.hasNext()) {
                OStructureBoundingBox var5 = (OStructureBoundingBox) var4.next();
                this.a(var1, var3, var5.a, var5.e - 2, var5.c, var5.d, var5.e, var5.f, 0, 0, false);
            }

            this.a(var1, var3, this.g.a, this.g.b + 4, this.g.c, this.g.d, this.g.e, this.g.f, 0, false);
            return true;
        }
    }
}
