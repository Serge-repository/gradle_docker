package com.web;

import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CustomElement extends RemoteWebElement {

    private String description;
    private By locator;

    private final Logger log = LogManager.getLogger(this);

    public CustomElement(String name, By locator) {
        this.locator = locator;
        this.description = name;
        setReadableElementName();
    }

    public WebElement getWebElement() {
        try {
            return ThucydidesWebDriverSupport.getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(description + " was not found by: " + locator.toString());
        }
    }

    public List<WebElement> getWebElementsListWithinCurrent(CustomElement elementWithin) {
        return getWebElement().findElements(elementWithin.locator);
    }

    @Override
    public WebElement findElement(By by) {
        return getWebElement().findElement(by);
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getWebElement().findElements(by);
    }

    @Override
    public void clear() {
        log("Going to clear " + description);
        getWebElement().clear();
        log("Cleared " + description);
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        clear();
        log("Going to fill " + description + " with " + Arrays.toString(keysToSend));
        getWebElement().sendKeys(keysToSend);
        log(description + " was filled with " + Arrays.toString(keysToSend));
    }

    @Override
    public String getText() {
        return getWebElement().getText();
    }

    @Override
    public void click() {
        log("Going to click " + description);
        getWebElement().click();
        log(description + " was clicked");
    }

    @Override
    public boolean isEnabled() {
        String angularDisabledProperty = getWebElement().getAttribute("ng-reflect-disabled");
        if (angularDisabledProperty != null) {
            return !angularDisabledProperty.equals("true");
        }
        return getWebElement().isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        try {
            return getWebElement().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public void submit() {
        log("Going to submit " + description);
        getWebElement().submit();
        log("Submitted " + description);
    }

    @Override
    public String getAttribute(String name) {
        return getWebElement().getAttribute(name);
    }

    private void log(String logText) {
        String classname = null;
        Optional<StackTraceElement> element = Arrays.stream(Thread.currentThread().getStackTrace())
                .filter(st -> (st.toString().contains("Page") || st.toString().contains("Block"))
                        && !st.toString().contains("Tests"))
                .findFirst();
        if (element.isPresent()) {
            classname = element.get().getClassName();
        }
        log.debug((classname == null ? "" : classname + " - ") + logText);
    }

    private void setReadableElementName() {
        try {
            String[] itemName = this.description.split("_");
            StringBuilder description = new StringBuilder("'");

            for (int i = 0; i < itemName.length - 1; i++) {
                String currentNamePart = itemName[i].toLowerCase();
                description.append(currentNamePart.substring(0, 1).toUpperCase()).append(currentNamePart.substring(1));
                description.append(" ");
            }

            this.description = WebItemTypes.valueOf(itemName[itemName.length - 1]).getItemDescription() + " " + description.toString().trim() + "'";
        } catch (IllegalArgumentException | IndexOutOfBoundsException ignored) {
        }
    }

    @Override
    public boolean isSelected() {
        return getWebElement().isSelected();
    }

    @Override
    public String getCssValue(String propertyName) {
        return getWebElement().getCssValue(propertyName);
    }

    enum WebItemTypes {
        BUTTON("Button"),
        LINK("Link"),
        INPUT("Input field"),
        CHECKBOX("Checkbox"),
        RADIOBUTTON("Radio button"),
        SELECT("Select"),
        CONTAINER("Container"),
        DATEPICKER("Datepicker"),
        TAB("Tab"),
        TEXT("Text");

        private String description;

        WebItemTypes(String itemDescription) {
            description = itemDescription;
        }

        public String getItemDescription() {
            return description;
        }
    }

    @Override
    public String toString() {
        return "CustomElement{" +
                "description='" + description + '\'' +
                ", locator=" + locator +
                '}';
    }
}
