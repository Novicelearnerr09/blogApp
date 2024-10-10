package com.sayaliblog.blogappapis.repositories;

import com.sayaliblog.blogappapis.entities.Category;
import com.sayaliblog.blogappapis.entities.Post;
import com.sayaliblog.blogappapis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
   List<Post> findByCategory(Category category);
   // List<Post> findAllBYUser(User user);
//    @Query("select p  from Post p  where p.title like :key")
//    List<Post> searchByTitle(@Param("key")String title);

    @Query("select p from Post p where p.title like :key")
    List<Post> serachByTitle(@Param("key") String title);
}
