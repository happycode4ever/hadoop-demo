package org.hadoop.mapreduce.phonedata_sort;

import lombok.*;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlowBean implements Writable {
    private long upflow;
    private long downflow;
    private long sumflow;

    public FlowBean(long upflow,long downflow){
        this.upflow=upflow;
        this.downflow=downflow;
        this.sumflow=upflow+downflow;
    }
    public void write(DataOutput output) throws IOException {
        output.writeLong(this.upflow);
        output.writeLong(this.downflow);
        output.writeLong(this.sumflow);
    }

    public void readFields(DataInput dataInput) throws IOException {

        this.upflow=dataInput.readLong();
        this.downflow=dataInput.readLong();
        this.sumflow=dataInput.readLong();

    }

    @Override
    public String toString() {
        return this.upflow+"\t"+this.downflow+"\t"+this.sumflow;
    }

}
