package com.example.homeworkno38.repository;

import com.example.homeworkno38.model.Note;
import com.example.homeworkno38.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

//    DELETE order FROM Order order JOIN FETCH Dish order.dish dish ON dish.order_id = order.id
//    @Query(value = "SELECT * FROM notes WHERE id = 2;", nativeQuery = true)
//    @Modifying
//    @Query("UPDATE User user SET user.password = :newPassword WHERE user.id = :id")
//    int updatePassword(@Param("newPassword") String newPassword, @Param("id") int id);
}
