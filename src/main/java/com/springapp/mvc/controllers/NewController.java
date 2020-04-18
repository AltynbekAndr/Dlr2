package com.springapp.mvc.controllers;

import com.springapp.mvc.model_for_users.Company;
import com.springapp.mvc.model_for_users.Courier;
import com.springapp.mvc.model_for_users.MyOrder;
import com.springapp.mvc.model_for_users.Shops;
import com.springapp.mvc.models2.Company2;
import com.springapp.mvc.models2.MyOrder4;
import com.springapp.mvc.models2.Success;
import com.springapp.mvc.service.MyServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Altynbek on 24.03.2020.
 */
@Controller
@RequestMapping("/")
public class NewController {
    @Autowired
    MyServiceClass myServiceClass;


    @RequestMapping(value = "/new_orders",method = RequestMethod.GET)
    public String new_orders(HttpServletRequest httpServletRequest,ModelMap model) {
        HttpSession session = httpServletRequest.getSession();
        Company2 company2 = (Company2) session.getAttribute("company");
        Company company = new Company();
        try {
            company.setId(company2.getId());
            int sizeToPagination = myServiceClass.getSizeToPagination(company);
            int pages = sizeToPagination%30;
            boolean pagesEnd = false;
            if(pages!=0){
                pagesEnd=true;
            }
            List<Courier> couriers = myServiceClass.getAllCourier(company);
            List<MyOrder> myOrders = myServiceClass.myOrderListNew(company);
            model.addAttribute("couriers", couriers);
            model.addAttribute("myOrders", myOrders);
            model.addAttribute("sizeToPagination", sizeToPagination/30);
            model.addAttribute("pagesEnd", pagesEnd);
            model.addAttribute("activePageNumber", 0);


            return "new_orders";
        }catch (NullPointerException e){
            return "sign_in";
        }

    }

    @ResponseBody
    @RequestMapping(value = "/update_myorder",method = RequestMethod.POST)
    public Object company(@RequestBody MyOrder4 myOrder4) {
        MyOrder myOrder = new MyOrder();
        Shops shop = new Shops();
        shop.setId(Integer.valueOf(myOrder4.getShopsId()));
        myOrder.setId(myOrder4.getId());
        myOrder.setSum(myOrder4.getSum());
        myOrder.setShops(shop);
        Company company = new Company();
        company.setId(Integer.valueOf(myOrder4.getCompanyId()));
        myOrder.setCompany(company);
        myOrder.setStatus("accepted");
        myOrder.setDate(myOrder4.getDate());
        Courier courier = new Courier();
        courier.setId(Integer.valueOf(myOrder4.getCourierId()));
        myOrder.setCourier(courier);
        boolean result = myServiceClass.updateOrderById(myOrder);
        if(result){
            Success success = new Success();
            success.setResult(200);
            return success;
        }else{
            Success success = new Success();
            success.setResult(500);
            return success;

        }
    }
}
