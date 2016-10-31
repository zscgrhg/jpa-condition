package service;


import com.example.jpa.condition.ConditionList;
import com.example.jpa.tools.CExcuter;
import com.example.jpa.tools.CUtil;
import com.example.jpa.tools.CriteriaQueryHolder;
import domain.UUser;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

/**
 * Created by THINK on 2016/10/30.
 */
public class UserService implements CExcuter<UUser> {
    EntityManager em;
    final Class<UUser> domainClass = UUser.class;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(final EntityManager em) {
        this.em = em;
    }


    public EntityManager getEntityManager() {
        return em;
    }


    public Class<UUser> getEntityClass() {
        return UUser.class;
    }

    @Override
    public CriteriaQueryHolder createQuery(final ConditionList<UUser> conditionList) {
        return CUtil.createQueryByCondition(em, domainClass, conditionList);
    }

    @Override
    public int countByConditions(final ConditionList<UUser> conditionList) {
        return CUtil.countByConditions(em, domainClass, conditionList);
    }

    @Override
    public List<UUser> findByConditions(final ConditionList<UUser> conditionList) {
        return CUtil.findByConditions(em, domainClass, conditionList);
    }

    @Override
    public List<UUser> findByConditions(final ConditionList<UUser> conditionList, final LockModeType lockModeType) {
        return CUtil.findByConditions(em, domainClass, conditionList, lockModeType);
    }

    @Override
    public UUser getSingleResult(final ConditionList<UUser> conditionList) {
        return CUtil.getSingleResult(em, domainClass, conditionList);
    }

    @Override
    public UUser getSingleResult(final ConditionList<UUser> conditionList, final LockModeType lockModeType) {
        return CUtil.getSingleResult(em, domainClass, conditionList, lockModeType);
    }
}
