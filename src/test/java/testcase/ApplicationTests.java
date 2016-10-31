package testcase;


import com.example.jpa.condition.ConditionList;
import domain.GGroup;
import domain.RoleConst;
import domain.UUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.UserConditionBuilder;
import service.UserService;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ApplicationTests {
    EntityManagerFactory emf;
    EntityManager em;

    public static void initDb(EntityManager em) {
        EntityTransaction v_transaction = em.getTransaction();
        v_transaction.begin();
        GGroup v_group = new GGroup();
        v_group.setName("admin");
        v_group.setDescription("no.1");
        HashSet<String> v_authorities = new HashSet<>();
        v_authorities.add(RoleConst.ROLE_ADMIN);
        v_authorities.add(RoleConst.ROLE_USER);
        v_group.setAuthorities(v_authorities);
        em.persist(v_group);
        HashSet<UUser> v_users = new HashSet<>();
        v_group.setUsers(v_users);
        for (int i = 0; i < 5; i++) {
            UUser v_uUser = new UUser();
            v_uUser.setUsername("user " + i);
            v_uUser.setPassword("1234");
            v_uUser.setEnabled(true);
            ArrayList<GGroup> v_groups = new ArrayList<>();
            v_groups.add(v_group);
            v_uUser.setGroups(v_groups);
            v_group.getUsers().add(v_uUser);
            em.persist(v_uUser);
        }
        v_transaction.commit();
    }

    @Before
    public void setUp() {
        emf =
                Persistence.createEntityManagerFactory("EmployeeService");
        em = emf.createEntityManager();
        try {
            initDb(em);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void queryUser() {
        final String name = "user 1";
        UserConditionBuilder v_builder = new UserConditionBuilder();
        v_builder.setUsername(name);
        v_builder.setEnabled(true);
        v_builder.setRole(RoleConst.ROLE_ADMIN);
        ConditionList<UUser> v_build = v_builder.build();

        UserService v_userService = new UserService();
        v_userService.setEm(em);

        UUser user = v_userService.getSingleResult(v_build);

        assertEquals(name, user.getUsername());
    }

    @Test
    public void testEq() {
        final String name = "user 1";
        UserConditionBuilder v_builder = new UserConditionBuilder();
        v_builder.setUsername(name);
        ConditionList<UUser> v_build = v_builder.build();
        UserService v_userService = new UserService();
        v_userService.setEm(em);
        UUser user = v_userService.getSingleResult(v_build);
        assertEquals(name, user.getUsername());
    }

    @Test
    public void testIsMemBer1() {
        final String role = RoleConst.ROLE_ADMIN;
        UserConditionBuilder v_builder = new UserConditionBuilder();
        v_builder.setRole(role);
        ConditionList<UUser> v_build = v_builder.build();
        UserService v_userService = new UserService();
        v_userService.setEm(em);
        List<UUser> v_resultList = v_userService.findByConditions(v_build);
        assertEquals(5, v_resultList.size());
    }

    @Test
    public void testIsMemBer2() {
        final String role = RoleConst.ROLE_MASTER;
        UserConditionBuilder v_builder = new UserConditionBuilder();
        v_builder.setRole(role);
        ConditionList<UUser> v_build = v_builder.build();
        UserService v_userService = new UserService();
        v_userService.setEm(em);
        List<UUser> v_resultList = v_userService.findByConditions(v_build);
        assertEquals(0, v_resultList.size());
    }

    @Test
    public void testLike() {
        final String name = "user";
        UserConditionBuilder v_builder = new UserConditionBuilder();
        v_builder.setUsernameLike(name);
        ConditionList<UUser> v_build = v_builder.build();
        UserService v_userService = new UserService();
        v_userService.setEm(em);
        List<UUser> v_resultList = v_userService.findByConditions(v_build);
        System.out.println(1);
        assertEquals(5, v_resultList.size());
    }

    @Test
    public void testLike2() {
        final String name = "1user";
        UserConditionBuilder v_builder = new UserConditionBuilder();
        v_builder.setUsernameLike(name);
        ConditionList<UUser> v_build = v_builder.build();
        UserService v_userService = new UserService();
        v_userService.setEm(em);
        List<UUser> v_resultList = v_userService.findByConditions(v_build);
        assertEquals(0, v_resultList.size());
    }

    @Test
    public void testNotEmpty() {
        UserConditionBuilder v_builder = new UserConditionBuilder();
        v_builder.setGroupIsEmpty(false);
        ConditionList<UUser> v_build = v_builder.build();
        UserService v_userService = new UserService();
        v_userService.setEm(em);

        List<UUser> v_resultList = v_userService.findByConditions(v_build);
        assertEquals(5, v_resultList.size());
    }

    @Test
    public void testCount() {
        UserConditionBuilder v_builder = new UserConditionBuilder();
        v_builder.setGroupIsEmpty(false);
        ConditionList<UUser> v_build = v_builder.build();
        UserService v_userService = new UserService();
        v_userService.setEm(em);
        int count = v_userService.countByConditions(v_build);
        assertEquals(5, count);
    }
}
