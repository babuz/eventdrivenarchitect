package cqrs.bank.account.common.events;


import cqrs.bank.cqrs.core.events.BaseEvent;

public class FundWithdrawEvent extends BaseEvent {
    private double amount;

    protected FundWithdrawEvent(FundWithdrawEventBuilder<?, ?> b) {
        super(b);
        this.amount = b.amount;
    }

    public static FundWithdrawEventBuilder<?, ?> builder() {
        return new FundWithdrawEventBuilderImpl();
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString() {
        return "FundWithdrawEvent(amount=" + this.getAmount() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FundWithdrawEvent)) return false;
        final FundWithdrawEvent other = (FundWithdrawEvent) o;
        if (!other.canEqual((Object) this)) return false;
        if (Double.compare(this.getAmount(), other.getAmount()) != 0) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FundWithdrawEvent;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $amount = Double.doubleToLongBits(this.getAmount());
        result = result * PRIME + (int) ($amount >>> 32 ^ $amount);
        return result;
    }

    public static abstract class FundWithdrawEventBuilder<C extends FundWithdrawEvent, B extends FundWithdrawEventBuilder<C, B>> extends BaseEventBuilder<C, B> {
        private double amount;

        public B amount(double amount) {
            this.amount = amount;
            return self();
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "FundWithdrawEvent.FundWithdrawEventBuilder(super=" + super.toString() + ", amount=" + this.amount + ")";
        }
    }

    private static final class FundWithdrawEventBuilderImpl extends FundWithdrawEventBuilder<FundWithdrawEvent, FundWithdrawEventBuilderImpl> {
        private FundWithdrawEventBuilderImpl() {
        }

        protected FundWithdrawEventBuilderImpl self() {
            return this;
        }

        public FundWithdrawEvent build() {
            return new FundWithdrawEvent(this);
        }
    }
}
