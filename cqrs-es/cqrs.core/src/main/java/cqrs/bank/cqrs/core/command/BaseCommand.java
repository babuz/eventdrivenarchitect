package cqrs.bank.cqrs.core.command;

import cqrs.bank.cqrs.core.messages.Message;

public class BaseCommand extends Message {
    protected BaseCommand(BaseCommandBuilder<?, ?> b) {
        super(b);
    }

    public static BaseCommandBuilder<?, ?> builder() {
        return new BaseCommandBuilderImpl();
    }

    public String toString() {
        return "BaseCommand()";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BaseCommand)) return false;
        final BaseCommand other = (BaseCommand) o;
        if (!other.canEqual((Object) this)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseCommand;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }

    public static abstract class BaseCommandBuilder<C extends BaseCommand, B extends BaseCommandBuilder<C, B>> extends MessageBuilder<C, B> {
        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "BaseCommand.BaseCommandBuilder(super=" + super.toString() + ")";
        }
    }

    private static final class BaseCommandBuilderImpl extends BaseCommandBuilder<BaseCommand, BaseCommandBuilderImpl> {
        private BaseCommandBuilderImpl() {
        }

        protected BaseCommandBuilderImpl self() {
            return this;
        }

        public BaseCommand build() {
            return new BaseCommand(this);
        }
    }
}
