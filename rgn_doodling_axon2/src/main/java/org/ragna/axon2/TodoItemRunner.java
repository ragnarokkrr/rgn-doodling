package org.ragna.axon2;

import java.io.File;
import java.util.UUID;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;

public class TodoItemRunner {

	public static void main(String[] args) {
		CommandBus commandBus = new SimpleCommandBus();

		CommandGateway commandGateway = new DefaultCommandGateway(commandBus);

		EventStore eventStore = new FileSystemEventStore(
				new SimpleEventFileResolver(new File("./events")));
		EventBus eventBus = new SimpleEventBus();

		EventSourcingRepository<TodoItem> repository = new EventSourcingRepository<TodoItem>(
				TodoItem.class, eventStore);
		repository.setEventBus(eventBus);

		AggregateAnnotationCommandHandler.subscribe(TodoItem.class, repository,
				commandBus);

		final String itemId = UUID.randomUUID().toString();

		commandGateway.send(new CreateToDoItemCommand(itemId, "Need  to see this"));
		
		commandGateway.send(new MarkCompletedCommand(itemId));

	
		final String itemId2 = UUID.randomUUID().toString();

		commandGateway.send(new CreateToDoItemCommand(itemId2, "Need  to see this"));
		
		commandGateway.send(new MarkCompletedCommand(itemId2));
	
	}

}
