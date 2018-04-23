package app.repositories;

import app.domain.dtos.json.exportDtos.EmployeeCardExportJSONDto;
import app.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Integer> {

    EmployeeCard findByNumber(String number);

    @Query("select new app.domain.dtos.json.exportDtos.EmployeeCardExportJSONDto(ec.number) from " +
            "EmployeeCard ec left join ec.employee e where e.id is null")
    List<EmployeeCardExportJSONDto> freeCards();
}
