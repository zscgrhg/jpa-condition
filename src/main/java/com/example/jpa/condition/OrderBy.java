package com.example.jpa.condition;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by THINK on 2016/10/30.
 */
@EqualsAndHashCode
@Getter
public class OrderBy {
    final String property;
    final boolean asc;


    public OrderBy(final String prop) {
        String p = prop.trim();
        if (p.startsWith("-")) {
            this.asc = false;
            this.property = p.substring(1);
        } else {
            this.asc = true;
            this.property = p;
        }
    }
}
