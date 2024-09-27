package com.tools.bank.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tools.bank.model.BankStatement;
import com.tools.bank.model.TallyStatement;

public class AppUtil {

    public static void populateDepositCountMap(List<BankStatement> bankStatements, Map<Double, Integer> depositCountMap) {
        for (BankStatement bankStatement : bankStatements) {
            double deposit = bankStatement.getDeposit();
            if (depositCountMap.get(deposit) != null) {
                depositCountMap.put(deposit, depositCountMap.get(deposit) + 1);
            } else {
                depositCountMap.put(deposit, 1);
            }
        }
    }

     public static void populateDebitCountMap(List<TallyStatement> tallyStatements, Map<Double, Integer> debitCountMap) {
        for (TallyStatement tallyStatement : tallyStatements) {
            double debit = tallyStatement.getDebit();
            if (debitCountMap.get(debit) != null) {
                debitCountMap.put(debit, debitCountMap.get(debit) + 1);
            } else {
                debitCountMap.put(debit, 1);
            }
        }
    }

    public static Date addDays(Date date, int i) {
        return new Date(date.getTime() + i * 24 * 60 * 60 * 1000);
    }
}
