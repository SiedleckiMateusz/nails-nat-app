package pl.siedleckimateusz.nailsnatapp.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import pl.siedleckimateusz.nailsnatapp.entity.Repeat;
import pl.siedleckimateusz.nailsnatapp.entity.TimeOffEntity;
import pl.siedleckimateusz.nailsnatapp.repository.TimeOffRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TimeOffRepoForTest implements TimeOffRepo {

    /*
    Repo has three items on start
    Times Off:
     -all weekend is off
     -all days between 8-16
        till 01-01-2030
     */

    private final List<TimeOffEntity> list = new ArrayList<>();

    private static final LocalDate EXP_DATE = LocalDate.of(2030,1,1);

    private Long id = 1L;

    private static volatile TimeOffRepoForTest timeOffRepoForTest;

    private TimeOffRepoForTest() {
        setup();
    }

    public static TimeOffRepoForTest getInstance(){
        if (timeOffRepoForTest==null){
            timeOffRepoForTest = new TimeOffRepoForTest();
        }

        return timeOffRepoForTest;
    }

    private void setup() {
        save(TimeOffEntity.builder()
                .dayOfEvent(LocalDate.of(2020, 9, 1))
                .expirationDate(EXP_DATE)
                .startTime(LocalTime.of(8, 0))
                .endTime(LocalTime.of(16, 0))
                .repeat(Repeat.DAILY)
                .build());

        save(TimeOffEntity.builder()
                .dayOfEvent(LocalDate.of(2020,9,5))
                .expirationDate(EXP_DATE)
                .startTime(LocalTime.of(8,0))
                .endTime(LocalTime.of(21,0))
                .repeat(Repeat.WEEKLY)
                .build());

        save(TimeOffEntity.builder()
                .dayOfEvent(LocalDate.of(2020,9,6))
                .expirationDate(EXP_DATE)
                .startTime(LocalTime.of(8,0))
                .endTime(LocalTime.of(21,0))
                .repeat(Repeat.WEEKLY)
                .build());
    }

    public TimeOffEntity save(TimeOffEntity entity){
        entity.setId(id++);
        list.add(entity);

        return entity;
    }

    public List<TimeOffEntity> saveAll(List<TimeOffEntity> entities){
        entities.forEach(e->e.setId(id++));
        list.addAll(entities);

        return entities;
    }

    public List<TimeOffEntity> findAll(){
        return list;
    }

    @Override
    public List<TimeOffEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TimeOffEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<TimeOffEntity> findAllById(Iterable<Long> iterable) {
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
    public void delete(TimeOffEntity entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends TimeOffEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends TimeOffEntity> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<TimeOffEntity> findById(Long aLong) {
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
    public <S extends TimeOffEntity> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<TimeOffEntity> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public TimeOffEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends TimeOffEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TimeOffEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TimeOffEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TimeOffEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TimeOffEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TimeOffEntity> boolean exists(Example<S> example) {
        return false;
    }


}
