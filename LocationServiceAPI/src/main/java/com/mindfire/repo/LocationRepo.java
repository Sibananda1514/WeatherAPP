package com.mindfire.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindfire.entities.LocationEnitity;

@Repository
public interface LocationRepo extends JpaRepository<LocationEnitity, Serializable> {

	@Query(value = "SELECT MAX(POSITION) FROM T_LOCATIONS", nativeQuery = true)
	public Integer findMaxLocPos();

	public List<LocationEnitity> findByisActive(String isActive);

	public List<LocationEnitity> findBylocationName(String locationName);

	@Procedure(procedureName = "P_UPDATE_LOCATION", outputParameterName = "LOCATION_UPDATE")
	public Integer updateLocationPostion(@Param("PREV_INDEX") Integer prevIndex,
			@Param("CURRENT_INDEX") Integer currentIndex);

}
