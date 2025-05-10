package web.utils;
import jakarta.faces.context.FacesContext;
import jakarta.faces.component.UIComponent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
 
@FacesConverter("localDataTimeConverter")
public class LocalDataTimeConverter implements Converter<LocalDateTime>
{
    String pattern = "yyyy-MM-dd HH:mm:ss";
    @Override
    public LocalDateTime getAsObject(FacesContext context, UIComponent component, String value)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(value, formatter);
        return dateTime;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, LocalDateTime value) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formatDateTime = value.format(formatter);
        return formatDateTime;
    }
    
}
