/*http://dgimenes.com/blog/2014/03/06/javafx-formatting-data-in-tableview.html*/

package llibreries;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class FormattedDateValueFactory<EntityType> implements Callback<TableColumn.CellDataFeatures<EntityType, String>, ObservableValue<String>> {
    private String getterName;
    private SimpleDateFormat formatter;

    /**
     *  Convert String to date
     * @param fieldName fieldname
     * @param dateFormatPattern data format
     */
    public FormattedDateValueFactory(String fieldName, String dateFormatPattern) {
        this.getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
        this.formatter = new SimpleDateFormat(dateFormatPattern);
    }

    /**
     * These classes work as the PropertyValueFactory, but allow easy formatting, accepting a format string on the constructor.
     * @param features
     * @return ObservableValue<String>
     */
    public ObservableValue<String> call(CellDataFeatures<EntityType, String> features) {
        try {
            EntityType entity = features.getValue();
            Method m = entity.getClass().getMethod(getterName);
            Date date = (Date) m.invoke(entity);
            String formattedDate = formatter.format(date);
            return new SimpleObservableValue<String>(formattedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
