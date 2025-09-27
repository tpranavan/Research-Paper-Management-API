package backend_assessment.Research.Paper.Management.API.entity;

import backend_assessment.Research.Paper.Management.API.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ResearchPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @JsonProperty("abstract")
    private String abstractText;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Status status;



}