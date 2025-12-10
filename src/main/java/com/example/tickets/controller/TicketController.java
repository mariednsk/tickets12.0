package com.example.tickets.controller;

import com.example.tickets.model.Ticket;
import com.example.tickets.repository.TicketRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /** Список билетов + форма добавления (форма видна только ADMIN в шаблоне) */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        // объект для формы добавления
        model.addAttribute("ticket", new Ticket());
        return "tickets";
    }

    /** Создание билета — только ADMIN */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("ticket") Ticket ticket,
                      BindingResult bindingResult,
                      Model model) {

        if (bindingResult.hasErrors()) {
            // Перерисовываем список с ошибками
            model.addAttribute("tickets", ticketRepository.findAll());
            return "tickets";
        }
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    /** Страница редактирования — только ADMIN */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Билет не найден: " + id));
        model.addAttribute("ticket", ticket);
        return "edit_ticket";
    }

    /** Сохранение изменения — только ADMIN */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String saveEdited(@PathVariable Long id,
                             @Valid @ModelAttribute("ticket") Ticket ticket,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit_ticket";
        }
        // фиксируем правильный id
        ticket.setId(id);
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    /** Удаление — только ADMIN */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (!ticketRepository.existsById(id)) {
            return "redirect:/tickets";
        }
        ticketRepository.deleteById(id);
        return "redirect:/tickets";
    }
}




//package com.example.tickets.controller;
//
//import com.example.tickets.model.Ticket;
//import com.example.tickets.repository.TicketRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/tickets")
//public class TicketController {
//
//    private final TicketRepository ticketRepository;
//
//    public TicketController(TicketRepository ticketRepository) {
//        this.ticketRepository = ticketRepository;
//    }
//
//    @GetMapping
//    public String listTickets(Model model) {
//        model.addAttribute("tickets", ticketRepository.findAll());
//        model.addAttribute("ticket", new Ticket());
//        return "tickets";
//    }
//
//    @PostMapping("/add")
//    public String addTicket(@ModelAttribute Ticket ticket, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("tickets", ticketRepository.findAll());
//            return "tickets";
//        }
//        ticketRepository.save(ticket);
//        return "redirect:/tickets";
//    }
//
//    @PostMapping("/delete/{id}")
//    public String deleteTicket(@PathVariable Long id) {
//        ticketRepository.deleteById(id);
//        return "redirect:/tickets";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editTicketForm(@PathVariable Long id, Model model) {
//        model.addAttribute("ticket", ticketRepository.findById(id).orElseThrow());
//        return "edit_ticket";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String saveEditedTicket(@PathVariable Long id, @ModelAttribute Ticket ticket, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "edit_ticket";
//        }
//        ticket.setId(id);
//        ticketRepository.save(ticket);
//        return "redirect:/tickets";
//    }
//}
