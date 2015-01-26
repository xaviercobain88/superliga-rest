package core.application.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TournamentDTO {

    @NotNull
    @Size(min = 2, max = 200)
    protected String name;
    protected Long id;

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
}
