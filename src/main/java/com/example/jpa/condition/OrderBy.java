package com.example.jpa.condition;

/**
 * Created by THINK on 2016/10/30.
 */
public class OrderBy {
    final String property;
    final boolean asc;

    public String getProperty() {
        return property;
    }

    public boolean isAsc() {
        return asc;
    }

    public OrderBy(final String prop) {

        if (prop.startsWith("-")) {
            this.asc = false;
            this.property = prop.substring(1);
        } else {
            this.asc = true;
            this.property = prop;
        }

    }
}
