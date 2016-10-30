package service;

import com.example.jpa.condition.ConditionList;
import com.example.jpa.condition.Like;
import domain.UUser;


/**
 * Created by THINK on 2016/10/30.
 */
public class UserConditionBuilder extends ConditionList.Builder<UUser> {

    private String username;
    private Boolean enabled;
    private String role;
    private String usernameLike;
    public Boolean isEnabled() {
        return enabled;
    }

    String[] passwords;

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public String getUsernameLike() {
        return usernameLike;
    }

    public void setUsernameLike(final String usernameLike) {
        this.usernameLike = usernameLike;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    @Override
    public void conditions() {
        eq("username",username);
        eq("enabled",enabled);
        isMember("groups.authorities",role);
        in("password",passwords);
        like("username",usernameLike, Like.Mode.ANYWHERE);
    }
}
