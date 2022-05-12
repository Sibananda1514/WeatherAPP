package com.mindfire.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_locations")
public class LocationEnitity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "location_name")
	private String locationName;

	@Column(name = "is_active")
	private String isActive;

	@Column(name = "position")
	private Integer position;

	@Column(name = "created_on", updatable = false)
	@Temporal(TemporalType.TIME)
	@CreationTimestamp
	private Date createdOn;

	@Column(name = "last_updated_on", insertable = false)
	@Temporal(TemporalType.DATE)
	@UpdateTimestamp
	private Date lastUpdatedOn;
}
