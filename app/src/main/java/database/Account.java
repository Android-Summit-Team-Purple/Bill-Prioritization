package database;

/**
 * A account with an id, customer id, type, nickname, and balance.
 */
public class Account {

    public long id;
    public long customerId;
    public String type;
    public String nickname;
    public float balance;

    public Account(long customerId, String type, String nickname, float balance) {
        this.customerId = customerId;
        this.type = type;
        this.nickname = nickname;
        this.balance = balance;
    }

    public Account(long id, long customerId, String type, String nickname, float balance) {
        this.id = id;
        this.customerId = customerId;
        this.type = type;
        this.nickname = nickname;
        this.balance = balance;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
