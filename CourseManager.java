package com.hibernate.app;

import java.util.stream.Stream;
import com.hibernate.model.Course;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * The class {@code CourseManager} provides some methods for
 * managing the course entities and shows a demo of their use.
 *
**/

public class CourseManager
{
  private static final SessionFactory sessionFactory;

  private static final String CFGFILE = "/hibernate.cfg.xml";

  static
  {
    try
    {
      sessionFactory = new Configuration().configure(
          CFGFILE).buildSessionFactory();
    }
    catch (final Throwable e)
    {
      System.err.println("Initial SessionFactory creation failed." + e);

      throw new ExceptionInInitializerError(e);
    }
  }

  /**
   * Saves a course.
   *
   * @param n  the course name.
   *
   * @return the course identifier.
   *
  **/
  public Long saveCourse(final String n)
  {
    Transaction transaction = null;
    Long        courseId    = null;

    try (Session session = sessionFactory.openSession())
    {
      transaction = session.beginTransaction();

      final Course course = new Course();

      course.setCourseName(n);

      courseId = (Long) session.save(course);

      transaction.commit();
    }
    catch (final HibernateException e)
    {
      transaction.rollback();
      e.printStackTrace();
    }

    return courseId;
  }

  /**
   * Prints the list of courses.
   *
  **/
  @SuppressWarnings("unchecked")
  public void listCourse()
  {
    Transaction transaction = null;

    try (Session session = sessionFactory.openSession())
    {
      transaction = session.beginTransaction();

      final Stream<Course> courses = session.createQuery("from Course")
          .stream();

      courses.forEach((c) -> System.out.println(
          "course " + c.getCourseName()));

      transaction.commit();
    }
    catch (final HibernateException e)
    {
      transaction.rollback();
      e.printStackTrace();
    }
  }

  /**
   * Changes the name of a course.
   *
   * @param i  the identifier.
   * @param n  The class.
   *
  **/
  public void updateCourse(final Long i, final String n)
  {
    Transaction transaction = null;

    try (Session session = sessionFactory.openSession())
    {
      transaction = session.beginTransaction();

      final Course course = session.get(Course.class, i);

      course.setCourseName(n);
      transaction.commit();
    }
    catch (final HibernateException e)
    {
      transaction.rollback();
      e.printStackTrace();
    }
  }

  /**
   * Deletes a course.
   *
   * @param i  the identifier.
   *
  **/
  public void deleteCourse(final Long i)
  {
    Transaction transaction = null;

    try (Session session = sessionFactory.openSession())
    {
      transaction = session.beginTransaction();

      final Course course = session.get(Course.class, i);

      session.delete(course);
      transaction.commit();
    }
    catch (final HibernateException e)
    {
      transaction.rollback();
      e.printStackTrace();
    }
  }
}

