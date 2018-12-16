package ru.basejava.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {

    private Link homepage;
    private List<Position> organizations;

    public Organization(Link homepage, Position... organizations) {
        Objects.requireNonNull(homepage, "homepage can't be null");
        Objects.requireNonNull(organizations, "organizations cant't be null");
        this.homepage = homepage;
        this.organizations = Arrays.asList(organizations);
    }

    public List<Position> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Position> organizations) {
        this.organizations = organizations;
    }

    public Link getHomepage() {
        return homepage;
    }

    public void setHomepage(Link homepage) {
        this.homepage = homepage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homepage, that.homepage) &&
                Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homepage, organizations);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homepage=" + homepage +
                ", organizations=" + organizations +
                '}';
    }

    public static class Position extends AbstractSection {

        private LocalDate startDate;
        private LocalDate endDate;
        private String title;
        private String description;

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate can't be null");
            Objects.requireNonNull(title, "title can't be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
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
            Position that = (Position) o;
            return Objects.equals(startDate, that.startDate) &&
                    Objects.equals(endDate, that.endDate) &&
                    Objects.equals(title, that.title) &&
                    Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }

        @Override
        public String toString() {
            return startDate + " - " +
                    (endDate == null ? "Сейчас" : endDate) +
                    title + "\n" + (description == null ? "" : description);
        }
    }
}
