package ru.basejava;

import ru.basejava.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static ru.basejava.model.ContactTypes.*;
import static ru.basejava.model.SectionTypes.*;

public class ResumeTestData {

    public static Resume getInstance(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        Map<SectionTypes, AbstractSection> sections = new EnumMap<>(SectionTypes.class);
        SimpleTextSection objective = new SimpleTextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n");

        SimpleTextSection personal = new SimpleTextSection("Аналитический склад ума, сильная логика, креативность инициативность. Пурист кода и архитектуры.\n");

        MarkedTextSection achievement = new MarkedTextSection(Arrays.asList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));

        MarkedTextSection qualification = new MarkedTextSection(Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2\n",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce\n",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,\n",
                "MySQL, SQLite, MS SQL, HSQLDB\n",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,\n",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,\n",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "Python: Django.\n",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js\n",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka\n",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix,\n",
                "Администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.\n",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования\n",
                "Родной русский, английский \"upper intermediate\"\n"));

        OrganizationSection experience = new OrganizationSection(
                new Link("Java Online Projects", "http://javaops.ru/"),
                new ArrayList<>(Collections.singletonList(
                        new Organization(
                                LocalDate.of(2013, Month.OCTOBER, 1),
                                null,
                                "Автор проекта.\n",
                                "Создание, организация и проведение Java онлайн проектов и стажировок.\n"
                        )
                )
                )
        );

        OrganizationSection experience1 = new OrganizationSection(
                new Link("Wrike", "https://www.wrike.com/"),
                new ArrayList<>(Collections.singletonList(
                        new Organization(
                                LocalDate.of(2014, Month.OCTOBER, 1),
                                LocalDate.of(2016, Month.JANUARY, 1),
                                "Старший разработчик (backend)\n",
                                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.\n"
                        )
                )
                )
        );

        OrganizationSection education = new OrganizationSection(
                new Link("Coursera", "https://www.coursera.org/course/progfun"),
                new ArrayList<>(Collections.singletonList(
                        new Organization(
                                LocalDate.of(2013, Month.MARCH, 1),
                                LocalDate.of(2013, Month.MAY, 1),
                                "Functional Programming Principles in Scala\" by Martin Odersky\n",
                                null
                        )

                )
                )
        );
        // TODO
        OrganizationSection education1 = new OrganizationSection(
                new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/"),
                new ArrayList<>(Arrays.asList(
                        new Organization(
                                LocalDate.of(1993, Month.SEPTEMBER, 1),
                                LocalDate.of(1996, Month.JULY, 1),
                                "Аспирантура (программист С, С++)\n",
                                null
                        ),
                        new Organization(
                                LocalDate.of(1987, Month.SEPTEMBER, 1),
                                LocalDate.of(1993, Month.JULY, 1),
                                "Инженер (программист Fortran, C)\n",
                                null
                        )
                )
                )
        );

        sections.put(OBJECTIVE, objective);
        sections.put(PERSONAL, personal);
        sections.put(ACHIEVEMENT, achievement);
        sections.put(QUALIFICATION, qualification);
        sections.put(EXPERIENCE, experience);
        sections.put(EDUCATION, education);

        resume.setSections(sections);

        Map<ContactTypes, String> contacts = new EnumMap<>(ContactTypes.class);
        contacts.put(PHONE_NUMBER, PHONE_NUMBER.getContact());
        contacts.put(SKYPE, SKYPE.getContact());
        contacts.put(EMAIL, EMAIL.getContact());
        contacts.put(LINKEDIN, LINKEDIN.getContact());
        contacts.put(GITHUB, GITHUB.getContact());
        contacts.put(STACKOVERFLOW, STACKOVERFLOW.getContact());
        contacts.put(HOMEPAGE, HOMEPAGE.getContact());

        resume.setContacts(contacts);

//        Map<ContactTypes, String> contacts = new EnumMap<>(ContactTypes.class);

        return resume;
    }
}
