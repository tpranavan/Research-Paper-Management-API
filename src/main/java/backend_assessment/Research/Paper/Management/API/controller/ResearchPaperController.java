package backend_assessment.Research.Paper.Management.API.controller;

import backend_assessment.Research.Paper.Management.API.dto.ResearchPaperRequest;
import backend_assessment.Research.Paper.Management.API.entity.ResearchPaper;
import backend_assessment.Research.Paper.Management.API.service.ResearchPaperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/research-papers")
public class ResearchPaperController {

    private final ResearchPaperService service;

    public ResearchPaperController(ResearchPaperService service) {
        this.service = service;
    }

    // Create new research paper
    @PostMapping
    public ResponseEntity<ResearchPaper> create(@Valid @RequestBody ResearchPaperRequest request) {
        ResearchPaper saved = service.createPaper(request);
        return ResponseEntity.ok(saved);
    }

    // Search with optional filters + pagination
    @GetMapping
    public ResponseEntity<Page<ResearchPaper>> search(@RequestParam(required = false) String name,
                                                      @RequestParam(required = false) String description,
                                                      @RequestParam(required = false, name = "abstract") String abstractText,
                                                      @RequestParam(required = false) String status,
                                                      Pageable pageable) {
        Page<ResearchPaper> results = service.search(name, description, abstractText, status, pageable);
        return ResponseEntity.ok(results);
    }
}
