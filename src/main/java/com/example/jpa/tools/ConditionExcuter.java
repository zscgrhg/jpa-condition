package com.example.jpa.tools;

import com.example.jpa.condition.ConditionList;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Created by THINK on 2016/10/30.
 */
public interface ConditionExcuter<T> {

    EntityManager getEntityManager();

    Class<T> getEntityClass();

    default CriteriaQuery createQuery(ConditionList<T> conditionList) {
        return ConditionUtil.createQueryByCondition(getEntityManager(), getEntityClass(), conditionList);
    }
}
