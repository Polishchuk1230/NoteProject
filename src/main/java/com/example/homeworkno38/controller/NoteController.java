package com.example.homeworkno38.controller;

import com.example.homeworkno38.repository.NoteRepository;
import com.example.homeworkno38.model.Note;
import com.example.homeworkno38.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private NoteRepository noteRepository;
    private UserRepository userRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository; this.userRepository = userRepository;
    }

    //--------------------------------------------------------------------------------------------Получение всех заметок
    @GetMapping("")
    public String getAll(Model model, java.security.Principal principal) {
        model.addAttribute("notes", userRepository.findByUsername(principal.getName()).getNotes());
        return "notesView";
    }

    //---------------------------------------------------------------------------------Создание и редактирование заметки
    @GetMapping("/createform")
    public String getCreateForm() {
        return "noteCreationForm";
    }

    @GetMapping("/editform")
    public String getEditForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", this.noteRepository.findById(id).get());
        return "noteEditingForm";
    }

    @PostMapping("/save")
    public String postSaveNote(@ModelAttribute Note note, Principal principal) {
        note.setLastEditTime(new java.util.Date());//Обновление последней даты редактирования заметки
        note.setUser(userRepository.findByUsername(principal.getName()));//Присвоение пользователя
        this.noteRepository.save(note);
        return "redirect:/notes";
    }

    //--------------------------------------------------------------------------------------------------Удаление заметки
    @GetMapping("/deleteform")
    public String getDeleteForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", this.noteRepository.findById(id).get());
        return "noteDeletingForm";
    }

    @PostMapping("/delete")
    public String postDeleteNote(@RequestParam("id") int id) {
        this.noteRepository.deleteById(id);
        return "redirect:/notes";
    }

    //-----------------------------------------------------------------------------Получение информации об одной заметке
    @GetMapping("/open")
    public String getNote(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", this.noteRepository.findById(id).get());
        return "noteView";
    }

}
