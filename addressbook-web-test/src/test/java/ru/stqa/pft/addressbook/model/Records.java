package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Records extends ForwardingSet<RecordData> {

    private Set<RecordData> delegate;

    public Records(Records records) {
        this.delegate = new HashSet<RecordData>(records.delegate);
    }

    public Records() {
        this.delegate = new HashSet<RecordData>();
    }

    public Records(Collection<RecordData> records) {
        this.delegate = new HashSet<RecordData>(records);
    }



    @Override
    protected Set<RecordData> delegate() {
        return delegate;
    }

    public Records withAdded(RecordData record){
        Records records = new Records(this);
        records.add(record);
        return records;
    }

    public Records without(RecordData record){
        Records records = new Records(this);
        records.remove(record);
        return records;
    }

}
