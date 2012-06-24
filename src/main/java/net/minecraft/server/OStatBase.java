package net.minecraft.server;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import net.minecraft.server.OAchievementMap;
import net.minecraft.server.OIStatType;
import net.minecraft.server.OStatCollector;
import net.minecraft.server.OStatList;
import net.minecraft.server.OStatTypeDistance;
import net.minecraft.server.OStatTypeSimple;
import net.minecraft.server.OStatTypeTime;

public class OStatBase {

    public final int e;
    private final String a;
    public boolean f;
    public String g;
    private final OIStatType b;
    private static NumberFormat c = NumberFormat.getIntegerInstance(Locale.US);
    public static OIStatType h = new OStatTypeSimple();
    private static DecimalFormat d = new DecimalFormat("########0.00");
    public static OIStatType i = new OStatTypeTime();
    public static OIStatType j = new OStatTypeDistance();

    public OStatBase(int var1, String var2, OIStatType var3) {
        super();
        this.f = false;
        this.e = var1;
        this.a = var2;
        this.b = var3;
    }

    public OStatBase(int var1, String var2) {
        this(var1, var2, h);
    }

    public OStatBase e() {
        this.f = true;
        return this;
    }

    public OStatBase d() {
        if (OStatList.a.containsKey(Integer.valueOf(this.e))) {
            throw new RuntimeException("Duplicate stat id: \"" + ((OStatBase) OStatList.a.get(Integer.valueOf(this.e))).a + "\" and \"" + this.a + "\" at id " + this.e);
        } else {
            OStatList.b.add(this);
            OStatList.a.put(Integer.valueOf(this.e), this);
            this.g = OAchievementMap.a(this.e);
            return this;
        }
    }

    @Override
    public String toString() {
        return OStatCollector.a(this.a);
    }

}
