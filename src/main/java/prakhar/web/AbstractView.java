package prakhar.web;


import io.dropwizard.views.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class AbstractView extends View {
    public AbstractView(String templateName) {
        super(templateName);
    }
    private String dateFormat = "dd/MM/yyyy";

    public int indent(String number) {
        if (number == null)
            return 0;
        return number.split("\\.").length;
    }

    /**
     *
     * @param localDate
     * @return converts the given LocalDate into a string using "dd/MM/yyyy" format
     */
    public String toDDMMYYYY(LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return localDate.format(formatter);
    }
}

