package com.amplify.honeydew.actions;

import android.widget.TextView;
import com.android.uiautomator.core.*;
import com.amplify.honeydew.Result;

import java.util.List;
import java.util.Map;

public abstract class InspectOptionInSettingsMenu extends SelectMenuInSettings {
    private Boolean enabled;

    public InspectOptionInSettingsMenu(UiDevice uiDevice, boolean enabled) {
        super(uiDevice);
        this.enabled = enabled;
    }

    @Override
    public Result execute(Map<String, Object> arguments) throws UiObjectNotFoundException {
        super.execute(arguments);
        List<String> optionNames = (List<String>) arguments.get("optionNames");
        UiScrollable optionsMenu = new UiScrollable(new UiSelector().className("android.widget.ListView").packageName("com.android.settings").focused(false));

        for (String optionName : optionNames) {
            UiObject option = optionsMenu.getChildByText(new UiSelector().className(TextView.class.getName()), optionName);
            if (!enabled.equals(option.isEnabled())) {
                return Result.FAILURE;
            }
        }
        return Result.OK;
    }
}
