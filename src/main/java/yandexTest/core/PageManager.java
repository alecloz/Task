package yandexTest.core;

import org.reflections.Reflections;
import yandexTest.annotation.PageTitle;
import yandexTest.pages.BasePage;

import java.util.Set;

public class PageManager {
    private static BasePage currentPage;
    private static Set<Class<? extends BasePage>> allpages;

    static {
        Reflections reflections = new Reflections("yandexTest");
        allpages = reflections.getSubTypesOf(BasePage.class);
    }


    public static void setupPage(String title) {
        currentPage = findPageByTitle(title);
    }

    public static BasePage getCurrentPage() {
        return currentPage;
    }

    private static BasePage findPageByTitle(String title) {
        Class<? extends BasePage> pageClass = allpages.stream()
                .filter(page -> page.getAnnotation(PageTitle.class) != null)
                .filter(page -> page.getAnnotation(PageTitle.class).value().equals(title))
                .findAny().orElseThrow(() -> new RuntimeException("Не найдена страница с названием: " + title));
        BasePage foundPage = null;
        try {
            foundPage = pageClass.getConstructor().newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundPage;
    }
}
