package backend_assessment.Research.Paper.Management.API.ut;

import backend_assessment.Research.Paper.Management.API.dto.ResearchPaperRequest;
import backend_assessment.Research.Paper.Management.API.entity.ResearchPaper;
import backend_assessment.Research.Paper.Management.API.enums.Status;
import backend_assessment.Research.Paper.Management.API.repository.ResearchPaperRepository;
import backend_assessment.Research.Paper.Management.API.service.ResearchPaperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ResearchPaperServiceTest {

    private ResearchPaperRepository repository;
    private ResearchPaperService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ResearchPaperRepository.class);
        service = new ResearchPaperService(repository);
    }

    @Test
    void testCreatePaperDefaults() {
        ResearchPaperRequest req = new ResearchPaperRequest();
        req.setName("Test Paper");
        req.setDescription("Desc");
        req.setAbstractText("Abstract");

        Mockito.when(repository.save(any(ResearchPaper.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ResearchPaper paper = service.createPaper(req);

        assertNotNull(paper);
        assertEquals("Test Paper", paper.getName());
        assertEquals(Status.REVIEW, paper.getStatus());
        assertNotNull(paper.getDate());
    }
}
