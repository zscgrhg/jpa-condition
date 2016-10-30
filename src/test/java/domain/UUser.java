package domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by THINK on 2016/10/24.
 * {@link }exclude the {@link UUser#groups} lazy Collection to avoid {@link StackOverflowError}
 */

@Entity
@Access(AccessType.FIELD)
public class UUser implements Serializable {

    private static final long serialVersionUID = -8748293873537157101L;
    @Id
    String username;
    String password;
    Boolean enabled;
    @ManyToMany
    Collection<GGroup> groups;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<GGroup> getGroups() {
        return groups;
    }

    public void setGroups(final Collection<GGroup> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "UUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", groups=" + groups +
                '}';
    }
}
