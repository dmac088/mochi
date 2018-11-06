package io.javabrains.springbootstarter.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PartyController {
	
	@Autowired
	private PartyService PartyService;	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, value="/Party")
    public List<Party> getAllPartys() {
		System.out.println("calling getAllPartys");
        return PartyService.getAllPartys();
    }	
	
	@ResponseBody
	@RequestMapping("/Party/{id}")
	public Optional<Party> getParty(@PathVariable Long id) {
		System.out.println("calling getParty");
		return PartyService.getParty(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/Party")
	public String addParty( Party Party) {
		System.out.println("calling addParty");
		PartyService.addParty(Party);
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT, value="/Party/{id}")
	public void updateParty(@RequestBody Party Party, @PathVariable String id) {
		System.out.println("calling updateParty");
		PartyService.updateParty(id, Party);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.DELETE,value="/Party/{id}")
	public void deleteParty(@PathVariable Long id) {
		System.out.println("calling deleteParty");
		PartyService.deleteParty(id);
	}
	
}
