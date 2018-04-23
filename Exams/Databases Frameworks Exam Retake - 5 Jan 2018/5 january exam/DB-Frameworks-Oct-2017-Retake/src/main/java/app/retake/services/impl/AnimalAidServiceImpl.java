package app.retake.services.impl;


import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {

    private final AnimalAidRepository animalAidRepository;
    private final ModelParser modelParser;

    @Autowired
    public AnimalAidServiceImpl(AnimalAidRepository animalAidRepository, ModelParser modelParser) {
        this.animalAidRepository = animalAidRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {
        if (!ValidationUtil.isValid(dto)) {
            throw new IllegalArgumentException();
        }

        boolean doesAnimalAidExist = this.animalAidRepository.findAll()
                .stream()
                .anyMatch(ai -> ai.getPrice().equals(dto.getPrice()) && ai.getName().equals(dto.getName()));
        if (doesAnimalAidExist) {
            throw new IllegalArgumentException();
        }

        AnimalAid animalAid = this.animalAidRepository.findByName(dto.getName());
        if (animalAid != null) {
            animalAid.setPrice(dto.getPrice());
            this.animalAidRepository.saveAndFlush(animalAid);
            return;
        }

        animalAid = this.modelParser.convert(dto, AnimalAid.class);
        this.animalAidRepository.saveAndFlush(animalAid);
    }
}
