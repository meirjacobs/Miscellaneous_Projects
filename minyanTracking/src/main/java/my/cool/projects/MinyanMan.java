package my.cool.projects;

public class MinyanMan {
    protected String name;
    protected int minyanimAttended;
    protected int shacharisAttended;
    protected int minchamaarivAttended;
    protected int position;

    protected MinyanMan(String name, int minyanimAttended, int position) {
        this.name = name;
        this.minyanimAttended = minyanimAttended;
        this.position = position;
    }

    protected MinyanMan(String name, int minyanimAttended, int shacharisAttended, int minchamaarivAttended, int position) {
        this(name, minyanimAttended, position);
        this.shacharisAttended = shacharisAttended;
        this.minchamaarivAttended = minchamaarivAttended;
    }
}
