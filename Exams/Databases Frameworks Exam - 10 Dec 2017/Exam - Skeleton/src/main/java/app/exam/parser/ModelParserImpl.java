package app.exam.parser;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import app.exam.parser.interfaces.ModelParser;

@Component
public class ModelParserImpl implements ModelParser {

    private final ModelMapper mapper;

    public ModelParserImpl() {
        this.mapper = new ModelMapper();
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.mapper.map(source, destinationClass);
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        return null;
    }
}
