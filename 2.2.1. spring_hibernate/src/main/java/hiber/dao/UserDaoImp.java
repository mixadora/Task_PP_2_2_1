package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   protected EntityManager entityManager;

   @Override
   public void add(User user) {
      entityManager.merge(user);
   }

   @Override
   public void addCar(Car car) {
      entityManager.merge(car);
   }

   public User getUser(Long id){
      return entityManager.find(User.class, id) ;
   }

   @Override
   public Car getCar(Long id) {
      return entityManager.find(Car.class, id) ;
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      Query query = entityManager.createQuery("select u from User u ");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getUserByCar(String model, int series) {
      Query query = entityManager.createQuery("select u from User u where u.car.model= :paramModel and u.car.series= :paramSeries");
      query.setParameter("paramModel", model);
      query.setParameter("paramSeries", series);
      return query.getResultList();
   }


}
