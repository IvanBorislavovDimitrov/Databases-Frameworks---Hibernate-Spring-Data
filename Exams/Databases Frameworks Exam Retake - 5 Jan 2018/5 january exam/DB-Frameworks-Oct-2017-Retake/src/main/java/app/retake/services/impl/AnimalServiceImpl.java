package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final PassportRepository passportRepository;
    private final ModelParser modelParser;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, PassportRepository passportRepository, ModelParser modelParser) {
        this.animalRepository = animalRepository;
        this.passportRepository = passportRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) throws ParseException {
        if (!ValidationUtil.isValid(dto)) {
            throw new IllegalArgumentException();
        }

        Passport passport = this.modelParser.convert(dto.getPassport(), Passport.class);
        passport.setRegisterDate(new SimpleDateFormat("dd-MM-yyyy").parse(dto.getPassport().getDate()));
        if (this.passportRepository.findAll()
                .stream()
                .anyMatch(x -> x.getSerialNumber().equals(passport.getSerialNumber()))) {
            throw new IllegalArgumentException();
        }
        this.passportRepository.saveAndFlush(passport);
        if (this.animalRepository.findAll()
                .stream()
                .anyMatch(x -> x.getPassport().getSerialNumber().equals(dto.getPassport().getSerialNumber()))) {
            throw new IllegalArgumentException();
        }
        Animal animal = this.modelParser.convert(dto, Animal.class);
        animal.setPassport(passport);
        this.animalRepository.saveAndFlush(animal);
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        return
                this.animalRepository.findAll()
                .stream()
                .filter(x -> x.getPassport().getOwnerPhoneNumber().equals(phoneNumber))
                .map(x -> new AnimalsJSONExportDTO(
                      x.getPassport().getOwnerName(),
                        x.getName(),
                        x.getAge().toString(),
                        x.getPassport().getSerialNumber(),
                        x.getPassport().getRegisterDate()
                ))
                .sorted((x1, x2) -> {
                    Integer age1 = Integer.parseInt(x1.getAge());
                    Integer age2 = Integer.parseInt(x2.getAge());
                    if (age1.equals(age2)) {
                        return x1.getSerialNumber().compareTo(x2.getSerialNumber());
                    }

                    return age1.compareTo(age2);
                })
                .collect(Collectors.toList());
    }
}
