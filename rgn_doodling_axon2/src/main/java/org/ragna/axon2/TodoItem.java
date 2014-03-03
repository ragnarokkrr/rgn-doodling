package org.ragna.axon2;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

@SuppressWarnings("rawtypes")
public class TodoItem extends AbstractAnnotatedAggregateRoot {
	
	@AggregateIdentifier
	private String id;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1645349190603198253L;

	public TodoItem() {
		super();
	}

	@CommandHandler
	public TodoItem(CreateToDoItemCommand command) {
		apply(new TodoItemCreatedEvent(command.getTodoId(),
				command.getDescription()));
	}
	
	@EventHandler
	public void on(TodoItemCreatedEvent event){
		this.id = event.getTodoId();
	}
	
	@CommandHandler
	public void markCompleted(MarkCompletedCommand command){
		apply(new TodoItemCompletedEvent(id));
	}
	
}
