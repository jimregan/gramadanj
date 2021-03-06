package ie.tcd.slscs.itut.gramadanj.ainm;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright © 2016 Trinity College, Dublin
 * Irish Speech and Language Technology Research Centre
 * Cóipcheart © 2016 Coláiste na Tríonóide, Baile Átha Cliath
 * An tIonad taighde do Theicneolaíocht Urlabhra agus Teangeolaíochta na Gaeilge
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
public class Header {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleNote() {
        return titleNote;
    }

    public void setTitleNote(String titleNote) {
        this.titleNote = titleNote;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getFloruit() {
        return floruit;
    }

    public void setFloruit(String floruit) {
        this.floruit = floruit;
    }

    public String getBirthPlaceID() {
        return birthPlaceID;
    }

    public void setBirthPlaceID(String birthPlaceID) {
        this.birthPlaceID = birthPlaceID;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public List<String> getOccupations() {
        return occupations;
    }

    public void setOccupations(List<String> occupations) {
        this.occupations = occupations;
    }
    public void addOccupation(String occupation) {
        this.occupations.add(occupation);
    }

    public String getFaith() {
        return faith;
    }

    public void setFaith(String faith) {
        this.faith = faith;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
    public void addAuthor(String author) {
        this.authors.add(author);
    }

    private String title;
    private String titleNote;
    private String forename;
    private String surname;
    private String addName;
    private String birth;
    private String death;
    private String floruit;
    private String birthPlaceID;
    private String birthPlace;
    private String sex;
    private String school;
    private String university;
    private List<String> occupations;
    private String occupationB9;
    private String faith;
    private List<String> authors;
    private publicationStmt publicationStatement;
    private revisionDesc revisions;

    public publicationStmt getPublicationStatement() {
        return publicationStatement;
    }

    public void setPublicationStatement(publicationStmt publicationStatement) {
        this.publicationStatement = publicationStatement;
    }

    public revisionDesc getRevisions() {
        return revisions;
    }

    public void setRevisions(revisionDesc revisions) {
        this.revisions = revisions;
    }

    public String getOccupationB9() {
        return occupationB9;
    }

    public void setOccupationB9(String occupationB9) {
        this.occupationB9 = occupationB9;
    }

    public Header() {
        occupations = new ArrayList<String>();
        authors = new ArrayList<String>();
    }

    public class publicationStmt {
        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getPublicationPlace() {
            return publicationPlace;
        }

        public void setPublicationPlace(String publicationPlace) {
            this.publicationPlace = publicationPlace;
        }

        public String getAvailability() {
            return availability;
        }

        public void setAvailability(String availability) {
            this.availability = availability;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        private String publisher;
        private String publicationPlace;
        private String availability;
        private String date;
    }

    public class Change {
        private String status;
        private String when;
        private String who;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getWhen() {
            return when;
        }

        public void setWhen(String when) {
            this.when = when;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }

    public class revisionDesc {
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Change> getChanges() {
            return changes;
        }

        public void setChanges(List<Change> changes) {
            this.changes = changes;
        }
        public void addChange(Change change) {
            this.changes.add(change);
        }
        public void addChange(String status, String when, String who) {
            Change change = new Change();
            change.setStatus(status);
            change.setWhen(when);
            change.setWho(who);
            this.changes.add(change);
        }

        private String status;
        private List<Change> changes;
    }
}
