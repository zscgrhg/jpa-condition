package com.example.jpa.tools;

import com.example.jpa.condition.ConditionList;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by THINK on 2016/10/30.
 */
public interface CExcuter<T> {


    CriteriaQueryHolder createQuery(ConditionList<T> conditionList);


    int countByConditions(ConditionList<T> conditionList);

    List<T> findByConditions(ConditionList<T> conditionList);

    List<T> findByConditions(ConditionList<T> conditionList, LockModeType lockModeType);

    T getSingleResult(ConditionList<T> conditionList);

    T getSingleResult(ConditionList<T> conditionList, LockModeType lockModeType);
}
