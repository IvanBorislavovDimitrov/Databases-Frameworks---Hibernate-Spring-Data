package app.model.servicesImp;

import app.io.FileIO;
import app.model.dto.json.LenDto;
import app.model.entities.Len;
import app.model.repositiories.LenRepository;
import app.model.serializers.JsonSerializer;
import app.model.services.LenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LenServiceImp implements LenService {

    private final LenRepository lenRepository;
    private final FileIO fileIO;
    private final JsonSerializer jsonSerializer;
    private final ModelMapper mapper;

    @Autowired
    public LenServiceImp(LenRepository lenRepository, FileIO fileIO, JsonSerializer jsonSerializer) {
        this.lenRepository = lenRepository;
        this.fileIO = fileIO;
        this.jsonSerializer = jsonSerializer;
        this.mapper = new ModelMapper();
    }

    @Override
    public void importLensesToTheDatabase(String fileName) {
        String jsonFileContent  = this.fileIO.readFile(fileName);
        LenDto[] lenDtos = this.jsonSerializer.deserialize(LenDto[].class, jsonFileContent);
        for (LenDto lenDto : lenDtos) {
            Len len = this.mapper.map(lenDto, Len.class);
            this.lenRepository.save(len);
            System.out.println(String.format("Successfully imported %s %dmm f%.1f", lenDto.getMake(), lenDto.getFocalLength(),
                    lenDto.getMaxAperture()));
        }
    }
}
