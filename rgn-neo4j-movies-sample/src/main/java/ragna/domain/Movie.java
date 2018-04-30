package ragna.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Movie")
public class Movie {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private int released;
    private String tagline;


    @JsonIgnoreProperties("movie")
    @Relationship(type="ACTED_IN", direction = Relationship.INCOMING)
    private List<Role> roles;

    public Movie() {
    }

    public Movie(String title, int released, String tagline) {
        this.title = title;
        this.released = released;
        this.tagline = tagline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        if (this.roles == null){
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return released == movie.released &&
                Objects.equals(id, movie.id) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(tagline, movie.tagline) &&
                Objects.equals(roles, movie.roles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, released, tagline, roles);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", released=" + released +
                ", tagline='" + tagline + '\'' +
                ", roles=" + roles +
                '}';
    }
}
