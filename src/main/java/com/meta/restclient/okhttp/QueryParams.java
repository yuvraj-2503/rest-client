package com.meta.restclient.okhttp;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class QueryParams implements Iterable<QueryParam> {
    private final List<QueryParam> params = new ArrayList<>();
    private static final String AMPERSAND = "&";

    public QueryParams() {
    }

    public QueryParams add(String key, String value) {
        this.params.add(new QueryParam(key, value));
        return this;
    }

    public String join() {
        List<String> params = new ArrayList<>();
        Iterator<QueryParam> var2 = this.params.iterator();

        while(var2.hasNext()) {
            QueryParam param = var2.next();
            params.add(param.join());
        }

        return String.join("&", params);
    }

    @NotNull
    public Iterator<QueryParam> iterator() {
        return this.params.iterator();
    }

    public void forEach(Consumer<? super QueryParam> action) {
        this.params.forEach(action);
    }

    public Spliterator<QueryParam> spliterator() {
        return this.params.spliterator();
    }
}
