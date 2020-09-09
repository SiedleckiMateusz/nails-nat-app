package pl.siedleckimateusz.nailsnatapp.entity.mapper;

public interface Mapper<NEW,ENTITY> {

    ENTITY toEntity(NEW newObj);
}
