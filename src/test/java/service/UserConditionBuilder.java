package service;

import com.example.jpa.condition.ConditionList;
import com.example.jpa.condition.Like;
import domain.UUser;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Created by THINK on 2016/10/30.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserConditionBuilder extends ConditionList.Builder<UUser> {

    private String username;
    private Boolean enabled;
    private String role;
    private String usernameLike;




    public Boolean groupIsEmpty;
    String[] passwords;



    @Override
    public void conditions() {
        eq("username", username);
        eq("enabled", enabled);
        isMember("groups.authorities", role);
        in("password", passwords);
        like("username", usernameLike, Like.Mode.ANYWHERE);
        isEmpty("groups", groupIsEmpty);
    }
}
