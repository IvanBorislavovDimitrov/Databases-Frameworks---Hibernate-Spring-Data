package com.masdefect.servicesImp;

import com.masdefect.domain.dto.json.AnomalyExportJSONDto;
import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.json.AnomalyVictimsJSONDto;
import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.dto.xml.AnomaliesXMLDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.domain.dto.xml.VictimXMLDto;
import com.masdefect.domain.entities.Anomaly;
import com.masdefect.domain.entities.Person;
import com.masdefect.domain.entities.Planet;
import com.masdefect.parser.ValidationUtil;
import com.masdefect.repository.AnomalyRepository;
import com.masdefect.repository.PersonRepository;
import com.masdefect.repository.PlanetRepository;
import com.masdefect.service.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AnomalyServiceImp implements AnomalyService {

    private AnomalyRepository anomalyRepository;
    private PlanetRepository planetRepository;
    private PersonRepository personRepository;

    @Autowired
    public AnomalyServiceImp(AnomalyRepository anomalyRepository, PlanetRepository planetRepository, PersonRepository personRepository) {
        this.anomalyRepository = anomalyRepository;
        this.planetRepository = planetRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void create(AnomalyImportJSONDto anomalyImpotJSONDto) {
        if (!ValidationUtil.isValid(anomalyImpotJSONDto)) {
            throw new IllegalArgumentException();
        }

        Planet originPlanet = this.planetRepository.findByName(anomalyImpotJSONDto.getOriginPlanet());
        Planet teleportPlanet = this.planetRepository.findByName(anomalyImpotJSONDto.getTeleportPlanet());

        if (originPlanet == null || teleportPlanet == null) {
            throw new IllegalArgumentException();
        }

        Anomaly anomaly = new Anomaly();
        anomaly.setOriginPlanet(originPlanet);
        anomaly.setTeleportPlanet(teleportPlanet);

        this.anomalyRepository.saveAndFlush(anomaly);
    }

    @Override
    public void create(AnomalyVictimsJSONDto anomalyVictimsDto) {
        if (! ValidationUtil.isValid(anomalyVictimsDto)) {
            throw new IllegalArgumentException();
        }

        Anomaly anomaly = this.anomalyRepository.getOne(anomalyVictimsDto.getAnomalyId());
        if (anomaly == null) {
            throw new IllegalArgumentException();
        }
        Person person = this.personRepository.findByName(anomalyVictimsDto.getPerson());
        if (person == null) {
            throw new IllegalArgumentException();
        }

        anomaly.getPeople().add(person);
        this.anomalyRepository.saveAndFlush(anomaly);
    }

    @Override
    public void create(AnomalyXMLDto anomalyImportXMLDto) {
        if (! ValidationUtil.isValid(anomalyImportXMLDto)) {
            throw new IllegalArgumentException();
        }

        Planet originPlanet = this.planetRepository.findByName(anomalyImportXMLDto.getOriginPlanet());
        Planet teleportPlanet = this.planetRepository.findByName(anomalyImportXMLDto.getTeleportPlanet());
        if (originPlanet == null || teleportPlanet == null) {
            throw new IllegalArgumentException();
        }

        Anomaly anomaly = new Anomaly();
        anomaly.setTeleportPlanet(teleportPlanet);
        anomaly.setOriginPlanet(originPlanet);
        for (VictimXMLDto victimXMLDto : anomalyImportXMLDto.getVictims()) {
            Person person = this.personRepository.findByName(victimXMLDto.getVictim());
            if (person == null) {
                continue;
            }

            anomaly.getPeople().add(person);
        }

        this.anomalyRepository.saveAndFlush(anomaly);
    }

    @Override
    public AnomalyExportJSONDto findMostAffectingAnomalies() {
        List<Anomaly> anomalies = this.anomalyRepository.findAll();
        int maxx = 0;
        Anomaly a = anomalies.get(0);
        for (Anomaly anomaly : anomalies) {
            if (anomaly.getPeople().size() > maxx) {
                maxx = anomaly.getPeople().size();
                a = anomaly;
            }
        }
        AnomalyExportJSONDto a1 = new AnomalyExportJSONDto();
        a1.setId(a.getId());
        String plan1 = a.getOriginPlanet().getName();
        String plan2 = a.getTeleportPlanet().getName();

        PlanetExportJSONDto planetExportJSONDto1 = new PlanetExportJSONDto();
        planetExportJSONDto1.setName(plan1);


        PlanetExportJSONDto planetExportJSONDto2 = new PlanetExportJSONDto();
        planetExportJSONDto2.setName(plan2);

        a1.setOriginPlanet(planetExportJSONDto1);
        a1.setTeleoprtPlanet(planetExportJSONDto2);
        return a1;
    }

    @Override
    public AnomaliesXMLDto finaAllAnomalies() {
        AnomaliesXMLDto anomaliesXMLDto = new AnomaliesXMLDto();
        List<Anomaly> anomalies = this.anomalyRepository.findAll();
        anomalies.sort((x1, x2) -> x1.getId().compareTo(x2.getId()));
        for (Anomaly anomaly : anomalies) {
            AnomalyXMLDto anomalyXMLDto = new AnomalyXMLDto();
            anomalyXMLDto.setOriginPlanet(anomaly.getOriginPlanet().getName());
            anomalyXMLDto.setTeleportPlanet(anomaly.getTeleportPlanet().getName());
            anomaliesXMLDto.getAnomalies().add(anomalyXMLDto);
        }

        return anomaliesXMLDto;
    }
}
