package edu.asu.recommendation.customization.dao.impl;

import edu.asu.recommendation.customization.dao.RecommendDAO;
import edu.asu.recommendation.customization.dto.TemplatesDTO;
import edu.asu.recommendation.customization.dto.UserTemplateSubscriptionTableDTO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RecommendDAOImpl implements RecommendDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public TemplatesDTO getTemplatesDTO(String templateName)	
	{
		Session session = sessionFactory.openSession();
		String queryString = "FROM TemplatesDTO t WHERE t.templateName = :templateName";
		Query query = session.createQuery(queryString);
		query.setParameter("templateName", templateName);
		TemplatesDTO templatesDTO = (TemplatesDTO) query.uniqueResult();
		int tempId = ((TemplatesDTO) query.uniqueResult()).getTemplateId();
		System.out.println("template Id = " + tempId);
		return templatesDTO;
	}
	
	
	public UserTemplateSubscriptionTableDTO getUserTempAttributes(Integer userId, Integer templateId, Integer userTemplateSubscriptionId)
	{
		Session session = sessionFactory.getCurrentSession();
	    String queryString = "FROM UserTemplateSubscriptionDTO s WHERE userID.userId = :userId and templateID.templateId = :templateId and s.userTemplateSubscriptionId = :userTemplateSubscriptionId";
	    Query query = session.createQuery(queryString);
		query.setParameter("userId", userId);
		query.setParameter("templateId", templateId);
		query.setParameter("userTemplateSubscriptionId", userTemplateSubscriptionId);
		System.out.println("DAO " + userId + " " + templateId + " " + userTemplateSubscriptionId );
		UserTemplateSubscriptionTableDTO userTempDto = (UserTemplateSubscriptionTableDTO) query.uniqueResult();
		if(userTempDto == null)
			System.out.println("Null");
		return userTempDto;
	
	}
	
	public Boolean updateUserTemplateSubscription(UserTemplateSubscriptionTableDTO userTempDTO)
	{
		try 
		{
			sessionFactory.getCurrentSession().save(userTempDTO);
			return true;
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			return false;
		}
		
	}

	
}
