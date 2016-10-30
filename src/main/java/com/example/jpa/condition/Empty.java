package com.example.jpa.condition;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by THINK on 2016/10/30.
 */
public class Empty<T> extends PathCondition<T, Object> {
    final boolean isEmpty;

    public Empty(final String path, final boolean isEmpty) {
        super(path, null);
        this.isEmpty = isEmpty;
    }

    @Override
    public void apply(final CriteriaBuilder cb, final CriteriaQuery cq, final Root<T> rt, final List<Predicate> predicates) {
        Path p = getPath(rt, path);

        if (isEmpty) {
            predicates.add(cb.isEmpty(p));
        } else {
            predicates.add(cb.isNotEmpty(p));
        }
    }
}
