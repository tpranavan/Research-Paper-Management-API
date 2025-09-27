package backend_assessment.Research.Paper.Management.API.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResearchPaperRequest {
    private String name;
    private String description;
    @JsonProperty("abstract")
    private String abstractText;
    // getters & setters
}