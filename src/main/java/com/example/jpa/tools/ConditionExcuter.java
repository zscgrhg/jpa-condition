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
        CriteriaQuery query
                = ConditionUtil.createQueryByCondition(getEntityManager(), getEntityClass(), conditionList);
        Set<Root<T>> v_roots = query.getRoots();
        Root<T> root = v_roots.iterator().next();
        CriteriaQuery v_select =
                query.select(getEntityManager().getCriteriaBuilder().count(root));
        javax.persistence.Query q = getEntityManager().createQuery(v_select);
        return ((Long) q.getSingleResult()).intValue();
    }

    default List<T> findByConditions(ConditionList<T> conditionList) {
        CriteriaQuery query
                = ConditionUtil.createQueryByCondition(getEntityManager(), getEntityClass(), conditionList);
        Set<Root<T>> v_roots = query.getRoots();
        Root<T> root = v_roots.iterator().next();
        CriteriaQuery v_select =
                query.select(root);
        javax.persistence.Query q = getEntityManager().createQuery(v_select);
        if (conditionList.page >= 0 && conditionList.pageSize > 0) {
            q.setFirstResult(conditionList.getFirstResult());
            q.setMaxResults(conditionList.pageSize);
        }
        return q.getResultList();
    }

    default T getSingleResult(ConditionList<T> conditionList) {
        CriteriaQuery query
                = ConditionUtil.createQueryByCondition(getEntityManager(), getEntityClass(), conditionList);
        Set<Root<T>> v_roots = query.getRoots();
        Root<T> root = v_roots.iterator().next();
        CriteriaQuery<T> v_select =
                query.select(root);
        javax.persistence.Query q = getEntityManager().createQuery(v_select);
        return (T) q.getSingleResult();
    }
}
