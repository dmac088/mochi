package io.nzbee.domain.bag;

import org.apache.tomcat.util.buf.StringUtils;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.IBagItemPortService;

public class BagItemServiceImpl implements IBagItemService{

	@Autowired
	private IBagItemPortService bagItemService;
	
    @Autowired
    private KieContainer kieContainer;
	
	@Override
	public void save(BagItem object) {
		bagItemService.save(object);
	}

	@Override
	public void delete(BagItem object) {
		bagItemService.delete(object);
	}

	@Override
	public void update(BagItem object) {
		bagItemService.save(object);
	}

	@Override
	public void checkAllBagItemRules(BagItem object) {
		KieSession kieSession = kieContainer.newKieSession();
    	kieSession.insert(object);
    	DroolsBagItemWrapper dpw = new DroolsBagItemWrapper(object);
    	kieSession.insert(dpw);
    	System.out.println(StringUtils.join(dpw.getPromotionCodes()));
    	System.out.println("************* Fire Bag Item Rules **************");
    	kieSession.fireAllRules();
        System.out.println("************************************");
        System.out.println("Customer bag\n" + object.getBag().getCustomer().getUserName());
	}

}
