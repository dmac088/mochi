package io.javabrains.springbootstarter.entity;

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
@RequestMapping("/api")
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
	@RequestMapping(method=RequestMethod.GET, value="/Party/Role/{roleTypeDesc}")
    public List<Party> getAllPartys(@PathVariable String roleTypeDesc) {
		System.out.println("calling getAllPartys");
        return PartyService.getAllPartys(roleTypeDesc);
    }	
	
	@ResponseBody
	@RequestMapping("/Party/Id/{id}")
	public Optional<Party> getParty(@PathVariable Long id) {
		System.out.println("calling getParty");
		return PartyService.getParty(id);
	}
	
	@ResponseBody
	@RequestMapping("/Party/UserName/{userName}")
	public Optional<Party> getParty(@PathVariable String userName) {
		System.out.println("calling getParty");
		return PartyService.getParty(userName);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/Party")
	public void addParty( Party Party) {
		System.out.println("calling addParty");
		PartyService.addParty(Party);
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
