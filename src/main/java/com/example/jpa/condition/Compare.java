package com.example.jpa.condition;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by THINK on 2016/10/30.
 */
public class Compare<T> extends PathCondition<T, Comparable> {


    private final CType ctype;

    public Compare(final String path, final Comparable param, final CType ctype) {
        super(path, param);
        this.ctype = ctype;
    }

    @Override
    public void apply(final CriteriaBuilder cb, final CriteriaQuery cq, final Root<T> rt, final List<Predicate> predicates) {
        Path p = getPath(rt, path);
        Predicate comp;
        switch (ctype) {
            case GT:
                comp = cb.greaterThan(p, param);
                break;
            case GTE:
                comp = cb.greaterThanOrEqualTo(p, param);
                break;
            case LT:
                comp = cb.lessThan(p, param);
                break;
            case LTE:
                comp = cb.lessThanOrEqualTo(p, param);
                break;
            case NEQ:
                comp = cb.notEqual(p, param);
                break;
            default:
                comp = cb.equal(p, param);

        }
        predicates.add(comp);
    }


    public enum CType {
        NEQ, GTE, LTE, GT, LT, EQ
    }
}
