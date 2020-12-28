package my.cool.projects;

import java.util.Objects;

public class BoardLocation {
    int row;
    int column;

    public BoardLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardLocation that = (BoardLocation) o;
        return row == that.row &&
                column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
