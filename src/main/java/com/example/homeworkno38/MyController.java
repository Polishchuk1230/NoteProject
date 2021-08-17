package com.example.homeworkno38;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class MyController {
    @Autowired
    private NoteRepository noteRepository;
    //В демонстрационных целях опустил NoteService уровень.

    @GetMapping("/form")
    public String getNoteForm() {
        return "noteForm";
    }

    @PostMapping("/create")
    public String postCreateNote(@ModelAttribute Note note) {
        this.noteRepository.save(note);
        return "redirect:/note/view";
    }

    @GetMapping("/view")
    public String getCreateNote(Model model) {
        model.addAttribute("note", this.noteRepository.getLatestNote());
        return "noteView";
    }
}
