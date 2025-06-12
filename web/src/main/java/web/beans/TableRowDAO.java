package web.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Named("tableRowDAO")
@ApplicationScoped
@NoArgsConstructor
public class TableRowDAO implements Serializable {
    
  private static final int LATEST_ATTEMPTS_COUNT = 10;
  EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myUnit");
  EntityManager entityManager = entityManagerFactory.createEntityManager();

  public List<TableRow> getAttemptsList() {
    return entityManager.createQuery("select attempt from TableRow attempt", TableRow.class).getResultList();
  }

  public List<TableRow> getLatestAttemptsList() {
    int attemptsCount = getAttemptsCount();
    int firstResultIndex = Math.max(attemptsCount - LATEST_ATTEMPTS_COUNT, 0);
    return  entityManager.createQuery("select attempt From TableRow attempt", TableRow.class)
      .setFirstResult(firstResultIndex).setMaxResults(LATEST_ATTEMPTS_COUNT).getResultList();
  }

  public void addAttempt(TableRow attempt) {
    entityManager.persist(attempt);
  }

  public int getAttemptsCount() {
    return entityManager.createQuery("select count(*) from TableRow", Number.class).getSingleResult().intValue();
  }

}
