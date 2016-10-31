package com.example.jpa.tools;

import com.example.jpa.condition.ConditionList;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

/**
 * Created by THINK on 2016/10/30.
 */
public interface ConditionExcuter<T> {

    EntityManager getEntityManager();

    Class<T> getEntityClass();

    default CriteriaQueryHolder createQuery(ConditionList<T> conditionList) {
        return ConditionUtil.createQueryByCondition(getEntityManager(), getEntityClass(), conditionList);
    }



    default int countByConditions(ConditionList<T> conditionList) {
        CriteriaQueryHolder v_queryHolder
                = ConditionUtil.createQueryByCondition(getEntityManager(), getEntityClass(), conditionList);
        CriteriaQuery query = v_queryHolder.getQuery();
        Root<T> root = v_queryHolder.getRoot();
        CriteriaQuery v_select =
                query.select(getEntityManager().getCriteriaBuilder().count(root));
        javax.persistence.Query q = getEntityManager().createQuery(v_select);
        return ((Long) q.getSingleResult()).intValue();
    }

    default List<T> findByConditions(ConditionList<T> conditionList) {
        return findByConditions(conditionList, LockModeType.NONE);
    }

    default List<T> findByConditions(ConditionList<T> conditionList, LockModeType lockModeType) {
        CriteriaQueryHolder v_queryHolder
                = ConditionUtil.createQueryByCondition(getEntityManager(), getEntityClass(), conditionList);
        CriteriaQuery v_query = v_queryHolder.getQuery();
        Root<T> root = v_queryHolder.getRoot();
        CriteriaQuery v_select =
                v_query.select(root);
        javax.persistence.Query q = getEntityManager().createQuery(v_select);

        if (conditionList.page >= 0 && conditionList.pageSize > 0) {
            q.setFirstResult(conditionList.getFirstResult());
            q.setMaxResults(conditionList.pageSize);
        }
        q.setLockMode(lockModeType);
        return q.getResultList();
    }

    default T getSingleResult(ConditionList<T> conditionList) {
        return getSingleResult(conditionList, LockModeType.NONE);
    }

    default T getSingleResult(ConditionList<T> conditionList, LockModeType lockModeType) {
        CriteriaQueryHolder v_queryHolder
                = ConditionUtil.createQueryByCondition(getEntityManager(), getEntityClass(), conditionList);
        CriteriaQuery v_query = v_queryHolder.getQuery();
        Root<T> root = v_queryHolder.getRoot();
        CriteriaQuery<T> v_select =
                v_query.select(root);
        javax.persistence.Query q = getEntityManager().createQuery(v_select);
        q.setLockMode(lockModeType);
        return (T) q.getSingleResult();
    }
}
