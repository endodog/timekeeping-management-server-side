package com.endodog.timekeeping_management.repositories.base;

import com.endodog.timekeeping_management.model.dto.AttendanceDTO;
import com.endodog.timekeeping_management.model.entity.Attendance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepositoryBase extends BaseRepository<Attendance> {

  List<AttendanceDTO> findAttendancesByConstructionId(String id);

}