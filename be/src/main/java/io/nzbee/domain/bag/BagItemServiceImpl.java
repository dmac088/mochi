package io.nzbee.domain.bag;

import org.apache.tomcat.util.buf.StringUtils;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.IBagItemPortService;
import io.nzbee.domain.promotion.DroolsPromotionWrapper;


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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkAllBagItemRules(BagItem object) {
		KieSession kieSession = kieContainer.newKieSession();
    	kieSession.insert(object);
    	DroolsPromotionWrapper dpw = new DroolsPromotionWrapper(object.getProduct().getPromotions());
    	kieSession.insert(dpw);
    	System.out.println(StringUtils.join(dpw.getPromotionCodes()));
    	System.out.println("************* Fire Rules **************");
    	kieSession.fireAllRules();
        System.out.println("************************************");
        System.out.println("Customer bag\n" + object.getBag().getCustomer().getUserName());
		
	}

}
