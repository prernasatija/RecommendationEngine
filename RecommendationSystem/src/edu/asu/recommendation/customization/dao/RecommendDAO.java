package edu.asu.recommendation.customization.dao;

import edu.asu.recommendation.customization.dto.TemplatesDTO;

public interface RecommendDAO {
	public TemplatesDTO getTemplatesDTO(String templatesName);
}
