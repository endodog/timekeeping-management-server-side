package com.endodog.timekeeping_management.repositories.impl;

import com.endodog.timekeeping_management.constant.Sort;
import com.endodog.timekeeping_management.model.base.BaseDTO;
import com.endodog.timekeeping_management.model.base.BaseEntity;
import com.endodog.timekeeping_management.repositories.GenericRepository;
import com.endodog.timekeeping_management.utils.QueryHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class AbstractRepositoryImpl<T extends BaseEntity, P extends BaseDTO, ID extends Serializable> implements GenericRepository<T, P, ID> {

  @PersistenceContext
  private EntityManager entityManager;
  private final Class<T> persistenceClass;
  private final Class<P> dtoClass;

  public AbstractRepositoryImpl() {
    persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    dtoClass = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
  }

  protected Class<T> getPersistenceClass() {
    return this.persistenceClass;
  }

  protected Class<P> getDtoClass() {
    return this.dtoClass;
  }

  protected EntityManager getEntityManager() {
    return this.entityManager;
  }

  protected String getSimpleNameEntity() {
    return this.getPersistenceClass().getSimpleName();
  }

  protected void setListParameterQuery(Object obj, Query query) {
    Field[] fields = obj.getClass().getDeclaredFields();

    for (Field field : fields) {
      field.setAccessible(true);

      if (field.getAnnotations().length > 0) {
        try {
          if (field.get(obj) != null) {
            query.setParameter(field.getName(), field.get(obj));
          }
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    }

  }

  @Override
  public Page<P> findAll(Pageable pageable, Object example, Sort sort) {
    List<P> entities;
    long totalElements;
    String querySQL = QueryHelper.QueryFactory.findAll(this.getDtoClass(), this.getSimpleNameEntity(), example, sort);
    System.out.println(querySQL);

    try (EntityManager entityManager = this.getEntityManager()) {
      Query query = entityManager.createQuery(querySQL, this.getDtoClass())
              .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
              .setMaxResults(pageable.getPageSize());
      setListParameterQuery(example, query);

      entities = query.getResultList();
      totalElements = this.count(example);
    } catch (PersistenceException e) {
      throw e;
    }

    return new PageImpl<>(entities, pageable, totalElements);
  }

  @Override
  public List<P> findAll(Pageable pageable, Sort sort) {
    List<P> entities; // test null
    String querySQL = QueryHelper.QueryFactory.findAll(this.getDtoClass(), this.getSimpleNameEntity(), sort);

    try (EntityManager entityManager = this.getEntityManager()) {
      Query query = entityManager.createQuery(querySQL, this.getDtoClass())
              .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
              .setMaxResults(pageable.getPageSize());
      entities = query.getResultList();
    } catch (PersistenceException e) {
      throw e;
    }

    return entities;
  }

  @Override
  public List<P> findAllByIds(List<ID> ids) {
    List<P> entities;
    String querySQL = QueryHelper.QueryFactory.findAllByIds(this.getDtoClass(), this.getSimpleNameEntity());

    try (EntityManager entityManager = this.getEntityManager()) {
      Query query = entityManager.createQuery(querySQL, this.getDtoClass())
              .setParameter(1, ids);
      entities = query.getResultList();
    } catch (PersistenceException e) {
      throw e;
    }

    return entities;
  }

  @Override
  public Optional<P> findById(ID id) {
    P entity;
    String querySQL = QueryHelper.QueryFactory.findById(this.getDtoClass(), this.getSimpleNameEntity());

    try (EntityManager entityManager = this.getEntityManager()) {
      Query query = entityManager.createQuery(querySQL, this.getDtoClass())
              .setParameter(1, id);
      entity = (P) query.getSingleResult();
    } catch (NoResultException e) {
      return Optional.empty();
    } catch (PersistenceException e) {
      throw e;
    }

    return Optional.ofNullable(entity);

  }

  @Override
  public long count(Object example) {
    long countEntity;
    String querySQL = QueryHelper.QueryFactory.count(this.getSimpleNameEntity(), example != null ? example : null);

    try (EntityManager entityManager = this.getEntityManager()) {
      Query query = entityManager.createQuery(querySQL);
      setListParameterQuery(example, query);

      countEntity = (long) query.getSingleResult();
    } catch (PersistenceException e) {
      throw e;
    }

    return countEntity;
  }

  @Override
  public void save(T t) {
    try (EntityManager entityManager = this.getEntityManager()) {
      entityManager.persist(t);
    } catch (PersistenceException e) {
      throw e;
    }
  }

  @Override
  public void saveAll(List<T> entities) {
    try (EntityManager entityManager = this.getEntityManager()) {
      for (T entity : entities) {
        entityManager.persist(entity);
      }
    } catch (PersistenceException e) {
      throw e;
    }
  }

  @Override
  public T update(T t) {
    T entity;

    try (EntityManager entityManager = this.getEntityManager()) {
      entity = entityManager.merge(t);
    } catch (PersistenceException e) {
      throw e;
    }

    return entity;
  }

  @Override
  public void deleteByIds(List<ID> ids) {
    String querySQL = QueryHelper.QueryFactory.deleteByIds(this.getSimpleNameEntity());

    try (EntityManager entityManager = this.getEntityManager()) {
      entityManager.createQuery(querySQL, this.getDtoClass())
              .setParameter(1, ids);

    } catch (PersistenceException e) {
      throw e;
    }
  }

  @Override
  public void deleteById(ID id) {
    String querySQL = QueryHelper.QueryFactory.deleteById(this.getSimpleNameEntity());

    try (EntityManager entityManager = this.getEntityManager()) {
      entityManager.createQuery(querySQL, this.getDtoClass())
              .setParameter(1, id);

    } catch (PersistenceException e) {
      throw e;
    }
  }

  @Override
  public void deleteAll() {
    String querySQL = QueryHelper.QueryFactory.deleteAll(this.getSimpleNameEntity());

    try (EntityManager entityManager = this.getEntityManager()) {
      entityManager.createQuery(querySQL, this.getDtoClass());

    } catch (PersistenceException e) {
      throw e;
    }
  }

}
