package net.canarymod.api.nbt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.minecraft.server.NBTBase;
import net.minecraft.server.NBTTagList;

public class CanaryListTag<T extends CanaryBaseTag> extends CanaryBaseTag implements ListTag<T> {

    protected CanaryListTag(NBTTagList tag) {
        super(tag);
    }

    @Override
    public boolean add(T e) {
        getHandle().a(e.getHandle());
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(int index, T element) {
        getHandle().a.add(index, element.getHandle());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return getHandle().a.addAll(makeRaw(c));
    }
    
    private Collection<NBTBase> makeRaw(Collection<?> c) {
        Collection<NBTBase> raw = new ArrayList<NBTBase>(c.size());
        for(Object o : c) {
            raw.add(((CanaryBaseTag) o).getHandle());
        }
        return raw;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return getHandle().a.addAll(index, makeRaw(c));
    }

    @Override
    public void clear() {
        getHandle().a.clear();
    }

    @Override
    public boolean contains(Object o) {
        return getHandle().a.contains(o);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean containsAll(Collection<?> c) {
        return getHandle().a.containsAll(c);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        return (T) CanaryBaseTag.wrap(getHandle().b(index));
    }

    @Override
    public int indexOf(Object o) {
        return getHandle().a.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return getHandle().a.isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<T> iterator() {
        return getHandle().a.iterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        return getHandle().a.lastIndexOf(o);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListIterator<T> listIterator() {
        return getHandle().a.listIterator();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListIterator<T> listIterator(int index) {
        return getHandle().a.listIterator(index);
    }

    @Override
    public boolean remove(Object o) {
        return getHandle().a.remove(o);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        return (T) getHandle().a.remove(index);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean removeAll(Collection<?> c) {
        return getHandle().a.removeAll(makeRaw(c));
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean retainAll(Collection<?> c) {
        return getHandle().a.retainAll(makeRaw(c));
    }

    @SuppressWarnings("unchecked")
    @Override
    public T set(int index, T element) {
        return (T) getHandle().a.set(index, element);
    }

    @Override
    public int size() {
        return getHandle().c();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return getHandle().a.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return getHandle().a.toArray();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> E[] toArray(E[] a) {
        int size = size();
        if(a.length >= size) {
            for(int i=0; i<size; i++) {
                a[i] = (E) get(i); // the get method wraps the original nbt tag
            }
            if(a.length > size) {
                a[size] = null; // as specified by the list javadoc
            }
            return a;
        } else {
            E[] arr = (E[]) Array.newInstance(a.getClass(), size);
            for(int i=0; i<size; i++) {
                arr[i] = (E) get(i); // the get method wraps the original nbt tag
            }
            return arr;
        }
    }

    @Override
    protected NBTTagList getHandle() {
        return (NBTTagList) tag;
    }
}
