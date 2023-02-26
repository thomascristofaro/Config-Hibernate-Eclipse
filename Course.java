package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The class {@code Course} defines a simple course model.
 *
**/

@Entity
@Table(name = "courses")
public class Course
{
  private long courseId;
  private String courseName;

  /**
   * Class constructor.
   *
  **/
  public Course()
  {
  }

  /**
   * Class constructor.
   *
   * @param n  the name.
   *
  **/
  public Course(final String n)
  {
    this.courseName = n;
  }

  /**
   * Gets the identifier.
   *
   * @return the identifier.
   *
  **/
  @Id
  @GeneratedValue
  @Column(name = "COURSE_ID")
  public long getCourseId()
  {
    return courseId;
  }

  /**
   * Sets the identifier.
   *
   * @param i  the identifier.
   *
  **/
  public void setCourseId(final long i)
  {
    this.courseId = i;
  }

  /**
   * Gets the name.
   *
   * @return the name.
   *
  **/
  @Column(name = "COURSE_NAME", nullable = false)
  public String getCourseName()
  {
    return courseName;
  }

  /**
   * Sets the name.
   *
   * @param n  the name.
   *
  **/
  public void setCourseName(final String n)
  {
    this.courseName = n;
  }
}
