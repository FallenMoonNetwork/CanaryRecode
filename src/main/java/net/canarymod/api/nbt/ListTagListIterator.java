package net.canarymod.api.nbt;

import java.util.ListIterator;
import net.minecraft.server.NBTBase;

/**
 * Even more magic
 * 
 * @author Jason (darkdiplomat)
 * @param <E>
 */
class ListTagListIterator<E extends BaseTag> extends ListTagIterator<E> implements ListIterator<E> {

    ListTagListIterator(ListIterator<?> nativeListIterator) {
        super(nativeListIterator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean hasPrevious() {
        return ((ListIterator<E>) nativeIterator).hasPrevious();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E previous() {
        return (E) CanaryBaseTag.wrap((NBTBase) ((ListIterator<E>) nativeIterator).previous());
    }

    @SuppressWarnings("unchecked")
    @Override
    public int nextIndex() {
        return ((ListIterator<E>) nativeIterator).nextIndex();
    }

    @SuppressWarnings("unchecked")
    @Override
    public int previousIndex() {
        return ((ListIterator<E>) nativeIterator).previousIndex();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void set(E e) {
        ((ListIterator<E>) nativeIterator).set((E) ((CanaryBaseTag) e).getHandle());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(E e) {
        ((ListIterator<E>) nativeIterator).set((E) ((CanaryBaseTag) e).getHandle());
    }

}
