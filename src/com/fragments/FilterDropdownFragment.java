package com.fragments;

import com.web.CustomElement;
import org.openqa.selenium.By;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class FilterDropdownFragment { //образ для всех выпадающих списков на всех страницах

    private final CustomElement ELEMENTS_INSIDE_DROPDOWN_CONTAINER = new CustomElement("ELEMENTS_INSIDE_DROPDOWN_CONTAINER", By.xpath("//li"));

    private final CustomElement filterElement;
    private final boolean multipleSelectionAllowed;

    public FilterDropdownFragment(CustomElement filterElement, boolean multipleSelectionAllowed) {
        this.filterElement = filterElement;
        this.multipleSelectionAllowed = multipleSelectionAllowed;
    }

    public void selectFilterOption(String nameOfOption) {
        selectFilterOptions(Collections.singletonList(nameOfOption));
    }

    public void selectFilterOptions(List<String> nameOfOptions) {
        if (nameOfOptions.size() > 1 && !multipleSelectionAllowed)
            throw new RuntimeException("This filter does not accept multiple selection.");
        openCloseFilter();
        nameOfOptions.forEach(option -> selectOneOptionInOpenedFilter(ELEMENTS_INSIDE_DROPDOWN_CONTAINER, option));
        if (multipleSelectionAllowed) openCloseFilter();
    }

    private void openCloseFilter() {
        filterElement.click();
    }

    private void selectOneOptionInOpenedFilter(CustomElement filterOptionContainer, String option) {
        try {
            filterElement.getWebElementsListWithinCurrent(filterOptionContainer).stream()
                    .filter(webElement -> webElement.getText().equals(option))
                    .findFirst().get()
                    .click();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Select item with name '" + option + "' was not found in select.");
        }
    }
}
