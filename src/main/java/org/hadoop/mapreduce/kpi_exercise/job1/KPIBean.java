package org.hadoop.mapreduce.kpi_exercise.job1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KPIBean implements WritableComparable<KPIBean> {
    private String empId;
    private String ename;
    private String deptId;
    private String dname;
    private String grade;
    private int sal;
    private int basePrice;
    private int score;
    private String kpi;
    private int totalRank;


    @Override
    public int compareTo(KPIBean o) {
        return o.score - this.score;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(empId);
        dataOutput.writeUTF(ename);
        dataOutput.writeUTF(deptId);
        dataOutput.writeUTF(dname);
        dataOutput.writeUTF(grade);
        dataOutput.writeInt(sal);
        dataOutput.writeInt(basePrice);
        dataOutput.writeInt(score);
        dataOutput.writeUTF(kpi);
        dataOutput.writeInt(totalRank);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        empId = dataInput.readUTF();
        ename = dataInput.readUTF();
        deptId = dataInput.readUTF();
        dname = dataInput.readUTF();
        grade = dataInput.readUTF();
        sal = dataInput.readInt();
        basePrice = dataInput.readInt();
        score = dataInput.readInt();
        kpi = dataInput.readUTF();
        totalRank = dataInput.readInt();
    }

    @Override
    public String toString() {
        return empId + '\t' +
                ename + '\t' +
                deptId + '\t' +
                dname + '\t' +
                grade + '\t' +
                sal + '\t' +
                basePrice + '\t' +
                score + '\t' +
                kpi + '\t' +
                totalRank;
    }
}
