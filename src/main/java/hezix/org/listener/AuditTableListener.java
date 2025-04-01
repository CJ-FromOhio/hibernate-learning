package hezix.org.listener;

import hezix.org.entity.Audit;
import hezix.org.entity.Operation;
import org.hibernate.event.spi.*;

import java.io.Serializable;

public class AuditTableListener implements PreDeleteEventListener, PreInsertEventListener {
    @Override
    public boolean onPreDelete(PreDeleteEvent event) {
        auditEntity(event, Operation.DELETE);
        return false;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        auditEntity(event, Operation.INSERT);
        return false;
    }
    private void auditEntity(AbstractPreDatabaseOperationEvent event, Operation operation) {
        if(event.getEntity().getClass() != Audit.class) {
            var audit = Audit.builder()
                    .entityId((Serializable) event.getId())
                    .entityName(event.getPersister().getEntityName())
                    .operation(operation)
                    .build();
            event.getSession().save(audit);
        }
    }
}
