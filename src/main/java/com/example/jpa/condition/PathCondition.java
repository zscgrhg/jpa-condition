package com.example.jpa.condition;

import lombok.EqualsAndHashCode;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * Created by THINK on 2016/10/30.
 */
@EqualsAndHashCode
public abstract class PathCondition<T, O> implements Condition<T> {

    final String path;
    final O param;

    protected Path getPath(Root rt, String attrPath) {
        String[] path = attrPath.split("\\.");
        Path x = rt;
        for (int i = 0; i < path.length - 1; i++) {
            if (x instanceof Root) {
                x = ((Root) x).join(path[i]);
            } else {
                x = ((Join) x).join(path[i]);
            }
        }
        String attr = path[path.length - 1];
        x = x.get(attr);
        return x;
    }

    public PathCondition(final String path, final O param) {

        this.path = path;
        this.param = param;
    }
}
