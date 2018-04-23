package app.retake.services.impl;

import app.retake.domain.dto.*;
import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import app.retake.domain.models.Vet;
import app.retake.parser.ValidationUtil;
import app.retake.repositories.AnimalAidRepository;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.ProcedureRepository;
import app.retake.repositories.VetRepository;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private final VetRepository vetRepository;
    private final ProcedureRepository procedureRepository;
    private final AnimalRepository animalRepository;
    private final AnimalAidRepository animalAidRepository;

    @Autowired
    public ProcedureServiceImpl(VetRepository vetRepository, ProcedureRepository procedureRepository,
                                AnimalRepository animalRepository, AnimalAidRepository animalAidRepository) {
        this.vetRepository = vetRepository;
        this.procedureRepository = procedureRepository;
        this.animalRepository = animalRepository;
        this.animalAidRepository = animalAidRepository;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {
        if (! ValidationUtil.isValid(dto)) {
            throw new IllegalArgumentException();
        }
        Procedure procedure = new Procedure();
        procedure.setDate(dto.getDate() == null ? null : new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDate()));
        Vet vet = this.vetRepository.findByName(dto.getVet());
        if (vet == null) {
            throw new IllegalArgumentException();
        }

        Animal animal = this.animalRepository.findAll()
                .stream()
                .filter(x -> x.getPassport().getSerialNumber().equals(dto.getAnimal()))
                .findFirst()
                .orElse(null);

        if (animal == null) {
            throw new IllegalArgumentException();
        }

        for (ProcedureAnimalAidXMLImportDTO animalAidDto : dto.getAnimalAids()) {
            AnimalAid animalAid = this.animalAidRepository.findByName(animalAidDto.getName());
            if (animalAid == null) {
                throw new IllegalArgumentException();
            }
            procedure.getServices().add(animalAid);
            animalAid.getProcedures().add(procedure);
        }
        procedure.setAnimal(animal);
        procedure.setVet(vet);

        this.procedureRepository.saveAndFlush(procedure);
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        ProcedureWrapperXMLExportDTO procedureWrapper = new ProcedureWrapperXMLExportDTO();
        List<Procedure> procedures = this.procedureRepository.findAll();
        for (Procedure procedure : procedures) {
            ProcedureXMLExportDTO procedureDto = new ProcedureXMLExportDTO();
            procedureDto.setAnimalPassport(procedure.getAnimal().getName());
            procedureDto.setOwner(procedure.getAnimal().getPassport().getOwnerName());
            procedureDto.setDate(procedure.getDate() == null ? null : procedure.getDate().toString());
            for (AnimalAid animalAid : procedure.getServices()) {
                ProcedureAnimalAidXMLExportDTO animalAidDto = new ProcedureAnimalAidXMLExportDTO();
                animalAidDto.setName(animalAid.getName());
                animalAidDto.setPrice(animalAid.getPrice());
                procedureDto.getAnimalAids().add(animalAidDto);
            }
            procedureWrapper.getProcedures().add(procedureDto);
        }

        return procedureWrapper;
    }
}

