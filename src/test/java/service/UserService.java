package service;


import com.example.jpa.tools.ConditionExcuter;
import domain.UUser;

import javax.persistence.EntityManager;

/**
 * Created by THINK on 2016/10/30.
 */
public class UserService extends ConditionExcuter<UUser> {
    EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(final EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<UUser> getEntityClass() {
        return UUser.class;
    }
}
