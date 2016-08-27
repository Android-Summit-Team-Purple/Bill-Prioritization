package database;

/**
 * A bill with an id, account id, status, payee, nickname, creation date, payment date, recurring
 * date, upcoming payment date, and priority status.
 */
public class Bill {

    public long id;
    public long accountId;
    public String status;
    public String payee;
    public String nickname;
    public long creationDate;
    public String paymentDate;
    public long recurringDate;
    public long upcomingPaymentDate;
    public boolean isPriority;

    public Bill(long id, long accountId, String status, String payee, String nickname, long creationDate, String paymentDate, long recurringDate, long upcomingPaymentDate) {
        this.id = id;
        this.accountId = accountId;
        this.status = status;
        this.payee = payee;
        this.nickname = nickname;
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.recurringDate = recurringDate;
        this.upcomingPaymentDate = upcomingPaymentDate;
//        this.isPriority = calculatePriority();
    }

    public Bill(long accountId, String status, String payee, String nickname, long creationDate, String paymentDate, long recurringDate, long upcomingPaymentDate) {
        this.accountId = accountId;
        this.status = status;
        this.payee = payee;
        this.nickname = nickname;
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.recurringDate = recurringDate;
        this.upcomingPaymentDate = upcomingPaymentDate;
        this.isPriority = calculatePriority();
    }

    private boolean calculatePriority() {
        return true;
    }
}
