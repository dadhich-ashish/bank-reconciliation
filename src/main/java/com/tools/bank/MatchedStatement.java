
package com.tools.bank;

public class MatchedStatement {

    private BankStatement bankStatement;

    private TallyStatement tallyStatement;

    public MatchedStatement(BankStatement bankStatement, TallyStatement tallyStatement) {

        this.bankStatement = bankStatement;

        this.tallyStatement = tallyStatement;

    }

    public BankStatement getBankStatement() {

        return bankStatement;

    }

    public void setBankStatement(BankStatement bankStatement) {

        this.bankStatement = bankStatement;

    }

    public TallyStatement getTallyStatement() {

        return tallyStatement;

    }

    public void setTallyStatement(TallyStatement tallyStatement) {

        this.tallyStatement = tallyStatement;

    }

    @Override

    public String toString() {

        return " Matched # " + bankStatement.getTransactionDate() + " # " + tallyStatement.getDate()
                + " # " + bankStatement.getWithdrawal() + " # " + bankStatement.getDeposit() + " # "
                + tallyStatement.getDebit() + " # " + tallyStatement.getCredit() + " # "
                + tallyStatement.getParticulars() + " # " + bankStatement.getNarration();

    }

}
