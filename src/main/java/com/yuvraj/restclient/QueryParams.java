package com.yuvraj.restclient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author Yuvraj
 */
public class QueryParams implements Iterable<QueryParam> {
    private final List<QueryParam> params = new ArrayList();
    private final String AMPERSAND = "&";

    public QueryParams() {
    }

    public QueryParams add(String key, String value) {
        this.params.add(new QueryParam(key, value));
        return this;
    }

    public String join() {
        List<String> params = new ArrayList();

        for (QueryParam param : this.params) {
            params.add(param.join());
        }

        return String.join(AMPERSAND, params);
    }

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
