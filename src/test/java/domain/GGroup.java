package domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Created by THINK on 2016/10/24.
 */

@Entity

public class GGroup implements Serializable {

    private static final long serialVersionUID = -3319399054490265347L;
    @Id
    String name;
    String description;
    @ManyToMany(mappedBy = "groups")

    Collection<UUser> users;
    @ElementCollection

    Set<String> authorities;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Collection<UUser> getUsers() {
        return users;
    }

    public void setUsers(final Collection<UUser> users) {
        this.users = users;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(final Set<String> authorities) {
        this.authorities = authorities;
    }
}
