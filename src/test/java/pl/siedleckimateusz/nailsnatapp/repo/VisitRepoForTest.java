package pl.siedleckimateusz.nailsnatapp.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.siedleckimateusz.nailsnatapp.entity.TreatmentEntity;
import pl.siedleckimateusz.nailsnatapp.entity.VisitEntity;
import pl.siedleckimateusz.nailsnatapp.repository.VisitRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VisitRepoForTest implements VisitRepo {

    /*

    Created only one treatment with time visit 45 minutes

    Created few visits:
        every day of week from tomorrow 17:30-18:15 and 19:00-19:45

     */

    List<VisitEntity> list = new ArrayList<>();

    private final static LocalDate DATE_NOW = LocalDate.now();

    private Long id = 1L;

    private static volatile VisitRepoForTest visitRepoForTest;


    private VisitRepoForTest() {
        setupList();
    }

    public static VisitRepoForTest getInstance(){
        if (visitRepoForTest==null){
            visitRepoForTest = new VisitRepoForTest();
        }

        return visitRepoForTest;
    }

    public VisitEntity save(VisitEntity entity){
        entity.setId(id++);
        list.add(entity);

        return entity;
    }

    public List<VisitEntity> findAllByDateOfVisitBetween(LocalDate start, LocalDate end){
        return list.stream()
                .filter(e->(e.getDateOfVisit().isAfter(start)&&e.getDateOfVisit().isBefore(end)))
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitEntity> findAll(){
        return list;
    }

    @Override
    public List<VisitEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<VisitEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<VisitEntity> findAllById(Iterable<Long> iterable) {
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
    public void delete(VisitEntity entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends VisitEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends VisitEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<VisitEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends VisitEntity> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<VisitEntity> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public VisitEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends VisitEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends VisitEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends VisitEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends VisitEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends VisitEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends VisitEntity> boolean exists(Example<S> example) {
        return false;
    }

    public List<VisitEntity> findAllByUserId(Long id){
        return list.stream()
                .filter(e->e.getUser().getId().equals(id))
                .collect(Collectors.toList());
    }

    private void setupList() {
        LocalDate dateTomorrow = DATE_NOW.plusDays(1);

        while (dateTomorrow.isBefore(LocalDate.of(2030,1,1))){

            save(VisitEntity.builder()
                    .startTime(LocalTime.of(17,30))
                    .treatment(getTreatment())
                    .dateOfVisit(dateTomorrow)
                    .build());

            save(VisitEntity.builder()
                    .startTime(LocalTime.of(19,0))
                    .treatment(getTreatment())
                    .dateOfVisit(dateTomorrow)
                    .build());

            dateTomorrow = dateTomorrow.plusWeeks(1);
        }
    }


    private TreatmentEntity getTreatment(){
        return TreatmentEntity.builder()
                .time(45)
                .build();
    }
}
