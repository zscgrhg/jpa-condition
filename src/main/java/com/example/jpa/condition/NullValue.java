package com.example.jpa.condition;

import lombok.EqualsAndHashCode;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by THINK on 2016/10/30.
 */
@EqualsAndHashCode(callSuper = true)
public class NullValue<T> extends PathCondition<T, Object> {
    final boolean isNull;

    public NullValue(final String path, final boolean isNull) {
        super(path, null);
        this.isNull = isNull;
    }

    @Override
    public void apply(final CriteriaBuilder cb, final CriteriaQuery cq, final Root<T> rt, final List<Predicate> predicates) {
        Path p = getPath(rt, path);

        if (isNull) {
            predicates.add(cb.isNull(p));
        } else {
            predicates.add(cb.isNotNull(p));
        }
    }
}
