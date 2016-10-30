package com.example.jpa.tools;

import com.example.jpa.condition.ConditionList;

import javax.persistence.EntityManager;
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

    default CriteriaQuery createQuery(ConditionList<T> conditionList) {
        return ConditionUtil.createQueryByCondition(getEntityManager(), getEntityClass(), conditionList);
    }

    default int countByConditions(ConditionList<T> conditionList) {
        CriteriaQuery v_queryByCondition
                = ConditionUtil.createQueryByCondition(getEntityManager(), getEntityClass(), conditionList);
        Set<Root<T>> v_roots = v_queryByCondition.getRoots();
        Root<T> v_next = v_roots.iterator().next();
        CriteriaQuery v_select =
                v_queryByCondition.select(getEntityManager().getCriteriaBuilder().count(v_next));
        javax.persistence.Query q = getEntityManager().createQuery(v_select);
        return ((Long) q.getSingleResult()).intValue();
    }

    default List<T> findByConditions(ConditionList<T> conditionList) {
        CriteriaQuery v_queryByCondition
                = ConditionUtil.createQueryByCondition(getEntityManager(), getEntityClass(), conditionList);
        javax.persistence.Query q = getEntityManager().createQuery(v_queryByCondition);
        if (conditionList.page >= 0 && conditionList.pageSize > 0) {
            q.setFirstResult(conditionList.getFirstResult());
            q.setMaxResults(conditionList.getFirstResult() + conditionList.pageSize);
        }
        return q.getResultList();
    }
}
