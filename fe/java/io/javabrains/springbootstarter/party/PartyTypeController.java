package io.javabrains.springbootstarter.party;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PartyTypeController {
	
	@Autowired
	private PartyTypeService PartyTypeService;	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/PartyType")
    public List<PartyType> getAllPartyTypes(HttpSession session) {
		System.out.println("calling getAllPartyTypes");
        return PartyTypeService.getAllPartyType();
    }	
	
	@ResponseBody
	@RequestMapping("/PartyType/{id}")
	public PartyType getPartyType(@PathVariable Long id) {
		System.out.println("calling getPartyType");
		return PartyTypeService.getPartyType(id);
	}
	
	//map the post request to this particular method
	/*@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/PartyTypes")
	public void addPartyType(@RequestBody PartyType PartyType) {
		PartyTypeservice.addPartyType(PartyType);
	}*/
	
	
	@RequestMapping(method=RequestMethod.POST, value="/PartyType")
	public String addPartyType( PartyType PartyType) {
		System.out.println("calling addPartyType");
		PartyTypeService.addPartyType(PartyType);
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/PartyType/{id}")
	public void updatePartyType(@RequestBody PartyType PartyType, @PathVariable String id) {
		System.out.println("calling updatePartyType");
		PartyTypeService.updatePartyType(id, PartyType);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE,value="/PartyType/{id}")
	public void deletePartyType(@PathVariable Long id) {
		System.out.println("calling deletePartyType");
		PartyTypeService.deletePartyType(id);
	}
	
}
