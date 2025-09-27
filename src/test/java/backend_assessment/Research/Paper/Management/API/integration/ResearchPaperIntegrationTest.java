package backend_assessment.Research.Paper.Management.API.integration;

import backend_assessment.Research.Paper.Management.API.dto.ResearchPaperRequest;
import backend_assessment.Research.Paper.Management.API.entity.ResearchPaper;
import backend_assessment.Research.Paper.Management.API.repository.ResearchPaperRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ResearchPaperIntegrationTest {

    @Autowired
    private ResearchPaperRepository repository;

    @Test
    void testPersistResearchPaper() {
        ResearchPaper paper = new ResearchPaper();
        paper.setName("Integration Test Paper");
        paper.setDescription("Desc");
        paper.setAbstractText("Abstract");

        ResearchPaper saved = repository.save(paper);

        assertNotNull(saved.getId());
        assertEquals("Integration Test Paper", saved.getName());
    }
}
