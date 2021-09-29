package com.example.SmartNoteApp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
public class SchoolNote {
        private long ID;
        private String course;
        private String lecturer;
        private String department;
        private String level;
        private String details;
        private String schoolDate;
        private String schoolTime;

 SchoolNote() {}
         SchoolNote(String course, String lecturer, String department, String level, String details, String schoolDate, String schoolTime) {
         this.course = course;
         this.lecturer = lecturer;
         this.department = department;
         this.level = level;
         this.details = details;
         this.schoolDate = schoolDate;
         this.schoolTime = schoolTime;
         }
         SchoolNote(long schoolId, String course, String lecturer, String department, String level, String details, String schoolDate, String schoolTime) {
         this.ID = schoolId;
         this.course = course;
         this.lecturer = lecturer;
         this.department = department;
         this.details = details;
         this.level = level;
         this.schoolDate = schoolDate;
         this.schoolTime = schoolTime;
         }

public long getID() {
        return ID;
        }

public void setID(long ID) {
        this.ID = ID;
        }

public String getCourse() {
        return course;
        }

public void setCourse(String course) {
        this.course = course;
        }

public String getLecturer() {
        return lecturer;
        }

public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
        }

public String getDepartment() {
        return department;
        }

public void setDepartment(String department) {
        this.department = department;
        }

public String getLevel() {
        return level;
        }

public void setLevel(String level) {
        this.level = level;
        }

public String getDetails() {return details; }

public void setDetails(String details) {this.details = details;}

public String getSchoolDate() {
        return schoolDate;
        }

public void setSchoolDate(String schoolDate) {
        this.schoolDate = schoolDate;
        }

public String getSchoolTime() {
        return schoolTime;
        }

public void setSchoolTime(String schoolTime) {
        this.schoolTime = schoolTime;
        }
        }


//public class SchoolNote extends RealmObject {
//
//    @PrimaryKey
//    private long id;
//    private String course;
//    private String lecturer;
//    private String department;
//    private String level;
//    private String schoolDate;
//    private String schoolTime;
//    private String noteDetails;
//
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getCourse() {
//        return course;
//    }
//
//    public void setCourse(String course) {
//        this.course = course;
//    }
//
//    public String getLecturer() {
//        return lecturer;
//    }
//
//    public void setLecturer(String lecturer) {
//        this.lecturer = lecturer;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//
//    public String getLevel() {
//        return level;
//    }
//
//    public void setLevel(String level) {
//        this.level = level;
//    }
//
//    public String getNoteDetails() {
//        return noteDetails;
//    }
//
//    public void setNoteDetails(String noteDetails) {
//        this.noteDetails = noteDetails;
//    }
//
//    public String getSchoolDate() {
//        return schoolDate;
//    }
//
//    public void setSchoolDate(String schoolDate) {
//        this.schoolDate = schoolDate;
//    }
//
//    public String getSchoolTime() {
//        return schoolTime;
//    }
//
//    public void setSchoolTime(String schoolTime) {
//        this.schoolTime = schoolTime;
//    }






