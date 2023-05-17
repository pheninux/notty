package com.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;


@Entity
public class Note implements Comparable<Note> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String context;
    private LocalDate date;

    public Note() {

    }

    public Note(String context, LocalDate date, String description) {
        this.context = context;
        this.date = date;
        this.description = description;
    }

    private String description;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return Objects.equals(getId(), note.getId()) && Objects.equals(getContext(), note.getContext()) && Objects.equals(getDate(), note.getDate()) && Objects.equals(getDescription(), note.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContext(), getDate(), getDescription());
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int compareTo(Note note) {
        return date.compareTo(note.getDate());
    }
}
