package com.example.jpa.tools;

import com.example.jpa.condition.Condition;
import com.example.jpa.condition.ConditionList;
import com.example.jpa.condition.OrderBy;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by THINK on 2016/10/30.
 */
public class ConditionUtil {
    public static <T> CriteriaQueryHolder<T> createQueryByCondition(EntityManager em, Class<T> clazz, ConditionList<T> conditionList) {
        Metamodel metamodel = em.getMetamodel();
        EntityType<?> entityType = metamodel.entity(clazz);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root rt = cq.from(clazz);
        List<Predicate> predicates = new ArrayList();

        List<Condition<T>> v_conditionList = conditionList.getConditionList();
        for (Condition<T> v_tCondition : v_conditionList) {
            v_tCondition.apply(cb, cq, rt, predicates);
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        cq.distinct(conditionList.distinct);
        boolean hasSingleId = entityType.hasSingleIdAttribute();
        if (conditionList.orderByList != null) {
            for (OrderBy o : conditionList.orderByList) {

                if (o.isAsc()) {
                    cq.orderBy(cb.asc(rt.get(o.getProperty())));
                } else {
                    cq.orderBy(cb.desc(rt.get(o.getProperty())));
                }
            }
        } else if (hasSingleId) {
            Type<?> idType = entityType.getIdType();
            Class<?> javaType = idType.getJavaType();
            SingularAttribute<?, ?> id = entityType.getId(javaType);
            cq.orderBy(cb.desc(rt.get(id)));
        }
        return new CriteriaQueryHolder<T>(rt, cq, conditionList);
    }
}
