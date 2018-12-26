package pipes_client_app.dialogs;

import javafx.collections.ObservableList;

public interface NakedDropDownObject {
    boolean setSelected(String selected);
    String getSelected();
    ObservableList<String> getDropDownNames();
}
