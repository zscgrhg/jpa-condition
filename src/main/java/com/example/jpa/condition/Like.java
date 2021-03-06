package com.example.jpa.condition;

import lombok.EqualsAndHashCode;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */
@EqualsAndHashCode(callSuper = true)
public class Like<T> extends PathCondition<T, String> {
    public enum Mode {
        START, END, ANYWHERE
    }

    final Mode matchMode;
    final boolean notLike;

    public Like(String path, String param, Mode matchMode, final boolean notLike) {

        super(path, param);
        this.matchMode = matchMode;
        this.notLike = notLike;
    }

    public Like(String path, String param, Mode matchMode) {

        super(path, param);
        this.matchMode = matchMode;
        this.notLike = false;
    }

    @Override
    public void apply(CriteriaBuilder cb, CriteriaQuery cq, Root<T> rt, List<Predicate> predicates) {
        Path p = getPath(rt, path);
        String t;
        switch (matchMode) {
            case START:
                t = param + "%";
                break;
            case END:
                t = "%";
                break;
            default:
                t = "%" + param + "%";
        }
        if (notLike) {
            predicates.add(cb.notLike(p, t));
        } else {
            predicates.add(cb.like(p, t));
        }

    }
}
