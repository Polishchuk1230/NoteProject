package com.example.homeworkno38;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {

//    @Query("SELECT * FROM notes ORDER BY id DESC LIMIT 1")
//    Note getLatestNote();
//
//    Iterable<Note> findByDescriptionContaining(String str);
//    Iterable<Note> findByDescriptionContainingOrTitleContaining(String str1, String str2);

//    @Query("SELECT n FROM Notes n WHERE n.title LIKE :str")
//    Iterable<Note> ownFind(@Param("str") String str);
}
