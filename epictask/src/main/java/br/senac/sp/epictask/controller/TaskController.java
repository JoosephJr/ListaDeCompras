package br.senac.sp.epictask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.senac.sp.epictask.model.Task;
import br.senac.sp.epictask.repository.TaskRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskRepository repository;

    @GetMapping
    public String index(Model model) {
        var lista = repository.findAll();
        model.addAttribute("tasks", lista);
        return "task/index";
    }

    @GetMapping("new") // http://localhost:8080/task/new
    public String form(Task task) {
        return "task/form";
    }

    @PostMapping("new")
    public String save(@Valid Task task, BindingResult result) {
        if (result.hasErrors())
            return "task/form";
        repository.save(task);
        return "redirect:/task";
    }

    @DeleteMapping("{id}") // /task/nº - todas as requisições que usam o verbo delete feitos pra /task/qualquer coisa vai executar o delete nesse mapeamento
    public String delete(@PathVariable Long id) { //recebe o id que está no final do endereço da requisição (o path)
        repository.deleteById(id);
        return "redirect:/task";
    }

    @GetMapping("delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/task";
    }

    @GetMapping("/detalhe/{id}")
    public String receitaDetalhe(@PathVariable Long id, Model model) {
        Task task = repository.findById(id).orElse(null);
        model.addAttribute("task", task);
        return "task/receita-detalhe";
    }

    

}
