package com.example.jpa.condition;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by THINK on 2016/10/30.
 */
public interface Condition<T> {
    void apply(CriteriaBuilder cb, CriteriaQuery cq, Root<T> rt, List<Predicate> predicates);
}
