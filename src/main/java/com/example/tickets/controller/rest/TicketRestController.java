package com.example.tickets.controller.rest;

import com.example.tickets.model.Ticket;
import com.example.tickets.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

    private final TicketRepository ticketRepository;

    public TicketRestController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Получить все проекты
    @GetMapping
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    // Получить один проект
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getOne(@PathVariable Long id) {
        return ticketRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Создать проект
    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        Ticket saved = ticketRepository.save(ticket);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Обновить проект
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable Long id,
                                         @RequestBody Ticket ticket) {
        if (!ticketRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ticket.setId(id);
        Ticket updated = ticketRepository.save(ticket);
        return ResponseEntity.ok(updated);
    }

    // Удалить проект
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!ticketRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ticketRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
