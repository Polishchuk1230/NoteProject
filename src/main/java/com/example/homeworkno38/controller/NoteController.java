package com.example.homeworkno38.controller;

import com.example.homeworkno38.repository.NoteRepository;
import com.example.homeworkno38.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    //--------------------------------------------------------------------------------------------Получение всех заметок
    @GetMapping("")
    public String getAll(Model model, java.security.Principal principal) {
        model.addAttribute("notes", this.noteRepository.findAll());
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
    public String postSaveNote(@ModelAttribute Note note) {
        note.setLastEditTime(new java.util.Date());//Обновление последней даты редактирования заметки
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
