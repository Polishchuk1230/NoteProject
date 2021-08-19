package com.example.homeworkno38;

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

    //--------------------------------------------------------------Шаблон отображения всех заметок (стартовая страница)
    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("notes", this.noteRepository.findAll());
        return "notesView";
    }

    //---------------------------------------------------------------------------------Создание и редактирование заметок
    @GetMapping("/createform")
    public String getNoteForm(Model model) {
        model.addAttribute("note", new Note());
        return "noteCreationForm";
    }

    @GetMapping("/editform")
    public String getEditForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", this.noteRepository.findById(id).get());
        return "noteEditingForm";
    }

    @PostMapping("/save")
    public String postCreateNote(@ModelAttribute Note note) {
        this.noteRepository.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/deleteform")
    public String getDeleteForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", this.noteRepository.findById(id).get());
        return "noteDeletingForm";
    }

    @PostMapping("/delete")
    public String postDeleteNote(@RequestParam("id") int id) {
        this.noteRepository.delete(this.noteRepository.findById(id).get());
        return "redirect:/notes";
    }

    //-----------------------------------------------------------------------------Получение информации об одной заметке
    @GetMapping("/open")
    public String getNote(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", this.noteRepository.findById(id).get());
        return "noteView";
    }


}
