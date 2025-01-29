package cqrs.bank.account.common.events;

import cqrs.bank.cqrs.core.events.BaseEvent;

public class AccountClosedEvent extends BaseEvent {
    protected AccountClosedEvent(AccountClosedEventBuilder<?, ?> b) {
        super(b);
    }

    public static AccountClosedEventBuilder<?, ?> builder() {
        return new AccountClosedEventBuilderImpl();
    }

    public String toString() {
        return "AccountClosedEvent()";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AccountClosedEvent)) return false;
        final AccountClosedEvent other = (AccountClosedEvent) o;
        if (!other.canEqual((Object) this)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AccountClosedEvent;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }

    public static abstract class AccountClosedEventBuilder<C extends AccountClosedEvent, B extends AccountClosedEventBuilder<C, B>> extends BaseEventBuilder<C, B> {
        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "AccountClosedEvent.AccountClosedEventBuilder(super=" + super.toString() + ")";
        }
    }

    private static final class AccountClosedEventBuilderImpl extends AccountClosedEventBuilder<AccountClosedEvent, AccountClosedEventBuilderImpl> {
        private AccountClosedEventBuilderImpl() {
        }

        protected AccountClosedEventBuilderImpl self() {
            return this;
        }

        public AccountClosedEvent build() {
            return new AccountClosedEvent(this);
        }
    }
}
