package itmo.java.data.make;

import itmo.java.IO.Printor;
import itmo.java.IO.Scannie;
import itmo.java.data.Color;
import itmo.java.data.Person;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Locale;

public class MakePerson {
    private final Person director;
    private final boolean isConsole;

    public MakePerson(boolean isConsole) {
        this.director = new Person();
        this.isConsole = isConsole;
    }

    public Person make(Scannie scannie, Printor printor) throws Exception {
        makeName(scannie, printor);
        makeBirthday(scannie, printor);
        makeHeight(scannie, printor);
        makePassportID(scannie, printor);
        makeEyeColour(scannie, printor);
        return director;
    }

    private void makeBirthday(Scannie scannie, Printor printor) throws Exception {
        if (isConsole) {
            printor.println("Введите день рождения режиссера в формате ZonedDateTime(загуглите пример (например 2007-12-03T10:15:30+01:00[Europe/Paris])): ");
            String birthday = scannie.scanString();
            if (birthday.isEmpty()) {
                director.setBirthday(null);
            }
            try {
                director.setBirthday(ZonedDateTime.parse(birthday));
            } catch (Exception e) {
                printor.println("Что-то не так: " + e.getMessage());
                makeBirthday(scannie, printor);
            }
        } else {
            String birthday = scannie.scanString();
            if (birthday.isEmpty()) {
                director.setBirthday(null);
            }
            director.setBirthday(ZonedDateTime.parse(birthday));
        }
    }

    private void makeName(Scannie scannie, Printor printor) throws Exception {
        if (isConsole) {
            printor.println("Введите имя режиссера: ");
            String name = scannie.scanString();
            try {
                director.setName(name);
            } catch (Exception e) {
                printor.println("Что-то не так: " + e.getMessage());
                printor.println("Имя должно быть словом, например John Smith");
                makeName(scannie, printor);
            }
        } else {
            String name = scannie.scanString();
            director.setName(name);
        }

    }

    private void makeHeight(Scannie scannie, Printor printor) throws Exception {
        if (isConsole) {
            printor.println("Введите рост режиссера: ");
            double height = Double.parseDouble(scannie.scanString());
            try {
                director.setHeight(height);
            } catch (Exception e) {
                printor.println("Что-то не так: " + e.getMessage());
                printor.println("Рост должен быть числом в промежутке (0;1.7e+308]");
                makeHeight(scannie, printor);
            }
        } else {
            String height = scannie.scanString();
            director.setHeight(Double.parseDouble(height));
        }
    }

    private void makePassportID(Scannie scannie, Printor printor) throws Exception {
        if (isConsole) {
            printor.println("Введите id пасспорта: ");
            String passportid = scannie.scanString();
            try {
                director.setPassportID(passportid);
            } catch (Exception e) {
                printor.println("Что-то не так: " + e.getMessage());
                makePassportID(scannie, printor);
            }
        } else {
            String passportid = scannie.scanString();
            director.setPassportID(passportid);
        }
    }

    private void makeEyeColour(Scannie scannie, Printor printor) throws Exception {
        if (isConsole) {
            printor.println("Введите цвет глаз: " + Arrays.toString(Color.values()));
            String eyecolour = scannie.scanString();
            try {
                director.setEyeColor(Color.valueOf(eyecolour.trim().toUpperCase(Locale.ROOT)));
            } catch (Exception e) {
                printor.println("Что-то не так: " + e.getMessage());
                printor.println("Цвет глаз должен соответствовать одному из значений списка: " + Arrays.toString(Color.values()));
                makeEyeColour(scannie, printor);
            }
        } else {
            String eyecolour = scannie.scanString();
            director.setEyeColor(Color.valueOf(eyecolour.trim().toUpperCase(Locale.ROOT)));
        }
    }


}
