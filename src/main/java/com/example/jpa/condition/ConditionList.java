package com.example.jpa.condition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by THINK on 2016/10/30.
 */
public class ConditionList<T> {
    public final List<Condition<T>> conditionList;
    public final boolean distinct;
    public final int page;
    public final int pageSize;

    public final List<OrderBy> orderByList;


    public int getFirstResult() {
        return page * pageSize;
    }


    public List<Condition<T>> getConditionList() {
        return conditionList;
    }

    public ConditionList(final List<Condition<T>> conditionList, final boolean distinct, final int page, final int pageSize, final List<OrderBy> orderByList) {
        this.conditionList = conditionList;
        this.distinct = distinct;
        this.page = page;
        this.pageSize = pageSize;
        this.orderByList = orderByList;
    }

    public abstract static class Builder<E> {

        protected List<Condition<E>> conditionList = new ArrayList<>();
        protected boolean distinct = false;
        protected int page = 0;
        protected int pageSize = 10;
        protected List<String> orderByList;

        public boolean isDistinct() {
            return distinct;
        }

        public void setDistinct(final boolean distinct) {
            this.distinct = distinct;
        }

        public int getPage() {
            return page;
        }

        public void setPage(final int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(final int pageSize) {
            this.pageSize = pageSize;
        }

        public List<String> getOrderByList() {
            return orderByList;
        }

        public void setOrderByList(final List<String> orderByList) {
            this.orderByList = orderByList;
        }

        public Builder distinct() {
            distinct = true;
            return this;
        }

        public Builder maxResult(int max) {
            pageSize = Math.min(Math.abs(max), Math.abs(pageSize));
            return this;
        }

        public Builder eq(String path, Comparable param) {
            if (null != param) {
                Compare v_compare = new Compare(path, param, Compare.CType.EQ);
                conditionList.add(v_compare);
            }
            return this;
        }

        public Builder neq(String path, Comparable param) {
            if (null != param) {
                Compare v_compare = new Compare(path, param, Compare.CType.NEQ);
                conditionList.add(v_compare);
            }
            return this;
        }

        public Builder gt(String path, Comparable param) {
            if (null != param) {
                Compare v_compare = new Compare(path, param, Compare.CType.GT);
                conditionList.add(v_compare);
            }
            return this;
        }

        public Builder gte(String path, Comparable param) {
            if (null != param) {
                Compare v_compare = new Compare(path, param, Compare.CType.GTE);
                conditionList.add(v_compare);
            }

            return this;
        }

        public Builder lt(String path, Comparable param) {
            if (null != param) {
                Compare v_compare = new Compare(path, param, Compare.CType.LT);
                conditionList.add(v_compare);
            }
            return this;
        }

        public Builder lte(String path, Comparable param) {
            if (null != param) {
                Compare v_compare = new Compare(path, param, Compare.CType.LTE);
                conditionList.add(v_compare);
            }
            return this;
        }

        public Builder in(String path, Collection param) {
            if (null != param) {
                In in = new In(path, false, param);
                conditionList.add(in);
            }
            return this;
        }


        public Builder in(String path, Object... param) {
            if (param != null) {
                In in = new In(path, false, param);
                conditionList.add(in);
            }
            return this;
        }

        public Builder notIn(String path, Object... param) {
            if (param != null) {
                In in = new In(path, true, param);
                conditionList.add(in);
            }
            return this;
        }

        public Builder notIn(String path, Collection param) {
            if (null != param) {
                In in = new In(path, true, param);
                conditionList.add(in);
            }
            return this;
        }

        public Builder like(String path, String param, Like.Mode mode) {
            if (null != param) {
                Like like = new Like(path, param, mode);
                conditionList.add(like);
            }
            return this;
        }

        public Builder notLike(String path, String param, Like.Mode mode) {
            if (null != param) {
                Like like = new Like(path, param, mode, true);
                conditionList.add(like);
            }
            return this;
        }

        public Builder isMember(String path, Object param) {
            if (null != param) {
                IsMember v_isMember = new IsMember(path, false, param);
                conditionList.add(v_isMember);
            }
            return this;
        }

        public Builder notMember(String path, Object param) {
            if (null != param) {
                IsMember v_isMember = new IsMember(path, true, param);
                conditionList.add(v_isMember);
            }
            return this;
        }

        public Builder isNull(String path, Boolean param) {
            if (null != param) {
                NullValue v_notNull = new NullValue(path, param);
                conditionList.add(v_notNull);
            }
            return this;
        }


        public Builder isEmpty(String path, Boolean param) {
            if (null != param) {
                Empty v_notEmpty = new Empty(path, param);
                conditionList.add(v_notEmpty);
            }
            return this;
        }


        public abstract void conditions();

        public ConditionList<E> build() {
            conditions();
            List<Condition<E>> v_conditions = Collections.unmodifiableList(conditionList);
            List<OrderBy> orderBy = new ArrayList<>();
            if (orderByList != null) {
                for (String v_s : orderByList) {
                    orderBy.add(new OrderBy(v_s));
                }
            }
            return new ConditionList<E>(v_conditions, distinct, page, pageSize, Collections.unmodifiableList(orderBy));
        }
    }
}
