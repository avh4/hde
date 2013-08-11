package net.avh4.util;

import fj.F;
import fj.P2;
import fj.data.HashMap;
import fj.data.List;
import fj.data.Option;

import java.util.Collection;
import java.util.Iterator;

public class MultiMap<K, T> implements Iterable {
    private final HashMap<K, List<T>> hashMap;

    public MultiMap() {
        hashMap = HashMap.hashMap();
    }

    public Iterator<K> iterator() {
        return hashMap.iterator();
    }

    public boolean eq(K k1, K k2) {
        return hashMap.eq(k1, k2);
    }

    public int hash(K k) {
        return hashMap.hash(k);
    }

    public Option<List<T>> get(K k) {
        return hashMap.get(k);
    }

    public F<K, Option<List<T>>> get() {
        return hashMap.get();
    }

    public void clear() {
        hashMap.clear();
    }

    public boolean contains(K k) {
        return hashMap.contains(k);
    }

    public List<K> keys() {
        return hashMap.keys();
    }

    public List<List<T>> values() {
        return hashMap.values();
    }

    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    public int size() {
        return hashMap.size();
    }

    public Collection<P2<K, List<T>>> toCollection() {
        return hashMap.toCollection();
    }

    public void add(K k, T v) {
        List<T> list = hashMap.get(k).orSome(List.<T>nil());
        hashMap.set(k, list.cons(v));
    }
}
