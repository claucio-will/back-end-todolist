package br.com.claucio.dev.todolist.repositories;

import br.com.claucio.dev.todolist.domain.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {

    Task findByDescription(String description);

    @Override
    @Query(value = "SELECT t FROM Task t WHERE t.appUser.username = ?#{principal}")
    Page<Task> findAll(Pageable pageable);

    @Override
    @Query(value = "SELECT t FROM Task t WHERE t.id = ?1 AND t.appUser.username = ?#{principal}")
    Optional<Task> findById(Integer id);
}
