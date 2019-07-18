package com.aandb.GameProfile.model;

public class Subject
{
    private int casi;
    private String name;
    private int semester;
    private String characteristics;
    private int duration;
    private int theoricHours;
    private int practicHours;
    private int theoricPracticHours;
    private int laboratoryHours;
    private int credits;
    private String foundation;
    private String summary;
    
    public Subject() {
    	this.name = "name";
        this.semester = 0;
        this.characteristics = "characteristics";
        this.duration = 0;
        this.theoricHours = 0;
        this.practicHours = 0;
        this.theoricPracticHours = 0;
        this.laboratoryHours = 0;
        this.credits = 0;
        this.foundation = "foundation";
        this.summary = "summary";
    }
    
    public Subject(int casi, String name, int semester, String characteristics, int duration, int theoricHours,
            int practicHours, int theoricPracticHours, int laboratoryHours, int credits, String foundation,
            String summary)
    {
        this.casi = casi;
        this.name = name;
        this.semester = semester;
        this.characteristics = characteristics;
        this.duration = duration;
        this.theoricHours = theoricHours;
        this.practicHours = practicHours;
        this.theoricPracticHours = theoricPracticHours;
        this.laboratoryHours = laboratoryHours;
        this.credits = credits;
        this.foundation = foundation;
        this.summary = summary;
    }

    public int getCasi()
    {
        return casi;
    }

    public void setCasi(int casi)
    {
        this.casi = casi;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getSemester()
    {
        return semester;
    }

    public void setSemester(int semester)
    {
        this.semester = semester;
    }

    public String getCharacteristics()
    {
        return characteristics;
    }

    public void setCharacteristics(String characteristics)
    {
        this.characteristics = characteristics;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public int getTheoricHours()
    {
        return theoricHours;
    }

    public void setTheoricHours(int theoricHours)
    {
        this.theoricHours = theoricHours;
    }

    public int getPracticHours()
    {
        return practicHours;
    }

    public void setPracticHours(int practicHours)
    {
        this.practicHours = practicHours;
    }

    public int getTheoricPracticHours()
    {
        return theoricPracticHours;
    }

    public void setTheoricPracticHours(int theoricPracticHours)
    {
        this.theoricPracticHours = theoricPracticHours;
    }

    public int getLaboratoryHours()
    {
        return laboratoryHours;
    }

    public void setLaboratoryHours(int laboratoryHours)
    {
        this.laboratoryHours = laboratoryHours;
    }

    public int getCredits()
    {
        return credits;
    }

    public void setCredits(int credits)
    {
        this.credits = credits;
    }

    public String getFoundation()
    {
        return foundation;
    }

    public void setFoundation(String foundation)
    {
        this.foundation = foundation;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }
}
