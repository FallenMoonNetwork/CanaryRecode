package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class StructureStrongholdPieces {

    private static final StructureStrongholdPieceWeight[] b = new StructureStrongholdPieceWeight[] { new StructureStrongholdPieceWeight(ComponentStrongholdStraight.class, 40, 0), new StructureStrongholdPieceWeight(ComponentStrongholdPrison.class, 5, 5), new StructureStrongholdPieceWeight(ComponentStrongholdLeftTurn.class, 20, 0),
            new StructureStrongholdPieceWeight(ComponentStrongholdRightTurn.class, 20, 0), new StructureStrongholdPieceWeight(ComponentStrongholdRoomCrossing.class, 10, 6), new StructureStrongholdPieceWeight(ComponentStrongholdStairsStraight.class, 5, 5), new StructureStrongholdPieceWeight(ComponentStrongholdStairs.class, 5, 5),
            new StructureStrongholdPieceWeight(ComponentStrongholdCrossing.class, 5, 4), new StructureStrongholdPieceWeight(ComponentStrongholdChestCorridor.class, 5, 4), new StructureStrongholdPieceWeight2(ComponentStrongholdLibrary.class, 10, 2), new StructureStrongholdPieceWeight3(ComponentStrongholdPortalRoom.class, 20, 1) };
    private static List c;
    private static Class d;
    static int a = 0;
    private static final StructureStrongholdStones e = new StructureStrongholdStones((StructureStrongholdPieceWeight2) null);

    public static void a() {
        c = new ArrayList();
        StructureStrongholdPieceWeight[] astructurestrongholdpieceweight = b;
        int i0 = astructurestrongholdpieceweight.length;

        for (int i1 = 0; i1 < i0; ++i1) {
            StructureStrongholdPieceWeight structurestrongholdpieceweight = astructurestrongholdpieceweight[i1];

            structurestrongholdpieceweight.c = 0;
            c.add(structurestrongholdpieceweight);
        }

        d = null;
    }

    private static boolean c() {
        boolean flag0 = false;

        a = 0;

        StructureStrongholdPieceWeight structurestrongholdpieceweight;

        for (Iterator iterator = c.iterator(); iterator.hasNext(); a += structurestrongholdpieceweight.b) {
            structurestrongholdpieceweight = (StructureStrongholdPieceWeight) iterator.next();
            if (structurestrongholdpieceweight.d > 0 && structurestrongholdpieceweight.c < structurestrongholdpieceweight.d) {
                flag0 = true;
            }
        }

        return flag0;
    }

    private static ComponentStronghold a(Class oclass0, List list, Random random, int i0, int i1, int i2, int i3, int i4) {
        Object object = null;

        if (oclass0 == ComponentStrongholdStraight.class) {
            object = ComponentStrongholdStraight.a(list, random, i0, i1, i2, i3, i4);
        } else if (oclass0 == ComponentStrongholdPrison.class) {
            object = ComponentStrongholdPrison.a(list, random, i0, i1, i2, i3, i4);
        } else if (oclass0 == ComponentStrongholdLeftTurn.class) {
            object = ComponentStrongholdLeftTurn.a(list, random, i0, i1, i2, i3, i4);
        } else if (oclass0 == ComponentStrongholdRightTurn.class) {
            object = ComponentStrongholdRightTurn.a(list, random, i0, i1, i2, i3, i4);
        } else if (oclass0 == ComponentStrongholdRoomCrossing.class) {
            object = ComponentStrongholdRoomCrossing.a(list, random, i0, i1, i2, i3, i4);
        } else if (oclass0 == ComponentStrongholdStairsStraight.class) {
            object = ComponentStrongholdStairsStraight.a(list, random, i0, i1, i2, i3, i4);
        } else if (oclass0 == ComponentStrongholdStairs.class) {
            object = ComponentStrongholdStairs.a(list, random, i0, i1, i2, i3, i4);
        } else if (oclass0 == ComponentStrongholdCrossing.class) {
            object = ComponentStrongholdCrossing.a(list, random, i0, i1, i2, i3, i4);
        } else if (oclass0 == ComponentStrongholdChestCorridor.class) {
            object = ComponentStrongholdChestCorridor.a(list, random, i0, i1, i2, i3, i4);
        } else if (oclass0 == ComponentStrongholdLibrary.class) {
            object = ComponentStrongholdLibrary.a(list, random, i0, i1, i2, i3, i4);
        } else if (oclass0 == ComponentStrongholdPortalRoom.class) {
            object = ComponentStrongholdPortalRoom.a(list, random, i0, i1, i2, i3, i4);
        }

        return (ComponentStronghold) object;
    }

    private static ComponentStronghold b(ComponentStrongholdStairs2 componentstrongholdstairs2, List list, Random random, int i0, int i1, int i2, int i3, int i4) {
        if (!c()) {
            return null;
        } else {
            if (d != null) {
                ComponentStronghold componentstronghold = a(d, list, random, i0, i1, i2, i3, i4);

                d = null;
                if (componentstronghold != null) {
                    return componentstronghold;
                }
            }

            int i5 = 0;

            while (i5 < 5) {
                ++i5;
                int i6 = random.nextInt(a);
                Iterator iterator = c.iterator();

                while (iterator.hasNext()) {
                    StructureStrongholdPieceWeight structurestrongholdpieceweight = (StructureStrongholdPieceWeight) iterator.next();

                    i6 -= structurestrongholdpieceweight.b;
                    if (i6 < 0) {
                        if (!structurestrongholdpieceweight.a(i4) || structurestrongholdpieceweight == componentstrongholdstairs2.a) {
                            break;
                        }

                        ComponentStronghold componentstronghold1 = a(structurestrongholdpieceweight.a, list, random, i0, i1, i2, i3, i4);

                        if (componentstronghold1 != null) {
                            ++structurestrongholdpieceweight.c;
                            componentstrongholdstairs2.a = structurestrongholdpieceweight;
                            if (!structurestrongholdpieceweight.a()) {
                                c.remove(structurestrongholdpieceweight);
                            }

                            return componentstronghold1;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = ComponentStrongholdCorridor.a(list, random, i0, i1, i2, i3);

            if (structureboundingbox != null && structureboundingbox.b > 1) {
                return new ComponentStrongholdCorridor(i4, random, structureboundingbox, i3);
            } else {
                return null;
            }
        }
    }

    private static StructureComponent c(ComponentStrongholdStairs2 componentstrongholdstairs2, List list, Random random, int i0, int i1, int i2, int i3, int i4) {
        if (i4 > 50) {
            return null;
        } else if (Math.abs(i0 - componentstrongholdstairs2.b().a) <= 112 && Math.abs(i2 - componentstrongholdstairs2.b().c) <= 112) {
            ComponentStronghold componentstronghold = b(componentstrongholdstairs2, list, random, i0, i1, i2, i3, i4 + 1);

            if (componentstronghold != null) {
                list.add(componentstronghold);
                componentstrongholdstairs2.c.add(componentstronghold);
            }

            return componentstronghold;
        } else {
            return null;
        }
    }

    static StructureComponent a(ComponentStrongholdStairs2 componentstrongholdstairs2, List list, Random random, int i0, int i1, int i2, int i3, int i4) {
        return c(componentstrongholdstairs2, list, random, i0, i1, i2, i3, i4);
    }

    static Class a(Class oclass0) {
        d = oclass0;
        return oclass0;
    }

    static StructureStrongholdStones b() {
        return e;
    }
}
