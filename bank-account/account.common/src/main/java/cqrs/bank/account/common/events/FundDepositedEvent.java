package cqrs.bank.account.common.events;

import cqrs.bank.cqrs.core.events.BaseEvent;

public class FundDepositedEvent extends BaseEvent {
    private double amount;

    protected FundDepositedEvent(FundDepositedEventBuilder<?, ?> b) {
        super(b);
        this.amount = b.amount;
    }

    public static FundDepositedEventBuilder<?, ?> builder() {
        return new FundDepositedEventBuilderImpl();
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString() {
        return "FundDepositedEvent(amount=" + this.getAmount() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FundDepositedEvent)) return false;
        final FundDepositedEvent other = (FundDepositedEvent) o;
        if (!other.canEqual((Object) this)) return false;
        if (Double.compare(this.getAmount(), other.getAmount()) != 0) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FundDepositedEvent;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $amount = Double.doubleToLongBits(this.getAmount());
        result = result * PRIME + (int) ($amount >>> 32 ^ $amount);
        return result;
    }

    public static abstract class FundDepositedEventBuilder<C extends FundDepositedEvent, B extends FundDepositedEventBuilder<C, B>> extends BaseEventBuilder<C, B> {
        private double amount;

        public B amount(double amount) {
            this.amount = amount;
            return self();
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "FundDepositedEvent.FundDepositedEventBuilder(super=" + super.toString() + ", amount=" + this.amount + ")";
        }
    }

    private static final class FundDepositedEventBuilderImpl extends FundDepositedEventBuilder<FundDepositedEvent, FundDepositedEventBuilderImpl> {
        private FundDepositedEventBuilderImpl() {
        }

        protected FundDepositedEventBuilderImpl self() {
            return this;
        }

        public FundDepositedEvent build() {
            return new FundDepositedEvent(this);
        }
    }
}
