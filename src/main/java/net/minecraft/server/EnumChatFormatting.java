package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum EnumChatFormatting {

    a("BLACK", 0, '0'), b("DARK_BLUE", 1, '1'), c("DARK_GREEN", 2, '2'), d("DARK_AQUA", 3, '3'), e("DARK_RED", 4, '4'), f("DARK_PURPLE", 5, '5'), g("GOLD", 6, '6'), h("GRAY", 7, '7'), i("DARK_GRAY", 8, '8'), j("BLUE", 9, '9'), k("GREEN", 10, 'a'), l("AQUA", 11, 'b'), m("RED", 12, 'c'), n("LIGHT_PURPLE", 13, 'd'), o("YELLOW", 14, 'e'), p("WHITE", 15, 'f'), q(
            "OBFUSCATED", 16, 'k', true), r("BOLD", 17, 'l', true), s("STRIKETHROUGH", 18, 'm', true), t("UNDERLINE", 19, 'n', true), u("ITALIC", 20, 'o', true), v("RESET", 21, 'r');
    private static final Map w = new HashMap();
    private static final Map x = new HashMap();
    private static final Pattern y = Pattern.compile("(?i)" + String.valueOf('\u00a7') + "[0-9A-FK-OR]");
    private final char z;
    private final boolean A;
    private final String B;
    private final String D; // CanaryMod: Track Name

    private static final EnumChatFormatting[] C = new EnumChatFormatting[]{ a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v };

    private EnumChatFormatting(String s0, int i0, char c0) {
        this(s0, i0, c0, false);
    }

    private EnumChatFormatting(String s0, int i0, char c0, boolean flag0) {
        this.z = c0;
        this.A = flag0;
        this.B = "\u00a7" + c0;
        this.D = s0;
    }

    public char a() {
        return this.z;
    }

    public boolean b() {
        return this.A;
    }

    public boolean c() {
        return !this.A && this != v;
    }

    public String d() {
        // return this.name().toLowerCase();
        return this.D.toLowerCase(); // CanaryMod: Fix bad color
    }

    public String toString() {
        return this.B;
    }

    public static EnumChatFormatting b(String s0) {
        return s0 == null ? null : (EnumChatFormatting) x.get(s0.toLowerCase());
    }

    public static Collection a(boolean flag0, boolean flag1) {
        ArrayList arraylist = new ArrayList();
        EnumChatFormatting[] aenumchatformatting = values();
        int i0 = aenumchatformatting.length;

        for (int i1 = 0; i1 < i0; ++i1) {
            EnumChatFormatting enumchatformatting = aenumchatformatting[i1];

            if ((!enumchatformatting.c() || flag0) && (!enumchatformatting.b() || flag1)) {
                arraylist.add(enumchatformatting.d());
            }
        }

        return arraylist;
    }

    static {
        EnumChatFormatting[] aenumchatformatting = values();
        int i0 = aenumchatformatting.length;

        for (int i1 = 0; i1 < i0; ++i1) {
            EnumChatFormatting enumchatformatting = aenumchatformatting[i1];

            w.put(Character.valueOf(enumchatformatting.a()), enumchatformatting);
            x.put(enumchatformatting.d(), enumchatformatting);
        }
    }
}
