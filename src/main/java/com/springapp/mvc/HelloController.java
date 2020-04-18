package com.springapp.mvc;
import com.google.gson.Gson;
import com.springapp.mvc.model_for_users.*;
import com.springapp.mvc.models2.*;
import com.springapp.mvc.service.MyServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {
	@Autowired
	MyServiceClass myServiceClass;
	Gson gson = new Gson();

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}
	@RequestMapping(value = "/sign_in", method = RequestMethod.GET)
	public String sign_ign(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "sign_in";
	}
	@RequestMapping(value = "/sign_in_admin", method = RequestMethod.GET)
	public String sign_in_admin(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "sign_in_admin";
	}
	@RequestMapping(value = "/main",method = RequestMethod.POST)
	public String main(HttpServletRequest httpServletRequest,ModelMap modelMap) {
		HttpSession httpSession = httpServletRequest.getSession();

		String login = httpServletRequest.getParameter("login");
		String password = httpServletRequest.getParameter("password");
		boolean flag = false;
		Company company = myServiceClass.userVerification(login, password);
		try {
			if(company!=null){
                flag = true;
			}
		}catch (NullPointerException e){}
		if(flag){
			Company2 company2 = new Company2();
			int sizeToPagination = myServiceClass.getSizeToPagination(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}

			company2.setId(company.getId());
			company2.setLogin(company.getLogin());
			company2.setPassword(company.getPassword());
			company2.setName(company.getName());
			company2.setAddress(company.getAddress());
			company2.setPhoto(company.getPhoto());


			List<Categories> categorieses = myServiceClass.getAllCategories(company);
			List<Courier> couriers = myServiceClass.getAllCourier(company);
			List<MyOrder> myOrders = myServiceClass.myOrderListNew(company);
			Categories2[] categorieses2 = new Categories2[categorieses.size()];
			for (int i = 0; i < categorieses.size(); i++) {
				Categories categories = categorieses.get(i);
				Categories2 categories2 = new Categories2();
				categories2.setId(categories.getId());
				categories2.setCategory(categories.getCategory());
				categorieses2[i] = categories2;
			}
			modelMap.addAttribute("categories", categorieses2);
			modelMap.addAttribute("company",company2);
			modelMap.addAttribute("couriers", couriers);
			modelMap.addAttribute("myOrders", myOrders);
			httpSession.setAttribute("company", company2);
			httpSession.setAttribute("categories", categorieses2);
			modelMap.addAttribute("sizeToPagination", sizeToPagination / 30);
			modelMap.addAttribute("pagesEnd", pagesEnd);
			modelMap.addAttribute("activePageNumber", 0);
			return "new_orders";
		}else{
			modelMap.addAttribute("error","Введенные данные не правильны");
			return "sign_in";
		}
	}
	@RequestMapping(value = "/main_admin",method = RequestMethod.POST)
	public String main_admin(HttpServletRequest httpServletRequest,ModelMap modelMap) {
		HttpSession httpSession = httpServletRequest.getSession();

		String login = httpServletRequest.getParameter("login");
		String password = httpServletRequest.getParameter("password");
		boolean flag = false;
		DealerAdmin dealerAdmin = new DealerAdmin();
		dealerAdmin.setLogin(login);
		dealerAdmin.setPassword(password);
		DealerAdmin dealerAdmin1 = myServiceClass.userVerification(dealerAdmin);
		try {
			if(dealerAdmin1 !=null){
				flag = true;
			}
		}catch (NullPointerException e){}
		if(flag){
			List<Company> companies =  myServiceClass.getCompaniesToAdmin();
			httpSession.setAttribute("admin", dealerAdmin1);
			modelMap.addAttribute("companies",companies);
			return "admin";
		}else{
			modelMap.addAttribute("error","Введенные данные не правильны");
			return "sign_in_admin";
		}
	}
	@RequestMapping(value = "/main_admin",method = RequestMethod.GET)
	public String main_admin_get(HttpServletRequest httpServletRequest,ModelMap modelMap) {
		HttpSession httpSession = httpServletRequest.getSession();

			DealerAdmin dealerAdmin = (DealerAdmin)httpSession.getAttribute("admin");
			if(dealerAdmin ==null){
				return "sign_in_admin";
			}
			List<Company> companies =  myServiceClass.getCompaniesToAdmin();
			modelMap.addAttribute("companies",companies);
			httpSession.setAttribute("admin", dealerAdmin);
			return "admin";

	}
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	public String main2() {
		return "hello";
	}
	@RequestMapping(value = "/all_shops",method = RequestMethod.GET)
	public String allshops(ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		Company2 company2 = (Company2) httpSession.getAttribute("company");
		try {
			Company company = new Company();
			company.setId(company2.getId());
			int sizeToPagination = myServiceClass.getSizeToPaginationUndGroups(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<Groups> groupses = myServiceClass.getGroups(company);
			if (groupses != null && groupses.size() != 0) {
				List<UndGroups> undGroupses = myServiceClass.getShopsWithUndGroups(company.getId());
				model.addAttribute("undGroupses", undGroupses);
				model.addAttribute("groups", groupses);
				model.addAttribute("group_id", "");
				model.addAttribute("sizeToPagination", sizeToPagination/30);
				model.addAttribute("pagesEnd", pagesEnd);
				model.addAttribute("activePageNumber", 0);
			}
		}catch (NullPointerException e){
			return "sign_in";
		}
		model.addAttribute("groupName", "Все");
		model.addAttribute("statusAddingLink", false);
		return "all_shops";
	}
	@RequestMapping(value = "/all_shops_p",method = RequestMethod.GET)
	public String all_shops_p(@RequestParam("page") String page, HttpServletRequest httpServletRequest,ModelMap model) {
		HttpSession httpSession = httpServletRequest.getSession();
		Company2 company2 = (Company2) httpSession.getAttribute("company");
		try {
			Company company = new Company();
			company.setId(company2.getId());
			int first = Integer.valueOf(page)*30-30;
			int sizeToPagination = myServiceClass.getSizeToPaginationUndGroups(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<Groups> groupses = myServiceClass.getGroups(company);
			if (groupses != null && groupses.size() != 0) {
				List<UndGroups> undGroupses = myServiceClass.getShopsWithUndGroups2(company.getId(), first);
				model.addAttribute("undGroupses", undGroupses);
				model.addAttribute("groups", groupses);
				model.addAttribute("group_id", "");
				model.addAttribute("sizeToPagination", sizeToPagination/30);
				model.addAttribute("pagesEnd", pagesEnd);
				model.addAttribute("activePageNumber", Integer.valueOf(page));
			}
		}catch (NullPointerException e){
			return "sign_in";
		}
		model.addAttribute("groupName", "Все");
		model.addAttribute("statusAddingLink", false);
		return "all_shops";
	}
	@RequestMapping(value = "/search_added_shop",method = RequestMethod.GET)
	public String search_added_shop(@RequestParam("shop_name") String shop_name,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		Company2 company2 = (Company2) httpSession.getAttribute("company");
		try {
			Company company = new Company();
			company.setId(company2.getId());
			List<Groups> groupses = myServiceClass.getGroups(company);
			if (groupses != null && groupses.size() != 0) {
				List<UndGroups> undGroupses = myServiceClass.getShopsWithUndGroupsForSearch(company.getId(), shop_name);
				model.addAttribute("undGroupses", undGroupses);
				model.addAttribute("groups", groupses);
				model.addAttribute("group_id", "");
			}
		}catch (NullPointerException e){
			return "sign_in";
		}
		model.addAttribute("groupName", "Все");
		model.addAttribute("statusAddingLink", false);
		return "all_shops";
	}
	@RequestMapping(value = "/shops_from_undgroup",method = RequestMethod.GET)
	public String shops_from_undgroup(@RequestParam("group_id") String group_id,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		try {
			Company2 company2 = (Company2) httpSession.getAttribute("company");
			Company company = new Company();
			company.setId(company2.getId());
			int sizeToPagination = myServiceClass.getSizeToPaginationUndGroupsOnGroup(company, Integer.valueOf(group_id));
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<Groups> groupses = myServiceClass.getGroups(company);
			if(groupses!=null&&groupses.size()!=0){
				model.addAttribute("groups", groupses);
				model.addAttribute("statusAddingLink", true);
				List<UndGroups> undGroupses = myServiceClass.getShopsWithUndGroups(company.getId(), Integer.valueOf(group_id));
				model.addAttribute("undGroupses", undGroupses);
				try{
					model.addAttribute("groupName", myServiceClass.getGroupById(Integer.valueOf(group_id)).getName());
					model.addAttribute("group_id", group_id);
					model.addAttribute("sizeToPagination", sizeToPagination/30);
					model.addAttribute("pagesEnd", pagesEnd);
					model.addAttribute("activePageNumber", 0);
					model.addAttribute("group", true);
				}catch (IndexOutOfBoundsException e){
					model.addAttribute("groupName", "Нет магазинов!");
					model.addAttribute("group_id", group_id);
				}

			}
		}catch (NullPointerException e){
			return "sign_in";
		}

		return "all_shops";
	}
	@RequestMapping(value = "/all_shops_pt",method = RequestMethod.GET)
	public String all_shops_pt(@RequestParam("page") String page,@RequestParam("group_id") String group_id,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		try {
			Company2 company2 = (Company2) httpSession.getAttribute("company");
			Company company = new Company();
			company.setId(company2.getId());
			int first = Integer.valueOf(page)*30-30;
			int sizeToPagination = myServiceClass.getSizeToPaginationUndGroupsOnGroup(company, Integer.valueOf(group_id));
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<Groups> groupses = myServiceClass.getGroups(company);
			if(groupses!=null&&groupses.size()!=0){
				model.addAttribute("groups", groupses);
				model.addAttribute("statusAddingLink", true);
				List<UndGroups> undGroupses = myServiceClass.getShopsWithUndGroups(company.getId(), Integer.valueOf(group_id),first);
				model.addAttribute("undGroupses", undGroupses);
				try{
					model.addAttribute("groupName", myServiceClass.getGroupById(Integer.valueOf(group_id)).getName());
					model.addAttribute("group_id", group_id);
					model.addAttribute("sizeToPagination", sizeToPagination/30);
					model.addAttribute("pagesEnd", pagesEnd);
					model.addAttribute("activePageNumber", Integer.valueOf(page));
					model.addAttribute("group", true);
				}catch (IndexOutOfBoundsException e){
					model.addAttribute("groupName", "Нет магазинов!");
					model.addAttribute("group_id", group_id);
				}

			}
		}catch (NullPointerException e){
			return "sign_in";
		}

		return "all_shops";
	}

	@RequestMapping(value = "/shops_from_undgroup_p",method = RequestMethod.GET)
	public String shops_from_undgroup_p(@RequestParam("page") String page, @RequestParam("group_id") String group_id,HttpServletRequest httpServletRequest,ModelMap model) {
		HttpSession httpSession = httpServletRequest.getSession();
		try {
			Company2 company2 = (Company2) httpSession.getAttribute("company");
			Company company = new Company();
			company.setId(company2.getId());
			int first = Integer.valueOf(page)*30-30;
			int sizeToPagination = myServiceClass.getSizeToPaginationUndGroupsOnGroup(company, Integer.valueOf(group_id));
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<Groups> groupses = myServiceClass.getGroups(company);
			if(groupses!=null&&groupses.size()!=0){
				model.addAttribute("groups", groupses);
				model.addAttribute("statusAddingLink", true);
				List<UndGroups> undGroupses = myServiceClass.getShopsWithUndGroups(company.getId(), Integer.valueOf(group_id),first);
				model.addAttribute("undGroupses", undGroupses);
				try{
					model.addAttribute("groupName", myServiceClass.getGroupById(Integer.valueOf(group_id)).getName());
					model.addAttribute("group_id", group_id);
					model.addAttribute("sizeToPagination", sizeToPagination/30);
					model.addAttribute("pagesEnd", pagesEnd);
					model.addAttribute("activePageNumber", 0);
					model.addAttribute("group", true);
				}catch (IndexOutOfBoundsException e){
					model.addAttribute("groupName", "Нет магазинов!");
					model.addAttribute("group_id", group_id);
				}

			}
		}catch (NullPointerException e){
			return "sign_in";
		}

		return "all_shops";
	}
	@RequestMapping(value = "/couriers",method = RequestMethod.GET)
	public String couriers(ModelMap model,HttpServletRequest httpServletRequest) {
		try{
			Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
			Company company = new Company();
			company.setId(company2.getId());
			List<Courier> list = myServiceClass.getAllCourier(company);
			Courier2[] couriers = new Courier2[list.size()];
			Courier2 courier2 = null;
			Courier courier = null;
			for(int i=0;i<list.size();i++){
				courier = list.get(i);
				courier2 = new Courier2();
				courier2.setId(courier.getId());
				courier2.setName(courier.getName());
				courier2.setL_name(courier.getL_name());
				courier2.setAddress(courier.getAddress());
				courier2.setPhoto(courier.getPhoto());
				courier2.setPhone(courier.getPhone());
				courier2.setLogin(courier.getLogin());
				courier2.setPassword(courier.getPassword());
				couriers [i]= courier2;
			}
			String str = gson.toJson(couriers);
			model.addAttribute("message", str);
			return "couriers";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/products",method = RequestMethod.GET)
	public String products(ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Company company = new Company();
		Company2 company2 = (Company2) session.getAttribute("company");
		try {
			company.setId(company2.getId());

			List<Products> allProducts = myServiceClass.getAllProducts(company);
			List<Categories> categorieses = myServiceClass.getAllCategories(company);

			model.addAttribute("categorieses", categorieses);
			model.addAttribute("products", allProducts);
			model.addAttribute("products_cat", "Все");
			return "products";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/history",method = RequestMethod.GET)
	public String history(ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			int sizeToPagination = myServiceClass.getSizeToPaginationCompleted(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<MyOrder> myOrders = myServiceClass.myOrderListToHistory(company);
			model.addAttribute("myOrders", myOrders);
			model.addAttribute("sizeToPagination", sizeToPagination/30);
			model.addAttribute("pagesEnd", pagesEnd);
			model.addAttribute("activePageNumber", 0);


			return "history";
		}catch (NullPointerException e){
			return "sign_in";
		}
	}
	@RequestMapping(value = "/history_p",method = RequestMethod.GET)
	public String history(@RequestParam("page") String page, HttpServletRequest httpServletRequest,ModelMap model) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			int first = Integer.valueOf(page)*30-30;
			int sizeToPagination = myServiceClass.getSizeToPaginationCompleted(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<MyOrder> myOrders = myServiceClass.myOrderListToHistory(company, first);
			model.addAttribute("myOrders", myOrders);
			model.addAttribute("sizeToPagination", sizeToPagination/30);
			model.addAttribute("pagesEnd", pagesEnd);
			model.addAttribute("activePageNumber", Integer.valueOf(page));

			return "history";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}

	@RequestMapping(value = "/new_orders_p",method = RequestMethod.GET)
	public String new_orders(@RequestParam("page") String page, HttpServletRequest httpServletRequest,ModelMap model) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			int first = Integer.valueOf(page)*30-30;
			int sizeToPagination = myServiceClass.getSizeToPagination(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<Courier> couriers = myServiceClass.getAllCourier(company);
			List<MyOrder> myOrders = myServiceClass.myOrderListNew(company,first);
			model.addAttribute("couriers", couriers);
			model.addAttribute("myOrders", myOrders);

			model.addAttribute("sizeToPagination", sizeToPagination/30);
			model.addAttribute("pagesEnd", pagesEnd);
			model.addAttribute("activePageNumber", Integer.valueOf(page));

			return "new_orders";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/accepted_orders",method = RequestMethod.GET)
	public String accepted_orders(HttpServletRequest httpServletRequest,ModelMap model) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try{
			company.setId(company2.getId());
			int sizeToPagination = myServiceClass.getSizeToPaginationAccepted(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<Courier> couriers = myServiceClass.getAllCourier(company);
			List<MyOrder> myOrders = myServiceClass.myOrderListAccepted(company);
			model.addAttribute("couriers", couriers);
			model.addAttribute("myOrders", myOrders);
			model.addAttribute("sizeToPagination", sizeToPagination/30);
			model.addAttribute("pagesEnd", pagesEnd);
			model.addAttribute("activePageNumber", 0);


			return "accepted_orders";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/accepted_orders_p",method = RequestMethod.GET)
	public String accepted_orders(@RequestParam("page") String page, HttpServletRequest httpServletRequest,ModelMap model) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			int first = Integer.valueOf(page)*30-30;
			int sizeToPagination = myServiceClass.getSizeToPaginationAccepted(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<Courier> couriers = myServiceClass.getAllCourier(company);
			List<MyOrder> myOrders = myServiceClass.myOrderListAccepted(company, first);
			model.addAttribute("couriers", couriers);
			model.addAttribute("myOrders", myOrders);
			model.addAttribute("sizeToPagination", sizeToPagination/30);
			model.addAttribute("pagesEnd", pagesEnd);
			model.addAttribute("activePageNumber", Integer.valueOf(page));

			return "accepted_orders";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/complete_orders",method = RequestMethod.GET)
	public String complete_orders(HttpServletRequest httpServletRequest,ModelMap model) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			int sizeToPagination = myServiceClass.getSizeToPaginationCompleted(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<Courier> couriers = myServiceClass.getAllCourier(company);
			List<MyOrder> myOrders = myServiceClass.myOrderListComplete(company);
			model.addAttribute("couriers", couriers);
			model.addAttribute("myOrders", myOrders);
			model.addAttribute("sizeToPagination", sizeToPagination/30);
			model.addAttribute("pagesEnd", pagesEnd);
			model.addAttribute("activePageNumber", 0);


			return "completed_orders";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/complete_orders_p",method = RequestMethod.GET)
	public String complete_orders(@RequestParam("page") String page, HttpServletRequest httpServletRequest,ModelMap model) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			int first = Integer.valueOf(page)*30-30;
			int sizeToPagination = myServiceClass.getSizeToPaginationCompleted(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<Courier> couriers = myServiceClass.getAllCourier(company);
			List<MyOrder> myOrders = myServiceClass.myOrderListComplete(company, first);
			model.addAttribute("couriers", couriers);
			model.addAttribute("myOrders", myOrders);
			model.addAttribute("sizeToPagination", sizeToPagination/30);
			model.addAttribute("pagesEnd", pagesEnd);
			model.addAttribute("activePageNumber", Integer.valueOf(page));

			return "completed_orders";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/add_category",method = RequestMethod.GET)
	public String add_category(ModelMap model,HttpServletRequest httpServletRequest) {
		Company company = new Company();
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		try {
			company.setId(company2.getId());
			List<Categories> list = myServiceClass.getAllCategories(company);
			Categories2[] categorieses2 = new Categories2[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Categories categories = list.get(i);
				Categories2 categories2 = new Categories2();
				categories2.setId(categories.getId());
				categories2.setCategory(categories.getCategory());
				categorieses2[i] = categories2;
			}
			model.addAttribute("categories", categorieses2);
			return "add_category";
		}catch (NullPointerException e){
			return "sign_in";
		}
	}
	@RequestMapping(value = "/add_product",method = RequestMethod.GET)
	public String add_product(ModelMap model,HttpServletRequest httpServletRequest) {
		Company company = new Company();
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		HttpSession session = httpServletRequest.getSession();
		try {
			company.setId(company2.getId());
			List<Categories> list = myServiceClass.getAllCategories(company);
			Categories2[] categorieses2 = new Categories2[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Categories categories = list.get(i);
				Categories2 categories2 = new Categories2();
				categories2.setId(categories.getId());
				categories2.setCategory(categories.getCategory());
				categorieses2[i] = categories2;
			}
			model.addAttribute("categories", categorieses2);
			session.setAttribute("categories", categorieses2);
			return "add_product";
		}catch (NullPointerException e){
			return "sign_in";
		}
	}
	@RequestMapping(value = "/add_courier",method = RequestMethod.GET)
	public String add_courier(ModelMap model,HttpServletRequest httpServletRequest) {
		Company company = new Company();
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		try {
			company.setId(company2.getId());
			model.addAttribute("message", "Hello world!");
			return "add_courier";
		}catch (NullPointerException e){
			return "sign_in";
		}
	}
	@RequestMapping(value = "/add_groups",method = RequestMethod.GET)
	public String add_groups(HttpServletRequest httpServletRequest,ModelMap modelMap) {
		HttpSession httpSession = httpServletRequest.getSession();
		Company company = new Company();
		Company2 company2 = (Company2) httpSession.getAttribute("company");
		try {
			company.setId(company2.getId());
			List<Groups> groups = myServiceClass.getGroups(company);
			modelMap.addAttribute("groups",groups);
			return "add_groups";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/add_shops",method = RequestMethod.GET)
	public String add_shops(ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		Company company = new Company();
		Company2 company2 =(Company2) httpSession.getAttribute("company");
		try {
			company.setId(company2.getId());

			List<Groups> groups = myServiceClass.getGroups(company);
			List<GroupsUs> groupsUs = myServiceClass.getGlobalGroups();
			List<UndGroups> undGroups = myServiceClass.getListUndGroups(company.getId());
			List<Integer> undGroupsShopsId = new ArrayList<Integer>();
			for(int i=0;i<undGroups.size();i++){
				undGroupsShopsId.add(undGroups.get(i).getShops().getId());
			}
			List<Shops>shops = null;
			if(undGroupsShopsId!=null&&undGroupsShopsId.size()!=0){
				shops = myServiceClass.getAllShopsByGroupsUsId(1,undGroupsShopsId);
				int sizeToPagination = myServiceClass.getSizeToPaginationShops(undGroupsShopsId);
				int pages = sizeToPagination%30;
				boolean pagesEnd = false;
				if(pages!=0){
					pagesEnd=true;
				}

				model.addAttribute("group_id", "1");
				model.addAttribute("sizeToPagination", sizeToPagination/30);
				model.addAttribute("pagesEnd", pagesEnd);
				model.addAttribute("activePageNumber", 0);
				model.addAttribute("group", true);
			}else{
				shops = myServiceClass.getAllShopsByGroupsUsId(1);
				int sizeToPagination = myServiceClass.getSizeToPaginationShops(1);
				int pages = sizeToPagination%30;
				boolean pagesEnd = false;
				if(pages!=0){
					pagesEnd=true;
				}

				model.addAttribute("group_id", "1");
				model.addAttribute("sizeToPagination", sizeToPagination/30);
				model.addAttribute("pagesEnd", pagesEnd);
				model.addAttribute("activePageNumber", 0);
				model.addAttribute("group", false);
			}

			String group_usName = null;
			if(shops!=null&&shops.size()!=0){
				GroupsUs groupUs = myServiceClass.getGlobalGroupById(Integer.valueOf(1));
				group_usName=groupUs.getName();
			}else{
				group_usName = "Все добавлены";
			}
			model.addAttribute("group_us", group_usName);
			model.addAttribute("GroupsUs", groupsUs);
			model.addAttribute("groups", groups);
			model.addAttribute("shops", shops);
			return "add_shops";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/add_shops_p",method = RequestMethod.GET)
	public String add_shops_p(@RequestParam("page") String page,@RequestParam("group_id") String group_id, ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		Company company = new Company();
		Company2 company2 =(Company2) httpSession.getAttribute("company");
		try {
			company.setId(company2.getId());

			List<Groups> groups = myServiceClass.getGroups(company);
			List<GroupsUs> groupsUs = myServiceClass.getGlobalGroups();
			List<UndGroups> undGroups = myServiceClass.getListUndGroups(company.getId());
			List<Integer> undGroupsShopsId = new ArrayList<Integer>();
			for(int i=0;i<undGroups.size();i++){
				undGroupsShopsId.add(undGroups.get(i).getShops().getId());
			}
			List<Shops>shops = null;
			if(undGroupsShopsId!=null&&undGroupsShopsId.size()!=0){
				int first = Integer.valueOf(page)*30-30;
				shops = myServiceClass.getAllShopsByGroupsUsId(Integer.valueOf(group_id),undGroupsShopsId,first);
				int sizeToPagination = myServiceClass.getSizeToPaginationShops(Integer.valueOf(group_id),undGroupsShopsId);
				int pages = sizeToPagination%30;
				boolean pagesEnd = false;
				if(pages!=0){
					pagesEnd=true;
				}

				model.addAttribute("group_id", group_id);
				model.addAttribute("sizeToPagination", sizeToPagination/30);
				model.addAttribute("pagesEnd", pagesEnd);
				model.addAttribute("activePageNumber", Integer.valueOf(page));
				model.addAttribute("group", true);
			}else{
				int tmpGroup_id = 1;
				if(group_id!=null||group_id.length()!=0||!group_id.equals("")){
					int first = Integer.valueOf(page)*30-30;
					shops = myServiceClass.getAllShopsByGroupsUsId(Integer.valueOf(group_id),first);
					tmpGroup_id = Integer.valueOf(group_id);
				}else{
					shops = myServiceClass.getAllShopsByGroupsUsId(1);
				}
				int sizeToPagination = myServiceClass.getSizeToPaginationShops(tmpGroup_id);
				int pages = sizeToPagination%30;
				boolean pagesEnd = false;
				if(pages!=0){
					pagesEnd=true;
				}

				model.addAttribute("group_id", group_id);
				model.addAttribute("sizeToPagination", sizeToPagination/30);
				model.addAttribute("pagesEnd", pagesEnd);
				model.addAttribute("activePageNumber", Integer.valueOf(page));
				model.addAttribute("group", pagesEnd);
			}

			String group_usName = null;
			if(shops!=null&&shops.size()!=0){
				GroupsUs groupUs = myServiceClass.getGlobalGroupById(Integer.valueOf(1));
				group_usName=groupUs.getName();
			}else{
				group_usName = "Все добавлены";
			}
			model.addAttribute("group_us", group_usName);
			model.addAttribute("GroupsUs", groupsUs);
			model.addAttribute("groups", groups);
			model.addAttribute("shops", shops);
			return "add_shops";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/get_shops_by_id",method = RequestMethod.GET)
	public String get_shop_by_id(@RequestParam("group_id") String group_id,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		Company company = new Company();
		Company2 company2 =(Company2) httpSession.getAttribute("company");
		try {
			company.setId(company2.getId());

			List<Groups> groups = myServiceClass.getGroups(company);
			List<GroupsUs> groupsUs = myServiceClass.getGlobalGroups();
			List<UndGroups> undGroups = myServiceClass.getListUndGroups(company.getId());
			List<Integer> undGroupsShopsId = new ArrayList<Integer>();
			for(int i=0;i<undGroups.size();i++){
				undGroupsShopsId.add(undGroups.get(i).getShops().getId());
			}
			List<Shops>shops = null;
			if(undGroupsShopsId!=null&&undGroupsShopsId.size()!=0){
				int first = 1*30-30;
				shops = myServiceClass.getAllShopsByGroupsUsId(Integer.valueOf(group_id),undGroupsShopsId,first);
				int sizeToPagination = myServiceClass.getSizeToPaginationShops(Integer.valueOf(group_id),undGroupsShopsId);
				int pages = sizeToPagination%30;
				boolean pagesEnd = false;
				if(pages!=0){
					pagesEnd=true;
				}

				model.addAttribute("group_id", group_id);
				model.addAttribute("sizeToPagination", sizeToPagination/30);
				model.addAttribute("pagesEnd", pagesEnd);
				model.addAttribute("activePageNumber", 0);
				model.addAttribute("group", true);
			}else{
				if(group_id!=null||group_id.length()!=0||!group_id.equals("")){
					shops = myServiceClass.getAllShopsByGroupsUsId(Integer.valueOf(group_id));
				}else{
					shops = myServiceClass.getAllShopsByGroupsUsId(1);
				}
				int sizeToPagination = myServiceClass.getSizeToPaginationShops(Integer.valueOf(group_id));
				int pages = sizeToPagination%30;
				boolean pagesEnd = false;
				if(pages!=0){
					pagesEnd=true;
				}

				model.addAttribute("group_id", group_id);
				model.addAttribute("sizeToPagination", sizeToPagination/30);
				model.addAttribute("pagesEnd", pagesEnd);
				model.addAttribute("activePageNumber", 0);
				model.addAttribute("group", false);
			}
			String group_usName = null;
			if(shops!=null&&shops.size()!=0){
				GroupsUs groupUs = myServiceClass.getGlobalGroupById(Integer.valueOf(group_id));
				group_usName=groupUs.getName();
			}else{
				group_usName = "Все добавлены";
			}
			model.addAttribute("group_us", group_usName);
			model.addAttribute("GroupsUs", groupsUs);
			model.addAttribute("groups", groups);
			model.addAttribute("shops", shops);
			return "add_shops";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/get_shops_by_name",method = RequestMethod.GET)
	public String get_shops_by_name(@RequestParam("shop_name") String shop_name,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		Company company = new Company();
		Company2 company2 =(Company2) httpSession.getAttribute("company");
		try {
			company.setId(company2.getId());
			List<UndGroups> undGroupses = myServiceClass.getShopsWithUndGroups(company.getId());
			List<Integer> shopsId = new ArrayList<>();
			for(int i=0;i<undGroupses.size();i++){
				Shops shop = undGroupses.get(i).getShops();
				shopsId.add(shop.getId());
			}
			List<Groups> groups = myServiceClass.getGroups(company);
			List<GroupsUs> groupsUs = myServiceClass.getGlobalGroups();
			List<Shops> shops = myServiceClass.searchShops(shop_name, shopsId);

			String group_usName = "Результат поиска...";
			model.addAttribute("group_us", group_usName);
			model.addAttribute("GroupsUs", groupsUs);
			model.addAttribute("groups", groups);
			model.addAttribute("shops", shops);
			return "result_search_adding_shops";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}



	@RequestMapping(value = "/product_change",method = RequestMethod.GET)
	public String product_change(@RequestParam("id_product") String id_product,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Company company = new Company();
		Products products = new Products();
		Company2 company2 = (Company2) session.getAttribute("company");
		try {
			company.setId(company2.getId());
			products.setId(Long.valueOf(id_product));
			products.setCompany(company);
			Products resProducts = myServiceClass.getProducts(products);

			model.addAttribute("resProducts", resProducts);
			return "product_change";
		}catch (NullPointerException e){
			return "sign_in";
		}
	}
	@RequestMapping(value = "/courier_change",method = RequestMethod.GET)
	public String courier_change(@RequestParam("id_courier") String id_courier,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Company company = new Company();
		Products products = new Products();
		Company2 company2 = (Company2) session.getAttribute("company");
		try {
			company.setId(company2.getId());
			products.setId(Long.valueOf(id_courier));
			products.setCompany(company);
			Courier courier = myServiceClass.getCourierById(company,Integer.valueOf(id_courier));

			model.addAttribute("courier", courier);
			return "courier_change";
		}catch (NullPointerException e){
			return "sign_in";
		}
	}

	@RequestMapping(value = "/detailsOrder",method = RequestMethod.GET)
	public String detailsOrder(@RequestParam("order") String order_id,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();

		Company2 company2 = (Company2) session.getAttribute("company");
		try {
			Company company = new Company();
			company.setId(company2.getId());
			List<Products> products = myServiceClass.getOrderById(Integer.valueOf(order_id), company2.getId());
			MyOrder myOrder = products.get(0).getMyorder();
			model.addAttribute("myOrder", myOrder);
			model.addAttribute("products", products);
			return "details_order";
		}catch (NullPointerException e){
			return "sign_in";
		}
	}
	@RequestMapping(value = "/detailsAcceptedOrder",method = RequestMethod.GET)
	public String detailsAcceptedOrder(@RequestParam("order") String order_id,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();

		Company2 company2 = (Company2) session.getAttribute("company");
		try {
			Company company = new Company();
			company.setId(company2.getId());
			List<Products> products = myServiceClass.getOrderByIdWithCourier(Integer.valueOf(order_id), company2.getId());
			MyOrder myOrder = products.get(0).getMyorder();
			model.addAttribute("myOrder", myOrder);
			model.addAttribute("products", products);
			return "details_order";
		}catch (NullPointerException e){
			return "sign_in";
		}
	}
	@RequestMapping(value = "/detailsCompletedOrder",method = RequestMethod.GET)
	public String detailsCompletedOrder(@RequestParam("order") String order_id,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();

		Company2 company2 = (Company2) session.getAttribute("company");
		try {
			Company company = new Company();
			company.setId(company2.getId());
			List<Products> products = myServiceClass.getOrderByIdWithCourier(Integer.valueOf(order_id), company2.getId());
			MyOrder myOrder = products.get(0).getMyorder();
			model.addAttribute("myOrder", myOrder);
			model.addAttribute("products", products);
			return "details_order";
		}catch (NullPointerException e){
			return "sign_in";
		}
	}
	@RequestMapping(value = "/detailsOrderHistory",method = RequestMethod.GET)
	public String detailsOrderHistory(@RequestParam("order") String order_id,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();

		Company2 company2 = (Company2) session.getAttribute("company");
		try {
			Company company = new Company();
			company.setId(company2.getId());
			List<Products> products = myServiceClass.getOrderByIdWithCourier(Integer.valueOf(order_id), company2.getId());
			MyOrder myOrder = products.get(0).getMyorder();
			model.addAttribute("myOrder", myOrder);
			model.addAttribute("products", products);
			return "details_order_history";
		}catch (NullPointerException e){
			return "sign_in";
		}
	}

	@RequestMapping(value = "/get_products_by_categoryId",method = RequestMethod.GET)
	public String get_products_by_categoryId(@RequestParam("category_id") String category_id,ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			Categories categories = new Categories();
			categories.setId(Integer.valueOf(category_id));
			List<Products> allProducts = myServiceClass.getAllProductsByCategoryId(company,categories);
			List<Categories> categorieses = myServiceClass.getAllCategories(company);

			model.addAttribute("categorieses", categorieses);
			model.addAttribute("products", allProducts);

			try{
				String products_cat = allProducts.get(0).getCategories().getCategory();
				model.addAttribute("products_cat", products_cat.toUpperCase());
			}catch (IndexOutOfBoundsException e){
				model.addAttribute("products_cat", "В категории нет продуктов");
			}

			return "products";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}

	@RequestMapping(value = "/orders_in_map",method = RequestMethod.GET)
	public String orders_in_map(ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			List<Products> products = myServiceClass.getOrdersToMap(company.getId());
			model.addAttribute("products",products);
			return "myorders_in_map";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/new_orders_concr_shop",method = RequestMethod.GET)
	public String new_orders_concr_shop(@RequestParam("shopId")String shopid, ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			List<Courier> couriers = myServiceClass.getAllCourier(company);
			List<MyOrder> orders = myServiceClass.getOrderConcreteShop(Integer.valueOf(shopid),company.getId());
			model.addAttribute("couriers",couriers);
			model.addAttribute("orders",orders);
			model.addAttribute("shopid",shopid);
			return "new_orders_concr_shop";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/shop_info",method = RequestMethod.GET)
	public String shop_info(@RequestParam("shopId")String shopid, ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			Shops shop = myServiceClass.getShopByid(Integer.valueOf(shopid));
			model.addAttribute("shop",shop);
			return "shop_info";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/debt_shops",method = RequestMethod.GET)
	public String debt_shops(HttpServletRequest httpServletRequest,ModelMap model) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			int sizeToPagination = myServiceClass.getSizeToPaginationUndGroupsToDebt(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<UndGroups> debtShopsTopage = myServiceClass.getDebtShopsTopage(Integer.valueOf(company.getId()));
			List<UndGroupsMin> undGroupsMins = new ArrayList<>();
			for(int i=0;i<debtShopsTopage.size();i++){
				UndGroups undGroup = debtShopsTopage.get(i);
				UndGroupsMin undGroupsMin = new UndGroupsMin();
				Shops shops = undGroup.getShops();
				Groups groups = undGroup.getGroups();
				Groups2 groups2 = new Groups2();
				groups2.setId(groups.getId());
				groups2.setName(groups.getName());
				Shops2 shops2 = new Shops2();
				shops2.setId(shops.getId());
				shops2.setAddress(shops.getAddress());
				shops2.setName(shops.getName());
				shops2.setLatitude(shops.getLatitude());
				shops2.setLongitude(shops.getLongitude());
				shops2.setPhone(shops.getPhone());
				shops2.setPhoto(shops.getPhoto());
				undGroupsMin.setShops2(shops2);
				undGroupsMin.setId(undGroup.getId());
				undGroupsMin.setDebt(undGroup.getShopDebt());
				undGroupsMin.setShopCons(undGroup.isShopCons());
				undGroupsMin.setGroups2(groups2);
				undGroupsMins.add(undGroupsMin);
			}
			model.addAttribute("undGroupsMins",undGroupsMins);
			model.addAttribute("sizeToPagination", sizeToPagination/30);
			model.addAttribute("pagesEnd", pagesEnd);
			model.addAttribute("activePageNumber", 0);
			return "debt_shops";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/debt_shops_p",method = RequestMethod.GET)
	public String debt_shops_p(@RequestParam("page") String page, HttpServletRequest httpServletRequest,ModelMap model) {
		HttpSession session = httpServletRequest.getSession();
		Company2 company2 = (Company2) session.getAttribute("company");
		Company company = new Company();
		try {
			company.setId(company2.getId());
			int first = Integer.valueOf(page)*30-30;
			int sizeToPagination = myServiceClass.getSizeToPaginationUndGroupsToDebt(company);
			int pages = sizeToPagination%30;
			boolean pagesEnd = false;
			if(pages!=0){
				pagesEnd=true;
			}
			List<UndGroups> debtShopsTopage = myServiceClass.getDebtShopsTopage(Integer.valueOf(company.getId()),first);
			List<UndGroupsMin> undGroupsMins = new ArrayList<>();
			for(int i=0;i<debtShopsTopage.size();i++){
				UndGroups undGroup = debtShopsTopage.get(i);
				UndGroupsMin undGroupsMin = new UndGroupsMin();
				Shops shops = undGroup.getShops();
				Groups groups = undGroup.getGroups();
				Groups2 groups2 = new Groups2();
				groups2.setId(groups.getId());
				groups2.setName(groups.getName());
				Shops2 shops2 = new Shops2();
				shops2.setId(shops.getId());
				shops2.setAddress(shops.getAddress());
				shops2.setName(shops.getName());
				shops2.setLatitude(shops.getLatitude());
				shops2.setLongitude(shops.getLongitude());
				shops2.setPhone(shops.getPhone());
				shops2.setPhoto(shops.getPhoto());
				undGroupsMin.setShops2(shops2);
				undGroupsMin.setId(undGroup.getId());
				undGroupsMin.setDebt(undGroup.getShopDebt());
				undGroupsMin.setShopCons(undGroup.isShopCons());
				undGroupsMin.setGroups2(groups2);
				undGroupsMins.add(undGroupsMin);
			}
			model.addAttribute("undGroupsMins",undGroupsMins);
			model.addAttribute("sizeToPagination", sizeToPagination/30);
			model.addAttribute("pagesEnd", pagesEnd);
			model.addAttribute("activePageNumber", Integer.valueOf(page));
			return "debt_shops";
		}catch (NullPointerException e){
			return "sign_in";
		}

	}
	@RequestMapping(value = "/admin_shop_info",method = RequestMethod.GET)
	public String admin_shop_info(@RequestParam("shopId")String shopid, ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		DealerAdmin dealerAdmin = (DealerAdmin) session.getAttribute("admin");
		try {
			dealerAdmin.getId();
			Shops shop = myServiceClass.getShopByid(Integer.valueOf(shopid));
			model.addAttribute("shop",shop);
			return "admin_shop_info";
		}catch (NullPointerException e){
			return "sign_in_admin";
		}

	}






	@RequestMapping(value = "/admin_add_company",method = RequestMethod.GET)
	public String admin_add_company(HttpServletRequest httpServletRequest,ModelMap modelMap) {
		HttpSession httpSession = httpServletRequest.getSession();

		DealerAdmin dealerAdmin = (DealerAdmin)httpSession.getAttribute("admin");
		if(dealerAdmin ==null){
			return "sign_in_admin";
		}
		httpSession.setAttribute("admin", dealerAdmin);
		return "admin_add_company";

	}
	@RequestMapping(value = "/admin_shops",method = RequestMethod.GET)
	public String admin_shops(HttpServletRequest httpServletRequest,ModelMap modelMap) {
		HttpSession httpSession = httpServletRequest.getSession();

		DealerAdmin dealerAdmin = (DealerAdmin)httpSession.getAttribute("admin");
		if(dealerAdmin ==null){
			return "sign_in_admin";
		}
		List<Shops> shops = myServiceClass.getAllShopsToAdmin();
		List<GroupsUs> groups = myServiceClass.getGlobalGroups();
		modelMap.addAttribute("group_us","Все");
		modelMap.addAttribute("shops",shops);
		modelMap.addAttribute("groups",groups);
		httpSession.setAttribute("admin", dealerAdmin);
		return "admin_shops";

	}
	@RequestMapping(value = "/admin_change_company",method = RequestMethod.GET)
	public String admin_change_company(@RequestParam("companyId")String companyId,HttpServletRequest httpServletRequest,ModelMap modelMap) {
		HttpSession httpSession = httpServletRequest.getSession();

		DealerAdmin dealerAdmin = (DealerAdmin)httpSession.getAttribute("admin");
		if(dealerAdmin ==null){
			return "sign_in_admin";
		}
		Company company = myServiceClass.getCompanyById(Integer.valueOf(companyId));
		modelMap.addAttribute("company",company);
		httpSession.setAttribute("admin", dealerAdmin);
		return "admin_change_company";

	}
	@RequestMapping(value = "/setGroup",method = RequestMethod.GET)
	public String setGroup(@RequestParam("group_id")String group_id,HttpServletRequest httpServletRequest,ModelMap modelMap) {
		HttpSession httpSession = httpServletRequest.getSession();

		Company2 company2 = (Company2)httpSession.getAttribute("company");
		if(company2==null){
			return "sign_in";
		}
		Groups groups = myServiceClass.getGroupById(Integer.valueOf(group_id));
		modelMap.addAttribute("groups",groups);
		httpSession.setAttribute("company",company2);
		return "groupsParam";

	}
	@RequestMapping(value = "/change_category",method = RequestMethod.GET)
	public String change_category(@RequestParam("id_category")String id_category,HttpServletRequest httpServletRequest,ModelMap modelMap) {
		HttpSession httpSession = httpServletRequest.getSession();

		Company2 company2 = (Company2)httpSession.getAttribute("company");
		if(company2==null){
			return "sign_in";
		}
		Company company = new Company();
		company.setId(company2.getId());
		List<Categories> list = myServiceClass.getAllCategories(company);
		Categories categories = myServiceClass.getCategorybyId(Integer.valueOf(id_category));
		Categories2 categories2 = new Categories2();
		categories2.setId(categories.getId());
		categories2.setCategory(categories.getCategory());
		modelMap.addAttribute("category", categories2);
		modelMap.addAttribute("categories",list);
		httpSession.setAttribute("company",company2);
		return "add_category_change";

	}
}