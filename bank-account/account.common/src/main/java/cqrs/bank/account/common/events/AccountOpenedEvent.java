package cqrs.bank.account.common.events;

import cqrs.bank.account.common.dto.AccountType;
import cqrs.bank.cqrs.core.events.BaseEvent;

import java.util.Date;

public class AccountOpenedEvent extends BaseEvent {
    private String accountHolder;
    private AccountType accountType;
    private Date createdDate;
    private double openingBalance;

    protected AccountOpenedEvent(AccountOpenedEventBuilder<?, ?> b) {
        super(b);
        this.accountHolder = b.accountHolder;
        this.accountType = b.accountType;
        this.createdDate = b.createdDate;
        this.openingBalance = b.openingBalance;
    }

    public static AccountOpenedEventBuilder<?, ?> builder() {
        return new AccountOpenedEventBuilderImpl();
    }

    public String getAccountHolder() {
        return this.accountHolder;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public double getOpeningBalance() {
        return this.openingBalance;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String toString() {
        return "AccountOpenedEvent(accountHolder=" + this.getAccountHolder() + ", accountType=" + this.getAccountType() + ", createdDate=" + this.getCreatedDate() + ", openingBalance=" + this.getOpeningBalance() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AccountOpenedEvent)) return false;
        final AccountOpenedEvent other = (AccountOpenedEvent) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$accountHolder = this.getAccountHolder();
        final Object other$accountHolder = other.getAccountHolder();
        if (this$accountHolder == null ? other$accountHolder != null : !this$accountHolder.equals(other$accountHolder))
            return false;
        final Object this$accountType = this.getAccountType();
        final Object other$accountType = other.getAccountType();
        if (this$accountType == null ? other$accountType != null : !this$accountType.equals(other$accountType))
            return false;
        final Object this$createdDate = this.getCreatedDate();
        final Object other$createdDate = other.getCreatedDate();
        if (this$createdDate == null ? other$createdDate != null : !this$createdDate.equals(other$createdDate))
            return false;
        if (Double.compare(this.getOpeningBalance(), other.getOpeningBalance()) != 0) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AccountOpenedEvent;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $accountHolder = this.getAccountHolder();
        result = result * PRIME + ($accountHolder == null ? 43 : $accountHolder.hashCode());
        final Object $accountType = this.getAccountType();
        result = result * PRIME + ($accountType == null ? 43 : $accountType.hashCode());
        final Object $createdDate = this.getCreatedDate();
        result = result * PRIME + ($createdDate == null ? 43 : $createdDate.hashCode());
        final long $openingBalance = Double.doubleToLongBits(this.getOpeningBalance());
        result = result * PRIME + (int) ($openingBalance >>> 32 ^ $openingBalance);
        return result;
    }

    public static abstract class AccountOpenedEventBuilder<C extends AccountOpenedEvent, B extends AccountOpenedEventBuilder<C, B>> extends BaseEventBuilder<C, B> {
        private String accountHolder;
        private AccountType accountType;
        private Date createdDate;
        private double openingBalance;

        public B accountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
            return self();
        }

        public B accountType(AccountType accountType) {
            this.accountType = accountType;
            return self();
        }

        public B createdDate(Date createdDate) {
            this.createdDate = createdDate;
            return self();
        }

        public B openingBalance(double openingBalance) {
            this.openingBalance = openingBalance;
            return self();
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "AccountOpenedEvent.AccountOpenedEventBuilder(super=" + super.toString() + ", accountHolder=" + this.accountHolder + ", accountType=" + this.accountType + ", createdDate=" + this.createdDate + ", openingBalance=" + this.openingBalance + ")";
        }
    }

    private static final class AccountOpenedEventBuilderImpl extends AccountOpenedEventBuilder<AccountOpenedEvent, AccountOpenedEventBuilderImpl> {
        private AccountOpenedEventBuilderImpl() {
        }

        protected AccountOpenedEventBuilderImpl self() {
            return this;
        }

        public AccountOpenedEvent build() {
            return new AccountOpenedEvent(this);
        }
    }
}
