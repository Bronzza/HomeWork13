package dateBases.entitys;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Developer {
    private Integer id;
    private String surName;
    private String firstName;
    private Integer age;
    private Boolean isMale;
    private Integer salary;

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", surName='" + surName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", isMale=" + isMale +
                ", salary=" + salary +
                '}';
    }
}
