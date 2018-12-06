package ru.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Experience extends AbstractSection {

    private Link homepage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String description;

    public Experience(Link homepage, LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(homepage, "homepage can't be null");
        Objects.requireNonNull(startDate, "startDate can't be null");
        Objects.requireNonNull(title, "title can't be null");
        this.homepage = homepage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    public Link getHomepage() {
        return homepage;
    }

    public void setHomepage(Link homepage) {
        this.homepage = homepage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(homepage, that.homepage) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homepage, startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        return homepage + "\n" + startDate + " - " +
                (endDate == null ? "Сейчас" : endDate) +
                title + "\n" + description;
    }
}
