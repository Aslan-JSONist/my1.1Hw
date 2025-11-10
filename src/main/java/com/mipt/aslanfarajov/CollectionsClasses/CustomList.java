package com.mipt.aslanfarajov.CollectionsClasses;
import java.util.Iterator;
import com.mipt.aslanfarajov.CollectionsClasses.CustomList;
import com.mipt.aslanfarajov.CollectionsClasses.CustomArrayList;
public interface CustomList<A> extends Iterable<A> {
    void add(A element);
    A get(int index);
    A remove(int index);
    int size();
    boolean isEmpty();
}
