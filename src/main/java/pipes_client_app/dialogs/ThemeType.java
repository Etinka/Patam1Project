package pipes_client_app.dialogs;

public enum ThemeType {
    Glow, Dark;

    String getName() {
        switch (this) {
            case Dark:
                return "Dark";
            default:
                return "Glow";
        }
    }

    static ThemeType getTypeByName(String name) {
        if (name.equals(Dark.name())) {
            return Dark;
        }
        return Glow;
    }

    public String getRegularPipe() {
        switch (this) {
            case Dark:
                return "./resources/dark/TubeCorner-R02.png";
            default:
                return "./resources/glow/Tube-R01.png";
        }
    }

    public String getAnglePipe() {
        switch (this) {
            case Dark:
                return "./resources/dark/TubeCorner-R02.png";
            default:
                return "./resources/glow/TubeCorner-R01.png";
        }
    }

    public String getBackgroundImage() {
        switch (this) {
            case Dark:
                return "./resources/dark/Wall-R01.jpg";
            default:
                return "./resources/glow/Wall-R02.jpg";
        }
    }

    public String getEndImage() {
        switch (this) {
            case Dark:
                return "./resources/dark/End-R01A.png";
            default:
                return "./resources/glow/End-R01.png";
        }
    }

    public String getStartImage() {
        return "./resources/Start-R01.png";
    }

    public String getMusic() {
        switch (this) {
            case Dark:
                return "./resources/dark/Rocky theme song.mp3";
            default:
                return "./resources/glow/incredibles theme song.mp3";
        }
    }
}
