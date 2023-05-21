package com.marketingapp5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp5.dto.LeadDto;
import com.marketingapp5.entities.Lead;
import com.marketingapp5.services.LeadService;
import com.marketingapp5.util.EmailService;

@Controller
public class LeadController {

	@Autowired
	private LeadService leadService;
	
	@Autowired
	private EmailService emailService;
	
	// http://localhost:8080/viewCreateLead
	
	@RequestMapping(value = "/viewCreateLead", method = RequestMethod.GET)
	public String viewCreateLeadForm() {
		
		return "create_registration";
	}
	
	@RequestMapping(value = "/saveLead", method = RequestMethod.POST)
	public String saveOneLead(LeadDto leadDto, ModelMap model) {
		Lead l = new Lead();
		l.setFirstName(leadDto.getFirstName());
		l.setLastName(leadDto.getLastName());
		l.setEmail(leadDto.getEmail());
		l.setMobile(leadDto.getMobile());
		
		emailService.sendEmail(leadDto.getEmail(), "Trap", "You are selected for the mission 'Dark Spot'");
		
		leadService.saveLead(l);
		
		model.addAttribute("msg", "Record is saved!"); //addAttribute() ~ setAttribute()
		return "create_registration";
	}
	
	//localhost:8080/listleads
	
	@RequestMapping("/listleads")
	public String getAllLeads(Model model) {
		List<Lead> leads = leadService.findAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
	
	@RequestMapping("/delete")
	public String deleteOneLead(@RequestParam("id") long id, Model model) {
		leadService.deleteLead(id);
		List<Lead> leads = leadService.findAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
	
	@RequestMapping("/update")
	public String updateOneLead(@RequestParam("id") long id, Model model) {
		Lead lead = leadService.findLeadById(id);
		model.addAttribute("lead", lead);
		return "update_lead";
	}
	
	@RequestMapping(value = "/updateLead", method = RequestMethod.POST)
	public String updateOneLead(@ModelAttribute("lead") Lead lead, ModelMap model) {
		leadService.saveLead(lead);
		
		List<Lead> leads = leadService.findAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
}
