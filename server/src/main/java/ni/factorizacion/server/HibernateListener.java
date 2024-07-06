package ni.factorizacion.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import ni.factorizacion.server.domain.dtos.output.EntrySimpleDto;
import ni.factorizacion.server.domain.entities.Entry;
import ni.factorizacion.server.services.impl.SseService;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateListener implements PostInsertEventListener {

    @Autowired
    private SseService sseService;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    private void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(this);
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        final Object entity = event.getEntity();
        if (entity instanceof Entry entry) {
            // Send TerminalType with "entry-added" event to all SSE clients
            sseService.sendEvent(entry.getTerminal().getType().toString(), "entry-added");

            EntrySimpleDto entryDto = EntrySimpleDto.from(entry);
            try {
                String objJackson = objectMapper.writeValueAsString(entryDto);
                System.out.println(objJackson);

                sseService.sendEvent(objJackson, "full-entry-added");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }
}
