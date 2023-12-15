package vttp.ssf.assessment.eventmanagement.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@Controller
@RequestMapping(path = "/")
public class EventController {

	@Autowired
	DatabaseService databaseSvc;

	// TODO: Task 5
	@GetMapping(path = "/events")
	public String displayName(Model m, String retrieved) throws IOException {
		String pathFileName = "/Users/joanna/Desktop/myProject/SSF/vttp2023-batch4-ssf-assessment/events.json";
		List<Event> listOfEvents = databaseSvc.readFile(pathFileName);
		Event eventObj = new Event(); 
		m.addAttribute("events", listOfEvents);
		m.addAttribute("eventObj", eventObj);
		

		return "view0";
	}

}
