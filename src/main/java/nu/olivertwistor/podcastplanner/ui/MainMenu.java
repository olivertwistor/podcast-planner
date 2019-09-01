package nu.olivertwistor.podcastplanner.ui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainMenu extends Menu
{
    public MainMenu()
    {
        super("Exit");
        this.menuOptions.put(1, "Add a new channel");
    }
}
