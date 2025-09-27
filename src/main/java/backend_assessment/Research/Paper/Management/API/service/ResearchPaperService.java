package backend_assessment.Research.Paper.Management.API.service;

import backend_assessment.Research.Paper.Management.API.dto.ResearchPaperRequest;
import backend_assessment.Research.Paper.Management.API.entity.ResearchPaper;
import backend_assessment.Research.Paper.Management.API.enums.Status;
import backend_assessment.Research.Paper.Management.API.repository.ResearchPaperRepository;
import backend_assessment.Research.Paper.Management.API.repository.ResearchPaperSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ResearchPaperService {

    private final ResearchPaperRepository repository;

    public ResearchPaperService(ResearchPaperRepository repository) {
        this.repository = repository;
    }

    public ResearchPaper createPaper(ResearchPaperRequest req) {
        ResearchPaper paper = new ResearchPaper();
        paper.setName(req.getName());
        paper.setDescription(req.getDescription());
        paper.setAbstractText(req.getAbstractText());
        paper.setDate(LocalDate.now());
        paper.setStatus(Status.REVIEW);
        return repository.save(paper);
    }

    public Page<ResearchPaper> search(String name, String description, String status,
                                      String abstractText, Pageable pageable) {
        return repository.findAll(
                ResearchPaperSpecification.withFilters(name, description, status, abstractText),
                pageable
        );
    }

}