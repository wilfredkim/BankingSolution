package Pojo;

public class Loan {
    private  String cutomerId,reasonForLoan;
    private  String accNumber;
    double amountRequest;
    LoanType loanType;
    private  String status;

    public String getStatus() {
        return status;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCutomerId() {
        return cutomerId;
    }

    public void setCutomerId(String cutomerId) {
        this.cutomerId = cutomerId;
    }

    public String getReasonForLoan() {
        return reasonForLoan;
    }

    public void setReasonForLoan(String reasonForLoan) {
        this.reasonForLoan = reasonForLoan;
    }

    public double getAmountRequest() {
        return amountRequest;
    }

    public void setAmountRequest(double amountRequest) {
        this.amountRequest = amountRequest;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
}
