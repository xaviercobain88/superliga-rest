package core.application.dto;

import core.domain.enums.DisciplineEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TournamentDTO {

    @NotNull
    @Size(min = 2, max = 200)
    protected String name;
    @Min(1)
    protected Long id;
    @NotNull
    protected DisciplineEnum discipline;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DisciplineEnum getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineEnum discipline) {
        this.discipline = discipline;
    }
}
