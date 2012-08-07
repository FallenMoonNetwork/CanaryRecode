package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OPotion;
import net.minecraft.server.OPotionEffect;

public class OPotionHelper {

    public static final String a = null;
    public static final String b;
    public static final String c = "+0-1-2-3&4-4+13";
    public static final String d;
    public static final String e;
    public static final String f;
    public static final String g;
    public static final String h;
    public static final String i;
    public static final String j;
    public static final String k;
    private static final HashMap l = new HashMap();
    private static final HashMap m = new HashMap();
    private static final HashMap n;
    private static final String[] o;

    public OPotionHelper() {
        super();
    }

    public static boolean a(int var0, int var1) {
        return (var0 & 1 << var1) != 0;
    }

    private static int b(int var0, int var1) {
        return a(var0, var1) ? 1 : 0;
    }

    private static int c(int var0, int var1) {
        return a(var0, var1) ? 0 : 1;
    }

    public static int a(Collection var0) {
        int var1 = 3694022;
        if (var0 != null && !var0.isEmpty()) {
            float var2 = 0.0F;
            float var3 = 0.0F;
            float var4 = 0.0F;
            float var5 = 0.0F;
            Iterator var6 = var0.iterator();

            while (var6.hasNext()) {
                OPotionEffect var7 = (OPotionEffect) var6.next();
                int var8 = OPotion.a[var7.a()].g();

                for (int var9 = 0; var9 <= var7.c(); ++var9) {
                    var2 += (var8 >> 16 & 255) / 255.0F;
                    var3 += (var8 >> 8 & 255) / 255.0F;
                    var4 += (var8 >> 0 & 255) / 255.0F;
                    ++var5;
                }
            }

            var2 = var2 / var5 * 255.0F;
            var3 = var3 / var5 * 255.0F;
            var4 = var4 / var5 * 255.0F;
            return (int) var2 << 16 | (int) var3 << 8 | (int) var4;
        } else {
            return var1;
        }
    }

    private static int a(boolean var0, boolean var1, boolean var2, int var3, int var4, int var5, int var6) {
        int var7 = 0;
        if (var0) {
            var7 = c(var6, var4);
        } else if (var3 != -1) {
            if (var3 == 0 && a(var6) == var4) {
                var7 = 1;
            } else if (var3 == 1 && a(var6) > var4) {
                var7 = 1;
            } else if (var3 == 2 && a(var6) < var4) {
                var7 = 1;
            }
        } else {
            var7 = b(var6, var4);
        }

        if (var1) {
            var7 *= var5;
        }

        if (var2) {
            var7 *= -1;
        }

        return var7;
    }

    private static int a(int var0) {
        int var1;
        for (var1 = 0; var0 > 0; ++var1) {
            var0 &= var0 - 1;
        }

        return var1;
    }

    private static int a(String var0, int var1, int var2, int var3) {
        if (var1 < var0.length() && var2 >= 0 && var1 < var2) {
            int var4 = var0.indexOf(124, var1);
            int var5;
            int var17;
            if (var4 >= 0 && var4 < var2) {
                var5 = a(var0, var1, var4 - 1, var3);
                if (var5 > 0) {
                    return var5;
                } else {
                    var17 = a(var0, var4 + 1, var2, var3);
                    return var17 > 0 ? var17 : 0;
                }
            } else {
                var5 = var0.indexOf(38, var1);
                if (var5 >= 0 && var5 < var2) {
                    var17 = a(var0, var1, var5 - 1, var3);
                    if (var17 <= 0) {
                        return 0;
                    } else {
                        int var18 = a(var0, var5 + 1, var2, var3);
                        return var18 <= 0 ? 0 : (var17 > var18 ? var17 : var18);
                    }
                } else {
                    boolean var6 = false;
                    boolean var7 = false;
                    boolean var8 = false;
                    boolean var9 = false;
                    boolean var10 = false;
                    byte var11 = -1;
                    int var12 = 0;
                    int var13 = 0;
                    int var14 = 0;

                    for (int var15 = var1; var15 < var2; ++var15) {
                        char var16 = var0.charAt(var15);
                        if (var16 >= 48 && var16 <= 57) {
                            if (var6) {
                                var13 = var16 - 48;
                                var7 = true;
                            } else {
                                var12 *= 10;
                                var12 += var16 - 48;
                                var8 = true;
                            }
                        } else if (var16 == 42) {
                            var6 = true;
                        } else if (var16 == 33) {
                            if (var8) {
                                var14 += a(var9, var7, var10, var11, var12, var13, var3);
                                var9 = false;
                                var10 = false;
                                var6 = false;
                                var7 = false;
                                var8 = false;
                                var13 = 0;
                                var12 = 0;
                                var11 = -1;
                            }

                            var9 = true;
                        } else if (var16 == 45) {
                            if (var8) {
                                var14 += a(var9, var7, var10, var11, var12, var13, var3);
                                var9 = false;
                                var10 = false;
                                var6 = false;
                                var7 = false;
                                var8 = false;
                                var13 = 0;
                                var12 = 0;
                                var11 = -1;
                            }

                            var10 = true;
                        } else if (var16 != 61 && var16 != 60 && var16 != 62) {
                            if (var16 == 43 && var8) {
                                var14 += a(var9, var7, var10, var11, var12, var13, var3);
                                var9 = false;
                                var10 = false;
                                var6 = false;
                                var7 = false;
                                var8 = false;
                                var13 = 0;
                                var12 = 0;
                                var11 = -1;
                            }
                        } else {
                            if (var8) {
                                var14 += a(var9, var7, var10, var11, var12, var13, var3);
                                var9 = false;
                                var10 = false;
                                var6 = false;
                                var7 = false;
                                var8 = false;
                                var13 = 0;
                                var12 = 0;
                                var11 = -1;
                            }

                            if (var16 == 61) {
                                var11 = 0;
                            } else if (var16 == 60) {
                                var11 = 2;
                            } else if (var16 == 62) {
                                var11 = 1;
                            }
                        }
                    }

                    if (var8) {
                        var14 += a(var9, var7, var10, var11, var12, var13, var3);
                    }

                    return var14;
                }
            }
        } else {
            return 0;
        }
    }

    public static List a(int var0, boolean var1) {
        ArrayList var2 = null;
        OPotion[] var3 = OPotion.a;
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            OPotion var6 = var3[var5];
            if (var6 != null && (!var6.f() || var1)) {
                String var7 = (String) l.get(Integer.valueOf(var6.a()));
                if (var7 != null) {
                    int var8 = a(var7, 0, var7.length(), var0);
                    if (var8 > 0) {
                        int var9 = 0;
                        String var10 = (String) m.get(Integer.valueOf(var6.a()));
                        if (var10 != null) {
                            var9 = a(var10, 0, var10.length(), var0);
                            if (var9 < 0) {
                                var9 = 0;
                            }
                        }

                        if (var6.b()) {
                            var8 = 1;
                        } else {
                            var8 = 1200 * (var8 * 3 + (var8 - 1) * 2);
                            var8 >>= var9;
                            var8 = (int) Math.round(var8 * var6.d());
                            if ((var0 & 16384) != 0) {
                                var8 = (int) Math.round(var8 * 0.75D + 0.5D);
                            }
                        }

                        if (var2 == null) {
                            var2 = new ArrayList();
                        }

                        var2.add(new OPotionEffect(var6.a(), var8, var9));
                    }
                }
            }
        }

        return var2;
    }

    private static int a(int var0, int var1, boolean var2, boolean var3, boolean var4) {
        if (var4) {
            if (!a(var0, var1)) {
                return 0;
            }
        } else if (var2) {
            var0 &= ~(1 << var1);
        } else if (var3) {
            if ((var0 & 1 << var1) != 0) {
                var0 &= ~(1 << var1);
            } else {
                var0 |= 1 << var1;
            }
        } else {
            var0 |= 1 << var1;
        }

        return var0;
    }

    public static int a(int var0, String var1) {
        byte var2 = 0;
        int var3 = var1.length();
        boolean var4 = false;
        boolean var5 = false;
        boolean var6 = false;
        boolean var7 = false;
        int var8 = 0;

        for (int var9 = var2; var9 < var3; ++var9) {
            char var10 = var1.charAt(var9);
            if (var10 >= 48 && var10 <= 57) {
                var8 *= 10;
                var8 += var10 - 48;
                var4 = true;
            } else if (var10 == 33) {
                if (var4) {
                    var0 = a(var0, var8, var6, var5, var7);
                    var7 = false;
                    var5 = false;
                    var6 = false;
                    var4 = false;
                    var8 = 0;
                }

                var5 = true;
            } else if (var10 == 45) {
                if (var4) {
                    var0 = a(var0, var8, var6, var5, var7);
                    var7 = false;
                    var5 = false;
                    var6 = false;
                    var4 = false;
                    var8 = 0;
                }

                var6 = true;
            } else if (var10 == 43) {
                if (var4) {
                    var0 = a(var0, var8, var6, var5, var7);
                    var7 = false;
                    var5 = false;
                    var6 = false;
                    var4 = false;
                    var8 = 0;
                }
            } else if (var10 == 38) {
                if (var4) {
                    var0 = a(var0, var8, var6, var5, var7);
                    var7 = false;
                    var5 = false;
                    var6 = false;
                    var4 = false;
                    var8 = 0;
                }

                var7 = true;
            }
        }

        if (var4) {
            var0 = a(var0, var8, var6, var5, var7);
        }

        return var0 & 32767;
    }

    static {
        l.put(Integer.valueOf(OPotion.l.a()), "0 & !1 & !2 & !3 & 0+6");
        b = "-0+1-2-3&4-4+13";
        l.put(Integer.valueOf(OPotion.c.a()), "!0 & 1 & !2 & !3 & 1+6");
        h = "+0+1-2-3&4-4+13";
        l.put(Integer.valueOf(OPotion.n.a()), "0 & 1 & !2 & !3 & 0+6");
        f = "+0-1+2-3&4-4+13";
        l.put(Integer.valueOf(OPotion.h.a()), "0 & !1 & 2 & !3");
        d = "-0-1+2-3&4-4+13";
        l.put(Integer.valueOf(OPotion.u.a()), "!0 & !1 & 2 & !3 & 2+6");
        e = "-0+3-4+13";
        l.put(Integer.valueOf(OPotion.t.a()), "!0 & !1 & !2 & 3 & 3+6");
        l.put(Integer.valueOf(OPotion.i.a()), "!0 & !1 & 2 & 3");
        l.put(Integer.valueOf(OPotion.d.a()), "!0 & 1 & !2 & 3 & 3+6");
        g = "+0-1-2+3&4-4+13";
        l.put(Integer.valueOf(OPotion.g.a()), "0 & !1 & !2 & 3 & 3+6");
        j = "+5-6-7";
        m.put(Integer.valueOf(OPotion.c.a()), "5");
        m.put(Integer.valueOf(OPotion.e.a()), "5");
        m.put(Integer.valueOf(OPotion.g.a()), "5");
        m.put(Integer.valueOf(OPotion.l.a()), "5");
        m.put(Integer.valueOf(OPotion.i.a()), "5");
        m.put(Integer.valueOf(OPotion.h.a()), "5");
        m.put(Integer.valueOf(OPotion.m.a()), "5");
        m.put(Integer.valueOf(OPotion.u.a()), "5");
        i = "-5+6-7";
        k = "+14&13-13";
        n = new HashMap();
        o = new String[] { "potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", "potion.prefix.gross", "potion.prefix.stinky" };
    }
}
