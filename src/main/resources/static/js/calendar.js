/* create an array of days which need to be disabled */

var disabledDays = /*[[${daysOff}]]*/ [];
console.log(disabledDays);
var minDateTab = /*[[${startDate}]]*/ [];
var minDate = new Date(minDateTab[0], minDateTab[1] - 1, minDateTab[2])
console.log(minDate);
var maxDateTab = /*[[${endDate}]]*/ [];
var maxDate = new Date(maxDateTab[0], maxDateTab[1] - 1, maxDateTab[2])
console.log(maxDate);

/* utility functions */
function nationalDays(date) {
    var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
    console.log('Checking (raw): ' + m + '-' + d + '-' + y);
    for (let i = 0; i < disabledDays.length; i++) {
        console.log('compare: ' + (m + 1) + '-' + d + '-' + y + ' with ' + disabledDays)

        let result = $.inArray((m + 1) + '-' + d + '-' + y, disabledDays);

        if (result != -1 || new Date() > date) {
            console.log('bad:  ' + (m + 1) + '-' + d + '-' + y + ' / ' + disabledDays[result]);
            return [false];
        }
    }
    console.log('good:  ' + (m + 1) + '-' + d + '-' + y);
    return [true];
}

/* create datepicker */
jQuery(document).ready(function () {
    jQuery('#date').datepicker({
        minDate: minDate,
        maxDate: maxDate,
        altField: '#inputDate',
        dateFormat: 'dd-mm-yy',
        constrainInput: true,
        beforeShowDay: nationalDays,
        closeText: "Zamknij",
        prevText: "&#x3C;Poprzedni",
        nextText: "Następny&#x3E;",
        currentText: "Dziś",
        monthNames: ["Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec",
            "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"],
        monthNamesShort: ["Sty", "Lu", "Mar", "Kw", "Maj", "Cze",
            "Lip", "Sie", "Wrz", "Pa", "Lis", "Gru"],
        dayNames: ["Niedziela", "Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota"],
        dayNamesShort: ["Nie", "Pn", "Wt", "Śr", "Czw", "Pt", "So"],
        dayNamesMin: ["N", "Pn", "Wt", "Śr", "Cz", "Pt", "So"],
        weekHeader: "Tydz",
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ""
    })
    ;
});