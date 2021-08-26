package com.example.homeworkno38;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {

    Iterable<Note> findByTitleContainingOrDescriptionContaining(String str1, String str2);

    @Query("SELECT n FROM Note n WHERE n.title LIKE %:str% OR n.description LIKE %:str%")
    Iterable<Note> findBySubstring(@Param("str") String str);
}
