package backend_assessment.Research.Paper.Management.API.repository;

import backend_assessment.Research.Paper.Management.API.entity.ResearchPaper;
import backend_assessment.Research.Paper.Management.API.enums.Status;
import org.springframework.data.jpa.domain.Specification;

public class ResearchPaperSpecification {

    public static Specification<ResearchPaper> withFilters(
            String name,
            String description,
            String abstractText,
            String status
    ) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (name != null && !name.isBlank()) {
                predicates.getExpressions().add(
                        cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%")
                );
            }

            if (description != null && !description.isBlank()) {
                predicates.getExpressions().add(
                        cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%")
                );
            }

            if (abstractText != null && !abstractText.isBlank()) {
                predicates.getExpressions().add(
                        cb.like(cb.lower(root.get("abstractText")), "%" + abstractText.toLowerCase() + "%")
                );
            }

            if (status != null && !status.isBlank()) {
                try {
                    Status statusEnum = Status.valueOf(status.toUpperCase());
                    predicates.getExpressions().add(cb.equal(root.get("status"), statusEnum));
                } catch (IllegalArgumentException e) {
                    // ignore invalid status
                }
            }

            return predicates;
        };
    }
}
