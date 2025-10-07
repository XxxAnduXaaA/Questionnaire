package com.example.formmaker.controller;

import com.example.formmaker.entity.*;
import com.example.formmaker.repository.FormResultRepository;
import com.example.formmaker.repository.UserRepository;
import com.example.formmaker.service.FormResultService;
import com.example.formmaker.service.FormService;
import jakarta.persistence.EntityExistsException;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/adminPanel")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminFormController {
    private final FormService formService;
    private final FormResultService formResultService;
    private final FormResultRepository formResultRepository;
    private final UserRepository userRepository;

    public AdminFormController(FormService formService, FormResultService formResultService, FormResultRepository formResultRepository, UserRepository userRepository) {
        this.formService = formService;
        this.formResultService = formResultService;
        this.formResultRepository = formResultRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/form")
    public String getFormPanel(Model model) {
        model.addAttribute("forms", formService.getAllForms());
        return "admin/forms";
    }

    @GetMapping("/form/new")
    public String getFormCreator(Model model) {
        Form form = new Form();
        Question q = new Question();
        Answer a = new Answer();

        form.setQuestions(new ArrayList<>());
        form.getQuestions().add(q);

        q.setForm(form);
        q.setAnswers((new ArrayList<>()));
        q.getAnswers().add(a);

        a.setQuestion(q);

        model.addAttribute("form", form);
        return "admin/form-create";
    }

    @PostMapping("/form/new")
    public String createForm(@ModelAttribute Form form) {
        if (formService.getFormByTitle(form.getTitle()).isPresent()) {
            throw new EntityExistsException("Форма с таким названием уже существует");
        }
        for (Question q : form.getQuestions()) {
            q.setForm(form);
            for (Answer a : q.getAnswers()) {
                a.setQuestion(q);
            }
        }
        formService.createForm(form);
        return "redirect:/adminPanel/form";
    }

    @GetMapping("/form/{formId}/edit")
    public String getFormUpdater(@PathVariable Long formId, Model model) {
        model.addAttribute("form", formService.getFormById(formId));
        return "admin/form-edit";
    }

    @PutMapping("/form/{formId}/edit")
    public String updateFormById(@PathVariable Long formId, @ModelAttribute Form form) {
        formService.updateFormById(formId, form);
        return "redirect:/form/{formId}";
    }

    @DeleteMapping("/form/{formId}")
    public String deleteFormById(@PathVariable Long formId) {
        formService.deleteFormById(formId);
        return "redirect:/adminPanel/forms";
    }

    @GetMapping("/form/results")
    public String getFormsResult(@RequestParam(value = "formId", required = false) Long formId, @RequestParam(value = "userId", required = false) Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size , Model model) {

        Page<FormResult> results;

        if (formId != null) {
//            model.addAttribute("formResults", formResultService.getCompletedFormsByForm(page, size, formId));
            results = formResultService.getCompletedFormsByForm(page, size, formId);
        }

        else if(userId != null){
//            model.addAttribute("userResults", formResultService.getCompletedFormsByUser(page, size, userId));
            results = formResultService.getCompletedFormsByUser(page, size, userId);
        }

        else{
            results = formResultService.getAllCompletedForms(page, size);
        }

//        model.addAttribute("allFormResults", formResultService.getAllCompletedForms(page, size));
        model.addAttribute("results", results);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", results.getTotalPages());
        model.addAttribute("forms", formService.getAllForms());
        model.addAttribute("users", userRepository.findAll());
        return "admin/formsResult";
    }

    @GetMapping("/form/results/{userFormId}")
    public String getUserFormPage(@PathVariable Long userFormId, Model model) {
        FormResult formResult = formResultRepository
                .findById(userFormId)
                .orElseThrow(() -> new RuntimeException("FormResult with " + userFormId + " not found"));
        model.addAttribute("form", formResult.getForm());
        List<UserAnswer> userAnswers = formResult.getAnswers();
        Map<Long, List<UserAnswer>> answersByQuestion = userAnswers.stream()
                .collect(Collectors.groupingBy(ua -> ua.getQuestion().getQuestionId()));

        model.addAttribute("answersByQuestion", answersByQuestion);
        return "user/userForm";
    }
}
