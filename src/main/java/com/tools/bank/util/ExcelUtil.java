package com.tools.bank.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tools.bank.BankStatement;
import com.tools.bank.TallyStatement;

public class ExcelUtil {

    public static List<TallyStatement> readTallyExcelFile(String excelFilePath) {

        List<TallyStatement> tallyStatements = new ArrayList<TallyStatement>();
        try (FileInputStream fis = new FileInputStream(excelFilePath);

                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {

                if (row.getRowNum() == 0) {
                    continue;
                }

                Date date;
                try {
                    date = getDateCellValue(row.getCell(0));
                } catch (ParseException e) {
                    continue;
                }
                String particulars = row.getCell(1).getStringCellValue();
                String vchType = row.getCell(2).getStringCellValue();
                String vchNo = row.getCell(3).getStringCellValue();
                double debit = getNumericCellValue(row.getCell(4));
                double credit = getNumericCellValue(row.getCell(5));
                TallyStatement tallyStatement = new TallyStatement(date, particulars, vchType, vchNo, debit, credit);

                tallyStatements.add(tallyStatement);

                // System.out.println(tallyStatement);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println(tallyStatements.size() + " records read from Tally file");
        return tallyStatements;
    }

    public static List<BankStatement> readBankExcelFile(String excelFilePath) {

        List<BankStatement> bankStatements = new ArrayList<BankStatement>();
        try (FileInputStream fis = new FileInputStream(excelFilePath);

                Workbook workbook = new HSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {

                if (row.getRowNum() == 0) {
                    continue;
                }

                Date transactionDate;
                try {
                    transactionDate = getDateCellValue(row.getCell(0));
                } catch (ParseException e) {
                    continue;
                }

                double withdrawal = getNumericCellValue(row.getCell(2));
                double deposit = getNumericCellValue(row.getCell(3));
                double balance = getNumericCellValue(row.getCell(4));
                String narration = row.getCell(5).getStringCellValue();
                String chequeNumber = ""; // row.getCell(1).getStringCellValue();
                BankStatement bankStatement = new BankStatement(transactionDate, chequeNumber, withdrawal, deposit,
                        balance, narration);

                bankStatements.add(bankStatement);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bankStatements.size() + " records read from Bank file");
        return bankStatements;
    }

    public static Date getDateCellValue(Cell cell) throws ParseException {
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        } else {
            String dateStr = cell.getStringCellValue();
            // Assuming the date format is "dd-MM-yyyy"
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(dateStr);
        }
    }

    public static double getNumericCellValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else {
            String cellValue = cell.getStringCellValue();
            if (cellValue.contains("Cr") || cellValue.contains("Dr")) {
                cellValue = cellValue.split(" ")[0];
            }
            return cellValue.isEmpty() ? 0 : Double.parseDouble(cellValue.replace(",", ""));
        }
    }
}
