package com.tools.bank.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tools.bank.BankStatement;
import com.tools.bank.MatchedStatement;
import com.tools.bank.TallyStatement;

public class MatchUtil {

    public static List<Double> findMatchedEntries(Map<Double, Integer> depositCountMap,
            Map<Double, Integer> debitCountMap) {
        List<Double> depositDebitMatchedEntries = new ArrayList<>();

        for (Map.Entry<Double, Integer> entry : depositCountMap.entrySet()) {
            double amount = entry.getKey();
            int depositCount = entry.getValue();
            int debitCount = debitCountMap.getOrDefault(amount, 0);

            if (depositCount == debitCount) {
                depositDebitMatchedEntries.add(amount);
            }
        }

        return depositDebitMatchedEntries;
    }

    public static void findUnmatchedEntries(Map<Double, Integer> depositCountMap, Map<Double, Integer> debitCountMap,
            List<Double> unmatchedDeposits, List<Double> unmatchedDebits) {
        for (Map.Entry<Double, Integer> entry : depositCountMap.entrySet()) {
            double amount = entry.getKey();
            int depositCount = entry.getValue();
            int debitCount = debitCountMap.getOrDefault(amount, 0);

            if (depositCount != debitCount) {
                unmatchedDeposits.add(amount);
            }
        }

        for (Map.Entry<Double, Integer> entry : debitCountMap.entrySet()) {
            double amount = entry.getKey();
            int debitCount = entry.getValue();
            int depositCount = depositCountMap.getOrDefault(amount, 0);

            if (debitCount != depositCount) {
                unmatchedDebits.add(amount);
            }
        }
    }


    public static List<MatchedStatement> matchStatements(List<BankStatement> bankStatements,
            List<TallyStatement> tallyStatements) {
        List<MatchedStatement> matchedStatements = new ArrayList<>();

        for (TallyStatement tally : tallyStatements) {
            for (BankStatement bank : bankStatements) {
                String[] particularsWords = tally.getParticulars().split("\\s+");
                boolean matchFound = false;

                for (String word : particularsWords) {
                    if (bank.getNarration().toLowerCase().contains(word.toLowerCase())) {
                        matchFound = true;
                        break;
                    }
                }
                // tally.getDate().equals(bank.getTransactionDate()) &&
                // || (bank.getTransactionDate().after(tally.getDate()) &&
                // bank.getTransactionDate().before(addDays(tally.getDate(), 5)))

                if (tally.getDate().equals(bank.getTransactionDate())
                        && (tally.getDebit() == bank.getDeposit() || tally.getCredit() == bank.getWithdrawal()) &&
                        matchFound) {

                    matchedStatements.add(new MatchedStatement(bank, tally));
                }
            }
        }
        System.out.println(matchedStatements.size() + " records matched");
        return matchedStatements;
    }
}
