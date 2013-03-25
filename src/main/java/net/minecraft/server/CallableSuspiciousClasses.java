package net.minecraft.server;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.Callable;


class CallableSuspiciousClasses implements Callable {

    final CrashReport a;

    CallableSuspiciousClasses(CrashReport crashreport) {
        this.a = crashreport;
    }

    public String a() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException { // CanaryMod: Throws Added
        StringBuilder stringbuilder = new StringBuilder();
        Field field = ClassLoader.class.getDeclaredField("classes");

        field.setAccessible(true);
        ArrayList arraylist = new ArrayList((Vector) field.get(CrashReport.class.getClassLoader()));
        boolean flag0 = true;
        boolean flag1 = !CrashReport.class.getCanonicalName().equals("net.minecraft.CrashReport");
        HashMap hashmap = new HashMap();
        String s0 = "";

        Collections.sort(arraylist, new ComparatorClassSorter(this));
        Iterator iterator = arraylist.iterator();

        while (iterator.hasNext()) {
            Class oclass0 = (Class) iterator.next();

            if (oclass0 != null) {
                String s1 = oclass0.getCanonicalName();

                if (s1 != null && !s1.startsWith("org.lwjgl.") && !s1.startsWith("paulscode.") && !s1.startsWith("org.bouncycastle.") && !s1.startsWith("argo.") && !s1.startsWith("com.jcraft.") && !s1.startsWith("com.fasterxml.") && !s1.equals("util.GLX")) {
                    if (flag1) {
                        if (s1.length() <= 3 || s1.equals("net.minecraft.client.MinecraftApplet") || s1.equals("net.minecraft.client.Minecraft") || s1.equals("net.minecraft.client.ClientBrandRetriever") || s1.equals("net.minecraft.server.MinecraftServer")) {
                            continue;
                        }
                    } else if (s1.startsWith("net.minecraft")) {
                        continue;
                    }

                    // CanaryMod: Keyword correction
                    Package package_ = oclass0.getPackage();
                    String s2 = package_ == null ? "" : package_.getName();

                    //

                    if (hashmap.containsKey(s2)) {
                        int i0 = ((Integer) hashmap.get(s2)).intValue();

                        hashmap.put(s2, Integer.valueOf(i0 + 1));
                        if (i0 == 3) {
                            if (!flag0) {
                                stringbuilder.append(", ");
                            }

                            stringbuilder.append("...");
                            flag0 = false;
                            continue;
                        }

                        if (i0 > 3) {
                            continue;
                        }
                    } else {
                        hashmap.put(s2, Integer.valueOf(1));
                    }

                    if (s0 != s2 && s0.length() > 0) {
                        stringbuilder.append("], ");
                    }

                    if (!flag0 && s0 == s2) {
                        stringbuilder.append(", ");
                    }

                    if (s0 != s2) {
                        stringbuilder.append("[");
                        stringbuilder.append(s2);
                        stringbuilder.append(".");
                    }

                    stringbuilder.append(oclass0.getSimpleName());
                    s0 = s2;
                    flag0 = false;
                }
            }
        }

        if (flag0) {
            stringbuilder.append("No suspicious classes found.");
        } else {
            stringbuilder.append("]");
        }

        return stringbuilder.toString();
    }

    public Object call() throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException { // CanaryMod: Throws added
        return this.a();
    }
}
