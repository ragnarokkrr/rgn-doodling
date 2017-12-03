package org.ragna.nomin.to;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by ragnarokkrr on 26/08/14.
 */
public class Details {
    private Boolean sex;
    private Date birth;
    private List<Education> educations;
    private Set<Kid> kids;


    public Details() {

    }

    public Details(Boolean sex, Date birth, List<Education> educations, Set<Kid> kids) {
        this.sex = sex;
        this.birth = birth;
        this.educations = educations;
        this.kids = kids;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public Set<Kid> getKids() {
        return kids;
    }

    public void setKids(Set<Kid> kids) {
        this.kids = kids;
    }


    @Override
    public String toString() {
        return "Details{" +
                "sex=" + sex +
                ", birth=" + birth +
                ", educations=" + educations +
                ", kids=" + kids +
                '}';
    }
}
