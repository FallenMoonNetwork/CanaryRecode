package net.minecraft.server;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public abstract class CommandBase implements ICommand {

    private static IAdminCommand a = null;

    public CommandBase() {}

    public int a() {
        return 4;
    }

    public String a(ICommandSender icommandsender) {
        return "/" + this.c();
    }

    public List b() {
        return null;
    }

    public boolean b(ICommandSender icommandsender) {
        return icommandsender.a(this.a(), this.c());
    }

    public List a(ICommandSender icommandsender, String[] astring) {
        return null;
    }

    public static int a(ICommandSender icommandsender, String s0) {
        try {
            return Integer.parseInt(s0);
        } catch (NumberFormatException numberformatexception) {
            throw new NumberInvalidException("commands.generic.num.invalid", new Object[] { s0});
        }
    }

    public static int a(ICommandSender icommandsender, String s0, int i0) {
        return a(icommandsender, s0, i0, Integer.MAX_VALUE);
    }

    public static int a(ICommandSender icommandsender, String s0, int i0, int i1) {
        int i2 = a(icommandsender, s0);

        if (i2 < i0) {
            throw new NumberInvalidException("commands.generic.num.tooSmall", new Object[] { Integer.valueOf(i2), Integer.valueOf(i0)});
        } else if (i2 > i1) {
            throw new NumberInvalidException("commands.generic.num.tooBig", new Object[] { Integer.valueOf(i2), Integer.valueOf(i1)});
        } else {
            return i2;
        }
    }

    public static double b(ICommandSender icommandsender, String s0) {
        try {
            return Double.parseDouble(s0);
        } catch (NumberFormatException numberformatexception) {
            throw new NumberInvalidException("commands.generic.double.invalid", new Object[] { s0});
        }
    }

    public static EntityPlayerMP c(ICommandSender icommandsender) {
        if (icommandsender instanceof EntityPlayerMP) {
            return (EntityPlayerMP) icommandsender;
        } else {
            throw new PlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
        }
    }

    public static EntityPlayerMP c(ICommandSender icommandsender, String s0) {
        EntityPlayerMP entityplayermp = PlayerSelector.a(icommandsender, s0);

        if (entityplayermp != null) {
            return entityplayermp;
        } else {
            entityplayermp = MinecraftServer.D().ad().f(s0);
            if (entityplayermp == null) {
                throw new PlayerNotFoundException();
            } else {
                return entityplayermp;
            }
        }
    }

    public static String d(ICommandSender icommandsender, String s0) {
        EntityPlayerMP entityplayermp = PlayerSelector.a(icommandsender, s0);

        if (entityplayermp != null) {
            return entityplayermp.am();
        } else if (PlayerSelector.b(s0)) {
            throw new PlayerNotFoundException();
        } else {
            return s0;
        }
    }

    public static String a(ICommandSender icommandsender, String[] astring, int i0) {
        return a(icommandsender, astring, i0, false);
    }

    public static String a(ICommandSender icommandsender, String[] astring, int i0, boolean flag0) {
        StringBuilder stringbuilder = new StringBuilder();

        for (int i1 = i0; i1 < astring.length; ++i1) {
            if (i1 > i0) {
                stringbuilder.append(" ");
            }

            String s0 = astring[i1];

            if (flag0) {
                String s1 = PlayerSelector.b(icommandsender, s0);

                if (s1 != null) {
                    s0 = s1;
                } else if (PlayerSelector.b(s0)) {
                    throw new PlayerNotFoundException();
                }
            }

            stringbuilder.append(s0);
        }

        return stringbuilder.toString();
    }

    public static String a(Object[] aobject) {
        StringBuilder stringbuilder = new StringBuilder();

        for (int i0 = 0; i0 < aobject.length; ++i0) {
            String s0 = aobject[i0].toString();

            if (i0 > 0) {
                if (i0 == aobject.length - 1) {
                    stringbuilder.append(" and ");
                } else {
                    stringbuilder.append(", ");
                }
            }

            stringbuilder.append(s0);
        }

        return stringbuilder.toString();
    }

    public static String a(Collection collection) {
        return a(collection.toArray(new String[0]));
    }

    public static boolean a(String s0, String s1) {
        return s1.regionMatches(true, 0, s0, 0, s0.length());
    }

    public static List a(String[] astring, String... astring0) {
        String s0 = astring[astring.length - 1];
        ArrayList arraylist = new ArrayList();
        String[] astring1 = astring0;
        int i0 = astring0.length;

        for (int i1 = 0; i1 < i0; ++i1) {
            String s1 = astring1[i1];

            if (a(s0, s1)) {
                arraylist.add(s1);
            }
        }

        return arraylist;
    }

    public static List a(String[] astring, Iterable iterable) {
        String s0 = astring[astring.length - 1];
        ArrayList arraylist = new ArrayList();
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            String s1 = (String) iterator.next();

            if (a(s0, s1)) {
                arraylist.add(s1);
            }
        }

        return arraylist;
    }

    public boolean a(String[] astring, int i0) {
        return false;
    }

    public static void a(ICommandSender icommandsender, String s0, Object... aobject) {
        a(icommandsender, 0, s0, aobject);
    }

    public static void a(ICommandSender icommandsender, int i0, String s0, Object... aobject) {
        if (a != null) {
            a.a(icommandsender, i0, s0, aobject);
        }
    }

    public static void a(IAdminCommand iadmincommand) {
        a = iadmincommand;
    }

    public int a(ICommand icommand) {
        return this.c().compareTo(icommand.c());
    }

    public int compareTo(Object object) {
        return this.a((ICommand) object);
    }
}
