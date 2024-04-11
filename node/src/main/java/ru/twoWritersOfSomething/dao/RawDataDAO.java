package ru.twoWritersOfSomething.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.twoWritersOfSomething.entities.RawData;

@Repository
public interface RawDataDAO extends JpaRepository<RawData, Long> {


}
