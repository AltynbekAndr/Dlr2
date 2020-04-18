package com.springapp.mvc.controllers;

import com.springapp.mvc.model_for_users.Company;
import com.springapp.mvc.model_for_users.MyOrder;
import com.springapp.mvc.model_for_users.MyOrderToHistory;
/*import org.dom4j.DocumentException;*/
import com.springapp.mvc.model_for_users.Products;
import com.springapp.mvc.models2.Company2;
import com.springapp.mvc.service.MyServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/*import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelPDFController {

    @Autowired
    MyServiceClass myServiceClass;

    @RequestMapping(value = "/pdf", method= RequestMethod.GET)
    public ModelAndView pdf(@RequestParam String fDate,@RequestParam String sDate,HttpServletRequest httpServletRequest) {
        if(fDate!=null&&fDate.length()!=0&&!fDate.equals("")){
            if(sDate!=null&&sDate.length()!=0&&!sDate.equals("")){
                HttpSession httpSession = httpServletRequest.getSession();
                Company2 company2 = (Company2) httpSession.getAttribute("company");
                Company company = new Company();
                company.setId(company2.getId());
                System.out.println(fDate+" : "+sDate);
                List<MyOrder> myOrders = myServiceClass.ordersList(fDate,sDate,company);
                for(int i=0;i<myOrders.size();i++){
                    MyOrder order = myOrders.get(i);
                    List<Products> products = myServiceClass.getOrderById(order.getId(), company2.getId());
                    order.setShops(products.get(0).getMyorder().getShops());
                    order.setProductses(products);
                }
                return new ModelAndView("pdfDocument", "modelObject", myOrders);
            }else{
                return null;
            }
        }else{
            return null;
        }



    }


    @RequestMapping(value = "/excel", method= RequestMethod.GET)
    public ModelAndView excel() {
        System.out.println("ExcelPDFController excel is called");

        //List<MyOrderToHistory> myOrderToHistories = createCats();
        List<MyOrderToHistory> myOrderToHistories = null;
        //excelDocument - look excel-pdf-config.xml
        return new ModelAndView("excelDocument", "modelObject", myOrderToHistories);

    }

}