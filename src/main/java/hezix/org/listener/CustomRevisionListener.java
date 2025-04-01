package hezix.org.listener;

import hezix.org.entity.Revision;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
        ((Revision) o).setUsername("user");
    }
}
