package com.example.final_project.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;
    private String year;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "student")
    private List<School> schools;

    public Student() {
    }

    public Student(
            String name,
            String email,
            LocalDate dob
    ) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(
            String name,
            String email,
            LocalDate dob,
            String year
    ) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.year = year;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String andrei, String email, int i, String junior) {
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {

        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", year=" + year +
                '}';
    }
}
