package pl.siedleckimateusz.nailsnatapp.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.repository.TreatmentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TreatmentRepoForTest implements TreatmentRepo {


    private List<TreatmentEntity> list = new ArrayList<>();

    private Long id = 1L;

    private static volatile TreatmentRepoForTest treatmentRepoForTest;


    private TreatmentRepoForTest() {
        if (treatmentRepoForTest!=null){
            throw new IllegalStateException("Cannot create new instance, please use getInstance method instead.");
        }else {
            setUp();
        }
    }

    public static TreatmentRepoForTest getInstance(){
        if (treatmentRepoForTest==null){
            treatmentRepoForTest = new TreatmentRepoForTest();
        }

        return treatmentRepoForTest;
    }

    public TreatmentEntity save(TreatmentEntity entity){
        entity.setId(id++);

        list.add(entity);

        return entity;
    }

    private void setUp() {
        save(TreatmentEntity.builder()
                .time(35)
                .name("One")
                .price(25)
                .build());

        save(TreatmentEntity.builder()
                .time(50)
                .name("Two")
                .price(45)
                .build());

        save(TreatmentEntity.builder()
                .time(90)
                .name("Three")
                .price(60)
                .build());

    }

    public List<TreatmentEntity> findAll(){
        return list;
    }

    @Override
    public List<TreatmentEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TreatmentEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<TreatmentEntity> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(TreatmentEntity entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends TreatmentEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends TreatmentEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends TreatmentEntity> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<TreatmentEntity> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public TreatmentEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends TreatmentEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TreatmentEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TreatmentEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TreatmentEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TreatmentEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TreatmentEntity> boolean exists(Example<S> example) {
        return false;
    }

    public Optional<TreatmentEntity> findById(Long id){
        return list.stream().filter(e->e.getId().equals(id)).findFirst();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
