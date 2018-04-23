package app.service.imp;

import app.domain.dtos.json.exportDtos.EmployeeCardExportJSONDto;
import app.domain.dtos.json.importDtos.EmployeeCardJSONDto;
import app.domain.entities.EmployeeCard;
import app.repositories.EmployeeCardRepository;
import app.service.api.EmployeeCardService;
import app.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeCardServiceImp implements EmployeeCardService {

    private final EmployeeCardRepository employeeCardRepository;
    private final ModelMapper mapper;

    @Autowired
    public EmployeeCardServiceImp(EmployeeCardRepository employeeCardRepository) {
        this.employeeCardRepository = employeeCardRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void create(EmployeeCardJSONDto employeeCardDto) {
        if (!ValidatorUtil.isValid(employeeCardDto)) {
            throw new IllegalArgumentException();
        }

        List<String> employeeCardsNumbers = this.employeeCardRepository.findAll()
                .stream()
                .map(EmployeeCard::getNumber)
                .collect(Collectors.toList());

        if (employeeCardsNumbers.contains(employeeCardDto.getNumber())) {
            throw new IllegalArgumentException();
        }

        EmployeeCard employeeCard = this.mapper.map(employeeCardDto, EmployeeCard.class);
        this.employeeCardRepository.saveAndFlush(employeeCard);
    }

    @Override
    public EmployeeCard findByNumber(String number) {
        return this.employeeCardRepository.findByNumber(number);
    }

    @Override
    public List<EmployeeCardExportJSONDto> freeCards() {
        List<EmployeeCardExportJSONDto> unusedCards = this.employeeCardRepository.freeCards();
        return unusedCards;
    }
}
