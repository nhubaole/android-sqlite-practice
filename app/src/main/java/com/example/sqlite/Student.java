package com.example.sqlite;

public class Student {
    String _id;
    String _name;
    String _dob;
    String _class;

    public Student(String _id, String _name, String _dob, String _class) {
        this._id = _id;
        this._name = _name;
        this._dob = _dob;
        this._class = _class;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_dob() {
        return _dob;
    }

    public void set_dob(String _dob) {
        this._dob = _dob;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }
}
