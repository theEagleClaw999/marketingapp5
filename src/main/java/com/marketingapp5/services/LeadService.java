package com.marketingapp5.services;

import java.util.List;

import com.marketingapp5.entities.Lead;

public interface LeadService {

	public void saveLead(Lead lead);
	
	List<Lead> findAllLeads();

	public void deleteLead(long id);

	public Lead findLeadById(long id);
}
