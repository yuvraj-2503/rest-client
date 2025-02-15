package com.yuvraj.restclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author Yuvraj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Headers implements Iterable<Header> {
    private List<Header> headers = new ArrayList<>();

    public Headers add(String key, String value) {
        headers.add(new Header(key, value));
        return this;
    }

    public Iterator<Header> iterator() {
        return headers.iterator();
    }

    public void forEach(Consumer<? super Header> action) {
        headers.forEach(action);
    }

    public Spliterator<Header> spliterator() {
        return headers.spliterator();
    }
}
