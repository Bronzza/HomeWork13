package dateBases.entitys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Developer {
    private Integer id;
    private String surName;
    private String firstName;
    private Integer age;
    private Boolean isMale;
    private Integer salary;
}
