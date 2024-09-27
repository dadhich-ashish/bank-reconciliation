
package com.tools.bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import com.tools.bank.util.AppUtil;
import com.tools.bank.util.ExcelUtil;
import com.tools.bank.util.MatchUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class App {
    private static final String LOG_FILE_PATH = "application.log";

    public static void main(String[] args) throws ParseException {
        System.out.println("Hello! It's Bank Reconciliation!");
       

        // 833
        List<BankStatement> bankStatements = ExcelUtil
                .readBankExcelFile("files/833/0776XXXXXXX083321-09-2024 by bank.xls");
        List<TallyStatement> tallyStatements = ExcelUtil
                .readTallyExcelFile("files/833/statement-833-by-tally-23-24.xlsx");

        System.out.println("Bank Statements: " + bankStatements.size());
        System.out.println("Tally Statements: " + tallyStatements.size());

        Map<Double, Integer> depositCountMap = new HashMap<>();
        Map<Double, Integer> debitCountMap = new HashMap<>();

        AppUtil.populateDebitCountMap(tallyStatements, debitCountMap);
        AppUtil.populateDepositCountMap(bankStatements, depositCountMap);

        System.out.println("Deposit Count Map: " + depositCountMap.size());
        System.out.println("Debit Count Map: " + debitCountMap.size());

        List<Double> depositDebitMatchedEntries = MatchUtil.findMatchedEntries(depositCountMap, debitCountMap);

        System.out.println("Matched Entries: " + depositDebitMatchedEntries.size());
        log("Matched Entries Amount | Count |");
        for (Double amount : depositDebitMatchedEntries) {
            log("Matched Entries Amount: | " + amount + "| " + depositCountMap.get(amount));
        }

        List<Double> unmatchedDeposits = new ArrayList<>();
        List<Double> unmatchedDebits = new ArrayList<>();

        MatchUtil.findUnmatchedEntries(depositCountMap, debitCountMap, unmatchedDeposits, unmatchedDebits);

        System.out.println("=====================================");

        System.out.println("Unmatched Deposits: " + unmatchedDeposits.size());
        log("Unmatched Deposit Amount | Count |");
        for (Double amount : unmatchedDeposits) {
            log("Unmatched Deposit Amount: | " + amount + " | " + depositCountMap.get(amount));
        }

        System.out.println("Unmatched Debits: " + unmatchedDebits.size());
        log("Unmatched Debit Amount | Count |");
        for (Double amount : unmatchedDebits) {
            log("Unmatched Debit Amount: | " + amount + " | " + debitCountMap.get(amount));
        }

        List<MatchedStatement> matchedStatements = MatchUtil.matchStatements(bankStatements,
                tallyStatements);

        System.out.println("=====================================");
        logRecords(matchedStatements);
    }

    public static void logRecords(List<MatchedStatement> matchedStatements) {
        for (MatchedStatement matchedStatement : matchedStatements) {
            try (FileWriter fw = new FileWriter("matched_statements.log", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                out.println(matchedStatement);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void log(String message) {
        try (FileWriter fw = new FileWriter(LOG_FILE_PATH, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(new Date() + ": " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
