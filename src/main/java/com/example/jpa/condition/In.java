package com.example.jpa.condition;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */
public class In<T> extends PathCondition<T, Collection> {


    public In(final String path, final Collection param) {
        super(path, param);
    }

    public In(final String path, Object... param) {
        super(path, Arrays.asList(param));
    }

    @Override
    public void apply(final CriteriaBuilder cb, final CriteriaQuery cq, final Root<T> rt, final List<Predicate> predicates) {
        Path p = getPath(rt, path);
        Predicate in = p.in(param);
        predicates.add(in);
    }
}
