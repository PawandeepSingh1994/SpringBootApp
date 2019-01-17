package com.barclays.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Component
@Entity
@Table(name="Person")
public class Person{

  @Id
  @NotNull
  @Column(name="Id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personIdSequence")
  @SequenceGenerator(name = "personIdSequence", allocationSize = 1, sequenceName = "pIdSequence")
  private int id;

  @Column(name="Name")
  private String name;

  @Column(name="Age")
  private int age;


  @OneToOne(cascade=CascadeType.ALL)
  @JoinColumn(name = "OccupationId")
  private Occupation occupation;

  public Person(){
    super();
  }

  public Person(String name, int age, Occupation occupation){
    super();
    this.name = name;
    this.age = age;
    this.occupation = occupation;
  }

  public Person(int id, String name, int age, Occupation occupation){
    super();
    this.id = id;
    this.name = name;
    this.age = age;
    this.occupation = occupation;
  }

  public void setId(int id){
    this.id = id;
  }

  public int getId(){
    return this.id;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return this.name;
  }

  public void setAge(int age){
    this.age = age;
  }

  public int getAge(){
    return this.age;
  }

  public void setOccupation(Occupation occupation) {
      this.occupation = occupation;
  }

  public Occupation getOccupation() { return this.occupation; }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", occupation=" + occupation +
                '}';
    }
}
