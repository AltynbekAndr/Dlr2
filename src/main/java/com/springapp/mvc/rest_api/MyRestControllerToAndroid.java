package com.springapp.mvc.rest_api;

import com.springapp.mvc.model_for_users.*;
import com.springapp.mvc.models2.*;
import com.springapp.mvc.service.MyServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("rest")
public class MyRestControllerToAndroid {
	@Autowired
	MyServiceClass serviceClassToRest;
	@RequestMapping(value = "signInShop", method = RequestMethod.POST)
	public Object signInShop(@RequestBody Shops shops){
		Shops shop1 = serviceClassToRest.userVerificationShop(shops.getLogin(), shops.getPassword());
		if(shop1!=null){
			Shops2 shop2 = new Shops2();
			shop2.setId(shop1.getId());
			shop2.setName(shop1.getName());
			shop2.setLogin(shop1.getLogin());
			shop2.setPassword(shop1.getPassword());
			shop2.setAddress(shop1.getAddress());
			shop2.setPhone(shop1.getPhone());
			shop2.setPhoto(shop1.getPhoto());
			shop2.setLatitude(shop1.getLatitude());
			shop2.setLongitude(shop1.getLongitude());
			GroupsUs2 groupsUs2 = new GroupsUs2();
			groupsUs2.setId(shop1.getGroupsus().getId());
			shop2.setGroupsUs2(groupsUs2);
			return shop2;
		}
		return "500";
	}
	@RequestMapping(value = "signInAdmin", method = RequestMethod.POST)
	public Object signInAdmin(@RequestBody DealerAdmin dealerAdmin){
		DealerAdmin admin = serviceClassToRest.userVerification(dealerAdmin);
		if(admin!=null){
			return admin;
		}
		return null;
	}
	@RequestMapping(value = "get_shops", method = RequestMethod.GET)
	public Object get_shops(){
		List<Shops> shops = serviceClassToRest.getAllShopsToAdminAndroid();
		List<Shops2> shopsToSend = new ArrayList<>();
		for(int i =0;i<shops.size();i++){
			Shops2 shops2 = new Shops2();
			GroupsUs2 groupsUs2 = new GroupsUs2();
			Shops shop = shops.get(i);
			GroupsUs groupsUs = shop.getGroupsus();
			groupsUs2.setId(groupsUs.getId());
			groupsUs2.setName(groupsUs.getName());
			shops2.setId(shop.getId());
			shops2.setName(shop.getName());
			shops2.setAddress(shop.getAddress());
			shops2.setLatitude(shop.getLatitude());
			shops2.setLongitude(shop.getLongitude());
			shops2.setLogin(shop.getLogin());
			shops2.setPassword(shop.getPassword());
			shops2.setPhone(shop.getPhone());
			shops2.setPhoto(shop.getPhoto());
			shops2.setGroupsUs2(groupsUs2);
			shopsToSend.add(shops2);
		}
		if(shopsToSend!=null){
			return shopsToSend;
		}
		return null;
	}

	@RequestMapping(value = "group_us", method = RequestMethod.GET)
	public Object group_us(){
		List<GroupsUs> globalGroups = serviceClassToRest.getGlobalGroups();
		List<GroupsUs2> globalGroupsToSend = new ArrayList<>();
		for(int i =0;i<globalGroups.size();i++){
			GroupsUs2 groupsUs2 = new GroupsUs2();
			GroupsUs groupsUs = globalGroups.get(i);
			groupsUs2.setId(groupsUs.getId());
			groupsUs2.setName(groupsUs.getName());
			globalGroupsToSend.add(groupsUs2);
		}
		if(globalGroupsToSend!=null){
			return globalGroupsToSend;
		}
		return null;
	}
	@RequestMapping(value = "companies", method = RequestMethod.POST)
	public Object companies(@RequestBody Shops shops) {
		List<UndGroups> resCompanies = serviceClassToRest.getCompaniesAndGroups(shops.getId());
		List<Groups2> companiesToSend = new ArrayList<>();
		for(int i =0;i<resCompanies.size();i++){
			Company company = resCompanies.get(i).getCompany();
			Groups groups = resCompanies.get(i).getGroups();
			Groups2 groups2 = new Groups2();
			Company2 company2 = new Company2();
			if(i==0){
				groups2.setShopImg(resCompanies.get(i).getShops().getPhoto());
			}



			company2.setId(company.getId());
			company2.setName(company.getName());
			company2.setPhoto(company.getPhoto());


			groups2.setCompany2(company2);
			groups2.setPriceGroup(groups.getPriceGroup());
			groups2.setId(groups.getId());
			groups2.setName(groups.getName());

			groups2.setPn(groups.isPn());
			groups2.setVt(groups.isVt());
			groups2.setSr(groups.isSr());
			groups2.setCh(groups.isCh());
			groups2.setPt(groups.isPt());
			groups2.setSb(groups.isSb());
			groups2.setVs(groups.isVs());

			groups2.setShopCons(resCompanies.get(i).isShopCons());

			companiesToSend.add(groups2);
		}
		return companiesToSend;
	}


	@RequestMapping(value = "categories", method = RequestMethod.GET)
	public Object categories(@RequestParam("company_id") String company_id) {
		List<Categories> resCategories = serviceClassToRest.getCompanyCategories(company_id);
		List<Categories2> categoriesToSend = new ArrayList();
		for(int i=0;i<resCategories.size();i++){
			Categories2 categories2 = new Categories2();
			categories2.setId(resCategories.get(i).getId());
			categories2.setCategory(resCategories.get(i).getCategory());
			categoriesToSend.add(categories2);
		}
		return categoriesToSend;
	}


	@RequestMapping(value = "products", method = RequestMethod.GET)
	public Object produtcs(@RequestParam("company_id") String company_id,@RequestParam("categories_id") String categories_id) {
		List<Products> resProducts = serviceClassToRest.getCompanyProducts(company_id,categories_id);
		List<Products2> productsToSend = new ArrayList();
		for(int i=0;i<resProducts.size();i++){
			Products2 products2 = new Products2();
			products2.setId(resProducts.get(i).getId());
			products2.setName(resProducts.get(i).getName());
			products2.setPhoto(resProducts.get(i).getPhoto());
			products2.setConsignation(resProducts.get(i).isConsignation());
			products2.setReturned(resProducts.get(i).isReturned());
			products2.setDescription(resProducts.get(i).getDescription());
			products2.setPrice(resProducts.get(i).getPrice());
			products2.setRec_price(resProducts.get(i).getRec_price());
			products2.setWeight(resProducts.get(i).getWeight());
			productsToSend.add(products2);
		}
		return productsToSend;
	}

	@RequestMapping(value = "createOrder", method = RequestMethod.POST)
	public Object createOrder(@RequestBody MyOrder3 myOrder3) {
		MyOrder myOrder = new MyOrder();

		Company company = new Company();
		company.setId(myOrder3.getCompany2().getId());

		myOrder.setCompany(company);
		myOrder.setStatus("new");
		Date d = new Date();
		String strDate [] =d.toString().split(" ");
		String month = strDate[1];
		if(month.equals("Jan")){
			month = "01";
		}else if(month.equals("Feb")){
			month = "02";
		}else if(month.equals("Mar")){
			month = "03";
		}else if(month.equals("Apr")){
			month = "04";
		}else if(month.equals("May")){
			month = "05";
		}else if(month.equals("Jun")){
			month = "06";
		}else if(month.equals("Jul")){
			month = "07";
		}else if(month.equals("Aug")){
			month = "08";
		}else if(month.equals("Sep")){
			month = "09";
		}else if(month.equals("Oct")){
			month = "10";
		}else if(month.equals("Nov")){
			month = "11";
		}else if(month.equals("Dec")){
			month = "12";
		}

		myOrder.setDate(strDate[5] + "-" + month + "-" + strDate[2]);
		myOrder.setDate_hms(new Date().getTime()+"");
		List<Products2> products2s =  myOrder3.getProductses();
		List<Products> productses =  new ArrayList<Products>();
		int lSum = 0;
		float debtSum=0;
		for(int i =0;i<products2s.size();i++){
			Products2 products2 = products2s.get(i);
			Products products = new Products();

			products.setCompany(company);
			products.setStatus(true);
			products.setName(products2.getName());
			products.setConsignation(products2.isConsignation());
			products.setWeight(products2.getWeight());
			products.setAmount(products2.getAmount());
			products.setPrice(products2.getPrice());
			products.setReturnAmount(products2.getReturnAmount());
			products.setMyorder(myOrder);
			lSum += Integer.valueOf(products2.getAmount())*Integer.valueOf(products2.getPrice());
			if(products2.isConsignation()){
				debtSum = Integer.valueOf(products2.getAmount())*Float.valueOf(products2.getPrice())+debtSum;
			}
			productses.add(products);

		}
		serviceClassToRest.updateUndGroupPlus(myOrder3.getShops2().getId(), debtSum);
		myOrder.setSelected(false);
		myOrder.setHide(false);
		myOrder.setRemoveorder(false);
		myOrder.setSum(lSum+"");
		myOrder.setProductses(productses);
		Shops shops = new Shops();
		shops.setId(myOrder3.getShops2().getId());
		myOrder.setShops(shops);

		boolean resultCreatingOrder = serviceClassToRest.createNewOrder(myOrder);
		if(resultCreatingOrder){
			return "success";
		}
		return "failure";
	}


	@RequestMapping(value = "getOrders", method = RequestMethod.GET)
	public Object getOrders(@RequestParam("shop_id") String shop_id) {
		List<MyOrder> orders =serviceClassToRest.getOrders(Integer.valueOf(shop_id));
        List<MyOrder3> ordersToSend = new ArrayList<>();
        for(int i=0;i<orders.size();i++){
			MyOrder order = orders.get(i);
			Company company = order.getCompany();
			MyOrder3 order3 = new MyOrder3();
			order3.setId(order.getId());
			order3.setDate(order.getDate());
			order3.setSum(order.getSum());
			order3.setStatus(order.getStatus());
			Company2 company2 = new Company2();
			company2.setId(company.getId());
			company2.setName(company.getName());
			company2.setPhoto(company.getPhoto());
			order3.setCompany2(company2);
			ordersToSend.add(order3);
		}
		return ordersToSend;
	}
	@RequestMapping(value = "getOrdersSelected", method = RequestMethod.GET)
	public Object getOrdersSelected(@RequestParam("shop_id") String shop_id) {
		List<MyOrder> orders =serviceClassToRest.getOrdersSelected(Integer.valueOf(shop_id));
		List<MyOrder3> ordersToSend = new ArrayList<>();
		for(int i=0;i<orders.size();i++){
			MyOrder order = orders.get(i);
			Company company = order.getCompany();
			MyOrder3 order3 = new MyOrder3();
			order3.setId(order.getId());
			order3.setDate(order.getDate());
			order3.setSum(order.getSum());
			order3.setStatus(order.getStatus());
			Company2 company2 = new Company2();
			company2.setId(company.getId());
			company2.setName(company.getName());
			company2.setPhoto(company.getPhoto());
			order3.setCompany2(company2);
			ordersToSend.add(order3);
		}
		return ordersToSend;
	}
	@RequestMapping(value = "getOrderById", method = RequestMethod.GET)
	public Object getOrderById(@RequestParam("shop_id") String shop_id,@RequestParam("order_id") String order_id) {
		List<Products> products =serviceClassToRest.getOrderByIdToRest(Integer.valueOf(shop_id), Integer.valueOf(order_id));
		List<Products2> productssToSend = new ArrayList<>();
		Products product = products.get(0);
		MyOrder myorder = product.getMyorder();

		MyOrder3 myOrder3 = new MyOrder3();
				 myOrder3.setId(myorder.getId());
				 myOrder3.setSum(myorder.getSum());
				 myOrder3.setDate(myorder.getDate());

        for(int i=0;i<products.size(); i++) {
			Products tempProduct = products.get(i);
			Products2 product2 = new Products2();
					product2.setId(tempProduct.getId());
					product2.setName(tempProduct.getName());
					product2.setPrice(tempProduct.getPrice());
					product2.setWeight(tempProduct.getWeight());
					product2.setAmount(tempProduct.getAmount());
					product2.setConsignation(tempProduct.isConsignation());
					product2.setReturnAmount(tempProduct.getReturnAmount());
					product2.setAcceptAmount(tempProduct.getAcceptAmount());
			productssToSend.add(product2);
		}
		myOrder3.setProductses(productssToSend);
		return myOrder3;
	}
	@RequestMapping(value = "getOrderByIdWithCourier", method = RequestMethod.GET)
	public Object getOrderByIdWithCourier(@RequestParam("shop_id") String shop_id,@RequestParam("order_id") String order_id) {
		List<Products> products =serviceClassToRest.getOrderByIdToRestWithCourier(Integer.valueOf(shop_id), Integer.valueOf(order_id));
		List<Products2> productssToSend = new ArrayList<>();
		Products product = products.get(0);
		MyOrder myorder = product.getMyorder();
		Courier courier = myorder.getCourier();



		MyOrder3 myOrder3 = new MyOrder3();
			myOrder3.setId(myorder.getId());
			myOrder3.setSum(myorder.getSum());
			myOrder3.setDate(myorder.getDate());

		Courier2 courier2 = new Courier2();
			courier2.setId(courier.getId());
			courier2.setName(courier.getName());
			courier2.setL_name(courier.getL_name());
			courier2.setPhoto(courier.getPhoto());
			courier2.setPhone(courier.getPhone());

			myOrder3.setCourier2(courier2);


		for(int i=0;i<products.size(); i++) {
			Products tempProduct = products.get(i);
			Products2 product2 = new Products2();
			product2.setId(tempProduct.getId());
			product2.setName(tempProduct.getName());
			product2.setPrice(tempProduct.getPrice());
			product2.setWeight(tempProduct.getWeight());
			product2.setAmount(tempProduct.getAmount());
			product2.setConsignation(tempProduct.isConsignation());
			product2.setReturnAmount(tempProduct.getReturnAmount());
			product2.setProductAccepted(tempProduct.isProductAccepted());
			product2.setAcceptAmount(tempProduct.getAcceptAmount());
			productssToSend.add(product2);
		}
		myOrder3.setProductses(productssToSend);
		return myOrder3;
	}

	@RequestMapping(value = "select_order", method = RequestMethod.GET)
	public Object select_order(@RequestParam("order_id") String order_id) {
		boolean result = serviceClassToRest.selectOrder(Integer.valueOf(order_id));
		if(result){
			return "true";
		}else{
			return "false";
		}
		
	}
	@RequestMapping(value = "deselect_order", method = RequestMethod.GET)
	public Object deselect_order(@RequestParam("order_id") String order_id) {
		boolean result = serviceClassToRest.deselectOrder(Integer.valueOf(order_id));
		if(result){
			return "true";
		}else{
			return "false";
		}
	}

	@RequestMapping(value = "remove_order", method = RequestMethod.GET)
	public Object remove_order(@RequestParam("order_id") String order_id) {
		boolean result = serviceClassToRest.removeOrder(Integer.valueOf(order_id));
		if(result){
			return "true";
		}else{
			return "false";
		}
	}

	@RequestMapping(value = "hide_order", method = RequestMethod.GET)
	public Object hide_order(@RequestParam("order_id") String order_id) {
		boolean result = serviceClassToRest.hideOrder(Integer.valueOf(order_id));
		if(result){
			return "true";
		}else{
			return "false";
		}
	}










	@RequestMapping(value = "signInCourier", method = RequestMethod.POST)
	public Object signInCourier(@RequestBody Courier courier){
		Courier courierRes = serviceClassToRest.userVerificationCourier(courier.getLogin(), courier.getPassword());
		if(courierRes!=null){
			Courier3 courier3 = new Courier3();
			courier3.setId(courierRes.getId());
			courier3.setName(courierRes.getName());
			courier3.setL_name(courierRes.getL_name());
			courier3.setLogin(courierRes.getLogin());
			courier3.setPassword(courierRes.getPassword());
			courier3.setAddress(courierRes.getAddress());
			courier3.setPhone(courierRes.getPhone());
			courier3.setPhoto(courierRes.getPhoto());
			Company company = courierRes.getCompany();
			Company2 company2 = new Company2();
			company2.setId(company.getId());
			company2.setName(company.getName());
			company2.setPhoto(company.getPhoto());
			company2.setAddress(company.getAddress());
			company2.setPhone(company.getPhone());
			courier3.setCompany2(company2);
			return courier3;
		}
		return "500";
	}

	@RequestMapping(value = "getAcceptedOrdersToCourier", method = RequestMethod.GET)
	public Object getNewOrdersToCourier(@RequestParam("courier_id") String courier_id) {
		List<MyOrder> orders =serviceClassToRest.getAcceptedOrdersToCourier(Integer.valueOf(courier_id));
		List<MyOrder3> ordersToSend = new ArrayList<>();
		for(int i=0;i<orders.size();i++){
			MyOrder myOrder = orders.get(i);
			Shops shop = myOrder.getShops();
			MyOrder3 myOrder3 = new MyOrder3();
			myOrder3.setId(myOrder.getId());
			myOrder3.setSum(myOrder.getSum());
			myOrder3.setDate(myOrder.getDate());

			Shops2 shop2 = new Shops2();
			shop2.setId(shop.getId());
			shop2.setName(shop.getName());
			shop2.setPhoto(shop.getPhoto());
			shop2.setAddress(shop.getAddress());
			shop2.setPhone(shop.getPhone());
			shop2.setLatitude(shop.getLatitude());
			shop2.setLongitude(shop.getLongitude());
			myOrder3.setShops2(shop2);
			ordersToSend.add(myOrder3);
		}
		return ordersToSend;
	}
	@RequestMapping(value = "getCompletedOrdersToCourier", method = RequestMethod.GET)
	public Object getAcceptedOrdersToCourier(@RequestParam("courier_id") String courier_id) {
		List<MyOrder> orders =serviceClassToRest.getCompletedOrdersToCourier(Integer.valueOf(courier_id));
		List<MyOrder3> ordersToSend = new ArrayList<>();
		for(int i=0;i<orders.size();i++){
			MyOrder myOrder = orders.get(i);
			Shops shop = myOrder.getShops();
			MyOrder3 myOrder3 = new MyOrder3();
			myOrder3.setId(myOrder.getId());
			myOrder3.setSum(myOrder.getSum());
			myOrder3.setDate(myOrder.getDate());

			Shops2 shop2 = new Shops2();
			shop2.setId(shop.getId());
			shop2.setName(shop.getName());
			shop2.setPhoto(shop.getPhoto());
			shop2.setAddress(shop.getAddress());
			shop2.setPhone(shop.getPhone());
			shop2.setLatitude(shop.getLatitude());
			shop2.setLongitude(shop.getLongitude());
			myOrder3.setShops2(shop2);
			ordersToSend.add(myOrder3);
		}
		return ordersToSend;
	}

	@RequestMapping(value = "getDetailsAcceptComplOrder", method = RequestMethod.GET)
	public Object getDetailsAcceptComplOrder(@RequestParam("order_id") String order_id) {
		List<Products> products =serviceClassToRest.getOrderWithProducts(Integer.valueOf(order_id));
		MyOrder3 myOrder3 = new MyOrder3();
		List<Products2> products2 = new ArrayList<>();
		for(int i=0;i<products.size();i++){
			Products product = products.get(i);
			Products2 product2 = new Products2();
			product2.setId(product.getId());
			product2.setName(product.getName());
			product2.setPrice(product.getPrice());
			product2.setWeight(product.getWeight());
			product2.setAmount(product.getAmount());
			product2.setReturnAmount(product.getReturnAmount());
			product2.setConsignation(product.isConsignation());
			product2.setProductAccepted(product.isProductAccepted());
			product2.setAcceptAmount(product.getAcceptAmount());
			products2.add(product2);
		}
		myOrder3.setProductses(products2);
		return myOrder3;
	}

	@RequestMapping(value = "order_change_amount", method = RequestMethod.GET)
	public Object order_change_amount(@RequestParam("product_id") String product_id,@RequestParam("product_amount") String product_amount) {
		boolean result = serviceClassToRest.order_change_amount(Integer.valueOf(product_id),Integer.valueOf(product_amount));
		if(result){
			return result;
		}
		return false;
	}
	@RequestMapping(value = "order_change_amount_minus_shopDebt", method = RequestMethod.GET)
	public Object order_change_amount_minus_shopDebt(@RequestParam("product_id") String product_id,@RequestParam("product_amount") String product_amount,@RequestParam("shopId") String shopId,@RequestParam("compId") String compId,@RequestParam("productPrice") String productPrice) {
		float sum = Integer.valueOf(product_amount)*Float.valueOf(productPrice);
		boolean result = serviceClassToRest.updateUndGroupMinus(Integer.valueOf(compId),Integer.valueOf(shopId),sum);
		boolean result2 = serviceClassToRest.order_change_amount(Integer.valueOf(product_id),Integer.valueOf(product_amount));
		if(result&&result2){
			return result;
		}
		return false;
	}
	@RequestMapping(value = "order_completed", method = RequestMethod.GET)
	public Object order_completed(@RequestParam("order_id") String order_id) {
		boolean result = serviceClassToRest.order_completed(Integer.valueOf(order_id));
		if(result){
			return result;
		}
		return false;
	}

	@RequestMapping(value = "debtShops", method = RequestMethod.POST)
	public Object debtShops(@RequestBody Company company) {
		List<UndGroups> undGroupsWithShopsDebt = serviceClassToRest.getDebtShops(company.getId());
		List<UndGroupsMin> undGrToSend = new ArrayList<>();
		if(undGroupsWithShopsDebt!=null&&undGroupsWithShopsDebt.size()!=0){
			for(int i=0;i<undGroupsWithShopsDebt.size();i++){
				UndGroups undGroup = undGroupsWithShopsDebt.get(i);
				Shops shop = undGroup.getShops();
				Shops2 shops2 = new Shops2();
				shops2.setId(shop.getId());
				shops2.setName(shop.getName());
				shops2.setAddress(shop.getAddress());
				shops2.setPhoto(shop.getPhoto());
				shops2.setPhone(shop.getPhone());
				shops2.setLatitude(shop.getLatitude());
				shops2.setLongitude(shop.getLongitude());

				UndGroupsMin undGroupsMin = new UndGroupsMin();
				undGroupsMin.setId(undGroup.getId());
				undGroupsMin.setShopCons(undGroup.isShopCons());
				undGroupsMin.setDebt(undGroup.getShopDebt());
				undGroupsMin.setShops2(shops2);
				undGrToSend.add(undGroupsMin);
			}
		}
		return undGrToSend;
	}

	@RequestMapping(value = "minusDebtShops", method = RequestMethod.POST)
	public Object minusDebtShops(@RequestBody DebtShop debtShop) {
		boolean result = serviceClassToRest.updateUndGroupMinus(debtShop.getCompId(),debtShop.getShopId(),debtShop.getDebt());
		if(result){
			return true;
		}
		return false;
	}


	@RequestMapping(value = "getAcceptedOrdersToCourierMap", method = RequestMethod.GET)
	public Object getAcceptedOrdersToCourierMap(@RequestParam("crId") String crId) {
		if(crId!=null&&!crId.isEmpty()){
			List<MyOrder> orders = serviceClassToRest.getAcceptedOrdersToCourierMap(Integer.valueOf(crId));
			List<MyOrder3> ordersToSend = new ArrayList<>();
			for(int i=0;i<orders.size();i++){
				MyOrder order = orders.get(i);
				Shops shop = order.getShops();

				MyOrder3 myOrder3 = new MyOrder3();
				myOrder3.setId(order.getId());
				myOrder3.setDate(order.getDate());
				myOrder3.setSum(order.getSum());
				myOrder3.setStatus(order.getStatus());


				Shops2 shops2 = new Shops2();
				shops2.setId(shop.getId());
				shops2.setName(shop.getName());
				shops2.setAddress(shop.getAddress());
				shops2.setPhoto(shop.getPhoto());
				shops2.setPhone(shop.getPhone());
				shops2.setLatitude(shop.getLatitude());
				shops2.setLongitude(shop.getLongitude());
				myOrder3.setShops2(shops2);


				ordersToSend.add(myOrder3);
			}
			return ordersToSend;
		}
		return null;
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Object save(@RequestBody Shops2 shops2) {
		Shops shops = new Shops();
		shops.setName(shops2.getName());
		shops.setLogin(shops2.getLogin());
		shops.setPassword(shops2.getPassword());
		shops.setAddress(shops2.getAddress());
		shops.setPhone(shops2.getPhone());
		shops.setLatitude(shops2.getLatitude());
		shops.setLongitude(shops2.getLongitude());
		serviceClassToRest.save(shops);
		return true;
	}
	@RequestMapping(value = "orderGenerate", method = RequestMethod.GET)
	public Object orderGenerate() {
		MyOrder3 myOrder3 = new MyOrder3();
		List<Products2> products2s = new ArrayList<>();
		for(int i=0;i<10;i++){
			Products2 products2 = new Products2();
			products2.setName("advsdvsdv");
			products2.setAmount("10");
			products2.setPrice("5");
			products2.setWeight("1kg");
			products2.setConsignation(false);
			products2.setReturnAmount("5");
			products2.setDebt(false);
			products2s.add(products2);
		}
		Shops2 shops2 = new Shops2();
		shops2.setId(1);
		Company2 company2 = new Company2();
		company2.setId(1);
		shops2.setId(1);
		myOrder3.setProductses(products2s);
		myOrder3.setShops2(shops2);
		myOrder3.setCompany2(company2);
		return myOrder3;
	}
























	@RequestMapping(value = "deleRows", method = RequestMethod.GET)
	public Object deleRows() {
		serviceClassToRest.updaterows();
		return "dvsfv";
	}
































}
