package app.domain.converter;

import app.domain.dto.PersonDto;
import app.domain.dto.PhoneNumberDto;
import app.domain.model.Person;
import app.domain.model.PhoneNumber;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DTOConvertUtil {

    public DTOConvertUtil() {
    }

    public static <S, D> D convert(S source, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(source, destinationClass);
    }

    public static <S, D> List<D> convert(Iterable<S> sourceIter, Class<D> destinationClass) {
        List<D> resultList = new ArrayList<>();
        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultList.add(d);
        }

        return resultList;
    }

    public static <S, D> Set<D> convertToSet(Iterable<S> sourceIter, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        Set<D> resultSet = new HashSet<>();
        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultSet.add(d);
        }

        return resultSet;
    }
}