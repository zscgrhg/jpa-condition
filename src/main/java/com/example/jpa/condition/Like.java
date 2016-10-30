package com.example.jpa.condition;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */
public class Like<T> extends PathCondition<T, String> {
    public enum Mode {
        START, END, ANYWHERE
    }

    final Mode matchMode;

    public Like(String path, String param, Mode matchMode) {

        super(path, param);
        this.matchMode = matchMode;
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
        Predicate like = cb.like(p, t);
        predicates.add(like);
    }
}
