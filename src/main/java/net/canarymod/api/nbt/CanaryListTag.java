package net.canarymod.api.nbt;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import net.minecraft.server.NBTBase;
import net.minecraft.server.NBTTagList;


/**
 * ListTag wrapper implementation
 * 
 * @author Greg (gregthegeek)
 * @author Jason (darkdiplomat)
 */
public class CanaryListTag<E extends CanaryBaseTag> extends CanaryBaseTag implements ListTag<E> {

    /**
     * Constructs a new wrapper for NBTTagList
     * 
     * @param tag
     *            the NBTTagList to wrap
     */
    public CanaryListTag(NBTTagList tag) {
        super(tag);
    }

    /**
     * Constructs a new CanaryListTag and associated NBTTagList
     * 
     * @param name
     *            the name of the tag
     */
    public CanaryListTag(String name) {
        super(new NBTTagList(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E e) {
        getHandle().a(e.getHandle());
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void add(int index, E element) {
        getHandle().a.add(index, element.getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return getHandle().a.addAll(makeRaw(c));
    }
    
    private Collection<NBTBase> makeRaw(Collection<?> c) {
        Collection<NBTBase> raw = new ArrayList<NBTBase>(c.size());

        for (Object o : c) {
            raw.add(((CanaryBaseTag) o).getHandle());
        }
        return raw;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return getHandle().a.addAll(index, makeRaw(c));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        getHandle().a.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        if (o instanceof CanaryBaseTag) { // Pass handle, not actual object
            getHandle().a.contains(((CanaryBaseTag) o).getHandle());
        }
        return getHandle().a.contains(o);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean containsAll(Collection<?> c) {
        return getHandle().a.containsAll(makeRaw(c));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        return (E) CanaryBaseTag.wrap(getHandle().b(index));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int indexOf(Object o) {
        if (o instanceof CanaryBaseTag) { // Pass handle, not actual object
            return getHandle().a.indexOf(((CanaryBaseTag) o).getHandle());
        }
        return getHandle().a.indexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return getHandle().a.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new ListTagIterator<E>(getHandle().a.iterator());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o instanceof CanaryBaseTag) { // Pass handle, not actual object
            return getHandle().a.lastIndexOf(((CanaryBaseTag) o).getHandle());
        }
        return getHandle().a.lastIndexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListTagListIterator<E>((ListIterator<NBTBase>) getHandle().a.listIterator(index));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Object o) {
        if (o instanceof CanaryBaseTag) { // Pass handle, not actual object
            return getHandle().a.remove(((CanaryBaseTag) o).getHandle());
        }
        return getHandle().a.remove(o);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        return (E) getHandle().a.remove(index);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean removeAll(Collection<?> c) {
        return getHandle().a.removeAll(makeRaw(c));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean retainAll(Collection<?> c) {
        return getHandle().a.retainAll(makeRaw(c));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E element) {
        return (E) getHandle().a.set(index, ((CanaryBaseTag) element).getHandle()); // Need to pass handle, not element
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return getHandle().c();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        ArrayList<E> elements = new ArrayList<E>();
        for (Object base : getHandle().a) {
            elements.add((E) CanaryBaseTag.wrap((NBTBase) base));
        }
        return elements.subList(fromIndex, toIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        int size = size();

        if (a.length >= size) {
            for (int i = 0; i < size; i++) {
                a[i] = (T) get(i); // the get method wraps the original nbt tag
            }
            if (a.length > size) {
                a[size] = null; // as specified by the list javadoc
            }
            return a;
        } else {
            T[] arr = (T[]) Array.newInstance(a.getClass(), size);

            for (int i = 0; i < size; i++) {
                arr[i] = (T) get(i); // the get method wraps the original nbt tag
            }
            return arr;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListTag<E> copy() {
        return new CanaryListTag<E>((NBTTagList) getHandle().b());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NBTTagList getHandle() {
        return (NBTTagList) tag;
    }
}
