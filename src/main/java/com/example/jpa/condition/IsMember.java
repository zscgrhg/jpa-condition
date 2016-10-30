package com.example.jpa.condition;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by THINK on 2016/10/23.
 */
public class IsMember<T> extends PathCondition<T, Object> {
    final boolean notMember;
    public IsMember(final String path, final boolean notMember,final Object param) {
        super(path, param);
        this.notMember = notMember;
    }

    @Override
    public void apply(CriteriaBuilder cb, CriteriaQuery cq, Root<T> rt, List<Predicate> predicates) {
        Path p = getPath(rt, path);
        Predicate isMember = cb.isMember(param, p);
        if(notMember){
            predicates.add(cb.not(isMember));
        }else {
            predicates.add(isMember);
        }

    }
}
