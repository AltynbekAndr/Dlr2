package com.springapp.mvc.model_for_users;
/*import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;*/
public class ExcelDocument{
/*public class ExcelDocument extends AbstractExcelView {


    @Override
    protected void buildExcelDocument(
            Map<String, Object> model,
            HSSFWorkbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //New Excel sheet
        HSSFSheet excelSheet = workbook.createSheet("Simple excel example");
        //Excel file name change
        response.setHeader("Content-Disposition", "attachment; filename=excelDocument.xls");

        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);

        //Create Style for header
        CellStyle styleHeader = workbook.createCellStyle();
        styleHeader.setFillForegroundColor(HSSFColor.BLUE.index);
        styleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styleHeader.setFont(font);

        //Set excel header
        setExcelHeader(excelSheet, styleHeader);

        //Get data from model
        List<MyOrderToHistory> myOrderToHistories = (List<MyOrderToHistory>) model.get("modelObject");
        int rowCount = 1;
        for (MyOrderToHistory myOrderToHistory : myOrderToHistories) {
            HSSFRow row = excelSheet.createRow(rowCount++);
            row.createCell(0).setCellValue(myOrderToHistory.getName());
            row.createCell(1).setCellValue(myOrderToHistory.getWeight());
            row.createCell(2).setCellValue(myOrderToHistory.getColor());
        }

    }
    public void setExcelHeader(HSSFSheet excelSheet, CellStyle styleHeader) {
        //set Excel Header names
        HSSFRow header = excelSheet.createRow(0);
        header.createCell(0).setCellValue("Magaz");
        header.getCell(0).setCellStyle(styleHeader);
        header.createCell(1).setCellValue("Date");
        header.getCell(1).setCellStyle(styleHeader);
        header.createCell(2).setCellValue("Summa");
        header.getCell(2).setCellStyle(styleHeader);
    }*/
}
