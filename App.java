package com.hibernate.app;

public class App {
  /**
   * Starts the demo.
   *
   * @param args  the method does not requires arguments.
   *
  **/
  public static void main(final String[] args)
  {
    final CourseManager cm = new CourseManager();

    cm.saveCourse("Physics");

    final Long courseId2 = cm.saveCourse("Chemistry");
    final Long courseId3 = cm.saveCourse("Maths");

    cm.listCourse();
    cm.updateCourse(courseId3, "Mathematics");
    cm.deleteCourse(courseId2);
    cm.listCourse();
  }
}
