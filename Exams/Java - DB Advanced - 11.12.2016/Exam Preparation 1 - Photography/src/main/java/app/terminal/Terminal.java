package app.terminal;

import app.model.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.text.ParseException;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private static final String INPUT_LENSES_FILE_JSON = "/input/lenses.json";
    private static final String INPUT_CAMERAS_FILE_JSON = "/input/cameras.json";
    private static final String INPUT_PHOTOGRAPHERS_FILE_JSON = "/input/photographers.json";
    private static final String INPUT_ACCESSORIES_FILE_XML = "src/main/resources/input/accessories.xml";
    private static final String INPUT_WORKSHOPS_FILE_XML = "src/main/resources/input/workshops.xml";
    private static final String OUTPUT_ORDERED_CUSTOMERS_JSON = "src/main/resources/output/ordered_customers.json";
    private static final String OUTPUT_LANDSCAPE_PHOTOGRAPHERS_JSON = "src/main/resources/output/landscape_photographers.json";
    private static final String OUTPUT_SAME_CAMERA_PHOTOGRAPHERS_XML = "output/same_camera_photographers.xml";
    private static final String OUTPUT_WORKSHOPS_BY_LOCATION_XML = "output/workshops_by_location.xml";

    private final LenService lenService;
    private final BasicCameraService basicCameraService;
    private final PhotographerService photographerService;
    private final AccessoryService accessoryService;
    private final WorkshopService workshopService;

    @Autowired
    public Terminal(LenService lenService, BasicCameraService basicCameraService,
                    PhotographerService photographerService, AccessoryService accessoryService,
                    WorkshopService workshopService) {
        this.lenService = lenService;
        this.basicCameraService = basicCameraService;
        this.photographerService = photographerService;
        this.accessoryService = accessoryService;
        this.workshopService = workshopService;
    }

    @Override
    public void run(String... strings) throws Exception {
//        seedDatabase();
//        this.photographerService.orderedCustomers(OUTPUT_ORDERED_CUSTOMERS_JSON);
//        this.photographerService.landScapePhotographers(OUTPUT_LANDSCAPE_PHOTOGRAPHERS_JSON);
//        this.photographerService.sameCamerasPhotographers(OUTPUT_SAME_CAMERA_PHOTOGRAPHERS_XML);
        this.workshopService.workshopsByLocations(OUTPUT_WORKSHOPS_BY_LOCATION_XML);
    }

    private void seedDatabase() throws JAXBException, ParseException {
        this.lenService.importLensesToTheDatabase(INPUT_LENSES_FILE_JSON);
        this.basicCameraService.importCamerasInTheDatabase(INPUT_CAMERAS_FILE_JSON);
        this.photographerService.importPhotographersToTheDatabase(INPUT_PHOTOGRAPHERS_FILE_JSON);
        this.accessoryService.importAccessoriesToTheDatabase(INPUT_ACCESSORIES_FILE_XML);
        this.workshopService.importWorkshopsInTheDatabase(INPUT_WORKSHOPS_FILE_XML);
    }
}
