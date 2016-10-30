package com.itametis.jsonconverter.pathstrategy.entity;

import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.JsonPath;
import com.itametis.jsonconverter.annotation.Jsonnable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable("teacher")
public class Teacher {

    @JsonField
    private String name;

    @JsonField
    private int age;

    @JsonField()
    @JsonPath(pathInJson = {"."}, pathInCode = "..")
    private List<Student> students;


    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
        this.students = new ArrayList<>();
    }


    public void addStudent(Student student) {
        this.students.add(student);
    }


    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    public List<Student> getStudents() {
        return students;
    }


    @Override
    public String toString() {
        return "Teacher{" + "name=" + name + ", age=" + age + ", students=" + students.size() + '}';
    }

}
