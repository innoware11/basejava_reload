package ru.basejava.storage.Strategies;

import ru.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSerializationStrategyImpl implements StorageStrategy {

    @Override
    public void writeStream(Resume resume, OutputStream os) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactTypes, String> contacts = resume.getContacts();
            int size = contacts.size();
            dos.writeInt(size);

            for(Map.Entry<ContactTypes, String> section : contacts.entrySet()) {
                dos.writeUTF(section.getKey().name());
                dos.writeUTF(section.getValue());
            }

            size = resume.getSections().size();
            dos.writeInt(size);
            for(Map.Entry<SectionTypes, AbstractSection> section : resume.getSections().entrySet()) {
                dos.writeUTF(section.getKey().name());
                switch(section.getKey()) {
                    case OBJECTIVE :
                    case PERSONAL :
                        dos.writeUTF(((SimpleTextSection)section.getValue()).getSimpleText());
                        break;
                    case ACHIEVEMENT :
                    case QUALIFICATION :
                        size = ((MarkedTextSection)section.getValue()).getList().size();
                        dos.writeInt(size);
                        for(String item : ((MarkedTextSection)section.getValue()).getList()) {
                            dos.writeUTF(item);
                        }
                        break;
                    case EXPERIENCE :
                    case EDUCATION :
                        size = ((OrganizationSection)section.getValue()).getOrganizations().size();
                        dos.writeInt(size);
                        for(Organization organization : (((OrganizationSection)section.getValue()).getOrganizations())) {
                            dos.writeUTF(organization.getHomepage().getName());
                            dos.writeUTF(organization.getHomepage().getUrl());

                            size = organization.getOrganizations().size();
                            dos.writeInt(size);
                            for(Organization.Position position : organization.getOrganizations()) {
                                dos.writeUTF(position.getStartDate().toString());
                                LocalDate endDate = position.getEndDate();
                                if(endDate == null) {
                                    dos.writeUTF("");
                                } else {
                                    dos.writeUTF(endDate.toString());
                                }
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription());
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume readStream(InputStream is) throws IOException {
        Resume resume;
        try(DataInputStream dis = new DataInputStream(is)) {
            resume = new Resume(dis.readUTF(), dis.readUTF());
            Map<ContactTypes, String> contacts = new HashMap<>();
            ContactTypes contactType;
            int size = dis.readInt();

            for(int i = 0; i < size; i++) {
                contactType = ContactTypes.valueOf(dis.readUTF());
                contacts.put(contactType, dis.readUTF());
            }
            resume.setContacts(contacts);

            Map<SectionTypes, AbstractSection> sections = new HashMap<>();
            SectionTypes sectionType;
            size = dis.readInt();
            for(int i = 0; i < size; i++) {
                sectionType = SectionTypes.valueOf(dis.readUTF());
                switch(sectionType) {
                    case OBJECTIVE :
                    case PERSONAL :
                        sections.put(sectionType, new SimpleTextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT :
                    case QUALIFICATION :
                        int counter1 = dis.readInt();
                        List<String> items1 = new ArrayList<>();
                        for(int j = 0; j < counter1; j++) {
                            items1.add(dis.readUTF());
                        }
                        sections.put(sectionType, new MarkedTextSection(items1));
                        break;
                    case EXPERIENCE :
                    case EDUCATION :
                        OrganizationSection organizationSection = new OrganizationSection();
                        int counter2 = dis.readInt();
                        List<Organization> items2 = new ArrayList<>();
                        for(int j = 0; j < counter2; j++) {
                            Organization organization = new Organization();
                            organization.setHomepage(new Link(dis.readUTF(), dis.readUTF()));

                            int counter3 = dis.readInt();
                            List<Organization.Position> organizations = new ArrayList<>();
                            for(int k = 0; k < counter3; k++) {
                                Organization.Position position = new Organization.Position();
                                position.setStartDate(LocalDate.parse(dis.readUTF()));
                                String endDate = dis.readUTF();
                                if(endDate.equals("")) {
                                    position.setEndDate(null);
                                } else {
                                    position.setEndDate(LocalDate.parse(endDate));
                                }
                                position.setTitle(dis.readUTF());
                                position.setDescription(dis.readUTF());
                                organizations.add(position);
                            }
                            organization.setOrganizations(organizations);
                            items2.add(organization);
                            organizationSection.setOrganizations(items2);
                        }
                        sections.put(sectionType, organizationSection);
                        break;
                }
                resume.setSections(sections);
            }
        }
        return resume;
    }
}