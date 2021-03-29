package io.nzbee.domain.bag;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.IBagPortService;

public class BagServiceImpl implements IBagService {

	@Autowired
	private IBagPortService bagService;
	
    @Autowired
    private KieContainer kieContainer;
	
	@Override
	public Bag findByCode(String locale, String currency, String userName) {
		return bagService.findByCode(locale, currency, userName);
	}

	@Override
	public void save(Bag object) {
		bagService.save(object);		
	}

	@Override
	public void delete(Bag object) {
		bagService.delete(object);
	}

	@Override
	public void update(Bag object) {
		bagService.save(object);
	}

	@Override
	public Bag findByCode(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void checkAllBagRules(Bag object) {
		KieSession kieSession = kieContainer.newKieSession();
    	kieSession.insert(object);
    	DroolsBagWrapper dpw = new DroolsBagWrapper(object);
    	kieSession.insert(dpw);
    	System.out.println("************* Fire Rules **************");
    	kieSession.fireAllRules();
        System.out.println("************************************");
        System.out.println("Customer bag\n" + object.getCustomer().getUserName());
	}
}
