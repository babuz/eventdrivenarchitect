package cqrs.bank.cqrs.core.domain;

import cqrs.bank.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
public class AggregateRoot {
    protected String id;
    private int version = -1;
    private final List<BaseEvent> changes = new ArrayList<>();

    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public List<BaseEvent> getUncommitedChanges() {
        return this.changes;
    }

    public void markChangesAreCompleted() {
        this.changes.clear();
    }

    protected void applyChange(BaseEvent event, Boolean isNewEvent) {
        try {
            var method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);

        } catch (NoSuchMethodException exception) {
            logger.log(Level.WARNING, MessageFormat.format("no such apply method in the object {0} {1}", event.getClass().getName(), exception.getMessage()));
        } catch (Exception e) {
            logger.log(Level.FINE, MessageFormat.format("unhandled exception received {0} ", e));
        } finally {
            if (isNewEvent) {
                this.changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        this.applyChange(event, true);
    }

    public void replyEvent(Iterable<BaseEvent> oldEvents) {
        oldEvents.forEach(event -> {
            applyChange(event, false);
        });
    }

}
