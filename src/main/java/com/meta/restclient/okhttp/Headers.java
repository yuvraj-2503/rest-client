package com.meta.restclient.okhttp;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Headers implements Iterable<Header> {
    private final List<Header> headers = new ArrayList<>();

    public Headers() {
    }

    public Headers add(String key, String value) {
        this.headers.add(new Header(key, value));
        return this;
    }

    @NotNull
    public Iterator<Header> iterator() {
        return this.headers.iterator();
    }

    public void forEach(Consumer<? super Header> action) {
        this.headers.forEach(action);
    }

    public Spliterator<Header> spliterator() {
        return this.headers.spliterator();
    }
}