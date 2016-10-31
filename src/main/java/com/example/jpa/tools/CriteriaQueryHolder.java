package com.example.jpa.tools;

import com.example.jpa.condition.ConditionList;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by THINK on 2016/10/31.
 */
@Getter
@EqualsAndHashCode
public class CriteriaQueryHolder<T> {
    final Root<T> root;
    final CriteriaQuery query;
    final ConditionList<T> conditionList;

    public CriteriaQueryHolder(final Root<T> root, final CriteriaQuery query, final ConditionList<T> conditionList) {
        this.root = root;
        this.query = query;
        this.conditionList = conditionList;
    }
}
