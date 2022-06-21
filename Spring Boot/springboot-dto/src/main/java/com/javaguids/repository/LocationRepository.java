package com.javaguids.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaguids.model.Location;

@Repository

public interface LocationRepository extends JpaRepository<Location, Long>{

	public Location save(Location location);

}
