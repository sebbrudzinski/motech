package org.motechproject.mds.testutil.records.history;

public class Record__History {
    private Long id;
    private Long record__HistoryCurrentVersion;
    private Boolean record__HistoryFromTrash;
    private String value;
    private Boolean record__HistoryIsLast;
    private Long record__HistorySchemaVersion;

    public Record__History() {
        this(null, null);
    }

    public Record__History(Long record__HistoryCurrentVersion, String value) {
        this.record__HistoryCurrentVersion = record__HistoryCurrentVersion;
        this.value = value;
        this.record__HistoryFromTrash = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecord__HistoryCurrentVersion() {
        return record__HistoryCurrentVersion;
    }

    public void setRecord__HistoryCurrentVersion(Long record__HistoryCurrentVersion) {
        this.record__HistoryCurrentVersion = record__HistoryCurrentVersion;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getRecord__HistoryIsLast() {
        return record__HistoryIsLast;
    }

    public void setRecord__HistoryIsLast(Boolean record__HistoryIsLast) {
        this.record__HistoryIsLast = record__HistoryIsLast;
    }

    public Boolean getRecord__HistoryFromTrash() {
        return record__HistoryFromTrash;
    }

    public void setRecord__HistoryFromTrash(Boolean record__HistoryFromTrash) {
        this.record__HistoryFromTrash = record__HistoryFromTrash;
    }

    public Long getRecord__HistorySchemaVersion() {
        return record__HistorySchemaVersion;
    }

    public void setRecord__HistorySchemaVersion(Long record__HistorySchemaVersion) {
        this.record__HistorySchemaVersion = record__HistorySchemaVersion;
    }
}
