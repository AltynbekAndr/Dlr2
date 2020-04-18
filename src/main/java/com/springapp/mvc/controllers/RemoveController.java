package com.springapp.mvc.controllers;

import com.springapp.mvc.model_for_users.*;
import com.springapp.mvc.models2.Company2;
import com.springapp.mvc.models2.Success;
import com.springapp.mvc.service.MyServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class RemoveController {
	@Autowired
	MyServiceClass myServiceClass;


	@RequestMapping(value = "/del_product",method = RequestMethod.GET)
	public String del_product(@RequestParam("id_product") String id_product,HttpServletRequest httpServletRequest,Model model) {
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		try {
			company2.getId();
			Products products = new Products();
			products.setId(Long.valueOf(id_product));
			String result = myServiceClass.delProduct(products);
			if(result.equals("success")){
				model.addAttribute("productDeleted",true);
				return "add_product2";
			}else{
				return "products";
			}
		}catch (NullPointerException e){}
			return "sign_in";
		}
	@RequestMapping(value = "/del_courier",method = RequestMethod.GET)
	public String del_courier(@RequestParam("id_courier") String id_courier,HttpServletRequest httpServletRequest,Model model) {
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		try {
			company2.getId();
			Courier courier = new Courier();
			courier.setId(Integer.valueOf(id_courier));
			myServiceClass.delCourier(courier);
			model.addAttribute("courierOrProd","courier");
			return "del_result";
		}catch (NullPointerException e){
			return "sign_in";
		}
	}
	@RequestMapping(value = "/del_group",method = RequestMethod.GET)
	public String del_group(@RequestParam("id_group") String id_group,HttpServletRequest httpServletRequest,Model model) {
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		try {
			company2.getId();
			Groups groups = new Groups();
			groups.setId(Integer.valueOf(id_group));
			boolean result = myServiceClass.delGroup(groups);
			if(result){
				model.addAttribute("groupDeleted",true);
				return "add_groups2";
			}else{
				return "products";
			}
		}catch (NullPointerException e){}
		return "sign_in";
	}
	@RequestMapping(value = "/del_category",method = RequestMethod.GET)
	public String del_category(@RequestParam("id_category") String id_category,HttpServletRequest httpServletRequest,Model model) {
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		try {
			company2.getId();
			Categories categories = new Categories();
			categories.setId(Integer.valueOf(id_category));
			boolean result = myServiceClass.delCategory(categories);
			if(result){
				model.addAttribute("categoryDeleted",true);
				model.addAttribute("courierOrProd","product");
				return "add_category2";
			}else{
				return "products";
			}
		}catch (NullPointerException e){}
		return "sign_in";
	}
	@ResponseBody
	@RequestMapping(value = "/del_shop",method = RequestMethod.POST)
	public Object del_shop(@RequestBody Shops shop) {
		Success success = new Success();
		boolean result = myServiceClass.delShop(shop);
			if(result){
				success.setResult(200);
				return result;
			}
		success.setResult(500);
		return result;
	}

	@RequestMapping(value = "/admin_del_company",method = RequestMethod.GET)
	public String admin_del_company(@RequestParam("companyId")String companyId,HttpServletRequest httpServletRequest,ModelMap modelMap) {
		HttpSession httpSession = httpServletRequest.getSession();

		DealerAdmin dealerAdmin = (DealerAdmin)httpSession.getAttribute("admin");
		if(dealerAdmin ==null){
			return "sign_in_admin";
		}
		Company company = new Company();
		company.setId(Integer.valueOf(companyId));
		myServiceClass.removeCompany(company);
		modelMap.addAttribute("remove",true);
		modelMap.addAttribute("login", "");
		httpSession.setAttribute("admin", dealerAdmin);
		return "admin_add_company_result";

	}

}