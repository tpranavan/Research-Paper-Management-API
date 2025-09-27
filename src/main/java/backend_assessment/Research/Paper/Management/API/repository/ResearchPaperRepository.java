package backend_assessment.Research.Paper.Management.API.repository;

import backend_assessment.Research.Paper.Management.API.entity.ResearchPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ResearchPaperRepository extends JpaRepository<ResearchPaper, Long>,
        JpaSpecificationExecutor<ResearchPaper> {
}
