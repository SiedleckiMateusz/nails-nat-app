package pl.siedleckimateusz.nailsnatapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.siedleckimateusz.nailsnatapp.entity.model.UserToSession;
import pl.siedleckimateusz.nailsnatapp.entity.model.VisitForm;
import pl.siedleckimateusz.nailsnatapp.entity.model.VisitToShowForUser;
import pl.siedleckimateusz.nailsnatapp.entity.validator.VisitFormValidator;
import pl.siedleckimateusz.nailsnatapp.exception.VisitFormValidationException;
import pl.siedleckimateusz.nailsnatapp.service.TreatmentService;
import pl.siedleckimateusz.nailsnatapp.service.UserService;
import pl.siedleckimateusz.nailsnatapp.service.VisitService;
import pl.siedleckimateusz.nailsnatapp.time.DayWithAvailableHours;
import pl.siedleckimateusz.nailsnatapp.time.VisitTimeManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static pl.siedleckimateusz.nailsnatapp.exception.VisitErrorMessage.*;

@Slf4j
@Controller
@RequestMapping("/visit")
@SessionScope
public class VisitController {

    private final TreatmentService treatmentService;
    private final UserService userService;
    private final VisitTimeManager visitTimeManager;
    private final VisitService visitService;
    private final VisitForm visitForm;
    private final UserToSession sessionUser;
    private final VisitFormValidator visitFormValidator;

    private final static String ERROR_MESSAGE = "errorMessage";


    public VisitController(TreatmentService treatmentService, UserService userService, VisitTimeManager visitTimeManager,
                           VisitService visitService, VisitForm visitForm, UserToSession sessionUser, VisitFormValidator visitFormValidator) {
        this.treatmentService = treatmentService;
        this.userService = userService;
        this.visitTimeManager = visitTimeManager;
        this.visitService = visitService;
        this.visitForm = visitForm;
        this.sessionUser = sessionUser;
        this.visitFormValidator = visitFormValidator;
    }

    @GetMapping
    public String getAllVisit(Model model) {

        List<VisitToShowForUser> actualVisitForUser = visitService.getActualVisitWhereUserId(sessionUser.getId());
        List<VisitToShowForUser> historyVisitForUser = visitService.getHistoryVisitWhereUserId(sessionUser.getId());

        model.addAttribute("actual",actualVisitForUser);
        model.addAttribute("history",historyVisitForUser);

        return "visit/index";
    }

    @GetMapping("/new")
    public String newVisit() {
        if (visitForm.getUser() == null) {
            visitForm.setUser(userService.findLoggedUser());
            log.info("Znaleziono zalogowanego użytkowanika i dodano do visitForm: "+visitForm.getUser().getUsername());
            return "redirect:/visit/new";
        }
        if (visitForm.getTreatment() == null) {
            return "redirect:/visit/set-treatment";
        }
        if (visitForm.getDateOfVisit() == null) {
            return "redirect:/visit/set-date";
        }
        if (visitForm.getStartTime() == null) {
            return "redirect:/visit/set-time";
        }

        return "redirect:/visit/summary";
    }

    @GetMapping("/set-treatment")
    public String toTreatmentForm(Model model) {
        model.addAttribute("treatmentsList", treatmentService.findAllTreatmentDto());
        log.info("Idę do strony pick_treatment");
        return "visit/form/pick_treatment";
    }

    @PostMapping("/set-treatment")
    public String saveTreatment(@RequestParam Long treatmentId, RedirectAttributes redirectAttributes) {

        visitForm.setTreatment(null);

        try {
            visitForm.setTreatment(visitFormValidator.validateTreatment(treatmentId));
            log.info("Wizyta przeszła walidację i została ustawiona w visitForm: "+visitForm.getTreatment().getName());
        } catch (VisitFormValidationException e) {
            log.error("Wizyta nie przeszła walidacji: "+e.getMessage());
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE,e.getMessage());
            return "redirect:/visit/set-treatment";
        }
        return "redirect:/visit/new";
    }

    @GetMapping("/set-date")
    public String toDateForm(Model model) {
        if (visitForm.getTreatment()==null){
            return "redirect:/visit/new";
        }
        visitForm.setDateOfVisit(null);

        LocalDate start = LocalDate.now().plusDays(1);
        LocalDate end = LocalDate.now().plusMonths(2);

        log.info("Wybieram przedział czasowy dla kalendarza: "+start+" - "+end);

        List<LocalDate> daysOff = visitTimeManager.getDaysOff(start, end
                , visitForm.getTreatment().getTime() + visitForm.getUser().getExtraTime());

        List<String> daysOffList = daysOff.stream()
                .sorted()
                .map(this::formatToJs)
                .collect(Collectors.toList());

        String[] daysOffTab = daysOffList.toArray(String[]::new);

        log.info("Utworzyłem tablicę niedostępnych dat: "+Arrays.toString(daysOffTab));
        model.addAttribute("daysOff", daysOffTab);
        model.addAttribute("startDate", new int[]{start.getYear(), start.getMonthValue(), start.getDayOfMonth()});
        model.addAttribute("endDate", new int[]{end.getYear(), end.getMonthValue(), end.getDayOfMonth()});

        log.info("Idę do strony pick_date");

        return "visit/form/pick_date";
    }

    @PostMapping("/set-date")
    public String saveDate(@RequestParam String date, RedirectAttributes redirectAttributes) {

        try {
            visitForm.setDateOfVisit(visitFormValidator.validateDate(date,visitForm.getFullTimeOfVisit()));
            log.info("Data przeszła walidację i została ustawiona w visitForm");
        } catch (VisitFormValidationException e) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE,e.getMessage());
            log.error("Data nie przeszła walidacji: "+e.getMessage());
            return "redirect:/visit/set-date";
        }

        return "redirect:/visit/new";
    }

    @GetMapping("/set-time")
    public String toTimeForm(Model model) {
        if (visitForm.getTreatment()==null || visitForm.getDateOfVisit()==null){
            return "redirect:/visit/new";
        }

        visitForm.setStartTime(null);

        LocalDate dateOfVisit = visitForm.getDateOfVisit();

        List<DayWithAvailableHours> daysList = generateFiveDays(dateOfVisit
                , visitForm.getFullTimeOfVisit());

        model.addAttribute("daysList", daysList);
        model.addAttribute("today", dateOfVisit.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        daysList.forEach(System.out::println);

        return "visit/form/pick_time";
    }

    @PostMapping("/set-time")
    public String saveTimeAndDate(@RequestParam String dateTime, RedirectAttributes redirectAttributes) {

        if (!visitFormValidator.textIsOk(dateTime)) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, EMPTY_TIME.getMessage());
            return "redirect:/visit/set-time";
        }

        try {
            LocalDateTime localDateTime = visitFormValidator
                    .validateTime(dateTime, visitForm.getFullTimeOfVisit());
            visitForm.setDateOfVisit(LocalDate.from(localDateTime));
            visitForm.setStartTime(LocalTime.from(localDateTime));

        } catch (VisitFormValidationException e) {
            visitForm.setDateOfVisit(null);
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE,e.getMessage());
            return "redirect:/visit/new";
        }

        return "redirect:/visit/new";
    }

    @GetMapping("/summary")
    public String toSummary(Model model) {
        model.addAttribute("visitForm", visitForm);

        return "visit/form/summary";
    }

    @PostMapping("/summary")
    public String saveVisit(@RequestParam String moreInfo, RedirectAttributes redirectAttributes
            , Model model) {
        if (!visitTimeManager.isAvailableTimeForVisit(visitForm.getDateOfVisit()
                ,visitForm.getStartTime(),visitForm.getFullTimeOfVisit())){
            log.error("Podana data i godzina jest niedostępna: "+visitForm.getDateOfVisit()+" "+visitForm.getStartTime());
            if (!visitTimeManager.isAvailableDateForVisit(visitForm.getDateOfVisit(),visitForm.getFullTimeOfVisit())){
                redirectAttributes.addFlashAttribute(ERROR_MESSAGE,NOT_AVAILABLE_DATE_AND_TIME.getMessage());
                log.error("W podanej dacie nie ma już miejsca na wizytę: "+visitForm.getDateOfVisit());
                visitForm.setStartTime(null);
                return "redirect:/visit/set-time";
            }else {
                redirectAttributes.addFlashAttribute(ERROR_MESSAGE,NOT_AVAILABLE_DATE.getMessage());
                visitForm.setDateOfVisit(null);
                return "redirect:/visit/set-date";
            }
        }

        if (visitFormValidator.textIsOk(moreInfo)){
            visitForm.setMoreInfo(moreInfo);
        }

        if (visitService.save(visitForm)!=null){
            model.addAttribute("title","Udało się!");
            model.addAttribute("message","Twóje zlecenie wizyty zostało pomyślnie przesłane. W krótce dostaniesz potwierdzenie od pracownika");
            visitForm.clearVisit();
            return "/success";
        }else {
            return "redirect:/error";
        }


    }

    @GetMapping("/cancel")
    public String cancelVisit() {
        visitForm.clearVisit();

        return "redirect:/?cancelVisit";
    }

    private List<DayWithAvailableHours> generateFiveDays(LocalDate date, int visitTime) {
        List<DayWithAvailableHours> dayList = new ArrayList<>();
        int counter = 0;
        LocalDate actualDate = date.minusDays(2);

        while (counter < 5) {
            if (actualDate.isAfter(LocalDate.now())) {
                List<LocalTime> availableHoursForDate = visitTimeManager.getAvailableHoursForDate(actualDate, visitTime);
                dayList.add(new DayWithAvailableHours(actualDate, availableHoursForDate));
                counter++;
            }

            actualDate = actualDate.plusDays(1);
        }

        return dayList;

    }

    private String formatToJs(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("M-d-yyyy"));
    }



}
