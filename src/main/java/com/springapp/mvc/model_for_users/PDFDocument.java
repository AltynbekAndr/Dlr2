package com.springapp.mvc.model_for_users;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PDFDocument extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(
            Map<String, Object> model,
            Document document,
            PdfWriter writer,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {




        PdfPTable table = new PdfPTable(2);
        Resource  resource = getApplicationContext().getResource("/resources/assets/fonts/ariblk.ttf");
        Resource  resource2 = getApplicationContext().getResource("/resources/assets/fonts/cour.ttf");
        String res  = resource.getFile().getAbsolutePath();
        String res2  = resource2.getFile().getAbsolutePath();
        Font font1 = FontFactory.getFont(res, "Cp1251", BaseFont.EMBEDDED, 10);
        Font font = FontFactory.getFont(res, "Cp1251", BaseFont.EMBEDDED, 10);
        Font font2 = FontFactory.getFont(res2,"Cp1251",BaseFont.EMBEDDED,10);
        font1.setColor(Color.BLACK);
        font.setColor(Color.GRAY);
        PdfPCell date = new PdfPCell(new Phrase("Дата",font));
        PdfPCell sum = new PdfPCell(new Phrase("Общая сумма заказа(сом)",font));


        date.setHorizontalAlignment(Element.ALIGN_LEFT);
        sum.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(date);
        table.addCell(sum);

        PdfPCell productCell = new PdfPCell(new Phrase("Продукты",font));
        productCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        List<MyOrder> myOrders = (List<MyOrder>) model.get("modelObject");
        PdfPCell productName = new PdfPCell(new Phrase("Название",font));
        PdfPCell productPrice = new PdfPCell(new Phrase("Цена",font));
        PdfPCell productWeight = new PdfPCell(new Phrase("Вес/Литр",font));
        PdfPCell productAmount = new PdfPCell(new Phrase("Кол.ство",font));
        PdfPCell productReturnAmount = new PdfPCell(new Phrase("Возврат",font));
        PdfPCell productIsDebt = new PdfPCell(new Phrase("В долг",font));


        for (int i=0;i<myOrders.size();i++) {
            MyOrder order = myOrders.get(i);
            PdfPCell orderNumberCell = new PdfPCell(new Phrase("Заказ № "+order.getId(),font1));
            PdfPTable orderNumberTable = new PdfPTable(1);
            orderNumberCell.setFixedHeight(25f);
            orderNumberCell.setVerticalAlignment(Element.ALIGN_CENTER);
            orderNumberTable.addCell(orderNumberCell);
            orderNumberTable.setSpacingBefore(25f);
            document.add(orderNumberTable);
            document.add(table);
            PdfPTable table1 = new PdfPTable(2);
            table1.addCell(new Phrase(order.getDate(), font2));
            table1.addCell(new Phrase(order.getSum(), font2));

            document.add(table1);

            PdfPTable table2 = new PdfPTable(6);
            table2.addCell(productName);
            table2.addCell(productPrice);
            table2.addCell(productWeight);
            table2.addCell(productAmount);
            table2.addCell(productReturnAmount);
            table2.addCell(productIsDebt);


            PdfPTable title = new PdfPTable(1);
            PdfPCell productsTitleCell = new PdfPCell(new Phrase("Продукты", font));
            productsTitleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            title.addCell(productsTitleCell);
            document.add(title);
            document.add(table2);
            List<Products> products = order.getProductses();
            for (int k = 0; k < products.size(); k++) {
                PdfPTable table3 = new PdfPTable(6);
                Products products1 = products.get(k);

                PdfPCell productNameValue = new PdfPCell(new Phrase(products1.getName(), font2));
                PdfPCell productPriceValue = new PdfPCell(new Phrase(products1.getPrice(), font2));
                PdfPCell productWeightValue = new PdfPCell(new Phrase(products1.getWeight(), font2));
                PdfPCell productAmountValue = new PdfPCell(new Phrase(products1.getAmount(), font2));
                PdfPCell productReturnAmountValue = new PdfPCell(new Phrase(products1.getReturnAmount(), font2));
                PdfPCell productIsDebtValue = null;
                if (products1.isConsignation()) {
                    productIsDebtValue = new PdfPCell(new Phrase("Да", font2));
                } else {
                    productIsDebtValue = new PdfPCell(new Phrase("Нет", font2));
                }

                table3.addCell(productNameValue);
                table3.addCell(productPriceValue);
                table3.addCell(productWeightValue);
                table3.addCell(productAmountValue);
                table3.addCell(productReturnAmountValue);
                table3.addCell(productIsDebtValue);

                document.add(table3);

            }

            PdfPTable courierTable = new PdfPTable(1);
            PdfPCell courierCell = new PdfPCell(new Phrase("Курьер", font));
            courierCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            courierTable.addCell(courierCell);
            document.add(courierTable);

            PdfPTable courierContentTable = new PdfPTable(3);
            PdfPTable courierContentTable2 = new PdfPTable(3);

            Courier courier = order.getCourier();
            PdfPCell courierHeaderName = new PdfPCell(new Phrase("Ф.И.О", font));
            PdfPCell courierHeaderAddress = new PdfPCell(new Phrase("Адрес", font));
            PdfPCell courierHeaderL_name = new PdfPCell(new Phrase("Телефон", font));
            courierContentTable.addCell(courierHeaderName);
            courierContentTable.addCell(courierHeaderAddress);
            courierContentTable.addCell(courierHeaderL_name);
            document.add(courierContentTable);
            try {
                PdfPCell courierName = new PdfPCell(new Phrase(courier.getName() + " " + courier.getL_name(), font2));
                PdfPCell courierAddress = new PdfPCell(new Phrase(courier.getAddress(), font2));
                PdfPCell courierL_name = new PdfPCell(new Phrase(courier.getPhone(), font2));
                courierContentTable2.addCell(courierName);
                courierContentTable2.addCell(courierAddress);
                courierContentTable2.addCell(courierL_name);
                document.add(courierContentTable2);
            } catch (NullPointerException npe) {
                PdfPCell courieName = new PdfPCell(new Phrase("null", font2));
                PdfPCell courierAddress = new PdfPCell(new Phrase("null", font2));
                PdfPCell courierL_name = new PdfPCell(new Phrase("null", font2));
                courierContentTable2.addCell(courieName);
                courierContentTable2.addCell(courierAddress);
                courierContentTable2.addCell(courierL_name);
                document.add(courierContentTable2);
            }


            PdfPTable shopTable = new PdfPTable(1);
            PdfPCell shopCell = new PdfPCell(new Phrase("Магазин", font));
            shopCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            shopTable.addCell(shopCell);
            document.add(shopTable);

            PdfPTable shopContentTable = new PdfPTable(3);
            PdfPTable shopContentTable2 = new PdfPTable(3);

            Shops shops = order.getShops();
            PdfPCell shopHeaderName = new PdfPCell(new Phrase("Название", font));
            PdfPCell shopHeaderAddress = new PdfPCell(new Phrase("Адрес", font));
            PdfPCell shopHeaderPhone = new PdfPCell(new Phrase("Телефон", font));
            shopContentTable.addCell(shopHeaderName);
            shopContentTable.addCell(shopHeaderAddress);
            shopContentTable.addCell(shopHeaderPhone);
            document.add(shopContentTable);

            PdfPCell shopName = new PdfPCell(new Phrase(shops.getName(), font2));
            PdfPCell shopAddress = new PdfPCell(new Phrase(shops.getAddress(), font2));
            PdfPCell shopPhone = new PdfPCell(new Phrase(shops.getPhone(), font2));
            shopContentTable2.addCell(shopName);
            shopContentTable2.addCell(shopAddress);
            shopContentTable2.addCell(shopPhone);
            document.add(shopContentTable2);


        }

    }


}
