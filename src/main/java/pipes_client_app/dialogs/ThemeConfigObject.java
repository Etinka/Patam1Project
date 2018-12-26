package pipes_client_app.dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ThemeConfigObject implements NakedDropDownObject {
    private ObservableList<String> dropDownNames;
    private ThemeType selected = ThemeType.Glow;

    public ThemeConfigObject() {
        dropDownNames = FXCollections.observableArrayList(ThemeType.Glow.getName(), ThemeType.Dark.getName());
    }

    @Override
    public boolean setSelected(String selected) {
        ThemeType themeType = ThemeType.getTypeByName(selected);
        if (themeType.equals(this.selected)) {
            return false;
        }
        this.selected = themeType;
        return true;
    }

    @Override
    public String getSelected() {
        return this.selected.getName();
    }

    public ThemeType getSelectedTheme() {
        return selected;
    }

    @Override
    public ObservableList<String> getDropDownNames() {
        return dropDownNames;
    }


}
