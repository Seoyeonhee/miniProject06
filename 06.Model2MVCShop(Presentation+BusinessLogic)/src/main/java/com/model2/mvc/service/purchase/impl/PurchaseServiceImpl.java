package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

	/// Field
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	public void setPurchase(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	/// Constructor
	public PurchaseServiceImpl() {
		System.out.println(this.getClass());
	}
	
	/// Method
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDao.insertPurchase(purchase);
	}

	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDao.findPurchase(tranNo);
	}

	public Purchase getPurchase2(int ProdNo) throws Exception {
		return purchaseDao.findPurchase2(ProdNo);
	}

	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		return purchaseDao.getPurchaseList(search, buyerId);
	}

	public HashMap<String, Object> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void updatePurcahse(Purchase purchase) throws Exception {
		purchaseDao.updatePurchase(purchase);
	}

	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase);
	}

}
