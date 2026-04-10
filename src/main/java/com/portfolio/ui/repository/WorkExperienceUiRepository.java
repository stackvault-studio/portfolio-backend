package com.portfolio.ui.repository;

import com.portfolio.repository.WorkExperienceRepository;
import com.portfolio.ui.model.dashboard.Stats;
import com.portfolio.ui.model.experience.ExperienceCardUiDataModel;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository("workExperienceUiRepository")
public interface WorkExperienceUiRepository extends WorkExperienceRepository {

    @Query(value = "SELECT we.id, trPos.en as posEng, trPos.fr as posFr, we.company_name, trDesc.en as descEng, trDesc.fr as descFr, we.company_logo, we.start_date, we.end_date, STRING_AGG(DISTINCT ttr.name, ',') AS technologies, STRING_AGG(DISTINCT c.name, ',') AS clientsNames " +
            "FROM work_experience we " +
            "JOIN project p ON p.company_id = we.id " +
            "LEFT JOIN project_client pc ON pc.project_id = p.id " +
            "LEFT JOIN client c ON c.id = pc.client_id " +
            "LEFT JOIN technology_topic_rate ttr ON ttr.project_id = p.id " +
            "LEFT JOIN translation_string trPos ON we.position_id = trPos.id " +
            "LEFT JOIN translation_string trDesc ON we.description_id = trDesc.id " +
            "GROUP BY we.id, trPos.en, trPos.fr, we.company_name, we.company_logo, we.start_date, we.end_date, trDesc.en, trDesc.fr ", nativeQuery = true)
    List<ExperienceCardUiDataModel> getExperienceTimeline();

    @Query(value = "SELECT (SELECT COUNT(*) FROM project) AS totalProjects, " +
            "  (SELECT COUNT(DISTINCT name) FROM client) AS totalClients," +
            "  (SELECT COUNT(*) FROM certification) AS totalCertifications," +
            "  (SELECT COUNT(DISTINCT name) FROM technology_topic_rate) AS totalTechnologies," +
            "  ROUND(" +
            "    (" +
            "      SELECT" +
            "        SUM(COALESCE(w.end_date, CURRENT_DATE) - w.start_date)" +
            "      FROM work_experience w" +
            "    ) / 365.25," +
            "    1" +
            "  ) AS yearsExperience", nativeQuery = true)
    Stats fetchStats();
}
